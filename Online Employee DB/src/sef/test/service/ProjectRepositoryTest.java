package sef.test.service;

import junit.framework.TestCase;

public class ProjectRepositoryTest extends TestCase{
	
	ProjectRepositoryTest runtest = new ProjectRepositoryTest ();
	
	public void testListAllProjects () {
		runtest.testListAllProjects();
	}
	public void testGetEmployeeProjects () {
		runtest.testGetEmployeeProjects();
	}
	public void testGetEmployeeProjectRoles () {
		runtest.testGetEmployeeProjectRoles();
	}
	public void testGetEmployeeProjectHistory () {
		runtest.testGetEmployeeProjectHistory();
	}
}
