package cp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class Streams
{	
	private static int i;
	
	public static void print( String h ) {
		i = 43;
	}
	
	private static Integer getPrice( String productName )
	{
		return productName.length() + 2;
	}
	
	public static void main( String[] args )
	{
		List<String> l = new ArrayList<>();
		l.add( "Water Bottle" );
		l.add( "Flowers" );
		l.add( "Chocolate" );
		
		l.stream().map( Streams::getPrice ).forEach( System.out::println );
		
//		List<Integer> prices = listPrices( l );
//		for( Integer price : prices ) {
//			System.out.println( price );
//		}
//		System.out.println( getPrice( "Water Bottle" ) );
		//whileLoop( l );
		//forLoop( l );
		//forEachLoop( l );
		// anonymousClassLoop( l );
		// lambdaLoop( l );
		// methodReferenceLoop( l );
		
//		IntStream stream = IntStream.range( 1, 10 );
//		stream.forEach( System.out::println );
		
	}
	
	private static List<Integer> listPrices( List<String> products )
	{
		List<Integer> list = new ArrayList<>();
		for( String product : products ) {
			list.add( getPrice( product ) );
		}
		return list;
	}
	
	private static void methodReferenceLoop( List<String> l )
	{
		l.forEach( System.out::println );
		// is equivalent to:
//		l.stream().forEach( System.out::println );
	}
	
	private static void lambdaLoop( List<String> l )
	{
		l.forEach( s -> { System.out.println( s ); } );
	}
	
	private static void anonymousClassLoop( List<String> l )
	{
		l.forEach( new Consumer<String>()
		{
			public void accept( String t )
			{
				System.out.println( t );
			}
		});
	}
	
	private static void forEachLoop( List<String> l )
	{
		for( String s : l ) {
			System.out.println( s );
		}
	}
	
	private static void forLoop( List<String> l )
	{
		for( int i = 0; i < l.size(); i++ ) {
			System.out.println( l.get( i ) );
		}
	}
	
	private static void whileLoop( List<String> l )
	{
		int i = 0;
		while( i < l.size() ) {
			System.out.println( l.get( i ) );
			i++;
		}
	}
}
