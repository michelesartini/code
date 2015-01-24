import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class Processor {
	protected Account acc1 = new Account(1000);
	protected Account acc2 = new Account(1000);
	
	protected Lock lock1 = new ReentrantLock();
	protected Lock lock2 = new ReentrantLock();

	public abstract void thread1() throws InterruptedException;
	public abstract void thread2() throws InterruptedException;
	
	
	public void finished() {
		System.out.println("Account 1 balance: " + this.acc1.getBalance());
		System.out.println("Account 2 balance: " + this.acc2.getBalance());
		System.out.println("Total balance: " + (this.acc1.getBalance() + this.acc2.getBalance()));
	}
}
