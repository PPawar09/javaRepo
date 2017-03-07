package com.pp.multithread;

public class ThreadBasicOps {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TypeOne to = new TypeOne();
		to.start();
		
		//Using Runnable
		Thread td = new Thread(new TypeRunnable(),"abc");
		Thread td1 = new Thread(new TypeRunnable(),"abc1");
		Thread td2 = new Thread(new TypeRunnable(),"abc2");
		
		td.start();
		td1.start();
		td2.start();
		
	
		
		/*		Besides the no-arg constructor and the constructor that takes a Runnable (the
				target, i.e., the instance with the job to do), there are other overloaded constructors
				in class Thread. The constructors we care about are
				n Thread()
				n Thread(Runnable target)
				n Thread(Runnable target, String name)
				n Thread(String name)*/

	}

}

/**
 * Extending java.lang.Thread
 * The simplest way to define code to run in a separate thread is to
 * 
 * The limitation with this approach (besides being a poor design choice in most
 * cases) is that if you extend Thread, you can't extend anything else. And it's not as if
 * you really need that inherited Thread class behavior, because in order to use a thread
 * you'll need to instantiate one anyway.
 * 
 */

class TypeOne extends Thread{
	@Override
	public void run(){
		System.out.println("**In side Thread run TypeOne***");
		//	td.sleep(5*60*1000); // Sleep for 5 minutes
	}
}

/**
 * Implementing java.lang.Runnable
 * Implementing the Runnable interface gives you a way to extend from any class you
 * like, but still define behavior that will be run by a separate thread. It looks like this:
 * 
 * Very Imp : here’s nothing special about the run() method as far as Java is
	concerned. Like main(), it just happens to be the name (and signature) of the method
	that the new thread knows to invoke. So if you see code that calls the run() method on
	a Runnable (or even on a Thread instance), that’s perfectly legal. But it doesn’t mean the
	run() method will run in a separate thread! Calling a run() method directly just means
	you’re invoking a method from whatever thread is currently executing, and the run()
	method goes onto the current call stack rather than at the beginning of a new call stack.
	The following code does not start a new thread of execution:
	Runnable r = new Runnable();
	r.run(); // Legal, but does not start a separate thread
 *
 */
class TypeRunnable implements Runnable{
	
	String threadName = "";
	
	@Override
	public void run(){
		System.out.println("**In side Thread run TypeTwo***" + Thread.currentThread().getName());
		//business logic goes here
		for(int i=0; i<20; i++){
			System.out.println("************"+i + " "+Thread.currentThread().getName());
		}
	}
}
