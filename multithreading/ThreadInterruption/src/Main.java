import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Shows how to interrupt threads. 
 *
 * @author michelesartini
 *
 */
public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Random random = new Random();
				
				for (int i = 0; i < 1E8; i++) {
					
					// Checking if the thread has been interrupted
					// if yes, it stops the execution.
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("t1 interrupted");
						break;
					}
					
					// simulating some kind of processing
					System.out.println("Value: " + Math.sinh(random.nextDouble()));
				}
				
			}
		});
		
		t1.start();
		Thread.sleep(500);
		t1.interrupt();
		t1.join();
		
		System.out.println("-------------------");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Random random = new Random();
				
				for (int i = 0; i < 1E8; i++) {
					
					try {
						// This is another way to check if the thread has been interrupted.
						// if the thread has been interrupted (see line: 70) an exception will be thrown.
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println("t2 interrupted");
						break;
					}
					
					// simulating some kind of processing
					System.out.println("Value: " + Math.sinh(random.nextDouble()));
				}
				
			}
		});
		
		t2.start();
		Thread.sleep(500);
		t2.interrupt();
		t2.join();
		
		System.out.println("-------------------");
		
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Double> future = executor.submit(new Callable<Double>() {
			
			@Override
			public Double call() throws Exception {
				Double d = null;
				for (int i = 0; i < 1E8; i++) {
					
					// Checking if the thread has been interrupted
					// if yes, it stops the execution.
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("t3 interrupted");
						break;
					}
					
					Random random = new Random();
					// simulating some kind of processing
					d = Math.sinh(random.nextDouble());
					System.out.println("Value: " + d);
				}
				return d;
			}
			
		});
			
		executor.shutdown();
		future.cancel(true);
		executor.awaitTermination(10, TimeUnit.MINUTES);
	}
}
