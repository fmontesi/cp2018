package cp;

public class BusyWait
{
	private static int x = 1;
	private static volatile int b = 0;
	
	public static void main()
	{
		Thread t1 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
				if ( b == 0 ) {
					System.out.println( "t1 x=" + x + " b=" + b );
					x++;
					b = 1;
				} else {
					System.out.println( "t1 wastes time" );
					i--;
				}
			}
		});
		Thread t2 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
				if ( b == 1 ) {
					System.out.println( "t2 x=" + x + " b=" + b );
					x++;
					b = 2;
				} else {
					System.out.println( "t2 wastes time" );
					i--;
				}
			}
		});
		Thread t3 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
				if ( b == 2 ) {
					System.out.println( "t3 x=" + x + " b=" + b );
					x++;
					b = 0;
				} else {
//					System.out.println( "t3 wastes time" );
					i--;
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch( InterruptedException e ) {}
		System.out.println( x );
	}
}
