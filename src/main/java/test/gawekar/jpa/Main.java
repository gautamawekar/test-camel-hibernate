package test.gawekar.jpa;

import java.util.List;

import javax.persistence.*;

import test.gawekar.jpa.model.Department;
import test.gawekar.jpa.model.Employee;

public class Main {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-gawekar",DBProperties.createUnitTestDBConnectionProperties()); 
	public static void main(String args[]){
		Department dept = new Department();
		dept.setName("NPG");
		persist(dept);
		persist(createEmployee("gautam",1000,dept));
		persist(createEmployee("abhijeet",4000,dept));
		persist(createEmployee("mani",5000,null));
		System.out.println(findEmployee(1));
		System.out.println(findAllEMployees());
		emf.close();
	}
	
	private static Employee createEmployee(String name,float salary,Department dept){
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSalary(salary);
		if (dept != null){
			emp.setDept(dept);
		}
		return emp;
	}
	
	private static Employee findEmployee(int id){
		EntityManager em = emf.createEntityManager();
		Employee emp = em.find(Employee.class, id);
		em.close();
		return emp;
		
	}
	
	private static List<?> findAllEMployees(){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT e FROM Employee e");
		List<?> result = query.getResultList();
		return result;
	}
	
	private static void persist(Object data){
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(data);
		et.commit();
		em.close();
	}
	
	
}
