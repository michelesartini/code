ThreadExecutorService
=========

Executes threads by using a FixedThreadPool, every thread will decrease the value of the latch object.
The main thread will wait until the latch value is 0.
