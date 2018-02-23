package producer_consumer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class Sequential
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
	
	private static void produce( Deque< Product > list )
	{
		// int stream range to add water bottles and flower bouquets
		IntStream.range( 0, 100 ).forEach(
			n -> {
				list.add( new Product( "Water bottle " + n, "Fresh" ) );
				list.add( new Product( "Flower bouquet " + n, "Roses" ) );
			}
		);
	}
	
	private static void consume( Deque< Product > list )
	{
		list.forEach( product -> System.out.println( product.toString() ) );
	}
	
	public static void main()
	{
		produce( THE_LIST );
		consume( THE_LIST );
	}
}
