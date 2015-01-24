import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Implements a Runnable class, in the run() method
 * it simulates some kind of logic and decreases the value of the latch.
 * 
 * @author michelesartini
 *
 */
public class Processor implements Runnable {
	
	private CountDownLatch latch;
	private Random random = new Random();
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	
	public void run() {
		System.out.println("Starting thread.");
		try {
			// just to simulate some kind of logic:
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread completed");
		// it will count down the value of the latch.
		this.latch.countDown();
	}
}
