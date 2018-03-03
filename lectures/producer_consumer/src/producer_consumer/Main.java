package producer_consumer;

public class Main
{
	public static void main( String[] args )
	{
//		Sequential.main();
//		doAndMeasure( () -> Latch.main( cores/2, cores/2 ) );
		doAndMeasure( () -> ExecutorsExample.main( 10, 100000 ) );
	}
	
	private static void doAndMeasure( Runnable runnable )
	{
		long t1 = System.currentTimeMillis();
		runnable.run();
		long result = System.currentTimeMillis() - t1;
		System.out.println( "Execution took: " + result + "ms" );
	}
}
