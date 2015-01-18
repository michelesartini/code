import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * Shows an example of how to avoid deadlocks by using the tryLock() method
 * present in the Lock class.
 *
 * @author michelesartini
 *
 */
public class ProcessorAvoidDeadLock extends Processor {

	private Random random = new Random();
	
	/**
	 * 1) Tries to acquire both locks.
	 * 2) if it succeeded, it returns.
	 * 3) It will release the lock that it was able to acquire.
	 * 4) It will wait a small amount of time before re attempting.
	 * 
	 * @param firstLock
	 * @param secondLock
	 * @throws InterruptedException
	 */
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		boolean gotFirstLock  = false;
		boolean gotSecondLock = false;
		// it loops untill it gets both locks
		while (true) {
			try {
				gotFirstLock  = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			} finally {
				
				// It acquired both locks so it can return
				if (gotFirstLock && gotSecondLock) {
					return;
				}
				// It got only one lock so it will release the lock
				// so it will avoid deadlock.
				if (gotFirstLock) {
					firstLock.unlock();
				}
				
				// It got only one lock so it will release the lock
				// so it will avoid deadlock.
				if (gotSecondLock) {
					secondLock.unlock();
				}
			}
			// Waiting before attempting to acquire locks again.
			Thread.sleep(1);
		}
	}
	
	@Override
	public void thread1() throws InterruptedException {
		System.out.println("[thread 1] started.");
		for (int i=0; i<1000; i++) {
			
			acquireLocks(lock1, lock2);
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
			
			acquireLocks(lock1, lock2);
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
