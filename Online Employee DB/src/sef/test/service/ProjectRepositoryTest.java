package sef.test.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import sef.domain.Project;
import sef.domain.ProjectRole;
import sef.impl.repository.StubProjectRepositoryImpl;
import sef.impl.repository.StubSkillRepositoryImpl;



public class ProjectRepositoryTest extends TestCase{
	StubProjectRepositoryImpl repo;
	
	protected void setUp(){
		repo = new StubProjectRepositoryImpl(DataSource dataSource);
	}
	
	//ProjectRepositoryTest runtest = new ProjectRepositoryTest ();
	
	public void testListAllProjects () {
		//runtest.testListAllProjects();
		List<Project> projectList = new ArrayList<Project>();
		projectList = repo.listAllProjects();
		assertTrue(projectList.size()>=1);
	}
	public void testGetEmployeeProjects () {
		//runtest.testGetEmployeeProjects();
		List<Project> projectList = new ArrayList<Project>();
		projectList = repo.getEmployeeProjects(1);
		assertTrue(projectList.size()>=1);
	}
	public void testGetEmployeeProjectRoles () {
		List<ProjectRole> projectRoleList = new ArrayList<ProjectRole>();
		projectRoleList = repo.getEmployeeProjectRoles(1, 1);
		assertTrue(projectRoleList.size()>=1);
		projectRoleList = repo.getEmployeeProjectRoles(999, 1);
		assertTrue(projectRoleList.size()==0);
		projectRoleList = repo.getEmployeeProjectRoles(1, 999);
		assertTrue(projectRoleList.size()==0);
		//runtest.testGetEmployeeProjectRoles();
	}
	public void testGetEmployeeProjectHistory () {
		//runtest.testGetEmployeeProjectHistory();
	}
}
