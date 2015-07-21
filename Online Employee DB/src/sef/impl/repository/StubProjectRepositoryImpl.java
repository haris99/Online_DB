package sef.impl.repository;

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

	public StubProjectRepositoryImpl(DataSource dataSource) {
	}

	@Override
	public List<Project> listAllProjects() {

		List<Project> list = new ArrayList<Project>();
		return list;
	}

	@Override
	public List<EmployeeProjectDetail> getEmployeeProjectHistory(long employeeID) {
		
		List<EmployeeProjectDetail> detailList = new ArrayList<EmployeeProjectDetail>();
		return detailList;
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
