package model;

import java.sql.Date;

public class Employee {
	Date birthDate;
	String firstName;
	String lastNameString;
	int id;
	float salary;
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastNameString;
	}
	public void setLastName(String lastNameString) {
		this.lastNameString = lastNameString;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [birthDate=" + birthDate + ", firstName=" + firstName + ", lastNameString=" + lastNameString
				+ ", id=" + id + ", salary=" + salary + "]";
	}
}
