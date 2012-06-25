package test.gawekar.jpahibernatehsqld.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Emp")
public class Employee {
	private final Date now = new Date();
	@Id @Column(name="EMP_ID") @GeneratedValue(strategy=GenerationType.TABLE) private int id;
	private String name;
	private float salary;
	@Enumerated(EnumType.STRING) private EmployeeType type = EmployeeType.CONTRACT_EMPLOYEE;
	@Temporal(TemporalType.DATE) private Date temporalDate = now;
	@Temporal(TemporalType.TIME) private Date temporalTime = now;
	@Temporal(TemporalType.TIMESTAMP) private Date temporalTimeStamp = now;
	@Temporal(TemporalType.DATE)
    private Calendar dob = Calendar.getInstance();
	@ManyToOne()
	@JoinColumn(name="dept_id")
    private Department dept;
    
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [dept=" + dept + ", name=" + name + "]";
	}

}
