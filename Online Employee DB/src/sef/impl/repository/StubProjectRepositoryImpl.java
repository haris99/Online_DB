package sef.impl.repository;

import java.sql.Connection;
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

	@Override
	public List<Project> listAllProjects() {
		Connection conn = null;
		List<Project> projectList = new ArrayList<Project>();
		Statement stmt;
		ResultSet rs;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM PROJECT");			
			
			while (rs.next()) {
				Project proj = new Project();
				proj.setID(rs.getLong("id"));
				proj.setName(rs.getString("name"));
				proj.setDescription(rs.getString("description"));
				proj.setClient(rs.getString("client"));
				projectList.add(proj);
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
		List<Project> list = new ArrayList<Project>();
		return list;
	}

	@Override
	public List<EmployeeProjectDetail> getEmployeeProjectHistory(long employeeID) {
		
		List<EmployeeProjectDetail> resultList = new ArrayList<EmployeeProjectDetail>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT P.*, D.id AS Detail_ID "
					+ "PROJECT P, EMPLOYEE_PROJECT_DETAIL D "
					+ "WHERE D.employee_detail_id = ? AND D.project_id = P.id GROUP BY P.id;");
			ps.setLong(1, employeeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				EmployeeProjectDetail empProjDetail = new EmployeeProjectDetail();
				
				Project proj = new Project();
				proj.setID(rs.getLong("id"));
				proj.setName(rs.getString("name"));
				proj.setDescription(rs.getString("description"));
				proj.setClient(rs.getString("client"));
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
					ProjectRole role = new ProjectRole();
					role.setID(rs2.getLong("id"));
					role.setRole(rs2.getString("role"));
					role.setStartDate(rs2.getDate("start_date"));
					role.setEndDate(rs2.getDate("end_date"));
					
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

	@Override
	public List<ProjectRole> getEmployeeProjectRoles(long employeeID,
			long projectID) {
		
		List<ProjectRole> list = new ArrayList<ProjectRole>();
		return list;
	}

	@Override
	public List<Project> getEmployeeProjects(long employeeID) {
		
		List<Project> list = new ArrayList<Project>();
		return list;
	}
}
