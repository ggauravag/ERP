package com.dbt.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.CustomerDAO;
import com.dbt.dao.OrderDAO;
import com.dbt.data.Address;
import com.dbt.data.Customer;
import com.dbt.data.Order;
import com.dbt.data.Product;
import com.dbt.forms.OrderForm;
import com.dbt.support.Email;

public class OrderAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "failure";
		System.out.println("OrderAction : Called");
		OrderForm orderform = (OrderForm) form;

		String method = request.getParameter("confirm");
		System.out.println("Method is : "+method);
		try 
		{
			if (method == null) 
			{
				String name = orderform.getName();
				String email = orderform.getEmail();
				String house = orderform.getHouse();
				String line1 = orderform.getLine1();
				String line2 = orderform.getLine2();
				String mobile = orderform.getMobile();
				String city = orderform.getCity();
				String state = orderform.getState();
				String zip = orderform.getZip();
				String type = orderform.getType();
				String tin = orderform.getTin();
				int cid = -1;
				if (orderform.getCustID() != null)
					cid = Integer.parseInt(orderform.getCustID());

				Address address = new Address(house, line1, line2, city, state,
						zip);

				Customer cust = new Customer(cid, name, mobile, email, address,
						type, tin);

				int numProducts = Integer.parseInt(orderform.getNumProd());

				String[] prodName = orderform.getProductNames();
				String[] prices = orderform.getProductPrices();
				String[] qtys = orderform.getProductQtys();
				List<Product> products = new ArrayList<Product>();
				int amount = 0;

				for (int i = 0; i < numProducts; i++) 
				{
					String[] props = prodName[i].split(" : ");
					amount += Integer.parseInt(qtys[i]) * Integer.parseInt(prices[i]);
					products.add(new Product(Integer.parseInt(props[1]), 0,props[0], Integer.parseInt(qtys[i]), Integer.parseInt(prices[i]), 0));
				}
				request.setAttribute("orderAmount", amount);
				
				String dateTime = orderform.getOrderTime();

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
				Date date = null;
				
				if (!"".equals(dateTime) && dateTime != null) 
				{
					try 
					{
						date = formatter.parse(dateTime);
						System.out.println("Valid Date : "+dateTime);
					} 
					catch (Exception e) 
					{
						Email.sendExceptionReport(e);
						date = new Date();
					}
				}
				else 
				{
					System.out.println("Invalid date : "+dateTime);
					date = new Date();
				}
				
				Order order = new Order(cust, products, 0, date, amount);
				request.getSession().setAttribute("order", order);
				if ("MERCHANT".equals(orderform.getCustType())) 
				{
					order.getCustomer().setType("MERCHANT");
					request.setAttribute("needTin", true);
				} 
				else
				{
					order.getCustomer().setType("CUSTOMER");
					request.setAttribute("needTin", false);
				}

				result = "confirm";
				
			} 
			else if ("submit".equals(method)) 
			{
				Order order = (Order) request.getSession().getAttribute("order");

				if (order == null) 
				{
					result = "failure";
				} 
				else 
				{
					int cid = order.getCustomer().getId();
					System.out.println("OrderAction - Submit Clicked, CID is : "+ cid);
					if (cid == -1) 
					{
						if (order.getCustomer().getType().equals("MERCHANT")) 
						{
							String tinNumber = request.getParameter("tinNumber");
							System.out.println("OrderAction - Submit : tinNumber = "+tinNumber);
							order.getCustomer().setTin(tinNumber);
							cid = new CustomerDAO().insertMerchant(order.getCustomer());

							if (cid != 0)
							{
								result = "success";
							} 
							else 
							{
								result = "failure";
								request.setAttribute("status", false);
							}
						} 
						else 
						{
							cid = new CustomerDAO().insertCustomer(order.getCustomer());
							if (cid != 0) 
							{
								result = "success";
							} 
							else 
							{
								result = "failure";
								request.setAttribute("status", false);
							}
						}
					} 
					else 
					{
						result = "success";
					}

					System.out.println("OrderAction - Submit Clicked, result is : "+ result);
					if ("success".equals(result)) 
					{
						order.getCustomer().setId(cid);
						boolean isOrderTaken = OrderDAO.takeOrder(order);
						if (!isOrderTaken) 
						{
							result = "failure";
							request.setAttribute("status", false);
						} 
						else 
						{
							request.setAttribute("order", order);
						}
					}
				}
			} 
			else if ("reset".equals(method)) 
			{
				Order order = (Order) request.getSession().getAttribute("order");
				request.setAttribute("order", order);
				result = "modify";
			}
		} 
		catch (Exception e) 
		{
			request.setAttribute("status", false);
			Email.sendExceptionReport(e);
		}

		return mapping.findForward(result);
	}
}
