package com.home.client;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;

import com.home.entities.Employee;
import com.home.entities.Person;
import com.home.entities.Student;
import com.home.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			//createPerson(session);
			getPersonById(session);
			//updateEmployeeById(session);
			//deleteEmployeeById(session);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void deleteEmployeeById(Session session) {
		Employee employee=session.get(Employee.class, 1);
		if(employee != null) {
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();
		}
	}

	private static void updateEmployeeById(Session session) {
		session.beginTransaction();
		Employee employee=session.get(Employee.class, 1);
		if(employee != null) {
			employee.setName("Vivek Garg");
		    employee.setEmail("vivek.garg@gmail.com");
		    employee.setSalary(10000.00);
		    session.update(employee);
		    session.getTransaction().commit();
		}
		else
			System.out.println("Employee does not exist!!!");
		}

	//Single table fetch data
	private static void getPersonById(Session session) {
		Person person=session.get(Person.class, 3);
		if((person instanceof Person) && !(person instanceof Employee) && !(person instanceof Student)) {
			System.out.println(person);
		}
		else if((person instanceof Person) && (person instanceof Employee)){
			Employee employee=(Employee)person;
			System.out.println(employee);
		}
		else if((person instanceof Person) && (person instanceof Student)){
			Student student=(Student)person;
			System.out.println(student);
		}
		}

	private static void createPerson(Session session) throws ParseException {
		session.beginTransaction();
		
		Person person=new Person();
		person.setGender("Male");
		person.setName("Vivek Garg");
		
		Employee employee=new Employee();
		employee.setName("Prabhat");
		employee.setEmail("prabhat@gmail.com");
		employee.setDoj(HibernateUtil.getDoj("10/12/2013"));
		employee.setSalary(16000.00);
		employee.setBonus(new BigDecimal("101.123"));
		employee.setGender("Male");
		employee.setDeptName("CSE");
		
		Student student=new Student();
		student.setName("Shubham");
		student.setGender("Male");
		student.setFee(2000.00f);
		student.setSchoolName("SIET");
		student.setSectionName("CSE");
		
		session.save(person);
		session.save(employee);
		session.save(student);
		session.getTransaction().commit();
	}
}
