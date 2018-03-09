package producer_consumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class ExecutorsBlockingList
{
	interface Product {}
	
	private static class ConcreteProduct implements Product {
		private final String name;
		private final String attributes;
		
		public ConcreteProduct( String name, String attributes )
		{
			this.name = name;
			this.attributes = attributes;
		}
		
		public String toString()
		{
			return name + " : " + attributes;
		}
	}
	
	private static class PoisonPill implements Product {	}
	
	private static final BlockingDeque<Product> THE_LIST = new LinkedBlockingDeque<>();
	private static CountDownLatch producersLatch;
	
	private static void produce( BlockingDeque< Product > list )
	{
		// int stream range to add water bottles and flower bouquets
		IntStream.range( 0, 1000 ).forEach(
			n -> {
				Main.veryExpensiveOperation();
				Product one = new ConcreteProduct( "Water bottle " + n, "Fresh" );
				Product two = new ConcreteProduct( "Flower bouquet " + n, "Roses" );
				list.add( one );
				list.add( two );
			}
		);
		producersLatch.countDown();
	}
	
	private static void consume( BlockingDeque< Product > list )
	{
		boolean keepRun = true;
		while( keepRun ) {
			try {
				Product product = list.takeFirst();
				System.out.println( product );
				if ( product instanceof PoisonPill ) {
					keepRun = false;
					list.add( new PoisonPill() );
				}
			} catch( InterruptedException e ) {}
		}
	}
	
	public static void main( int nProducers, int nConsumers )
	{
		producersLatch = new CountDownLatch( nProducers );
		int cores = Runtime.getRuntime().availableProcessors() + 1;
		ExecutorService producersExecutor = Executors.newFixedThreadPool( cores/2 );
		ExecutorService consumersExecutor = Executors.newFixedThreadPool( cores/2 );
		IntStream.range( 0, nProducers ).forEach( n ->
			producersExecutor.submit( () -> produce( THE_LIST ) )
		);
		IntStream.range( 0, nConsumers ).forEach( n ->
			consumersExecutor.submit( () -> consume( THE_LIST ) )
		);
		
		try {
			producersLatch.await();
			THE_LIST.add( new PoisonPill() );
		} catch( InterruptedException e ) {}
		
		producersExecutor.shutdown();
		consumersExecutor.shutdown();
		try {
			producersExecutor.awaitTermination( 1, TimeUnit.DAYS );
			consumersExecutor.awaitTermination( 1, TimeUnit.DAYS );
		} catch( InterruptedException e ) { e.printStackTrace(); }
		
		System.out.println( THE_LIST.size() == 1 );
	}
}
