package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.Employee;
import sef.domain.Project;
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
	
	private DataSource dataSource;
	
	public StubEmployeeRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	
	/*
	 * (non-Javadoc)
	 * Finds employees in database with given first name and last name
	 * 
	 * @see sef.interfaces.repository.EmployeeRepository#findEmployeesByName(java.lang.String, java.lang.String)
	 * @param 	firstName 
	 * 			Employee first name for search
	 * 
	 * @param	lastName
	 * 			Employee last name for search
	 * @return 	list of employees found
	 */
	@Override
	public List<Employee> findEmployeesByName(String firstName, String lastName) {
		Connection conn = null;
		List<Employee> resultList = new ArrayList<Employee>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE first_name LIKE ? AND last_name LIKE ?");
			ps.setString(1, firstName+"%");
			ps.setString(2, lastName+"%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Employee employee = setEmployee(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name"),
						rs.getString("middle_initial"),rs.getString("level"),rs.getString("work_force"),rs.getString("enterprise_id"));
				
				
				resultList.add(employee);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return resultList;
	}
	
	/*
	 * (non-Javadoc)
	 * Finds employees in database with employee ID
	 * 
	 * @see sef.interfaces.repository.EmployeeRepository#findEmployeeByID(long)
	 * @param 	employeeID 
	 * 			Employee ID for search
	 * 
	 * @return 	Employee with corresponding ID
	 */
	@Override
	public Employee findEmployeeByID(long employeeID) {
		
		Connection conn = null;
		Employee employee = new Employee();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE id = ?");
			ps.setLong(1, employeeID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				employee = setEmployee(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name"),
						rs.getString("middle_initial"),rs.getString("level"),rs.getString("work_force"),rs.getString("enterprise_id"));
			}
			
			rs.close();
			ps.close();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}	
		}
		return employee;
	}
	
	/*
	 * (non-Javadoc)
	 * Finds employees in database that work on corresponding project
	 * 
	 * @see sef.interfaces.repository.EmployeeRepository#findEmployeesByProject(long)
	 * @param 	projectID 
	 * 			Project ID for search
	 * 
	 * @return 	List of employees that works with project
	 */
	@Override
	public List<Employee> findEmployeesByProject(long projectID) {

		Connection conn = null;
		List<Employee> resultList = new ArrayList<Employee>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT E.* "
					+ "FROM EMPLOYEE E, EMPLOYEE_PROJECT_DETAIL D "
					+ "WHERE E.employee_detail_id = D.employee_detail_id AND D.project_id = ?;");
			ps.setLong(1, projectID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Employee employee = setEmployee(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name"),
						rs.getString("middle_initial"),rs.getString("level"),rs.getString("work_force"),rs.getString("enterprise_id"));
				
				resultList.add(employee);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return resultList;
	}
	
	/*
	 * (non-Javadoc)
	 * Creates Employee object with given parameters
	 * 
	 * @param 	id 
	 * 			employee ID
	 * 
	 * @param 	first_name 
	 * 			employee first name
	 * 
	 * @param 	last_name 
	 * 			employee last name
	 * 
	 * @param 	middle_initial 
	 * 			employee middle initial
	 * 
	 * @param 	level 
	 * 			employee level
	 * 
	 * @param 	work_force 
	 * 			employee work force
	 * 
	 * @param 	enterprise_id 
	 * 			employee enterprise id
	 * 
	 * @return 	Employee object with given parameters
	 */
	public Employee setEmployee(Long id, String first_name, String last_name, String middle_initial, String level, String work_force, String enterprise_id)
	{
		Employee employee = new Employee();
		employee.setID(id);
		employee.setFirstName(first_name);
		employee.setLastName(last_name);
		employee.setMiddleInitial(middle_initial);
		employee.setLevel(level);
		employee.setWorkForce(work_force);
		employee.setEnterpriseID(enterprise_id);
		
		return employee;
	}
}
