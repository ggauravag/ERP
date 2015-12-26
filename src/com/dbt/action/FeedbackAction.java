package com.dbt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dbt.dao.FeedbackDAO;
import com.dbt.dao.OrderDAO;
import com.dbt.data.Order;
import com.dbt.forms.FeedbackForm;
import com.dbt.support.DBTSms;
import com.dbt.support.Utils;

public class FeedbackAction extends Action {

	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String quesNo = request.getParameter("quesNo");
		if (!"".equals(quesNo) && quesNo != null) {
			int qno = Integer.parseInt(quesNo);
			int fid = Integer.parseInt(request.getParameter("feedbackID"));
			String ans[] = new String[qno];
			int ques[] = new int[qno];
			for (int i = 1; i <= qno; i++) {
				String[] an = request.getParameter("ans" + i).split(":");
				if (an.length == 2) {
					ans[i - 1] = an[0];
					ques[i - 1] = Integer.parseInt(an[1]);
				} else {
					ans[i - 1] = an[0];
					ques[i - 1] = Integer.parseInt(request.getParameter("qno"
							+ i));
				}
			}

			if (new FeedbackDAO().recordFeedback(ans, fid, ques))
				request.setAttribute("status", true);
			else
				request.setAttribute("status", false);

		}

		return mapping.findForward("success");
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		if ("submit".equals(request.getParameter("action"))) {
			return submit(mapping, form, request, response);
		}

		String result = "success";

		FeedbackForm feedback = (FeedbackForm) form;

		int orderID = feedback.getOrderId();
		int[] questions = feedback.getQuestions();

		String token = Utils.getMD5(Utils.getOTP(5));
		Order order = new OrderDAO().getOrder(orderID);
		String name = order.getCustomer().getName();
		String mobile = order.getCustomer().getMobile();
		String item = order.getOrderitems().get(0).getProduct_name();

		new FeedbackDAO().createFeedback(orderID, questions, token);
		DBTSms.sendFeedback(mobile, item, name, token, true);
		request.setAttribute("status", true);
		return mapping.findForward(result);
	}
}
