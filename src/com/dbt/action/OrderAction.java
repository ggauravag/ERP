package com.dbt.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.sun.scenario.effect.impl.prism.PrDrawable;

public class OrderAction extends Action {

	private boolean isMerchant(String name) {

		name = name.toLowerCase();
		boolean isMerchant = false;
		String[] names = { "enterprise", "trading", "co.", "company",
				"furniture", "market", "store", "service", "sons" };

		for (String each : names) {
			if (name.contains(each)) {
				isMerchant = true;
				break;
			}
		}

		return isMerchant;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		System.out.println("OrderAction : Called");
		OrderForm orderform = (OrderForm) form;

		String method = request.getParameter("confirm");

		if (method == null) {
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

			Address address = new Address(house, line1, line2, city, state, zip);

			Customer cust = new Customer(cid, name, mobile, email, address, type, tin);

			int numProducts = Integer.parseInt(orderform.getNumProd());

			String[] prodName = orderform.getProductNames();
			String[] prices = orderform.getProductPrices();
			String[] qtys = orderform.getProductQtys();
			List<Product> products = new ArrayList<Product>();
			int amount = 0;

			for (int i = 0; i < numProducts; i++) {
				String[] props = prodName[i].split(" : ");
				amount += Integer.parseInt(qtys[i])
						* Integer.parseInt(prices[i]);
				products.add(new Product(Integer.parseInt(props[1]), 0,
						props[0], Integer.parseInt(qtys[i]), Integer
								.parseInt(prices[i]), 0));
			}

			Order order = new Order(cust, products, 0, new Date(), amount);
			request.getSession().setAttribute("order", order);
			if(isMerchant(name))
				order.getCustomer().setType("MERCHANT");
			else
				order.getCustomer().setType("CUSTOMER");

			result = "confirm";
		} else if ("submit".equals(method)) {

			Order order = (Order) request.getSession().getAttribute("order");
			String name = order.getCustomer().getName();
			int cid = order.getCustomer().getId();
			System.out.println("OrderAction - Submit Clicked, CID is : " + cid);
			if (cid == -1) {
				if (isMerchant(name)) {
					cid = CustomerDAO.insertMerchant(order.getCustomer());

					if (cid != 0) {
						result = "success";
						
					} else
						result = "failure";
				} else {
					cid = CustomerDAO.insertCustomer(order.getCustomer());
					if (cid != 0) {
						result = "success";
					} else
						result = "failure";
				}
			} else {
				result = "success";
				
				
			}

			System.out.println("OrderAction - Submit Clicked, result is : "
					+ result);
			if ("success".equals(result)) {
				order.getCustomer().setId(cid);
				boolean isOrderTaken = OrderDAO.takeOrder(order);
				if (!isOrderTaken)
					result = "failure";
				else {
					
					request.setAttribute("order", order);
				}
			}

		} else if ("reset".equals(method)) {
			result = "modify";
		}

		return mapping.findForward(result);
	}
}
