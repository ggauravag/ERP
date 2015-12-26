package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import com.dbt.data.Category;
import com.dbt.data.Merchant;
import com.dbt.data.Product;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class StockDAO {

	public List<Category> getAllCategory() {
		List<Category> categories = new OrderDAO().getAllCategory();
		System.out.println("Number of categories at StockDAO : "
				+ categories.size());
		return categories;
	}

	public List<Product> getProducts(int catId) {
		List<Product> products = new OrderDAO().getProducts(catId);
		System.out.println("Number of products at StockDAO : "
				+ products.size());
		return products;
	}

	public List<Merchant> getAllMerchants() {
		List<Merchant> merchants = MerchantDAO.getAllMerchants();
		System.out.println("Number of merchants at StockDAO : "
				+ merchants.size());
		return merchants;
	}

	public boolean addNewCategory(String catName) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;
		System.out.println("addNewCategory(), catname : " + catName);
		try {
			con = DBConnection.getConnection();

			String query = "insert into category(name) values(?)";
			ps = con.prepareStatement(query);
			ps.setString(1, WordUtils.capitalizeFully(catName));

			ps.executeUpdate();

			result = true;
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, ps, null);
		}
		return result;
	}

	public boolean addNewProduct(int catId, String prodName, int qty, int sp,
			int cp) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;
		System.out.println("addNewProduct(), Product Name : "
				+ WordUtils.capitalizeFully(prodName));
		try {
			con = DBConnection.getConnection();

			String query = "insert into product(category, name, quantity, sell_price, cost_price) "
					+ "values(?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, catId);
			ps.setString(2, WordUtils.capitalizeFully(prodName));
			ps.setInt(3, qty);
			ps.setInt(4, sp);
			ps.setInt(5, cp);

			ps.executeUpdate();

			result = true;
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, ps, null);
		}
		return result;
	}

	public int addTransaction(int amount, String mode, String desc,
			String paid, Connection con) {
		int txId = new ExpenditureDAO().addTransaction(amount, mode, desc,
				paid, con);

		return txId;
	}

	public int addExpenditure(int txId, Connection con) {
		int expId = new ExpenditureDAO().addExpenditure(txId, con);

		return expId;
	}

	public int addPurchase(int merchantId, int amount, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		int purchaseId = 0;

		String query = "insert into purchase(date, merchant_id,amount)"
				+ " values(CURDATE(), ?,?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, merchantId);
			ps.setInt(2, amount);
			int res = ps.executeUpdate();

			if (res == 1) {
				String idQuery = "select _id from purchase where merchant_id = ? order by _id desc";
				ps.close();
				ps = con.prepareStatement(idQuery);
				ps.setInt(1, merchantId);

				rst = ps.executeQuery();

				if (rst.next())
					purchaseId = rst.getInt("_id");
			} else {
				purchaseId = 0;
				System.out.println("StockDAO : Error in updating purchase");
			}

			System.out.println("Purchase ID : " + purchaseId);

			return purchaseId;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}
		return purchaseId;
	}

	public boolean addPurchaseExpenditure(int purchaseId, int expenditureId,
			Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		boolean result = false;
		String query = "insert into purchase_expenditure(expenditure_id, purchase_id)"
				+ " values(?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, expenditureId);
			ps.setInt(2, purchaseId);

			int res = ps.executeUpdate();

			if (res == 1) {
				System.out.println("Purchase Expenditure added");
				result = true;
			} else {
				System.out.println("Unable to add purchase expenditure");
				result = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}
		return result;
	}

	public int updateProductStock(int productId, int categoryId, int qty,
			int cp, int sp, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		int result = 0;

		String insertQuery = "update product set quantity = quantity + ?, sell_price = ?, cost_price = ? "
				+ "where _id = ?";
		try {
			ps = con.prepareStatement(insertQuery);
			ps.setInt(1, qty);
			ps.setInt(2, sp);
			ps.setInt(3, cp);
			ps.setInt(4, productId);

			result = ps.executeUpdate();

			if (result == 1) {
				System.out.println("StockDAO : Product Stock Updated");
				result = productId;
			} else {
				System.out
						.println("StockDAO : Error in updating product stock");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}
		return result;
	}

	public boolean addPurchaseItem(int purchaseId, int productId, int quantity,
			int amount, Connection con) {
		PreparedStatement ps = null;
		ResultSet rst = null;
		boolean result = false;

		String query = "insert into purchase_item(purchase_id, product_id, quantity, amount)"
				+ " values(?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, purchaseId);
			ps.setInt(2, productId);
			ps.setInt(3, quantity);
			ps.setInt(4, amount);

			int res = ps.executeUpdate();

			if (res == 1) {
				System.out.println("Purchase Item added");
				result = true;
			} else {
				System.out.println("Unable to add purchase item");
				result = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Email.sendExceptionReport(e);

		} finally {
			DBConnection.closeResource(null, ps, rst);
		}
		return result;
	}
	
	public boolean addStock(int merchantId,int orderAmount, List<Product> products)
	{
		boolean result = false;
		Connection con = null;
		try 
		{
			con = DBConnection.getConnection();
			int prodId = 0, purchaseId;
			purchaseId = addPurchase(merchantId, orderAmount, con);
			Iterator<Product> iter = products.iterator();
			while (iter.hasNext()) 
			{
				Product prod = iter.next();
	
				prodId = updateProductStock(prod.getId(), prod.getCategory(),
						prod.getQuantity(), prod.getCostPrice(),
						prod.getSellPrice(), con);
				addPurchaseItem(purchaseId, prodId, prod.getQuantity(),
						prod.getCostPrice() * prod.getQuantity(), con);
			}
			result = true;
		}
		catch(Exception e)
		{
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, null, null);
		}
		return result;
	}

	public boolean purchaseStock(int merchantId, int orderAmount,
			int currentPay, String payMode, String desc, String paidTo,
			List<Product> products) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		boolean result = false;
		int prodId = 0, txId, expId, purchaseId;

		try {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);

			txId = addTransaction(currentPay, payMode, desc, paidTo, con);
			expId = addExpenditure(txId, con);
			purchaseId = addPurchase(merchantId, orderAmount, con);
			boolean purExp = addPurchaseExpenditure(purchaseId, expId, con);

			Iterator<Product> iter = products.iterator();
			while (iter.hasNext()) 
			{
				Product prod = iter.next();
				prodId = updateProductStock(prod.getId(), prod.getCategory(),
						prod.getQuantity(), prod.getCostPrice(),
						prod.getSellPrice(), con);
				addPurchaseItem(purchaseId, prodId, prod.getQuantity(),
						prod.getCostPrice() * prod.getQuantity(), con);
			}

			if (txId == 0 || expId == 0 || purchaseId == 0 || purExp == false
					|| prodId == 0) {
				con.rollback();
				System.out.println("Unable to purchase new stock.");
			} else {
				con.commit();
				System.out.println("Stock Purchased successfully");
				result = true;
			}
			con.setAutoCommit(true);

		} catch (Exception ex) {
			Email.sendExceptionReport(ex);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
		return result;
	}
}
