package cp;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class JavaGenerics
{
	private static class Box<MyType>
	{
		private MyType obj;

		public Box( MyType obj )
		{
			this.obj = obj;
		}

		public MyType object()
		{
			return obj;
		}
	}

	private interface StringContainer
	{
		public String theString();
	}

	private static class MyStringContainer implements StringContainer
	{
		private String s;

		public MyStringContainer( String s )
		{
			this.s = s;
		}

		public String theString()
		{
			return s;
		}
	}
	
	public static String m( Object o )
	{
		return o.toString(); 
	}
	
	public static String myClassMethod( MyClass o )
	{
		return o.toString();
	}
	
	public interface Catalogue {
		public Integer getPrice( String product );
	}
	
	public static class CatalogueImpl implements Catalogue {
		public Integer getPrice( String product )
		{
			return product.length();
		}
	}
	
	public static class CatalogueImplNewVersion implements Catalogue {
		public Integer getPrice( String product )
		{
			return product.length() + 2;
		}
	}
	
	private static void readCatalogue( Catalogue c )
	{
		System.out.println( c.getPrice( "Hello" ) );
	}

	private static class MyClass {
		private int number;
	}
	
	private static class MyExtClass extends MyClass {
		
	}

	public static <T extends String> void doStuff( T obj )
	{
		System.out.println( obj.trim() );
	}
	
	public static <T extends Boolean> void doStuff( T obj )
	{
		System.out.println( obj.booleanValue() );
	}

//	public static void doStuff( List strings )
//	{
//	}

	public static void main( String[] args )
	{
		int x = 0;
		boolean b = true;
		
		List<String> l = new LinkedList();
		l.add( "Hello" );
		doStuff( "Hello" );
		doStuff( true );
//		doStuff( "ell" );
		
//		readCatalogue( new CatalogueImpl() );
		readCatalogue( new CatalogueImplNewVersion() );
		/* myClassMethod( new MyExtClass() );
		m( new MyClass() );
		m( new Integer( 2 ) );
		m( "hello" );
		m( new LinkedList<String>() );
		
		List<String> l = new LinkedList<>();
		l.add( "hello" );
		l.add( "hi" );
		l.add( "imagination" );
		read( l );

		Box<String> box = new Box<>( "hello world" );
		read( box );

		MyStringContainer msc = new MyStringContainer( "Hooray" );
		read( msc );

		StringContainer sc = new StringContainer()
		{
			public String theString()
			{
				return "Yahoo!";
			}
		};

		read( sc );

		read( () -> {
			return "Yahoo from lambda!";
		} );

		read( () -> "Yahoo from lambda!" ); */
	}

	private static void read( StringContainer c )
	{
		System.out.println( c.theString() );
	}

	private static void read( Box<String> b )
	{
		System.out.println( b.object() );
	}

	private static void read( List<String> l )
	{
		String s = "";
		for( int i = 0; i < l.size(); i++ ) {
			s = s + " " + l.get( i );
		}
		System.out.println( s );
	}
}
