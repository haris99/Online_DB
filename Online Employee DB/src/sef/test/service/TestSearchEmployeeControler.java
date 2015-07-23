package sef.test.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import sef.controller.SearchEmployeeController;
import sef.domain.Employee;
import sef.interfaces.service.EmployeeDetailsService;
import sef.interfaces.service.SearchService;

public class TestSearchEmployeeControler extends TestCase {
	private SearchService service;
	private EmployeeDetailsService detailsService;
	SearchEmployeeController controller;

	protected void setUp(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		service = (SearchService)context.getBean("searchService");
		ApplicationContext detailContext = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		detailsService = (EmployeeDetailsService)detailContext.getBean("detailsService");
		controller = new SearchEmployeeController(service,detailsService);
	}
	
	
	public void testOnViewEmployeeDetails() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav = controller.onViewEmployeeDetails(1);
		assertEquals("find/employeeDetails",mav.getViewName());
	}
	
	public void testOnInitialSearchFormState() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav = controller.onInitialSearchFormState();
		assertEquals("find/employeeSearchForm", mav.getViewName());
	}
	
	public void testOnSubmitSearchByName(){
		ModelAndView mav = new ModelAndView();
		mav = controller.onSubmitSearchByName("vards", "uzvards", 1);
		assertEquals("find/employeeSearchForm", mav.getViewName());
	}
	
	public void testOnSubmitSearchByProject(){
		ModelAndView mav = new ModelAndView();
		mav = controller.onSubmitSearchByProject(1);
		assertEquals("find/employeeSearchForm", mav.getViewName());
	}
}
