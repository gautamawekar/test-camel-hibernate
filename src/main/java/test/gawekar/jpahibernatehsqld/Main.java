package test.gawekar.jpahibernatehsqld;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import test.gawekar.jpahibernatehsqld.model.Department;
import test.gawekar.jpahibernatehsqld.model.Employee;

public class Main {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-gawekar-hsqldb-local",DBProperties.createUnitTestDBConnectionProperties()); 
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
