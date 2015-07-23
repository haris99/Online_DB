package sef.test.service;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(EmployeeDetailsServiceTest.class);
		suite.addTestSuite(SearchEmployeeTest.class);
		suite.addTestSuite(StubProjectRepositoryImplTest.class);
		suite.addTestSuite(TestSearchEmployeeControler.class);
		//$JUnit-END$
		return suite;
	}

}
