/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author fmontesi
 */
public class CompletableFutureExample
{
	public static void concurrent( URL[] urls )
	{
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletableFuture<List<Integer>>[] futures = new CompletableFuture[urls.length];
		int i = 0;
		for( URL url : urls ) {
			futures[i++] = CompletableFuture.supplyAsync( () -> {
				try {
					InputStream istream = url.openStream();
					BufferedInputStream bufferedStream = new BufferedInputStream( istream );
					BufferedReader reader = new BufferedReader(
						new InputStreamReader( bufferedStream )
					);

					List< Integer > list = new ArrayList<>();
					reader.lines().forEach( line -> list.add( line.length() ) );
					return list;
				} catch( IOException e ) {
					e.printStackTrace();
					return new ArrayList<>();
				}
			}, executor );
		}
		
		executor.shutdown();
		CompletableFuture.allOf( futures ).join();
	}
}
