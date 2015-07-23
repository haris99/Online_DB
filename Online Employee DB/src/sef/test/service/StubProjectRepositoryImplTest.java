package sef.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import junit.framework.TestCase;
import sef.domain.Project;
import sef.domain.ProjectRole;
import sef.impl.repository.StubProjectRepositoryImpl;
import sef.interfaces.service.SearchService;

public class StubProjectRepositoryImplTest extends TestCase {
	private DataSource dataSource;
	StubProjectRepositoryImpl repo;
	DataSource wrongDataSource;
	StubProjectRepositoryImpl wrongRepo;

	public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		dataSource = (DataSource) context.getBean("dataSource");
		repo = new StubProjectRepositoryImpl(dataSource);
		//creating invalid data source to test how it will act
		wrongDataSource = null;
		wrongRepo = new StubProjectRepositoryImpl(wrongDataSource);
	}

	public void testGetEmployeeProjectRoles() {
		List<ProjectRole> resultList = new ArrayList<ProjectRole>();
		//there should be an employee with id 1 assigned to project 1
		resultList = repo.getEmployeeProjectRoles(1, 1);
		assertTrue(resultList.size() >= 1);
		resultList = new ArrayList<ProjectRole>();
		resultList = repo.getEmployeeProjectRoles(999, 1);
		//there is no employee with id 999 assigned to project 1
		assertTrue(resultList.size() == 0);
		resultList = new ArrayList<ProjectRole>();
		resultList = repo.getEmployeeProjectRoles(1, 999);
		//there is no employee with id 1 assigned to project 999
		assertTrue(resultList.size() == 0);
		//trying to call this method with an invalid data source should return an exception
		try {
			resultList = new ArrayList<ProjectRole>();
			resultList = wrongRepo.getEmployeeProjectRoles(1, 1);
			fail();
		} catch (NullPointerException e) {
			System.out.println("testGetEmployeeProjectRoles Catch succesful");
		}
	}

	public void testGetEmployeeProjects() {
		List<Project> resultList = new ArrayList<Project>();
		//employee with id 1 should have projects
		resultList = repo.getEmployeeProjects(1);
		assertTrue(resultList.size() >= 1);
		resultList = new ArrayList<Project>();
		resultList = repo.getEmployeeProjects(999);
		//employee with id 999 should have no projects
		assertTrue(resultList.size() == 0);
		//trying to call this method with an invalid data source should return an exception
		try {
			resultList = new ArrayList<Project>();
			resultList = wrongRepo.getEmployeeProjects(1);
			fail();
		} catch (NullPointerException e) {
			System.out.println("testGetEmployeeProjects Catch succesful");
		}
	}
}
