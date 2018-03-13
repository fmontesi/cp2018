/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author fmontesi
 */
public class CompletableFutureExample
{	
	public static void concurrent( int[][] matrix )
	{
		CompletableFuture<Integer>[] futures = new CompletableFuture[ matrix.length ];
		for( int row = 0; row < matrix.length; row++ ) {
			futures[row] = CompletableFuture.supplyAsync( () -> {
				int max = Integer.MIN_VALUE;
				for( int e : matrix[0] ) {
					if ( e > max ) {
						max = e;
					}
				}
				return max;
			});
		}
		try {
			Object max = CompletableFuture.anyOf( futures ).get();
			System.out.println( (Integer)max );
		} catch( InterruptedException | ExecutionException e ) {}
	}
}
