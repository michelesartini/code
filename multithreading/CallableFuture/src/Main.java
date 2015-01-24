import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Uses Callable, to execute a new thread that does some kind of processing and returns a value, 
 * that then is retrieved in the main thread by using Future.  
 *
 * @author michelesartini
 *
 */
public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// Callable is similar to Runnable, but it can returns a value.
		// A Runnable, however, does not return a result and cannot throw a checked exception.
		// When you define a Callable you have to specify the type of object it will return,
		// the call() method has to return the type of object you specified in the definition.
		// (If you are planning to submit multiple threads, you can use an array of Future)
		Future<Integer> future = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// Simulating some kind of processing.
				Random random = new Random();
				int nextInt = random.nextInt(1000);
				Thread.sleep(nextInt);
				// Returning the time for which the thread has slept for.
				return nextInt;
			}
			
		});

		executor.shutdown();
		
		try {
			// get() Waits if necessary for the computation to complete, and then retrieves its result.
			// you could also use this: executor.awaitTermination(1, TimeUnit.HOURS); in this case
			// you know that the thread has completed its execution therefore get will be non blocking.
			System.out.println("Integer value: " + future.get());
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
