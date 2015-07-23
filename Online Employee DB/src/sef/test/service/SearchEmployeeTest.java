package sef.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import sef.interfaces.service.SearchService;
import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class SearchEmployeeTest extends TestCase{
	
	private SearchService service;
	
	public void setUp() throws SQLException{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		service = (SearchService)context.getBean("searchService");

	}
	
	public void testListemployees(){
		List result = service.findEmployeesByName("Arnolds", "Skuja");
		assertNotNull(result);
		//there should be an employee with this name, last name, size should be atleast 1
		assertTrue(result.size() > 0);
		//there should be no employees with this name, last name
		result = service.findEmployeesByName("random", "arnold");
		assertTrue(result.size() == 0);
		String string = null;
		//passing null should return an empty list
		result = service.findEmployeesByName(string, "Skuja");
		assertTrue(result.size() == 0);
	}
	
	public void testFindEmployeesByProject(){
		List result = service.findEmployeesByProject(1);
		//there should be a project with id 1 and it should have employees assigned to it
		assertNotNull(result);
		assertTrue(result.size() > 0);
		//there should be no project with id 999
		result = service.findEmployeesByProject(999);
		assertTrue(result.size() == 0);
	}
	
	public void testListAllProjects(){
		List result = service.listAllProjects();
		//there should be atleast 1 project returned
		assertNotNull(result);
		assertTrue(result.size() > 0);
	}
}
