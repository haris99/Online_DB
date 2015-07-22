package sef.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import junit.framework.TestCase;
import sef.domain.EmployeeSkill;
import sef.impl.repository.StubSkillRepositoryImpl;

public class SkillRepositoryTest extends TestCase{

	//SkillRepositoryTest runtest = new SkillRepositoryTest ();
	
	public void testFindEmployeeSkills () {
		//runtest.TestfindEmployeeSkills();
		List<EmployeeSkill> skillList = new ArrayList<EmployeeSkill>();
		//NEED TO GET DATASOURCE SOMEHOW
		DataSource dataSource = (DataSource)ctx.lookup(dataSource);
		StubSkillRepositoryImpl repo = new StubSkillRepositoryImpl(DataSource dataSource);
		skillList = repo.findEmployeeSkills(1);
		//should be an employee with id 1 and skills
		assertTrue(skillList.size()>=1);
		skillList = new ArrayList<EmployeeSkill>();
		//there should be no employee with id 999
		skillList = repo.findEmployeeSkills(999);
		assertTrue(skillList.size()==0);
	}
}
