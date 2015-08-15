package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbt.data.Address;
import com.dbt.data.Merchant;
import com.dbt.database.DBConnection;
import com.dbt.exception.NoConnectionException;
import com.dbt.support.Email;

public class MerchantDAO {

	public boolean addMerchant(String name, String mobile, String email,
			String tin, String type) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		int userId = 0;
		boolean result = false;
		System.out.println("addMerchant(), Merchant Name : " + name);
		try {
			String[] names = name.split(" ");
			String firstName = names[0];
			String lastName = "";

			for (int i = 1; i < names.length; i++)
				lastName = lastName + " " + names[i];

			con = DBConnection.getConnection();

			String insertQuery = "insert into user(first_name, last_name, email, mobile, type) "
					+ "values(?, ?, ?, ?, ?)";

			ps = con.prepareStatement(insertQuery);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			ps.setString(4, mobile);
			ps.setString(5, type);

			int res = ps.executeUpdate();
			ps.close();
			if (res == 1) {
				String selectQuery = "select _id from user where email = ? and type = ?";
				ps = con.prepareStatement(selectQuery);
				ps.setString(1, email);
				ps.setString(2, type);

				rst = ps.executeQuery();

				if (rst.next())
					userId = rst.getInt("_id");

				String insertMerchant = "insert into merchant(_id, merchant_name, mobile, email, tin)"
						+ " values(?, ?, ?, ?, ?)";

				ps = con.prepareStatement(insertMerchant);
				ps.setInt(1, userId);
				ps.setString(2, name);
				ps.setString(3, mobile);
				ps.setString(4, email);
				ps.setString(5, tin);

				if (ps.executeUpdate() == 1)
					result = true;
				else
					result = false;
				ps.close();
			} else {
				System.out
						.println("MechantDAO : Error while inserting into user table");
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, ps, rst);
		}
		return result;
	}

	public static List<Merchant> getAllMerchants() {
		List<Merchant> merchants = new ArrayList<Merchant>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		try {
			con = DBConnection.getConnection();
			String sql = "select * from merchant";
			ps = con.prepareStatement(sql);
			rst = ps.executeQuery();
			while (rst.next()) {
				merchants.add(new Merchant(rst.getInt("_id"), rst
						.getString("merchant_name"), null,
						rst.getString("tin"), rst.getString("mobile"), rst
								.getString("email"), null));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, ps, rst);
		}

		System.out.println("Number of merchants : " + merchants.size());

		return merchants;
	}

	public List<Merchant> getFirms(String mobiles) {
		List<Merchant> merchants = new ArrayList<Merchant>();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;

		try {
			con = DBConnection.getConnection();
			String sql = "select * from merchant join address on merchant._id = address.user_id where merchant.mobile in ("
					+ mobiles + ")";
			// System.out.println("Query : " + sql);
			stmt = con.prepareStatement(sql);
			set = stmt.executeQuery();

			while (set.next()) {
				Address address = new Address(set.getString("house_no"),
						set.getString("line_1"), set.getString("line_2"),
						set.getString("city"), set.getString("state"),
						set.getString("zip"));
				Merchant merchant = new Merchant(set.getInt("_id"),
						set.getString("merchant_name"), address,
						set.getString("tin"), set.getString("mobile"),
						set.getString("email"), set.getString("logo"));
				merchants.add(merchant);
			}
		
		} catch (Exception ex) {
			Email.sendExceptionReport(ex);

		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return merchants;
	}
}
