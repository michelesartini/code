import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Executes threads by using a FixedThreadPool.
 * 
 * @author michelesartini
 *
 */
public class Main {

	public static int nThreads = 3;
	
	public static void main(String[] args) {
		
		// At any point, at most nThreads threads will be active processing tasks
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		
		for (int i=0; i<5; i++) {
			// submitting the tasks that we want to execute.
			// Be aware that we created the pool with a maximum size of nThreads, that means
			// that we can submit more that nThreads tasks, but only nThreads can be active at the same time.
			// The other tasks will wait for an available thread (in the thread pool) to be executed.
			executor.submit(new Processor(i));
		}
		// Initiates an orderly shutdown in which previously submitted tasks are executed, 
		// but no new tasks will be accepted
		executor.shutdown();
		try {
			// waiting that all tasks are completed.
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
