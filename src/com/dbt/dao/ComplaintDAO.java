package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import com.dbt.data.Customer;
import com.dbt.data.Order;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class ComplaintDAO 
{
    
	public static List<Order> getAllOrderDetails(String oid)
	{
		List<Order> order = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			System.out.println("ComplaintDAO : Order ID - "+oid);
			String query="";
			con = DBConnection.getConnection();
		    
			if(oid!=null && oid!=""){
				query="select od._id as uid,c.name,c.mobile,c.email,p.name as pname,oi.quantity,oi.amount,cat.name as catagory,od.amount as `Total Amount`,DATE_FORMAT(od.time,'%Y-%m-%d') as `date`,DATE_FORMAT(od.time,'%H:%i:%s') as `time`,ad.house_no as `hno`,ad.line_1,ad.line_2,ad.city,ad.state,ad.zip from `order` as od,category cat,customer as c,order_item as oi,product as p, address as ad where cat._id=p.category and oi.order_id=od._id and od.cust_id=c.user_id and p._id=oi.product_id and ad.user_id=c.user_id and od._id='"+oid+"'";
			}
		    System.out.println("ComplaintDAO : Query - "+query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
		    //while(rs.next())
		    //{
		    	//Customer customer = new Customer(rs.getString("name"),rs.getString("mobile"),rs.getString("email"));
		    	
		    	
		    	//Order ord = new Order(rs.getInt("_id"),rs.getInt("amount"),rs.getString("date"),rs.getTime("time").toString(),customer);
		    	//order.add(ord);
		   // }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, rs);
		}
		
		return order;
	}
	
	
	public static List<Order> getOrderDetails(String name,String oid,String mob)
	{
		List<Order> order = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			//System.out.println("ComplaintDAO : Name - "+name);
			String query="";
			con = DBConnection.getConnection();
			if(name!=null && name!="" && oid!=null && oid!="" && mob!=null && mob!="")
			{		
				query="select o._id,o.amount,DATE_FORMAT(o.time,'%Y-%m-%d') as `date`,DATE_FORMAT(o.time,'%H:%i:%s') as `time`,c.name as `name`,c.mobile as `mobile`,c.email as `email` from `order` as o,customer as c where c.user_id=o.cust_id And c.name like '%"+name+"%' And o._id='"+oid+"' And c.mobile='"+mob+"' order by `date` ";				
		        //query="select od._id as uid,c.name,c.mobile,p.name as pname,oi.quantity,od.amount,cat.name as catagory from `order` as od,category cat,customer as c,order_item as oi,product as p where cat._id=p.category and oi.order_id=od._id and od.cust_id=c.user_id and p._id=oi.product_id and c.mobile='"+mob+"' and c.name like '%"+name+"%' and od._id='"+oid+"' group by uid";
			    
			}
			else if(name!=null && name!="" && oid!=null && oid!="")
			{
				query="select o._id,o.amount,DATE_FORMAT(o.time,'%Y-%m-%d') as `date`,DATE_FORMAT(o.time,'%H:%i:%s') as `time`,c.name as `name`,c.mobile as `mobile`,c.email as `email` from `order` as o,customer as c where c.user_id=o.cust_id And c.name like '%"+name+"%' And o._id='"+oid+"' order by `date` ";				
				//query="select od._id as uid,c.name,c.mobile,p.name as pname,oi.quantity,od.amount,cat.name as catagory from `order` as od,category cat,customer as c,order_item as oi,product as p where cat._id=p.category and oi.order_id=od._id and od.cust_id=c.user_id and p._id=oi.product_id and c.name like '%"+name+"%' and od._id='"+oid+"' group by uid";	
			}
			else if(name!=null && name!="" && mob!=null && mob!="")
			{
				query="select o._id,o.amount,DATE_FORMAT(o.time,'%Y-%m-%d') as `date`,DATE_FORMAT(o.time,'%H:%i:%s') as `time`,c.name as `name`,c.mobile as `mobile`,c.email as `email` from `order` as o,customer as c where c.user_id=o.cust_id And c.name like '%"+name+"%' And c.mobile='"+mob+"' order by `date` ";				
				//query="select od._id as uid,c.name,c.mobile,p.name as pname,oi.quantity,od.amount,cat.name as catagory from `order` as od,category cat,customer as c,order_item as oi,product as p where cat._id=p.category and oi.order_id=od._id and od.cust_id=c.user_id and p._id=oi.product_id and c.mobile='"+mob+"' and c.name like '%"+name+"%' group by uid";	
			}
			else if(oid!=null && oid!="" && mob!=null && mob!="")
			{
				query="select o._id,o.amount,DATE_FORMAT(o.time,'%Y-%m-%d') as `date`,DATE_FORMAT(o.time,'%H:%i:%s') as `time`,c.name as `name`,c.mobile as `mobile`,c.email as `email` from `order` as o,customer as c where c.user_id=o.cust_id And c.mobile='"+mob+"' and o._id='"+oid+"' order by `date` ";				
				//query="select od._id as uid,c.name,c.mobile,p.name as pname,oi.quantity,od.amount,cat.name as catagory from `order` as od,category cat,customer as c,order_item as oi,product as p where cat._id=p.category and oi.order_id=od._id and od.cust_id=c.user_id and p._id=oi.product_id and c.mobile='"+mob+"' and od._id='"+oid+"' group by uid";	
			}
			else if(name!=null && name!="")
			{
				query="select o._id,o.amount,DATE_FORMAT(o.time,'%Y-%m-%d') as `date`,DATE_FORMAT(o.time,'%H:%i:%s') as `time`,c.name as `name`,c.mobile as `mobile`,c.email as `email` from `order` as o,customer as c where c.user_id=o.cust_id And c.name like '%"+name+"%' order by `date` ";				
				//query="select od._id as uid,c.name,c.mobile,p.name as pname,oi.quantity,od.amount,cat.name as catagory from `order` as od,category cat,customer as c,order_item as oi,product as p where cat._id=p.category and oi.order_id=od._id and od.cust_id=c.user_id and p._id=oi.product_id and c.name like '%"+name+"%' group by uid";	
			}
			else if(mob!=null && mob!="")
			{
				query="select o._id,o.amount,DATE_FORMAT(o.time,'%Y-%m-%d') as `date`,DATE_FORMAT(o.time,'%H:%i:%s') as `time`,c.name as `name`,c.mobile as `mobile`,c.email as `email` from `order` as o,customer as c where c.user_id=o.cust_id And c.mobile='"+mob+"' order by `date` ";		
				//query="select od._id as uid,c.name,c.mobile,p.name as pname,oi.quantity,od.amount,cat.name as catagory from `order` as od,category cat,customer as c,order_item as oi,product as p where cat._id=p.category and oi.order_id=od._id and od.cust_id=c.user_id and p._id=oi.product_id and c.mobile='"+mob+"' group by uid";	
			}
			else if(oid!=null && oid!="")
			{
				query="select o._id,o.amount,DATE_FORMAT(o.time,'%Y-%m-%d') as `date`,DATE_FORMAT(o.time,'%H:%i:%s') as `time`,c.name as `name`,c.mobile as `mobile`,c.email as `email` from `order` as o,customer as c where c.user_id=o.cust_id And o._id='"+oid+"' order by `date` ";
				//query="select od._id as uid,c.name,c.mobile,p.name as pname,oi.quantity,od.amount,cat.name as catagory from `order` as od,category cat,customer as c,order_item as oi,product as p where cat._id=p.category and oi.order_id=od._id and od.cust_id=c.user_id and p._id=oi.product_id and od._id='"+oid+"' group by uid";	
			}
			else 
			{
				return order;
			}
			
		    //System.out.println("ComplaintDAO : Query - "+query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
		    while(rs.next())
		    {
		    	Customer customer = new Customer(rs.getString("name"),rs.getString("mobile"),rs.getString("email"));
		    	Order ord = new Order(rs.getInt("_id"),rs.getInt("amount"),rs.getString("date"),rs.getTime("time").toString(),customer);
		    	order.add(ord);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, rs);
		}
		
		return order;
	}
	
}
