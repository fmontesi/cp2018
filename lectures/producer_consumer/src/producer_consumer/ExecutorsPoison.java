package producer_consumer;



import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class ExecutorsPoison
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
	
	private static final Deque<Product> THE_LIST = new LinkedList<>();
	
	private static void produce( Deque< Product > list )
	{
		// int stream range to add water bottles and flower bouquets
		IntStream.range( 0, 1000 ).forEach(
			n -> {
				Main.veryExpensiveOperation();
				Product one = new ConcreteProduct( "Water bottle " + n, "Fresh" );
				Product two = new ConcreteProduct( "Flower bouquet " + n, "Roses" );
				synchronized( list ) {
					list.add( one );
					list.add( two );
				}
			}
		);
		synchronized( list ) {
			list.add( new PoisonPill() );
		}
	}
	
	private static void consume( Deque< Product > list )
	{
		boolean keepRun = true;
		while( keepRun ) {
			synchronized( list ) {
				while( !list.isEmpty() && keepRun ) {
					Product product = list.pollFirst();
					if ( product != null ) {
						//System.out.println( product );
						if ( product instanceof PoisonPill ) {
							keepRun = false;
						}
					}
				}
			}
		}
	}
	
	// WORKS ONLY if nProducers == nConsumers
	public static void main( int nProducers, int nConsumers )
	{
		int cores = Runtime.getRuntime().availableProcessors() + 1;
		ExecutorService producersExecutor = Executors.newFixedThreadPool( cores/2 );
		ExecutorService consumersExecutor = Executors.newFixedThreadPool( cores/2 );
		IntStream.range( 0, nProducers ).forEach( n ->
			producersExecutor.submit( () -> produce( THE_LIST ) )
		);
		IntStream.range( 0, nConsumers ).forEach( n ->
			consumersExecutor.submit( () -> consume( THE_LIST ) )
		);
		producersExecutor.shutdown();
		consumersExecutor.shutdown();
		try {
			producersExecutor.awaitTermination( 1, TimeUnit.DAYS );
			consumersExecutor.awaitTermination( 1, TimeUnit.DAYS );
		} catch( InterruptedException e ) { e.printStackTrace(); }
		
		System.out.println( THE_LIST.isEmpty() );
	}
}
