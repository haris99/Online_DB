package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private DataSource dataSource;
	
	public StubEmployeeRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

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
				Employee employee = new Employee();
				employee.setID(rs.getLong("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setMiddleInitial(rs.getString("middle_initial"));
				employee.setLevel(rs.getString("level"));
				employee.setWorkForce(rs.getString("work_force"));
				employee.setEnterpriseID(rs.getString("enterprise_id"));
				
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
				employee.setID(rs.getLong("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setMiddleInitial(rs.getString("middle_initial"));
				employee.setLevel(rs.getString("level"));
				employee.setWorkForce(rs.getString("work_force"));
				employee.setEnterpriseID(rs.getString("enterprise_id"));
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

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {

		Connection conn = null;
		List<Employee> resultList = new ArrayList<Employee>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT E.* "
					+ "FROM EMPLOYEE E, PROJECT P, EMPLOYEE_PROJECT_DETAIL D "
					+ "WHERE E.employee_detail_id = D.employee_detail_id AND D.project_id = ? GROUP BY E.id");
			ps.setLong(1, projectID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Employee employee = new Employee();
				employee.setID(rs.getLong("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setMiddleInitial(rs.getString("middle_initial"));
				employee.setLevel(rs.getString("level"));
				employee.setWorkForce(rs.getString("work_force"));
				employee.setEnterpriseID(rs.getString("enterprise_id"));
				
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
}
