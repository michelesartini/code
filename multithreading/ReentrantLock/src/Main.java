/**
 * Simple main that executes two threads.
 *
 * @author michelesartini
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

			final Processor processor = new Processor();
			
			Thread t1 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						processor.thread1();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			Thread t2 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						processor.thread2();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			// Starting threads
			t1.start();
			t2.start();
			
			// The main thread will wait until both threads die.
			t1.join();
			t2.join();
			
			processor.finished();
	}

}
