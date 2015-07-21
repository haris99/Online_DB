package sef.impl.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.EmployeeSkill;
import sef.interfaces.repository.SkillRepository;

import org.apache.log4j.Logger;

public class StubSkillRepositoryImpl implements SkillRepository{

	//DataSource class encapsulates the driver, database url, username and 
	//password information.  The dataSource object is automatically created by 
	//the Spring framework and passed to the constructor therefore there's no need 
	//to instantiate the dataSource variable. A connection can be acquired by 
	//accessing the getConnection method of dataSource. 
	//
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.
		
	private static Logger log = Logger.getLogger(StubSkillRepositoryImpl.class);

	public StubSkillRepositoryImpl(DataSource dataSource){
	}

	@Override
	public List<EmployeeSkill> findEmployeeSkills(long employeeID) {

		List<EmployeeSkill> list = new ArrayList<EmployeeSkill>();
		return list;
	}



	
	
	


	

}
