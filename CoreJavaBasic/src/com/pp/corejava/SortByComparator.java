package com.pp.corejava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This is explain the use of Comparator Interface and its implementation using collection of 
 * Custom object Student.
 * 
 * @author Prakash Pawar
 */
public class SortByComparator {

	public static void main(String[] args) {

		ArrayList al=new ArrayList();  
		al.add(new Student(101,"Vijay",23));  
		al.add(new Student(106,"Ajay",27));  
		al.add(new Student(105,"Jai",21));  

		System.out.println("Sorting by Name...");  
		Collections.sort(al,new NameComparator());  

		Iterator itr=al.iterator();  
		while(itr.hasNext()){  
			Student st=(Student)itr.next();  
			System.out.println(st.rollno+" "+st.name+" "+st.age);  
		}  

		System.out.println("sorting by age...");  
		Collections.sort(al,new AgeComparator());  
		Iterator itr2=al.iterator();  
		while(itr2.hasNext()){  
			Student st=(Student)itr2.next();  
			System.out.println(st.rollno+" "+st.name+" "+st.age);  
		}  
	}
}

/**
 * Class implementing Comparator and overriding compare(Object o1,Object o2)
 * this compare the age criteria
 * @author Prakash Pawar
 *
 */
class AgeComparator implements Comparator{  
	
	@Override
	public int compare(Object o1,Object o2){  
		Student s1=(Student)o1;  
		Student s2=(Student)o2;  

		if(s1.age==s2.age)  
			return 0;  
		else if(s1.age>s2.age)  
			return 1;  
		else  
			return -1;  
	}  
}

/**
 * Class implementing Comparator and overriding compare(Object o1,Object o2)
 * this compare the name criteria
 * @author Prakash Pawar
 *
 */
class NameComparator implements Comparator{  

	@Override
	public int compare(Object o1,Object o2){  
		Student s1=(Student)o1;  
		Student s2=(Student)o2;  
		return s1.name.compareTo(s2.name);  
	}  
} 


