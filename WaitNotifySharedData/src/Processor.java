import java.util.LinkedList;
import java.util.Random;

/**
 * The producer thread will add new values to the list only if the list size is less than LIMIT.
 * If the list size is equal to LIMIT, it will wake up (calling lock.wait()) the consumer thread
 * that will start to get values.
 * 
 * The consumer thread will get values from the list only if the list size is greater than 0.
 * If the list size is equal to 0, t will wake up (calling lock.wait()) the producer thread
 * that will start to insert new values.
 * 
 * @author michelesartini
 *
 */
public class Processor {
	
	int LIMIT = 10;
	LinkedList<Integer> list = new LinkedList<Integer>();
	Object lock = new Object();
	
	/**
	 * 
	 * 1)  it starts.
	 * 2)  it acquires the lock.
	 * 3a) if the list size is < LIMIT it adds a new values and it calls lock.notify()
	 * 	   so that the consumer thread can wake up and acquire the lock to consume values.
	 * 3b) if the list size is == LIMIT, it calls lock.wait() so that the consumer thread 
	 *     can wake up and acquire the lock to consume values.
	 *     Once the consumer thread has called lock.notify(), the producer thread will acquire the lock again
	 *     and it will continue the execution from the loop "while (list.size() == LIMIT)". Based on that condition, 
	 *     the execution will continue following 3a) or 3b).
	 * 
	 * @throws InterruptedException
	 */
	public void produce() throws InterruptedException {
		int count = 0;
		
		while (true) {
			
			System.out.println("[Producer thread] running.");
			
			// synchronized (lock) will acquire the lock on the 'lock' object.
			synchronized (lock) {
				System.out.println("[Producer thread] acquired lock.");
				
				while (list.size() == LIMIT) {
					// limiting the size of the list to LIMIT.
					System.out.println("[Producer thread] relinquishing the lock because the size of the list is: " + LIMIT);
					// In this case, the list is full (according to our LIMIT), so we want that the other thread will start consuming values.
					// wait() will awaken the other thread which is waiting to get values form the list.
					// we have to call wait() on the same object that we used to acquire the lock.
					lock.wait();
				}
				
				System.out.println("[Producer thread] added a new value in the list.");
				list.add(count++);
				
				// notify() will inform the other thread, that is currently waiting to acquire the lock on the same object (in this case on 'this'), 
				// that it can wake up to continue the execution.
				// we have to call notify() on the same object that we used to acquire the lock.
				lock.notify();
			}
		}
	}
	
	/**
	 * 
	 * 1)  it starts.
	 * 2)  it acquires the lock.
	 * 3a) if the list size is > 0 it gets a new values and it calls lock.notify()
	 * 	   so that the producer thread can wake up and acquire the lock to add new values.
	 * 3b) if the list size is == 0, it calls lock.wait() so that the producer thread 
	 *     can wake up and acquire the lock to add new values.
	 *     Once the producer thread has called lock.notify(), the consumer thread will acquire the lock again
	 *     and it will continue the execution from the loop "(list.size() == 0)". Based on that condition, 
	 *     the execution will continue following 3a) or 3b).
	 * 
	 * @throws InterruptedException
	 */
	public void consume() throws InterruptedException {
		Random random = new Random();
		
		while (true) {
			
			System.out.println("[Consumer thread] running.");
			
			// synchronized (lock) will acquire the lock on the 'lock' object.
			synchronized (lock) {
				System.out.println("[Consumer thread] acquired lock.");
				
				while (list.size() == 0) {
					// The list is empty so we want that the producer thread will be waken up to insert new values in the list.
					lock.wait();
				}
				
				System.out.println("[Consumer thread] list size: " + list.size());
				System.out.println("[Consumer thread] list first element: " + list.removeFirst());
				
				// notify() will inform the other thread, that is currently waiting to acquire the lock on the same object (in this case on 'this'), 
				// that it can wake up to continue the execution.
				// we have to call notify() on the same object that we used to acquire the lock.
				lock.notify();
			}
			Thread.sleep(random.nextInt(500));
		}
	}

}
