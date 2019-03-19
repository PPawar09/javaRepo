package com.pp.corejava;

public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Multitask(), "test1");
		Thread t2 = new Thread(new Multitask(), "test2");
		
		t.start();
		t2.start();
	}

}

class Multitask implements Runnable{
	@Override
	public void run(){
		System.out.println("*******Inside Thread*******"+Thread.currentThread().getName());
	}
}
