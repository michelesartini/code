import java.util.Scanner;

/**
 * Simple class that implements two methods produce() and consume()
 * that are used to explain how wait() and notify() work.
 * 
 * @author michelesartini
 *
 */
public class Processor {
	
	public void produce() throws InterruptedException {
		// synchronized (this) will acquire the lock 
		// on the intrinsic lock of the Processor object (current object).
		synchronized (this) {
			System.out.println("[Producer thread] running.");
			System.out.println("[Producer thread] acquired the intrinsic lock.");
			// wait() will relinquish the lock of 'this', therefore another thread can acquire it.
			wait();
			System.out.println("[Producer thread] resumed.");
		}		
	}
	
	public void consume() throws InterruptedException {
		System.out.println("[Consumer thread] running.");
		System.out.println("[Consumer thread] is sleeping for 2 sec.");
		Thread.sleep(2000);
		
		Scanner scanner = new Scanner(System.in);
		
		// synchronized (this) will acquire the lock 
		// on the intrinsic lock of the Processor object.
		synchronized (this) {
			System.out.println("[Consumer thread] acquired the intrinsic lock.");
			System.out.println("[Consumer thread] waiting for the new line.");
			scanner.nextLine();
			System.out.println("[Consumer thread] return key pressed.");
			// notify() will inform the other thread, that is currently waiting to acquire the lock on the same object (in this case on 'this'), 
			// that it can wake up to continue the execution.
			// The other thread will not acquire the lock after that the notify() method is called, but it will be able to acquire the lock 
			// after that this synchronized block will be completely executed. 
			// For this reason the synchronized block should be closed after that the notify() method is being called. 
			notify();
		}
		scanner.close();
	}

}
