package sef.impl.service;

import sef.domain.EmployeeDetail;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.repository.SkillRepository;
import sef.interfaces.service.EmployeeDetailsService;

import org.apache.log4j.Logger;

public class StubEmployeeDetailsServiceImpl implements EmployeeDetailsService {

	// Tip: create member variables in this class that will contain the objects
	// passed by the Spring framework so that other methods can access the
	// objects.
	private EmployeeRepository er;
	private ProjectRepository pr;
	private SkillRepository sr;
	private static Logger log = Logger.getLogger(StubEmployeeDetailsServiceImpl.class);

	public StubEmployeeDetailsServiceImpl(EmployeeRepository empDAO, ProjectRepository projectDAO,
			SkillRepository skillDAO) {
		this.er = empDAO;
		this.pr = projectDAO;
		this.sr = skillDAO;
	}

	@Override
	public EmployeeDetail getEmployeeDetails(long employeeID) {

		EmployeeDetail detail = new EmployeeDetail();
		detail.setEmployee(er.findEmployeeByID(employeeID));
		detail.setSkillList(sr.findEmployeeSkills(employeeID));
		detail.setProjectList(pr.getEmployeeProjectHistory(employeeID));
		return detail;
	}
}
