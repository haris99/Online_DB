package sef.impl.service;

import java.util.ArrayList;
import java.util.List;

import sef.domain.Employee;
import sef.domain.Project;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.service.SearchService;

import org.apache.log4j.Logger;

public class StubSearchServiceImpl implements SearchService {

	// Tip: create member variables in this class that will contain the objects
	// passed by the Spring framework so that other methods can access the
	// objects.

	private static Logger log = Logger.getLogger(StubSearchServiceImpl.class);
	private EmployeeRepository empDAO;
	private ProjectRepository projectDAO;

	public StubSearchServiceImpl(EmployeeRepository empDAO, ProjectRepository projectDAO) {
		this.empDAO = empDAO;
		this.projectDAO = projectDAO;
	}

	@Override
	public List<Employee> findEmployeesByName(String firstName, String lastName) {

		return empDAO.findEmployeesByName(firstName, lastName);
	}

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {

		return empDAO.findEmployeesByProject(projectID);
	}

	@Override
	public List<Project> listAllProjects() {

		return projectDAO.listAllProjects();
	}

}
