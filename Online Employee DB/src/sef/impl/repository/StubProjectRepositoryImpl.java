package sef.impl.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.Project;
import sef.domain.EmployeeProjectDetail;
import sef.domain.ProjectRole;
import sef.interfaces.repository.ProjectRepository;

import org.apache.log4j.Logger;

public class StubProjectRepositoryImpl implements ProjectRepository {

	//DataSource class encapsulates the driver, database url, username and 
	//password information.  The dataSource object is automatically created by 
	//the Spring framework and passed to the constructor therefore there's no need 
	//to instantiate the dataSource variable. A connection can be acquired by 
	//accessing the getConnection method of dataSource. 
	//
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(StubProjectRepositoryImpl.class);

	private DataSource dataSource;
	
	public StubProjectRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/*
	 * (non-Javadoc)
	 * Gets data about all projects from database
	 * 
	 * @see sef.interfaces.repository.ProjectRepository#listAllProjects()
	 * 
	 * @return 	List of employees that works with project
	 */	
	@Override
	public List<Project> listAllProjects() {
		Connection conn = null;
		List<Project> resultList = new ArrayList<Project>();
		Statement stmt;
		ResultSet rs;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM PROJECT");			
			
			while (rs.next()) {
				Project proj = setProject(rs.getLong("id"), rs.getString("name"), 
						rs.getString("description"), rs.getString("client"));
				resultList.add(proj);
			}
			rs.close();
			stmt.close();
			
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
	 * Finds data about employees project history
	 * 
	 * @see sef.interfaces.repository.ProjectRepository#getEmployeeProjectHistory(long)
	 * @param 	employeeID 
	 * 			Employee ID
	 * @return 	list of EmployeeProjectDetail
	 */
	@Override
	public List<EmployeeProjectDetail> getEmployeeProjectHistory(long employeeID) {
		
		List<EmployeeProjectDetail> resultList = new ArrayList<EmployeeProjectDetail>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT P.*, S.id AS Detail_ID "
					+ "FROM PROJECT P, "
					+ "(SELECT * FROM employee_project_detail D WHERE D.employee_detail_id = ?) S "
					+ "WHERE S.project_id = P.id;");
			ps.setLong(1, employeeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				EmployeeProjectDetail empProjDetail = new EmployeeProjectDetail();
				
				Project proj = setProject(rs.getLong("id"), rs.getString("name"), 
						rs.getString("description"), rs.getString("client"));
				int detail_id = rs.getInt("Detail_ID");
				
				empProjDetail.setProject(proj);
				
				List<ProjectRole> rolesList = new ArrayList<ProjectRole>();
				
				PreparedStatement ps2 = conn.prepareStatement("SELECT R.* "
						+ "FROM PROJECT P, EMPLOYEE_PROJECT_DETAIL D, PROJECT_ROLE R "
						+ "WHERE R.employee_project_detail_id = ? GROUP BY R.id;");
				ps2.setInt(1, detail_id);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next())
				{
					ProjectRole role = setRole(rs2.getLong("id"), rs2.getString("role"), 
							rs2.getDate("start_date"), rs2.getDate("end_date"));
					
					rolesList.add(role);
				}
				rs2.close();
				ps2.close();
				
				empProjDetail.setProjectRoles(rolesList);
				resultList.add(empProjDetail);
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
	 * Finds data about employees roles in project
	 * 
	 * @see sef.interfaces.repository.ProjectRepository#getEmployeeProjectRoles(long, long)
	 * @param 	employeeID 
	 * 			Employee ID
	 * @param 	projectID 
	 * 			Project ID
	 * @return 	list of ProjectRole
	 */
	@Override
	public List<ProjectRole> getEmployeeProjectRoles(long employeeID,
			long projectID) {
				
		List<ProjectRole> resultList = new ArrayList<ProjectRole>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT R.* "
					+ "FROM "
					+ "(SELECT D.id "
					+ "FROM EMPLOYEE_PROJECT_DETAIL D "
					+ "WHERE d.employee_detail_id = ? AND d.project_id = ?) M, PROJECT_ROLE R "
					+ "WHERE R.employee_project_detail_id = M.id;");
			ps.setLong(1, employeeID);
			ps.setLong(2, projectID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				ProjectRole role = setRole(rs.getLong("id"), rs.getString("role"), 
						rs.getDate("start_date"), rs.getDate("end_date"));
				
				resultList.add(role);
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
	 * Finds data about projects, that employee works for
	 * 
	 * @see sef.interfaces.repository.ProjectRepository#getEmployeeProjects(long)
	 * @param 	employeeID 
	 * 			Employee ID
	 * @return 	list of Project
	 */
	@Override
	public List<Project> getEmployeeProjects(long employeeID) {
		
		List<Project> resultList = new ArrayList<Project>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT P.* "
					+ "FROM PROJECT P, "
					+ "(SELECT * FROM employee_project_detail D WHERE D.employee_detail_id = ?) S "
					+ "WHERE S.project_id = P.id;");
			ps.setLong(1, employeeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Project proj = setProject(rs.getLong("id"), rs.getString("name"), 
						rs.getString("description"), rs.getString("client"));				
				resultList.add(proj);
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
	 * Creates Project object with given parameters
	 * 
	 * @param 	id 
	 * 			project ID
	 * 
	 * @param 	name 
	 * 			project name
	 * 
	 * @param 	description 
	 * 			project description
	 * 
	 * @param 	client 
	 * 			client
	 * @return 	Project object with given parameters
	 */
	public Project setProject(Long id, String name, String description, String client)
	{
		Project proj = new Project();
		proj.setID(id);
		proj.setName(name);
		proj.setDescription(description);
		proj.setClient(client);
		
		return proj;
	}
	
	/*
	 * (non-Javadoc)
	 * Creates ProjectRole object with given parameters
	 * 
	 * @param 	id 
	 * 			role ID
	 * 
	 * @param 	role 
	 * 			role name
	 * 
	 * @param 	start_date 
	 * 			date, when started to work in given role
	 * 
	 * @param 	end_date 
	 * 			date, when ends to work in given role
	 * @return 	ProjectRole object with given parameters
	 */
	public ProjectRole setRole(Long id, String role, Date start_date, Date end_date)
	{
		ProjectRole projectRole = new ProjectRole();
		projectRole.setID(id);
		projectRole.setRole(role);
		projectRole.setStartDate(start_date);
		projectRole.setEndDate(end_date);
		
		return projectRole;
	}
}
