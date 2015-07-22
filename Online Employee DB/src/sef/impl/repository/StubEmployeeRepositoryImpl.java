package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.Employee;
import sef.domain.EmployeeDetail;
import sef.domain.EmployeeProjectDetail;
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
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE first_name = ? AND last_name = ?");
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return employee;
	}

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {

		List<Employee> resultList = new ArrayList<Employee>();
		
		/*List<EmployeeDetail> employeeProjList = new ArrayList<EmployeeDetail>();
		
		for(EmployeeDetail empDet:employeeProjList)
		{
			List<EmployeeProjectDetail> projectList = new ArrayList<EmployeeProjectDetail>();
			projectList = empDet.getProjectList();
			
			for(EmployeeProjectDetail projectDetail:projectList)
			{
				if(projectID == projectDetail.getProject().getID())
				{
					resultList.add(empDet.getEmployee());
				}
			}			
		}		*/
		return resultList;
	}
}
