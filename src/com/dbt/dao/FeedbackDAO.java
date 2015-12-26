package com.dbt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbt.data.Feedback;
import com.dbt.data.Question;
import com.dbt.database.DBConnection;
import com.dbt.support.Email;

public class FeedbackDAO {

	public List<Question> getQuestions() {
		List<Question> questions = new ArrayList<Question>();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select * from feedback_question order by _id");

			res = stmt.executeQuery();
			while (res.next()) {
				questions.add(new Question(res.getInt("_id"), res
						.getString("text"), res.getString("type"), 0));
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return questions;
	}
	
	
	public List<Feedback> searchFeedback(int orderID,String name,String mobile,Date toDate,Date fromDate)
	{
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		ResultSet set = null;
		try
		{
			con = DBConnection.getConnection();
			if("".equals(name))
				name = "-";
			stmt = con.prepareStatement("select f._id,f.order_id,f.generateDate,f.submitDate,concat(u.first_name,' ',u.last_name) as 'name'  from `order` o join feedback f on f.order_id = o._id join `user` u on u._id = o.cust_id where f.order_id = ? or u.mobile = ? or (f.generateDate BETWEEN ? and ?) or concat(u.first_name,' ',u.last_name) like '%"+name+"%'");
			stmt.setInt(1, orderID);
			stmt.setString(2, mobile);
			stmt.setDate(3, new java.sql.Date(fromDate.getTime()));
			stmt.setDate(4, new java.sql.Date(toDate.getTime()));
			System.out.println(stmt.toString());
			res = stmt.executeQuery();
			while(res.next())
			{
				Date submitDate = null;
				if(res.getTimestamp("submitDate") != null)
				{
					submitDate = new Date(res.getTimestamp("submitDate").getTime());
				}
				Feedback feedback = new Feedback(res.getInt("_id"),res.getInt("order_id"), new Date(res.getTimestamp("generateDate").getTime()), submitDate,null);
				
				PreparedStatement stmt1 = con.prepareStatement("select fr.ques_id,fr.feedback_id,fr.response,fq.text,fq.type from feedback_response fr join feedback_question fq on fq._id = fr.ques_id where fr.feedback_id = ?");
				stmt1.setInt(1, res.getInt("_id"));
				set = stmt1.executeQuery();
				List<Question> questions = new ArrayList<>();
				while(set.next())
				{
					Question ques = new Question(set.getInt("ques_id"), set.getString("text"), set.getString("type"), set.getInt("feedback_id"), set.getString("response"));
					questions.add(ques);
				}
				stmt1.close();set.close();
				feedback.setQuestions(questions);
				feedbacks.add(feedback);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			Email.sendExceptionReport(e);
		}
		finally
		{
			DBConnection.closeResource(con, stmt, res);
		}
		
		return feedbacks;
	}

	public void createFeedback(int orderId, int[] questions, String token) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("insert into feedback(order_id,generateDate,token) values(?,now(),?)");
			stmt.setInt(1, orderId);
			stmt.setString(2, token);
			int i = stmt.executeUpdate();
			stmt.close();
			stmt = con
					.prepareStatement("select _id from feedback where order_id = ? and token = ?");
			stmt.setInt(1, orderId);
			stmt.setString(2, token);
			res = stmt.executeQuery();
			if (res.next()) {
				int feedbackID = res.getInt("_id");
				stmt.close();
				stmt = con
						.prepareStatement("insert into feedback_response(feedback_id,ques_id) values(?,?)");

				for (int j = 0; j < questions.length; j++) {
					stmt.setInt(1, feedbackID);
					stmt.setInt(2, questions[j]);
					stmt.addBatch();
				}

				stmt.executeBatch();
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}
	}

	public boolean recordFeedback(String ans[], int fid, int ques[]) {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean res = false;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("update feedback_response set response = ? where feedback_id = ? and ques_id = ?");

			for (int i = 0; i < ques.length; i++) {
				stmt.setString(1, ans[i]);
				stmt.setInt(2, fid);
				stmt.setInt(3, ques[i]);
				stmt.addBatch();
			}

			int result[] = stmt.executeBatch();
			boolean r = true;
			for (int i = 0; i < result.length; i++) {
				if (result[i] != 1)
					r = false;
			}
			res = r;

			stmt.close();
			stmt = con
					.prepareStatement("update feedback set submitDate = now() where _id = ?");
			stmt.setInt(1, fid);
			stmt.executeUpdate();
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, null);
		}

		return res;
	}

	public List<Question> getFeedbackQues(String token) {
		List<Question> questions = new ArrayList<Question>();
		System.out.println("Token is : " + token);
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			con = DBConnection.getConnection();
			stmt = con
					.prepareStatement("select fb._id as fid,fbq._id as qid,fbq.text as text,fbq.type as type from feedback fb join feedback_response fbr on fb._id = fbr.feedback_id join feedback_question fbq on fbq._id = fbr.ques_id where fb.token = ? and fbr.response is NULL;");
			stmt.setString(1, token);
			res = stmt.executeQuery();

			while (res.next()) {
				questions.add(new Question(res.getInt("qid"), res
						.getString("text"), res.getString("type"), res
						.getInt("fid")));
			}
		} catch (Exception e) {
			Email.sendExceptionReport(e);
		} finally {
			DBConnection.closeResource(con, stmt, res);
		}

		return questions;
	}

}
