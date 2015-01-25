/**
 * Classic Singleton implementation.
 * 
 * Interesting points about this implementation:
 * 
 * 1) It uses a technique known as lazy instantiation to create the singleton;
 *    as a result, the singleton instance is not created until the getInstance() method is called for the first time. 
 *    This technique ensures that singleton instances are created only when needed.
 *    
 * 2) It is possible to have multiple singleton instances if classes loaded by different classloaders access a singleton. 
 *    That scenario is not so far-fetched; for example, some servlet containers use distinct classloaders for each servlet, 
 *    so if two servlets access a singleton, they will each have their own instance.
 *    
 * 3) if ClassicSingleton implements the java.io.Serializable interface, the class's instances can be serialized and deserialised. 
 *    However, if you serialize a singleton object and subsequently deserialise that object more than once, you will have multiple singleton instances.
 *    
 * 4) It is not thread-safe.
 * 
 * @author michelesartini
 *
 */
public class ClassicSingleton {
	
	private static ClassicSingleton instance = null;
	
	private ClassicSingleton() {}
	
	public static ClassicSingleton getInstance() {
		if (instance == null) {
			instance = new ClassicSingleton();
		}
		return instance;
	}
}
