package com.mcuesta.jdbc.JDBCMySQLDAO;

public class EmployeeDAOFactory {
	public EmployeeDAO createEmployeeDAO() {
		return new EmployeeDAOJDBCImpl();
	}
}
