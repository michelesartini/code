/**
 * Simple implementation of a thread sage Singleton.
 * 
 * Interesting points about this implementation:
 * 
 * 1) Synchronized methods can run up to 100 times slower than unsynchronized methods,
 *    therefore it doesn't have good performance.
 * 
 * @link http://www.javaworld.com/article/2073352/core-java/simply-singleton.html
 *
 */
public class EasyThreadSafeSingleton {
	
	private static EasyThreadSafeSingleton instance = null;
	
	private EasyThreadSafeSingleton() {}
	
	// As mentioned above, the synchronized method doesn't have good performance.
	public synchronized static EasyThreadSafeSingleton getInstance() {
		if (instance == null) {
			instance = new EasyThreadSafeSingleton();
		}
		return instance;
	}
	
	// The following method shows another way to implement the getInstance method,
	// but IT IS NOT Thread safe, therefore it should be avoided.
	public static EasyThreadSafeSingleton getInstanceDoubleCheck() {
			
			// What happens if two threads simultaneously access getInstance()? Imagine Thread 1 enters the synchronized block and is preempted. 
			// Subsequently, a second thread enters the if block. When Thread 1 exits the synchronized block, 
			// Thread 2 makes a second check to see if the singleton instance is still null. Since Thread 1 set the singleton member variable, 
			// Thread 2's second check will fail, and a second singleton will not be created. Or so it seems.
			// Unfortunately, double-checked locking is not guaranteed to work because the compiler is free to assign a value to the singleton member variable 
			// before the singleton's constructor is called. If that happens, Thread 1 can be preempted after the singleton reference has been assigned, 
			// but before the singleton is initialized, so Thread 2 can return a reference to an uninitialized singleton instance.
		
			if (instance == null) {
				
				synchronized (EasyThreadSafeSingleton.class) {
				
					if (instance == null) {
						instance = new EasyThreadSafeSingleton();
					}
				}
			}
			return instance;
		}
}
