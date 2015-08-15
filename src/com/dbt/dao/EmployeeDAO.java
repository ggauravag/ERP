package com.dbt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Address;
import com.dbt.data.Employee;
import com.dbt.data.User;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class EmployeeDAO {

	public int updateEmployeeDetails(int eid, String name, String mobile,
			String doj, String line1, String line2, String house, String city,
			String state, String zip, int salary, String role, String path) {
		int result = 0;
		int res = 0;
		int rs = 0;
		int sendResult = 0;
		System.out.println("Employee DAO called for update");

		Connection con = null;
		PreparedStatement stmt = null, stmt1 = null, stmt2 = null;
		java.text.DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			con = DBConnection.getConnection();
			System.out.println("update `user` set mobile=? wher");
			stmt = con
					.prepareStatement("update `user` set mobile=? where _id=?");
			stmt.setString(1, mobile);
			stmt.setInt(2, eid);
			result = stmt.executeUpdate();
			stmt.close();
			System.out.println("Result is:" + result);

			stmt1 = con
					.prepareStatement("update employee set salary=?,proofs=? where employee_id=?");
			stmt1.setInt(1, salary);
			stmt1.setString(2, path);
			stmt1.setInt(3, eid);
			res = stmt1.executeUpdate();
			stmt1.close();
			System.out.println("Res is:" + res);

			stmt2 = con
					.prepareStatement("update `address` set house_no=?,line_1=?,line_2=?,city=?,state=?,zip=? where user_id=?");
			stmt2.setString(1, house);
			stmt2.setString(2, line1);
			stmt2.setString(3, line2);
			stmt2.setString(4, city);
			stmt2.setString(5, state);
			stmt2.setString(6, zip);
			stmt2.setInt(7, eid);
			rs = stmt2.executeUpdate();
			stmt2.close();
			System.out.println("Rs is:" + rs);

			con.close();
			if (result == 1 && res == 1 && rs == 1) {
				sendResult = 1;
			} else {
				sendResult = 0;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		System.out.println("EmployeeDAO: Data updated Type Other");

		return sendResult;
	}

	public int insertEmployeeOther(String fname, String lname, String email,
			String mobile, String salary, String doj, String house,
			String line1, String line2, String city, String state, String zip) {
		System.out.println("Employee DAO called of OTHER");
		int result = 0;
		Connection con = null;
		CallableStatement stmt = null;
		java.text.DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			con = DBConnection.getConnection();
			if ("".equals(email)) {
				email = null;
			}

			stmt = con
					.prepareCall("call insertEmpOther(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setString(3, email);
			stmt.setString(4, mobile);
			stmt.setInt(5, Integer.parseInt(salary));
			stmt.setDate(6, new java.sql.Date(df.parse(doj).getTime()));
			stmt.registerOutParameter(7, Types.INTEGER);
			stmt.setString(8, house);
			stmt.setString(9, line1);
			stmt.setString(10, line2);
			stmt.setString(11, city);
			stmt.setString(12, state);
			stmt.setString(13, zip);
			stmt.execute();
			result = stmt.getInt(7);

			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		System.out.println("EmployeeDAO: Data inserted Type Other");
		return result;
	}

	public int insertPhoto(int id, String path) {
		int result = 0;
		System.out.println("Insert Photo called,Path is:" + path);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("update user_login set photo=? where user_id=?");
			ps.setString(1, path);
			ps.setInt(2, id);
			result = ps.executeUpdate();
			con.close();
			ps.close();
		} catch (Exception e) {

			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, ps, null);
		}

		return result;
	}

	public int insertEmployee(String fname, String lname, String email,
			String mobile, String salary, String doj, String role, String pass,
			String ip, String stime, String etime, String house, String line1,
			String line2, String city, String state, String zip) {
		System.out.println("Employee DAO insertEmployee called");
		int result = 0;
		Connection con = null;
		CallableStatement stmt = null;
		java.text.DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		java.text.DateFormat formatter = new SimpleDateFormat("hh:mm aa");

		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareCall("call insertEmpLog(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setString(3, email);
			stmt.setString(4, mobile);
			stmt.setInt(5, Integer.parseInt(salary));
			stmt.setDate(6, new java.sql.Date(df.parse(doj).getTime()));
			stmt.setString(7, role);
			stmt.setString(8, pass);
			stmt.setString(9, ip);
			stmt.setTime(10,
					new java.sql.Time(formatter.parse(stime).getTime()));
			stmt.setTime(11,
					new java.sql.Time(formatter.parse(etime).getTime()));
			stmt.setString(12, house);
			stmt.setString(13, line1);
			stmt.setString(14, line2);
			stmt.setString(15, city);
			stmt.setString(16, state);
			stmt.setString(17, zip);
			stmt.registerOutParameter(18, Types.INTEGER);

			stmt.execute();
			result = stmt.getInt(18);
			stmt.close();
			con.close();
		} catch (Exception e) {

			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		System.out.println("Result is:" + result);
		return result;
	}

	public List<Employee> getEmployeeDetails(String name) {
		List<Employee> emp = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			con = DBConnection.getConnection();
			System.out.println("EmployeeDAO called: Name:" + name);
			String query = "select u.first_name,u.last_name,u.email,u.mobile,e.date_of_join,e.salary,e.role,e.employee_id,a.house_no,a.line_1,a.line_2,a.city,a.state,a.zip from `user` u,employee e,address a where u._id=e.employee_id and e.status='ACTIVE' and a.user_id=u._id and e.date_of_leave is null and concat(u.first_name,' ',u.last_name) like '%"
					+ name + "%' order by e.employee_id";

			stmt = con.prepareStatement(query);
			set = stmt.executeQuery();
			System.out.println("EmployeeDAO: Query is:" + query);
			while (set.next()) {
				User user = new User(set.getString("first_name"),
						set.getString("last_name"), set.getString("email"),
						set.getString("mobile"));
				Address address = new Address(set.getString("house_no"),
						set.getString("line_1"), set.getString("line_2"),
						set.getString("city"), set.getString("state"),
						set.getString("zip"));
				Employee employee = new Employee(set.getInt("employee_id"),
						set.getDate("date_of_join"), set.getString("role"),
						set.getInt("salary"), user, address);
				emp.add(employee);
			}

		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}
		System.out.println("EmployeeDAO: Data successfully fetched.");

		return emp;
	}

	public List<Employee> getEmpDetails(String name) {
		List<Employee> emp = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			con = DBConnection.getConnection();
			System.out.println("EmployeeDAO called: Name:" + name);
			String query = "select u.first_name,u.last_name,u.email,u.mobile,e.date_of_join,e.salary,e.employee_id from `user` u,employee e where u._id=e.employee_id and e.status='ACTIVE' and e.date_of_leave is null and concat(u.first_name,' ',u.last_name) like '%"
					+ name + "%' order by e.employee_id";

			stmt = con.prepareStatement(query);
			set = stmt.executeQuery();
			System.out.println("EmployeeDAO: Query is:" + query);
			while (set.next()) {
				User user = new User(set.getString("first_name"),
						set.getString("last_name"), set.getString("email"),
						set.getString("mobile"));
				Employee employee = new Employee(set.getInt("employee_id"),
						set.getDate("date_of_join"), set.getInt("salary"), user);
				emp.add(employee);
			}

			System.out.println("Data inserted successfully");
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}
		System.out.println("EmployeeDAO: Data successfully fetched.");

		return emp;
	}

	public List<User> getEmployee() {
		List<User> employees = new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		try {
			System.out.println("In EmployeeDAO");
			con = DBConnection.getConnection();
			String query = "select _id, first_name, last_name, mobile from user where type = ?";
			// System.out.println("CustomerDAO : Query - "+query);
			ps = con.prepareStatement(query);
			ps.setString(1, "EMPLOYEE");

			rst = ps.executeQuery();

			while (rst.next()) {
				User employee = new User();
				employee.setId(rst.getInt("_id"));
				employee.setFirstName(rst.getString("first_name"));
				employee.setLastName(rst.getString("last_name"));
				employee.setMobile(rst.getString("mobile"));
				employees.add(employee);
			}
		} catch (Exception e) {

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, ps, rst);
		}

		return employees;
	}

}
