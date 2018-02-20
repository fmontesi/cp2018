package cp;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample
{
	private static AtomicInteger x = new AtomicInteger( 0 );
	private static AtomicInteger y = new AtomicInteger( 0 );
	
	public static void main()
	{
		Object obj = new Object();
		Thread t1 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
//				synchronized( obj ) {
					x.incrementAndGet();
//					System.out.println( "t1 x=" + x + " y=" + y );
					y.decrementAndGet();
//				}
			}
		});
		Thread t2 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
//				synchronized( obj ) {
					x.incrementAndGet();
//					System.out.println( "t2 x=" + x + " y=" + y );
					y.decrementAndGet();
//				}
			}
		});
		Thread t3 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
//				synchronized( obj ) {
					x.incrementAndGet();
//					System.out.println( "t3 x=" + x + " y=" + y );
					y.decrementAndGet();
//				}
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
		System.out.println( x.get() );
		System.out.println( y.get() );
	}
}
