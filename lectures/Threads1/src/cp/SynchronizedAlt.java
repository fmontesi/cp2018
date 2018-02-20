package cp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedAlt
{
	private static int x = 1;
	private static volatile int b = 0;
	
	public static void main()
	{
		final Lock lock = new ReentrantLock();
		Thread t1 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
				lock.lock();
				System.out.println( "t1 x=" + x + " b=" + b );
				x++;
				lock.unlock();
			}
		});
		Thread t2 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
				lock.lock();
				System.out.println( "t2 x=" + x + " b=" + b );
				x++;
				lock.unlock();
			}
		});
		Thread t3 = new Thread( () -> {
			for( int i = 0; i < 2; i++ ) {
				lock.lock();
				System.out.println( "t3 x=" + x + " b=" + b );
				x++;
				lock.unlock();
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
