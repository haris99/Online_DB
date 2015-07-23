package sef.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sef.domain.Project;
import sef.domain.ProjectRole;
import sef.impl.repository.StubProjectRepositoryImpl;
import sef.interfaces.service.SearchService;

public class StubProjectRepositoryImplTest extends TestCase {
	private DataSource dataSource;
	StubProjectRepositoryImpl repo;
	
	public void setUp(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		dataSource = (DataSource)context.getBean("dataSource");
		repo = new StubProjectRepositoryImpl(dataSource);
	}
	
	
	public void testGetEmployeeProjectRoles(){
		List<ProjectRole> resultList = new ArrayList<ProjectRole>();
		resultList = repo.getEmployeeProjectRoles(1, 1);
		assertTrue(resultList.size()>=1);
		resultList = new ArrayList<ProjectRole>();
		resultList = repo.getEmployeeProjectRoles(999, 1);
		assertTrue(resultList.size()==0);
		resultList = new ArrayList<ProjectRole>();
		resultList = repo.getEmployeeProjectRoles(1, 999);
		assertTrue(resultList.size()==0);
	}
	
	public void testGetEmployeeProjects(){
		List<Project> resultList = new ArrayList<Project>();
		resultList = repo.getEmployeeProjects(1);
		assertTrue(resultList.size()>=1);
		resultList = new ArrayList<Project>();
		resultList = repo.getEmployeeProjects(999);
		assertTrue(resultList.size()==0);
	}
}
