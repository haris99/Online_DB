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
		assertTrue(result.size() > 0);
		result = service.findEmployeesByName("random", "arnold");
		assertTrue(result.size() == 0);
		String string = null;
		result = service.findEmployeesByName(string, "Skuja");
		assertTrue(result.size() == 0);
	}
	
	public void testFindEmployeesByProject(){
		List result = service.findEmployeesByProject(1);
		assertNotNull(result);
		assertTrue(result.size() > 0);
		result = service.findEmployeesByProject(999);
		assertTrue(result.size() == 0);
	}
	
	public void testListAllProjects(){
		List result = service.listAllProjects();
		assertNotNull(result);
		assertTrue(result.size() > 0);
	}
}
