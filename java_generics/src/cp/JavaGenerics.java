package cp;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class JavaGenerics
{
    private static class Box<MyType> {
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
    
    private interface StringContainer {
        public String theString();
    }
    
    private static class MyStringContainer implements StringContainer {
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
    
    public static void main( String[] args )
    {
       List<String> l = new LinkedList<>();
       l.add( "hello" );
       l.add( "hi" );
       l.add( "imagination" );
       read( l );
       
       Box<String> box = new Box<>( "hello world" );
       read( box );
	   
       MyStringContainer msc = new MyStringContainer( "My God" );
       read( msc );
       
       StringContainer sc = new StringContainer() {
           public String theString() {
               return "Yahoo!";
           }
       };
	   
       read( sc );
       
       read( () -> { return "Yahoo from lambda!"; } );
	   
	   read( () -> "Yahoo from lambda!" );
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
