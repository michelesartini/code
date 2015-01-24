import java.util.Random;

/**
 * Class that generates a deadlock while acquiring the locks for the two accounts.
 * 
 * thread1() acquires the lock in this order:
 * 1) lock1.lock();
 * 2) lock2.lock();
 * 
 * thread2() acquires the lock in this order:
 * 1) lock2.lock();
 * 2) lock1.lock();
 * 
 * So each thread will acquire one lock but not the other one,
 * therefore no one can proceed the execution.
 * If you use the same order while acquiring the locks you will avoid deadlock.
 * Another way to solve this issue consist of using a method of the Lock class 
 * that allows you to try acquiring the lock, see the ProcessorAvoidDeadLock.java class.
 * 
 * @author michelesartini
 *
 */
public class ProcessorDeadLock extends Processor {

	private Random random = new Random();
	
	@Override
	public void thread1() throws InterruptedException {
		System.out.println("[thread 1] started.");
		for (int i=0; i<1000; i++) {
			
			lock1.lock();
			lock2.lock();
			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
		System.out.println("[thread 1] finished.");
	}

	@Override
	public void thread2() throws InterruptedException {
		System.out.println("[thread 2] started.");
		for (int i=0; i<1000; i++) {
			
			// If you invert the following locking order you will avoid the deadlock condition.
			lock2.lock();
			lock1.lock();

			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
		System.out.println("[thread 2] finished.");
	}


}
