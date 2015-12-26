package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.ManageCustomerDAO;
import com.dbt.forms.ManageCustomerForm;
import com.dbt.support.Email;

public class ManageCustomerAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("ManageCustomerAction : Called");
		String result = "failure";
		boolean custStatus = false;
		ManageCustomerForm myform = (ManageCustomerForm) form;

		try {
			String name = myform.getName();
			String email = myform.getEmail();
			String house = myform.getHouse();
			String line1 = myform.getLine1();
			String line2 = myform.getLine2();
			String mobile = myform.getMobile();
			String city = myform.getCity();
			String state = myform.getState();
			String zip = myform.getPin();
			String type = myform.getType();
			String tin = myform.getTin();
			String bType = myform.getButtonType();
			int id = myform.getId();

			if (bType.equals("deleteButton")) {
				custStatus = new ManageCustomerDAO().deleteCustomer(id, type);

				if (custStatus) {
					request.getSession().setAttribute("custStatus", "Deleted");
					result = "success";
				} else
					request.getSession().setAttribute("custStatus",
							"notDeleted");
			} else {
				custStatus = new ManageCustomerDAO().updateCustomerData(name,
						email, mobile, line1, line2, house, city, state, zip,
						tin, id, type);

				if (custStatus) {
					request.getSession().setAttribute("custStatus", "Updated");
					result = "success";
				} else
					request.getSession().setAttribute("custStatus",
							"notUpdated");
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		}

		return mapping.findForward(result);
	}

}
