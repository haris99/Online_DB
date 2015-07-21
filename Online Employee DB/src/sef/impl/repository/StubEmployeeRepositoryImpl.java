package sef.impl.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.Employee;
import sef.interfaces.repository.EmployeeRepository;

import org.apache.log4j.Logger;

public class StubEmployeeRepositoryImpl implements EmployeeRepository {


	//DataSource class encapsulates the driver, database url, username and 
	//password information.  The dataSource object is automatically created by 
	//the Spring framework and passed to the constructor therefore there's no need 
	//to instantiate the dataSource variable. A connection can be acquired by 
	//accessing the getConnection method of dataSource. 
	//
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(StubEmployeeRepositoryImpl.class);
		
	public StubEmployeeRepositoryImpl(DataSource dataSource) {
	}

	@Override
	public List<Employee> findEmployeesByName(String firstName, String lastName) {
		
		List<Employee> list = new ArrayList<Employee>();
		return list;
	}

	@Override
	public Employee findEmployeeByID(long employeeID) {
		
		Employee employee = new Employee();
		return employee;
	}

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {

		List<Employee> list = new ArrayList<Employee>();
		return list;
	}


}
