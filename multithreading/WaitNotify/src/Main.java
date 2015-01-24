/**
 * Simple main class that executes two threads:
 * - the 1st thread will execute the produce() method of the Processor class.
 * - the 2nd thread will execute the consume() method of the Processor class.
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
						processor.produce();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			Thread t2 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						processor.consume();
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
	}

}
