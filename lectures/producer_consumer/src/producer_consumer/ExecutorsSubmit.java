/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author fmontesi
 */
public class ExecutorsSubmit
{
	public static void sequential( int[][] matrix )
	{
		int max = Integer.MIN_VALUE;
		for( int[] row: matrix ) {
			for( int element : row ) {
				if ( element > max ) {
					max = element;
				}
			}
		}
		System.out.println( max );
	}
	
	public static void concurrent( int[][] matrix )
	{
		final int cores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool( cores );
		List<Future<Integer>> futures = new ArrayList<>( matrix.length );
		for( int row = 0; row < matrix.length; row++ ) {
			Future<Integer> f = executor.submit( () -> {
				int max = Integer.MIN_VALUE;
				for( int e : matrix[0] ) {
					if ( e > max ) {
						max = e;
					}
				}
				return max;
			});
			futures.add( f );
		}
		try {
			int max = Integer.MIN_VALUE;
			for( Future<Integer> f : futures ) {
				if ( f.get() > max ) {
					max = f.get();
				}
			}
			System.out.println( max );
		} catch( InterruptedException | ExecutionException e ) {}
		
		executor.shutdownNow();
	}
}
