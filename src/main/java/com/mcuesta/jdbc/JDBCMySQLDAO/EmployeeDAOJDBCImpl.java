package com.mcuesta.jdbc.JDBCMySQLDAO;

import java.security.PublicKey;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

import model.Employee;

public class EmployeeDAOJDBCImpl implements EmployeeDAO {
	private Connection con = null;
	CallableStatement addStmt;
	CallableStatement updateStmt;
	CallableStatement deleteStmt;
	PreparedStatement findByIdStmt;
	CallableStatement selectStmt;
	//cs.setNull(6, TYPES.FLOAT) si es voll afegir null a un cs.
	public EmployeeDAOJDBCImpl() {
		String username = "root", password = "", url = "jdbc:mysql://localhost:3306/employeedb";
		try { 
			con = DriverManager.getConnection(url, username, password);
			addStmt = con.prepareCall("{call afegir_empleat(?,?,?,?,?)}");
			updateStmt = con.prepareCall("{call actualitzar_empleat(?,?,?,?,?)}");
			deleteStmt = con.prepareCall("{call eliminar_empleat(?)}");
			selectStmt = con.prepareCall("{call seleccionar_empleats(?)}");
			String queryFindById = "SELECT * "
								 + "FROM EMPLOYEE "
								 + "WHERE ID = '?'";
			findByIdStmt = con.prepareStatement(queryFindById);
		}
		catch (SQLException e) {
			System.out.println("Error obtaining connection with the database: " + e);
			System.exit(-1);
		}
		
	}
	
	public void add(Employee emp) throws DAOException{
		try {
			addStmt.setInt(1, emp.getId());
			addStmt.setString(2, emp.getFirstName());
			addStmt.setString(3, emp.getLastName());
			addStmt.setDate(4, emp.getBirthDate());
			addStmt.setFloat(5, emp.getSalary());
			addStmt.execute();
		} catch (SQLException e) {
			System.out.println("Error al afegir un empleat.");
			//e.printStackTrace();
		}
	}
	
	public void update(Employee emp) throws DAOException{
		try {
			updateStmt.setString(1, emp.getFirstName());
			updateStmt.setString(2, emp.getLastName());
			updateStmt.setDate(3, new java.sql.Date(emp.getBirthDate().getTime()));
			updateStmt.setFloat(4, emp.getSalary());
			updateStmt.setInt(5, emp.getId());
			updateStmt.execute();
		}
		catch (SQLException e) {
			System.out.println("Error updating employee: " + e);
			System.exit(-1);
		}
	}
	
	public void delete(int id) throws DAOException{
		try {
			deleteStmt.setInt(1, id);
			deleteStmt.execute();
		}
		catch (SQLException e) {
			System.out.println("Error deleting employee: " + e);
			System.exit(-1);
		}
	}
	
	@SuppressWarnings("finally")
	public Employee findById(int id) throws DAOException{
		ResultSet rs = null;
		Employee emp = null;
		try {
			findByIdStmt.setInt(1, id);
			rs = findByIdStmt.executeQuery();
			emp.setId(rs.getInt(1));
			emp.setFirstName(rs.getString(2));
			emp.setLastName(rs.getString(3));
			emp.setBirthDate(rs.getDate(4));
			emp.setSalary(rs.getFloat(5));
		} catch (SQLException e) {
			System.out.println("Error finding employee by id: " + e);
			System.exit(-1);
		}
		finally {
			return emp;
		}
	}
	
	@SuppressWarnings("finally")
	public Employee[] getAllEmployees() throws DAOException{
		ResultSet rs = null;
		Employee[] allEmps = null;
		Employee e = null;
		ArrayList<Employee> employees = null;
		try {
			rs = findByIdStmt.executeQuery();
			employees = new ArrayList<Employee>();
			while (rs.next()) {
				e = new Employee();
				e.setId(rs.getInt(1));
				e.setFirstName(rs.getString(2));
				e.setLastName(rs.getString(3));
				e.setBirthDate(rs.getDate(4));
				e.setSalary(rs.getFloat(5));
				employees.add(e);
			}
			allEmps = new Employee[0];
			employees.toArray(allEmps);
		} catch (SQLException se) {
			System.out.println("Error finding employee by id: " + se);
			System.exit(-1);
		}
		finally {
			return allEmps;
		}
	}
	
	public void close() {
		try {
			con.close();
		}
		catch (SQLException e) {
			System.out.println("Exception closing Connection: " + e);
		}
	}
}
