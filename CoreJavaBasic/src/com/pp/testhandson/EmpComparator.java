package com.pp.testhandson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmpComparator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List empList = new ArrayList<Employee>();
		Employee emp1 = new Employee("Prakash", "IT", 32);
		Employee emp2 = new Employee("Vijay", "Mainfarme", 30);
		Employee emp3 = new Employee("Sachin", "Visual", 38);
		Employee emp4 = new Employee("Dheeraj", "Oracle", 25);

		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		empList.add(emp4);
		
		System.out.println("**Before Sorting**" + empList);
		Collections.sort(empList, new EmpNameComp());;
		System.out.println("**After Sorting**" + empList);
		
	}

}

class EmpNameComp implements Comparator<Employee>{
	@Override
	public int compare(Employee emp1, Employee emp2){
		return emp1.getName().compareTo(emp2.getName());
	}
}

class Employee{
	
	private String name = null;
	private String dept = null;
	private int age = 0;
	
	public Employee(String name,String dept,int age){
		this.name = name;
		this.dept = dept;
		this.age = age;
	}
	
	String getName(){
		return this.name;
	}
	
	String getDept(){
		return this.dept;
	}
	
	int getAge(){
		return this.age;
	}
}
