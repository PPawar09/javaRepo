package com.pp.corejava;

public class Polymorphism {

	public static void main(String[] args) {
		Animal an1 = new Dog();
		Animal an2 = new Cat();
		
		an1.eat();
		an2.sound();
	}

}

interface Animal {
	public void eat();
	public void sound();
}

class Dog implements Animal{

	public void eat(){
		System.out.println("*****Dog Eating******");
	}

	public void sound(){
		System.out.println("*****Dog Barking******");
	}
}

class Cat implements Animal{

	public void eat(){
		System.out.println("*****Cat Eating******");
	}

	public void sound(){
		System.out.println("*****Cat Meow******");
	}
}


