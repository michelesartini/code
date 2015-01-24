import java.util.concurrent.Semaphore;

/**
 * Singleton class.
 * 
 * It has a semaphore of 10, that means that it can handle no more than 10 simultaneous connections.
 * How does it work?
 * 1) Each threads that use this class, will call connect().
 * 2) connect() will try to acquire a semaphore, 
 * 2a) if the semaphore has enough number of permits available, the current thread will acquire a permit.
 * 2b) if all the available permits (in this case 10) are in used, the thread will wait.
 * 3) doConnect() will be called, it will increase the connections number, it will simulate some kind
 *    of processing by using Thread.sleep and it will decrease the number of connections before returning.
 * 4) The acquire permit will be release.
 * 
 * @author michelesartini
 *
 */
public class Connection {
	
	private static Connection instance = null;
	private int connections = 0;
	private Semaphore semaphore = new Semaphore(10);

	private Connection() {
		
	}
	
	public static Connection getInstance() {
		if (instance == null) {
			instance = new Connection();
		}
		return instance;
	}
	
	public void connect() {
		
		try {
			semaphore.acquire();
			
			doConnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
	
	private void doConnect() throws InterruptedException {
		synchronized (this) {
			connections++;
			System.out.println("Current connections: " + this.connections);
		}
		
		// simulating some kind of processing
		Thread.sleep(2000);
		
		synchronized (this) {
			connections--;
		}
	}
}
