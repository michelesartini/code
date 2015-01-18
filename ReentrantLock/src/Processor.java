import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Threads synchronisation using a ReentrantLock object.
 * 
 * 1)  [Thread 1] acquires the lock.
 * 2)  [Thread 2] is sleeping.
 * 3)  [Thread 1] calls await and it hand over the lock.
 * 4)  [Thread 2] acquires the lock.
 * 5)  [Thread 2] requests for a input.
 * 6)  [Thread 2] calls signal and it wakes up [Thread 1] which cannot acquire the lock
 *     because [Thread 2] still has the lock.
 * 7)  [Thread 2] does some processing.
 * 8)  [Thread 2] calls unlock.
 * 9)  [Thread 1] now can acquire the lock.
 * 10) [Thread 1] does some processing.
 * 11) [Thread 1] calls unlock. 
 * 
 * @author michelesartini
 *
 */
public class Processor {
	Random random = new Random();
	int value = 0;
	
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void thread1() throws InterruptedException {
		
		lock.lock();
		System.out.println("[thread 1] Waiting.");
		// It is similar to wait();
		this.condition.await();
		System.out.println("[thread 1] Woken up.");
		try {
			System.out.println("[thread 1] Processing.");
			// simulating some kind of processing.
			incrementRandomly();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("[thread 1] Unlocking.");
			lock.unlock();
		}
	}
	
	public void thread2() throws InterruptedException {
		Thread.sleep(this.random.nextInt(500));
		
		lock.lock();
		
		System.out.println("[thread 2] Press return key.");
		// Just reading the return key to simulate some kind of interaction
		new Scanner(System.in).nextLine();
		System.out.println("[thread 2] Return key pressed.");
		
		// It is similar to notify();
		this.condition.signal();
		
		try {
			// simulating some kind of processing.
			System.out.println("[thread 2] Processing.");
			incrementRandomly();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("[thread 2] Unlocking.");
			lock.unlock();
		}
	}
	
	/**
	 * Simple method that simulate some kind of processing.
	 * In this case its just increment the value of this.value
	 * with a random number.
	 */
	public void incrementRandomly() {
		this.value += this.random.nextInt(10);
	}
	
	public void finished() {
		System.out.println("Value: " + this.value);
		System.out.println("Finished.");
	}
}
