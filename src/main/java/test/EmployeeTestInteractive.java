package test;

import java.util.Date;

import com.mcuesta.jdbc.JDBCMySQLDAO.EmployeeDAO;
import com.mcuesta.jdbc.JDBCMySQLDAO.EmployeeDAOFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Employee;

public class EmployeeTestInteractive {
	public static void mian( String[] args ) {
		try {
			EmployeeDAOFactory factory = new EmployeeDAOFactory();
			EmployeeDAO dao = factory.createEmployeeDAO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
			String dateString = "200-09-15";
			Date d = sdf.parse(dateString);
			Employee e = new Employee();
			dao.add(e);
			
			Employee[] allEmps = dao.getAllEmployees();
			for(Employee employee : allEmps) System.out.println(employee + "\n");
			
			dao.delete(212);
			allEmps = dao.getAllEmployees();
			for(Employee emp : allEmps) System.out.println(emp + "\n");
			
			Employee employee = dao.findById(101);
			System.out.println(employee);
			
			employee.setSalary(90000);
			dao.update(employee);
			
			allEmps = dao.getAllEmployees();
			for(Employee emp : allEmps) System.out.println(emp + "\n");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
