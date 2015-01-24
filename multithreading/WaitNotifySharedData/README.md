WaitNotifySharedData
=========

This is a simple project is very similar to the WaitNotify project.
The main differences are:
- it emphasizes that you have to call `wait()` and `notify()` on the object that you used as lock.
- The producer and consumer threads will access shared data.