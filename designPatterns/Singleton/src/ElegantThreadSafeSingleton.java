/**
 * 
 * This singleton implementation is thread-safe because static member variables created when declared are guaranteed to be created the first time they are accessed. 
 * You get a thread-safe implementation that automatically employs lazy instantiation.
 * 
 * @author michelesartini
 *
 */
public class ElegantThreadSafeSingleton {
	
	private final static ElegantThreadSafeSingleton INSTANCE = new ElegantThreadSafeSingleton();
	
	private ElegantThreadSafeSingleton() {}
	
	ElegantThreadSafeSingleton getInstance() {
		return INSTANCE;
	}
}
