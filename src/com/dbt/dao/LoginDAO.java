package com.dbt.dao;

import static java.lang.System.out;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Privilege;
import com.dbt.data.User;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;
import com.dbt.support.Utils;

public class LoginDAO {

	public static boolean isSubMenu(String curPage, String menu) {
		boolean isSubMenu = false;
		// System.out.println("LoginDAO, URI : "+curPage+", Menu : "+menu);
		String nMenu = menu.toLowerCase().replace('s', '\u0000');
		if (curPage.toLowerCase().contains(nMenu)
				|| curPage.toLowerCase().contains(menu.toLowerCase()))
			isSubMenu = true;
		return isSubMenu;
	}

	public static List<Privilege> getPrivileges(String type) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<Privilege> privs = new ArrayList<Privilege>();
		try {
			con = DBConnection.getConnection();
			String sql = "select * from privilege where mainmenu is null and user_type like '%"
					+ type + "%' order by _id";
			stmt = con.prepareStatement(sql);
			res = stmt.executeQuery();
			while (res.next()) {
				Privilege priv = new Privilege();
				priv.setId(res.getInt("_id"));
				priv.setName(res.getString("name"));
				priv.setPath(res.getString("path"));
				priv.setIconClass(res.getString("icon"));
				String query = "select * from privilege where mainmenu = ? and user_type like '%"
						+ type + "%' order by _id";

				PreparedStatement stmt1 = con.prepareStatement(query);
				stmt1.setInt(1, res.getInt("_id"));
				ResultSet set = stmt1.executeQuery();
				while (set.next()) {
					Privilege subpriv = new Privilege(null,
							set.getString("name"), set.getString("path"),
							set.getInt("_id"), type, null);
					priv.subprivs.add(subpriv);
				}
				privs.add(priv);
				set.close();
				stmt1.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return privs;
	}

	public String isValidToken(String authToken, String newPassword) {
		String name = null;
		Connection con = null;
		CallableStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{Call ResetPassword(?,?,?)}");
			stmt.setString(1, authToken);
			stmt.setString(2, newPassword);
			stmt.registerOutParameter(3, Types.VARCHAR);
			stmt.execute();
			name = stmt.getString(3);
		} catch (Exception e) {
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		return name;
	}

	public void resetPassword(String email, String hashToken) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("insert into password_reset values((select user_id from user_login where email = ?),?,DATE_ADD(now(),interval 24 hour),FALSE);");
			stmt.setString(1, email);
			stmt.setString(2, hashToken);
			System.out
					.println("Email & HashToken : " + email + "," + hashToken);
			stmt.executeUpdate();
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block

			Email.sendExceptionReport(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

	}

	public boolean isRegisteredEmail(String email) {
		boolean isValid = false;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select * from user_login where email = ?");
			stmt.setString(1, email);
			res = stmt.executeQuery();
			if (res.next()) {
				isValid = true;
			}
		} catch (Exception e) {
			
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return isValid;
	}

	public User getOwner() {
		User owner = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;

		try {
			con = DBConnection.getConnection();
			String sql = "select first_name,last_name, mobile, u.email from user_login ul join user u on ul.user_id = u._id where ul.type = 'OWNER';";
			stmt = con.prepareStatement(sql);
			set = stmt.executeQuery();
			if (set.next()) {
				owner = new User();
				owner.setFirstName(set.getString("first_name"));
				owner.setLastName(set.getString("last_name"));
				owner.setMobile(set.getString("mobile"));
				owner.setEmail(set.getString("email"));
			}

		} catch (Exception e) {
			
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return owner;
	}

	public void updateLogIp(User user, String logip) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			String sql = "update user_login set log_ip = ? where user_id = ?";
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, logip);
			stmt.setInt(2, user.getId());
			int result = stmt.executeUpdate();
			if (result == 1) {
				out.println("Last Log IP updated to : " + logip);
			} else {
				out.println("Failed to update logip to : " + logip);
			}

		} catch (Exception e) {
			
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}
	}

	public User login(String email, String password) {
		User user = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			String sql = "select _id,first_name,last_name,mobile,status,photo,user_login.type,log_ip,st_time,end_time from user join user_login on user._id = user_login.user_id where user_login.email = ? and user_login.password = ?;";
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email.trim().toLowerCase());
			stmt.setString(2, Utils.getMD5(password.trim()));
			set = stmt.executeQuery();
			if (set.next()) {
				user = new User(set.getInt("_id"), set.getString("first_name"),
						set.getString("last_name"), email,
						set.getString("mobile"), password,
						set.getString("status"), set.getString("type"),
						set.getString("log_ip"), set.getTime("st_time"),
						set.getTime("end_time"));
				user.setPhoto(set.getString("photo"));
			}

		} catch (Exception e) {
			
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return user;
	}

}
