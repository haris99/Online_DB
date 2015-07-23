package sef.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sef.domain.EmployeeDetail;
import sef.domain.Project;
import sef.domain.ProjectRole;
import sef.impl.repository.StubProjectRepositoryImpl;
import sef.impl.repository.StubSkillRepositoryImpl;
import sef.impl.service.StubEmployeeDetailsServiceImpl;
import sef.impl.service.StubSearchServiceImpl;
import sef.interfaces.service.EmployeeDetailsService;
import sef.interfaces.service.SearchService;



public class EmployeeDetailsServiceTest extends TestCase{
	private EmployeeDetailsService service;
	
	
	protected void setUp(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		service = (EmployeeDetailsService)context.getBean("detailsService");
	}
	
	
	public void testGetEmployeeDetails () {
		EmployeeDetail details = new EmployeeDetail();
		details = service.getEmployeeDetails(1);
		//there should be an employee with id 1
		assertTrue(details.getEmployee().getEnterpriseID() != null);
		details = new EmployeeDetail();
		//there should be no employee with id 999
		details = service.getEmployeeDetails(999);
		assertTrue(details.getEmployee().getEnterpriseID() == null);
	}
}
