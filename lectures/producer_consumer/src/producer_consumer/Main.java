package producer_consumer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.IntStream;

public class Main
{
	public static void main( String[] args )
	{
		// doAndMeasure( () -> Sequential.main() );
//		doAndMeasure( () -> {
//			final int cores = Runtime.getRuntime().availableProcessors();
//			Latch.main( cores/2, cores/2 );
//		} );
//		doAndMeasure( () -> ExecutorsExample.main( 2, 2 ) );
		// doAndMeasure( () -> ExecutorsPoison.main( 2, 2 ) );
//		doAndMeasure( () -> ExecutorsBarrier.main( 2, 2 ) );
		//doAndMeasure( () -> ExecutorsBlockingList.main( 2, 2 ) );
//		doAndMeasure( () -> ExecutorsBarrierThreadSafe.main( 2, 2 ) );
//		doAndMeasure( () -> ExecutorsBarrierThreadSafe.main( 2, 2 ) );
//		doAndMeasure( () -> ExecutorsBlockingList.main( 2, 2 ) );
//		doAndMeasure( () -> ExecutorsBlockingList.main( 2, 2 ) );
		final int N_ROWS = 1_000;
		final int N_COLUMNS = 500_000;
		final int[][] matrix = new int[N_ROWS][N_COLUMNS];
/*		for( int row = 0; row < N_ROWS; row++ ) {
			for( int col = 0; col < N_COLUMNS; col++ ) {
				matrix[row][col] = row + col;
			}
		}
		doAndMeasure( () -> {
			ExecutorsSubmit.concurrent( matrix );
		} );*/

		try {
			URL[] urls = {
				new URL( "https://www.sdu.dk/en/" ),
				new URL( "http://www.fabriziomontesi.com/index.html" ),
				new URL( "https://www.facebook.com" ),
				new URL( "https://www.twitter.com/" ),
				new URL( "https://www.github.com/" ),
				new URL( "https://www.tv2.dk/" ),
				new URL( "https://www.instagram.com/?hl=en" )
			};
			doAndMeasure( () ->
				CompletableFutureExample.concurrent( urls )
			);
		} catch( MalformedURLException e ) {}
	}
	
	private static void doAndMeasure( Runnable runnable )
	{
		long t1 = System.currentTimeMillis();
		runnable.run();
		long result = System.currentTimeMillis() - t1;
		System.out.println( "Execution took: " + result + "ms" );
	}
	
	public static void veryExpensiveOperation()
	{
		IntStream.range( 0, 1000000 ).average();
	}
}
