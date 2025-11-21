# Thread

* A thread is a lightweight sub-process, the smallest unit of processing.

## Multitasking

* Multitasking is a process of executing multiple tasks simultaneously. We use multitasking to utilize the CPU. Multitasking can be achieved in two ways:

1. Process-based Multitasking (Multiprocessing)
     * each process allocates a separate memory area
2. Thread-based Multitasking (Multithreading)
     * share the same address space

## Thread States

![image](https://github.com/HJ4263/JU/assets/143291338/75fe2b2a-1609-4333-b965-4d46986a6259)

* New:
* Active: start()
  * Runnable
    * threads lie for thier slice of time to get run in the CPU
  * Running
    * run for a pre-determined time
* Blocked / Waiting
  * a thread is insctive for a span of time
  * in this state it doesn't consume any cycle of CPU
  * when main thread invokes join() method it goes into waiting for the child threads to finish
* Timed Waiting
  * waitng for leads to stervation
  * sleep()
* Terminated

## Commonly used methods of Thread class

    public void run(): is used to perform action for a thread.
    public void start(): starts the execution of the thread.JVM calls the run() method on the thread.
    public void sleep(long miliseconds): Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds.
    public void join(): waits for a thread to die.
    public void join(long miliseconds): waits for a thread to die for the specified miliseconds.
    public int getPriority(): returns the priority of the thread.
    public int setPriority(int priority): changes the priority of the thread.
    public String getName(): returns the name of the thread.
    public void setName(String name): changes the name of the thread.
    public Thread currentThread(): returns the reference of currently executing thread.
    public int getId(): returns the id of the thread.
    public Thread.State getState(): returns the state of the thread.
    public boolean isAlive(): tests if the thread is alive.
    public void yield(): causes the currently executing thread object to temporarily pause and allow other threads to execute.
    public void suspend(): is used to suspend the thread(depricated).
    public void resume(): is used to resume the suspended thread(depricated).
    public void stop(): is used to stop the thread(depricated).
    public boolean isDaemon(): tests if the thread is a daemon thread.
    public void setDaemon(boolean b): marks the thread as daemon or user thread.
    public void interrupt(): interrupts the thread.
    public boolean isInterrupted(): tests if the thread has been interrupted.
    public static boolean interrupted(): tests if the current thread has been interrupted.

## Thread Scheduler

* A component of Java that decides which thread to run or execute and which thread to wait is called a thread scheduler in Java.
* There are two factors for scheduling a thread
    * Priority: Priority of each thread lies between 1 to 10. If a thread has a higher priority, it means that thread has got a better chance of getting picked up by the thread scheduler.
    * Time of Arrival: Suppose two threads of the same priority enter the runnable state, then priority cannot be the factor to pick a thread from these two threads. Then, A thread that arrived first gets the preference over the other threads.
      
    ### Thread Scheduler Algorithms
  * First Come First Serve Scheduling:
      * queue
      * may lead to stervation
  * Time-slicing scheduling:
      * gets a slice of time, in CPU
  * Preemptive-Priority Scheduling:
      * queue based on priority
      * limited time

![image](https://github.com/HJ4263/JU/assets/143291338/f8678427-48e5-445f-8948-3e17fec9b703)

