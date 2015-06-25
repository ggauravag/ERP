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
	public List<Merchant> getFirms(String mobiles) {
		List<Merchant> merchants = new ArrayList<Merchant>();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet set = null;

		try {
			con = DBConnection.getConnection();
			String sql = "select * from merchant join address on merchant._id = address.user_id where merchant.mobile in ("
					+ mobiles + ")";
			//System.out.println("Query : " + sql);
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
		} catch (NoConnectionException ex) {
			Email.sendExceptionReport(ex);
			ex.printStackTrace();
		} catch (SQLException ex) {
			Email.sendExceptionReport(ex);
			ex.printStackTrace();
		} finally {
			DBConnection.closeResource(con, stmt, set);
		}

		return merchants;
	}
}
