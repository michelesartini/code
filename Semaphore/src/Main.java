import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Schedules 200 Threads that try to establish a connection using the Connection class. 
 *
 * @author michelesartini
 *
 */
public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Started.");
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 200; i++) {
			executor.submit(new Runnable() {
			
				public void run() {
					// Creating a connection
					// Once the process is complete, connect() will also release
					// the acquired connection (for this example its fine).
					Connection.getInstance().connect();
				}
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.HOURS);
		System.out.println("Completed.");
	}
}
