/**
 * Simple main that executes two threads.
 * If you use the ProcessorDeadLock class, the two threads will cause a deadlock.
 * If you use the ProcessorAvoidDeadLock class, the two threads will avoid deadlock conditions.
 *
 * @author michelesartini
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

			//final Processor processor = new ProcessorDeadLock();
			final Processor processor = new ProcessorAvoidDeadLock();
			
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
