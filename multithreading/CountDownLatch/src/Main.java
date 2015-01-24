import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executes threads by using a FixedThreadPool, the thread will decrease the value of the latch object.
 * The main thread will wait until the latch value is 0.
 * 
 * @author michelesartini
 *
 */
public class Main {

	public static int nThreads = 3;
	public static int countDown = 3;
	
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(countDown);
		
		// At any point, at most nThreads threads will be active processing tasks
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		
		for (int i=0; i<nThreads; i++) {
			// submitting the tasks that we want to execute.
			// Be aware that we created the pool with a maximum size of nThreads, that means
			// that we can submit more that nThreads tasks, but only nThreads can be active at the same time.
			// The other tasks will wait for an available thread (in the thread pool) to be executed.
			executor.submit(new Processor(latch));
		}
		
		try {
			// The main thread will wait until the latch value is 0.
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed.");
	}
}
