package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.OrderDAO;
import com.dbt.forms.EditOrderForm;

public class EditOrderAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("EditOrderAction : Called");
		String result = "failure";
		boolean editStatus = false;
		EditOrderForm myform = (EditOrderForm)form;
		
		try
		{
			int orderId = Integer.parseInt(myform.getOrderId());
			String deleteOrder = myform.getDeleteOrder();
			
			if(deleteOrder.equals("delete"))
			{
				editStatus = new OrderDAO().deleteOrder(orderId);
				
				if(editStatus)
				{
					result = "success";
					request.getSession().setAttribute("editOrder", "Deleted");
				}
				else
					request.getSession().setAttribute("editOrder", "notDeleted");
			}
			else
			{
				int customerId = Integer.parseInt(myform.getCustomerId());
				int numProd = Integer.parseInt(myform.getNumProd());
				
				if(numProd == 0)
				{
					editStatus = new OrderDAO().editOrderCustomer(orderId, customerId);
				}
				else
				{
					int prodIds[] = new int[numProd];
					int prodQtys[] = new int[numProd];
					int prodPrices[] = new int[numProd];
					
					String[] names = myform.getProductNames();
					String[] qtys = myform.getProductQtys();
					String[] prices = myform.getProductPrices();
					
					for(int i = 0; i < numProd; i++)
					{
						String Ids[] = names[i].split(" : ");
						prodIds[i] = Integer.parseInt(Ids[1]);
						
						prodQtys[i] = Integer.parseInt(qtys[i]);
						prodPrices[i] = Integer.parseInt(prices[i]);
					}
					
					editStatus = new OrderDAO().editOrder(orderId, customerId, 
							prodIds, prodQtys, prodPrices, numProd);
				}
				
				if(editStatus)
				{
					result = "success";
					request.getSession().setAttribute("editOrder", "Edited");
				}
				else
					request.getSession().setAttribute("editOrder", "notEdited");
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mapping.findForward(result);
	}

}
