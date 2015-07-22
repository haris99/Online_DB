package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	private DataSource dataSource;
	
	public StubSkillRepositoryImpl(DataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public List<EmployeeSkill> findEmployeeSkills(long employeeID) {

		List<EmployeeSkill> resultList = new ArrayList<EmployeeSkill>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE_SKILL S "
					+ "WHERE S.employee_detail_id = ?;");
			ps.setLong(1, employeeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				EmployeeSkill empSkill = new EmployeeSkill();
				empSkill.setID(rs.getLong("id"));
				empSkill.setName(rs.getString("name"));
				empSkill.setDescription(rs.getString("description"));
				empSkill.setRating(rs.getInt("rating"));
				
				resultList.add(empSkill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return resultList;
	}



	
	
	


	

}
