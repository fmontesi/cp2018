package producer_consumer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class Latch
{
	private static class Product {
		private final String name;
		private final String attributes;
		
		public Product( String name, String attributes )
		{
			this.name = name;
			this.attributes = attributes;
		}
		
		public String toString()
		{
			return name + " : " + attributes;
		}
	}
	
	private static final Deque<Product> THE_LIST = new LinkedList<>();
	private static CountDownLatch producerLatch;
	private static CountDownLatch consumerLatch;
	
	private static void produce( Deque< Product > list )
	{
		// int stream range to add water bottles and flower bouquets
		IntStream.range( 0, 100000 ).forEach(
			n -> {
				Product one = new Product( "Water bottle " + n, "Fresh" );
				Product two = new Product( "Flower bouquet " + n, "Roses" );
				synchronized( list ) {
					list.add( one );
					list.add( two );
				}
			}
		);
		producerLatch.countDown();
	}
	
	private static void consume( Deque< Product > list )
	{
		boolean keepRun = true;
		
		while( keepRun ) {
			if ( producerLatch.getCount() == 0 ) {
				keepRun = false;
			}
			synchronized( list ) {
				while( !list.isEmpty() ) {
					Product product = list.pollFirst();
					if ( product != null ) {
						// System.out.println( product );
					}
				}
			}			
		}
		consumerLatch.countDown();
	}
	
	public static void main( int nProducers, int nConsumers )
	{
		producerLatch = new CountDownLatch( nProducers );
		consumerLatch = new CountDownLatch( nConsumers );
		IntStream.range( 0, nProducers ).forEach( n ->
			new Thread( () -> produce( THE_LIST ) ).start()
		);
		IntStream.range( 0, nConsumers ).forEach( n ->
			new Thread( () -> consume( THE_LIST ) ).start()
		);
		try {
			consumerLatch.await();
		} catch( InterruptedException e ) { e.printStackTrace(); }
		
		System.out.println( THE_LIST.isEmpty() );
	}
}
