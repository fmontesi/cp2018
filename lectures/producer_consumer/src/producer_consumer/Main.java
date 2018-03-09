package producer_consumer;

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
		doAndMeasure( () -> ExecutorsBlockingList.main( 2, 2 ) );
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
