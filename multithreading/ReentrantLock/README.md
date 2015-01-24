ReentrantLock
=========

This is a simple project that explains how to use the ReentrantLock class.

Benefits of ReentrantLock in Java
=========

1. Ability to lock interruptibly.
2. Ability to timeout while waiting for lock.
3. Power to create fair lock.
4. API to get list of waiting thread for lock.
5. Flexibility to try for lock without blocking.

Disadvantages of ReentrantLock in Java
=========

Major drawback of using ReentrantLock in Java is wrapping method body inside try-finally block, which makes code unreadable and hides business logic. Another disadvantage is that, now programmer is responsible for acquiring and releasing lock, which is a power but also opens gate for new subtle bugs, when programmer forget to release the lock in finally block.

Link
=========
[Reentrant lock and synchronized difference](http://javarevisited.blogspot.co.uk/2013/03/reentrantlock-example-in-java-synchronized-difference-vs-lock.html)
