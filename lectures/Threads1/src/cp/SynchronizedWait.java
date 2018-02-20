package cp;

public class SynchronizedWait
{
	private static int x = 1;
	
	public static void main()
	{
		Object obj = new Object();
		
		Thread t1 = new Thread( () -> {
			for( int i = 0; i < 10; i++ ) {
				synchronized( obj ) {
					x++;
				}
			}
		});
		Thread t2 = new Thread( () -> {
			for( int i = 0; i < 10; i++ ) {
				synchronized( obj ) {
					x++;
				}
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
		} catch( InterruptedException e ) {}
		try {
			t2.join();
		} catch( InterruptedException e ) {}
		System.out.println( x );
	}
}
