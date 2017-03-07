package com.pp.corejava;

public class EqualTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee emp1 = new Employee();
		emp1.setName("Prakash");
		
		Employee emp2 = new Employee();
		emp2.setName("Prakash");
		
		System.out.println("*****Test Eqaul******" + emp1.equals(emp2)); 
		// equal compare values and == compare the reference

	}

}

class Employee{
	String name = null;
	String empId = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public boolean equals(Object obj){
		boolean flg = false;
		if(obj instanceof Employee && null != obj){
			return (this.hashCode() == obj.hashCode() && this.name.equalsIgnoreCase(((Employee)obj).getName()));
		}
		return flg;
	}

	@Override
	public int hashCode(){
		int x = 0;
		x = this.getName().length();
		System.out.println("*****Inside hashcode*****" + x);
		return x;
	}
}
