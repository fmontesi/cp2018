package producer_consumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class ExecutorsBarrierThreadSafe
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
	
	private static void produce( BlockingDeque< Product > list, CountDownLatch producersLatch )
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
	
	private static void consume( int id, BlockingDeque< Product > list, CyclicBarrier barrier )
	{
		boolean keepRun = true;
		while( keepRun ) {
			try {
				Product product = list.takeFirst();
				try {
					barrier.await();
				} catch( InterruptedException | BrokenBarrierException e ) {}
				System.out.println( "Consumer " + id + ": " + product );
				if ( product instanceof PoisonPill ) {
					keepRun = false;
				}
			} catch( InterruptedException e ) {}
		}
	}
	
	// Works only if the number of products can be divided by the number of consumers
	public static void main( int nProducers, int nConsumers )
	{
		final BlockingDeque<Product> list = new LinkedBlockingDeque<>();
		final CyclicBarrier barrier = new CyclicBarrier( nConsumers );
		final CountDownLatch producersLatch = new CountDownLatch( nProducers );
		int cores = Runtime.getRuntime().availableProcessors() + 1;
		ExecutorService producersExecutor = Executors.newFixedThreadPool( cores/2 );
		ExecutorService consumersExecutor = Executors.newFixedThreadPool( cores/2 );
		IntStream.range( 0, nProducers ).forEach( n ->
			producersExecutor.submit( () -> produce( list, producersLatch ) )
		);
		IntStream.range( 0, nConsumers ).forEach( n ->
			consumersExecutor.submit( () -> consume( n, list, barrier ) )
		);
		
		try {
			producersLatch.await();
			IntStream.range( 0, nConsumers ).forEach( n ->
				list.add( new PoisonPill() )
			);
		} catch( InterruptedException e ) {}
		
		producersExecutor.shutdown();
		consumersExecutor.shutdown();
		try {
			producersExecutor.awaitTermination( 1, TimeUnit.DAYS );
			consumersExecutor.awaitTermination( 1, TimeUnit.DAYS );
		} catch( InterruptedException e ) { e.printStackTrace(); }
		
		System.out.println( list.isEmpty() );
	}
}
