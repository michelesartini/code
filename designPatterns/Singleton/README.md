Singleton
=========

This is a simple project that explains the Singleton pattern.

What is it?
=========

Ensure that a class can only have one instance, and it provides a global point of access to the only instance available.
It is part of the creational pattern.

When do we need it?
=========

There is no straightforward answer to this question, however It is commonly accepted that the singleton can yield best results in a situation where various parts of an application concurrently try to access a shared resource.

- Multiple classes require the same object instance.
- There can **only be one object** for the entire application.

Scenarios examples
=========

##### Example 1 - Logger Classes
The Singleton pattern is used in the design of logger classes. This classes are ussualy implemented as a singletons, and provides a global logging access point in all the application components without being necessary to create an object each time a logging operations is performed.

##### Example 2 - Configuration Classes
The Singleton pattern is used to design the classes which provides the configuration settings for an application. By implementing configuration classes as Singleton not only that we provide a global access point, but we also keep the instance we use as a cache object. When the class is instantiated( or when a value is read ) the singleton will keep the values in its internal structure. If the values are read from the database or from files this avoids the reloading the values each time the configuration parameters are used.

Benefits
=========

1. You will have one and only one object of a specific class.

2. Lazy instantiation
(If you opt for the lazy instantiation paradigm, then the singleton variable will not get memory until the property or function designated to return the reference is first called).


Issues
=========

1. It deviates from the Single Responsibility Principle. A singleton class has the responsibility to create an instance of itself along with other business responsibilities. However, this issue can be solved by delegating the creation part to a factory object.

2. Singleton classes cannot be sub classed.

3. Singletons can hide dependencies. One of the features of an efficient system architecture is minimizing dependencies between classes. This will in turn help you while conducting unit tests and while isolating any part of the program to a separate assembly. A singleton will make you sacrifice this feature in your application. Since the object creation part is invisible to us, we cannot expect the singleton constructor to accept any parameters. This setback may look unimportant on the first glance but as the software complexity increases, it will limit the flexibility of the program

4. The classic implementation has multithreaded issues.


Links
=========
[Wikipedia](http://en.wikipedia.org/wiki/Singleton_pattern)

[OODesign](http://www.oodesign.com/singleton-pattern.html)

[JavaWorld](http://www.javaworld.com/article/2073352/core-java/simply-singleton.html)

[CodeProject](http://www.codeproject.com/Articles/307233/Singleton-Pattern-Positive-and-Negative-Aspects)

