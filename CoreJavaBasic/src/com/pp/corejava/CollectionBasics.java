package com.pp.corejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionBasics {

	public static void main(String[] args) {
		
		CollectionBasics cb = new CollectionBasics();
		
		// Most commonly used collection are List,Map,Set (ArrayList,HashMap,HashSet)
		// Collection Utility API is Collections and Arrays can be used for conversion or sorting.
		
		cb.playList(); // ArrayList Example code
		cb.playMap();  // HashMap Example code
		cb.playSet();  // HashSet Example code
		
		//Sorting List using util Collections and Arrays
		cb.SortingCollections();
		
		//custom object sorting by implementing compareTo of Interface Comaparable
		cb.testSortStudent(); 
	}
	
	/**
	 * This method uses List and its operation
	 */
	public void playList(){
		
		List<String> strLst = new ArrayList<String>();
		strLst.add("Praash");
		strLst.add("Prakash");
		strLst.add("Praash");
		strLst.add("Prakash");
		strLst.add("Prakash");

		System.out.println("*****Actual Size of List **** " + strLst.size());

		// delete item from list, iterator is the best option to avoid runtime Exception.
		// below is the example Iterator#remove()
		Iterator<String> itr = strLst.iterator();

		while(itr.hasNext()){
			String s = itr.next();
			if(s == "Praash" ){
				itr.remove();
			}
		}

		//Traversing List using for each loop.
		for(String s: strLst){
			if(s.equals("Praash")){
				strLst.remove(s);  // This will throw Exception.
			}
		}
		
		//convert List into Array.
		String[] abc = (String[])strLst.toArray();
		
		//convert array into List
		List abcList = Arrays.asList(abc);
		
		System.out.println("*****Size After **** " + strLst.size());
	}
	
	/**
	 * This method uses Map and its operation
	 */
	public void playMap(){
		
		Map<String,String> emp = new HashMap<String,String>();
		emp.put("1", "abc");
		emp.put("2", "abc");
		emp.put("3", "abc");

		//logic to iterate whole Map
		Iterator itr = emp.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<String, String> pair = (Map.Entry<String, String>)itr.next();
			System.out.println("****Map Data******" + pair.getValue() +"\n");
		}

		//If you're only interested in the keys of Map
		for (String key : emp.keySet()) {
			System.out.println("****Map Key******" + key +"\n");
		}
		
		//If you're only interested in the Values of Map
		for (String value : emp.values()) {
			System.out.println("****Map Values******" + value +"\n");
		}
		
		//delete item from Map
		Iterator itr1 = emp.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<String, String> pair = (Map.Entry<String, String>)itr1.next();
			if(pair.getKey().equalsIgnoreCase("")){
				itr1.remove();
			}
		}
	}
	
	/**
	 * This method uses Set and its operation
	 */
	public void playSet(){

		Set<String> stSet = new HashSet<String>();
		stSet.add("prakash");
		stSet.add("prakash");
		System.out.println("****Set Data Size***** " + stSet.size());

		// traverse Set items
		for(String s : stSet){
			System.out.println("****Set Data***** " + s);
		}

		// delete item from Set
		Iterator itr = stSet.iterator();
		while(itr.hasNext()){
			String s = (String)itr.next();
			if(s.endsWith("")){
				itr.remove();
			}
		}
	}
	
	/**
	  * Sorting List using util API Collections and Arrays
	  *
	  * Below example used String list to sort and String has already inbuilt implementation of Interface Comaparable 
	  * 
	  * What about custom object
	  * If you've already figured out the problem, our guess is that you did it without
	  *	the help of the obscure error message shown above…How the heck do you sort
	  * instances of DVDInfo? Why were we able to sort instances of String? When you
	  * look up Collections.sort() in the API your first reaction might be to panic.
	  * Hang tight, once again the generics section will help you read that weird looking
	  *	method signature. If you read the description of the one-arg sort() method,
	  * you'll see that the sort() method takes a List argument, and that the objects in
	  * the List must implement an interface called Comparable. It turns out that String
	  * implements Comparable, and that's why we were able to sort a list of Strings using
	  * the Collections.sort() method */
	
	public void SortingCollections(){

		ArrayList<String> lstString = new ArrayList<String>();
		lstString.add("Denver");
		lstString.add("Boulder");
		lstString.add("Vail");
		lstString.add("Aspen");
		lstString.add("Telluride");
		
		System.out.println("***List Display Before Sort " + lstString);
		Collections.sort(lstString); // Using sort of Collections
		System.out.println("***List Display After Sort " + lstString);

	}
	
	/**
	 * For custom Object Collection sorting based on some criteria can be done by implementing interface Compartor or Comparable.
	 * Comparator is prefered one for me becuase you can sort object based on multiple criteria like name, age etc.
	 */
	public void testSortStudent(){
		List listStudent = new ArrayList<Student1>();
		
		//Define several student
		Student1 s1 = new Student1("Yogesh");
		Student1 s2 = new Student1("Ramesh");
		Student1 s3 = new Student1("Ajay");
		Student1 s4 = new Student1("Vijay");
		Student1 s5 = new Student1("Bhoopy");
		
		listStudent.add(s1);
		listStudent.add(s2);
		listStudent.add(s3);
		listStudent.add(s4);
		listStudent.add(s5);
		
		System.out.println("***StudentList Display Before Name Sort " + listStudent);
		Collections.sort(listStudent); // This using Comparable Interface for sorting Collections
		System.out.println("***StudentList Display After Name Sort " + listStudent);
	}
}

/**
 * Defining class student to test sort of custom object Student based on the name of the student.
 * This class will implement Comparable interface and override compareTo()
 * 
 * The compareTo() method returns an int with the following characteristics:
 * you need to decide what to return 0 1 and -1 based on your criteria
 *		> negative If thisObjt < anotherObject
 *		> zero If thisObject == anotherObject
 *		> positive If thisObject > anotherObjectec
 * 
 *
 */
class Student1 implements Comparable<Student1>{
	
	public Student1(String name){
		this.name = name;
	}

	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Student1 obj){
		return this.name.compareTo(obj.getName());
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}