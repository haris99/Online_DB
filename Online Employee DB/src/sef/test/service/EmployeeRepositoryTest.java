package sef.test.service;

import junit.framework.TestCase;

public class EmployeeRepositoryTest extends TestCase{
	
	EmployeeRepositoryTest runtest = new EmployeeRepositoryTest();
	
	public void testFindEmployeeByName () {
		runtest.testFindEmployeeByName ();
	}
	public void testFindEmployeeByProject () {
		runtest.testFindEmployeeByName();
	}
	public void testFindEmployeeByID () {
		runtest.testFindEmployeeByID();
	}
}
