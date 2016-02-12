package com.dbt.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.StockDAO;
import com.dbt.data.Product;
import com.dbt.data.Stock;
import com.dbt.forms.MerchantDetailForm;
import com.dbt.forms.StockForm;
import com.dbt.support.Email;

public class StockAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String result = "";
		System.out.println("StockAction : Called");
		Stock stockDetail = (Stock) request.getSession().getAttribute(
				"newStockDetails");

		if (stockDetail == null) {
			StockForm stockForm = (StockForm) form;
			try 
			{
				int category = Integer.parseInt(stockForm.getCategory());
				int numProducts = Integer.parseInt(stockForm.getNumProd());
				String[] prodNames = stockForm.getProductNames();
				String[] prodCps = stockForm.getProductCPs();
				String[] prodSps = stockForm.getProductSPs();
				String[] prodQtys = stockForm.getProductQtys();
				String[] prodIds = stockForm.getProductIds();
				List<Product> products = new ArrayList<Product>();
				int totalAmount = 0;
				for (int i = 0; i < numProducts; i++) {
					products.add(new Product(Integer.parseInt(prodIds[i]),
							category, prodNames[i], Integer
									.parseInt(prodQtys[i]), Integer
									.parseInt(prodSps[i]), Integer
									.parseInt(prodCps[i])));

					totalAmount += Integer.parseInt(prodQtys[i])
							* Integer.parseInt(prodCps[i]);
					System.out.println("ID - " + prodIds[i] + ", Name - "
							+ prodNames[i]);
				}

				Stock stock = new Stock(products, category);
				stock.setAmount(totalAmount);
				request.getSession().setAttribute("newStockDetails", stock);

				result = "success";
			} catch (Exception e) {
				Email.sendExceptionReport(e);
			}
		} 
		else 
		{
			MerchantDetailForm merchantForm = (MerchantDetailForm) form;

			try 
			{
				int merchantId = Integer.parseInt(merchantForm.getmerchantId());
				int amount = Integer.parseInt(merchantForm.getAmount());
				int currentAmount = 0;
				if(!"".equals(merchantForm.getCurrentAmount()))
				{
					currentAmount = Integer.parseInt(merchantForm.getCurrentAmount());
				}

				String expMode = merchantForm.getExpMode();
				String desc = merchantForm.getDescription();
				String paid = merchantForm.getPaid();

				List<Product> product = stockDetail.getProducts();
				boolean stockPurchased = false;
				if("Yes".equals(merchantForm.getAddPayment()))
				{
					System.out.println("Stock With payment");
					stockPurchased = new StockDAO().purchaseStock(merchantId, amount, currentAmount, expMode, desc, paid, product);
				}
				else
				{
					System.out.println("Stock Without payment");
					stockPurchased = new StockDAO().addStock(merchantId, amount, product);
				}
				System.out.println("Status : "+stockPurchased);
				request.getSession().removeAttribute("newStockDetails");
				if (stockPurchased) {
					System.out.println("Stock Added Successfully");
					result = "success";
					request.getSession().setAttribute("stockStatus", "Success");
				} else
					request.getSession().setAttribute("stockStatus", "Failure");

			} catch (Exception e) {
				Email.sendExceptionReport(e);
			}
		}

		return mapping.findForward(result);
	}
}
