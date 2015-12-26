package com.dbt.support;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dbt.data.Address;
import com.dbt.data.Customer;
import com.dbt.data.Invoice;
import com.dbt.data.Merchant;
import com.dbt.data.Order;
import com.dbt.data.Order_item;
import com.dbt.data.Payment;
import com.dbt.vo.Shipment;

public class Email {
	
	public void sendAPIMail(String[] to, String fromName, String fromEmail, String subject, String body)
	{
		try {
			URL url = new URL("https://mandrillapp.com/api/1.0/messages/send.json");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			JSONObject message = new JSONObject();
			JSONObject mail = new JSONObject();
			
			mail.put("key", "grgsow-rdPAqZnMDELMCtA");
			
			message.put("html", body);
			message.put("subject", subject);
			message.put("from_email", fromEmail);
			message.put("from_name", fromName);
			
			JSONArray toArray = new JSONArray();
			JSONObject receipent;
			for(int i = 0; i < to.length; i++)
			{
				receipent = new JSONObject();
				receipent.put("email", to[i]);
				receipent.put("type","to");
				toArray.add(receipent);
			}
			 
			message.put("to", toArray);
			message.put("important", true);
			message.put("track_opens", null);
			message.put("track_clicks", null);
			message.put("auto_text", null);
			message.put("auto_html", null);
			message.put("inline_css", null);
			message.put("url_strip_qs", null);
			message.put("preserve_recipients", null);
			message.put("view_content_link", null);
			message.put("tracking_domain", null);
			message.put("signing_domain", null);
			message.put("return_path_domain", null);
			
			message.put("merge_language", "mailchimp");
			/*message.put("", null);
			message.put("", null);
			message.put("", null);
			message.put("", null);
			message.put("", null);
			message.put("", null);*/
			message.put("merge", false);
			
			mail.put("message", message);
			
			connection.setDoOutput(true);
			DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
			stream.writeBytes(mail.toJSONString());
			stream.flush();
			stream.close();
			
			
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			//System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);
			
			/*BufferedReader error = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			String inputError;
			StringBuffer responseE = new StringBuffer();

			while ((inputError = error.readLine()) != null) {
				responseE.append(inputError);
			}
			error.close();
			
			System.out.println(responseE.toString());*/
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//System.out.println(response.toString());
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendPasswordReset(final String email, String hashToken) {
		final String from = "ramfurnitures@gmail.com";

		/*Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "false");
		properties.put("mail.smtp.host", "smtp.mandrillapp.com");
		// properties.put("mail.smtp.ssl.trust", "smtp.mandrillapp.com");
		// // User name
		// // password

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"ramfurnitures@gmail.com",
								"mHBi_QXl5NGLaUHFO7suTg");// Specify
						// the
						// Username
						// and
						// the
						// PassWord
					}
				});*/

		try {
			//final MimeMessage message = new MimeMessage(session);

			//message.setFrom(new InternetAddress(from, "Ram Furniture"));
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(
			//		email));
			//message.setSubject("Password Reset");

			//MimeMultipart multipart = new MimeMultipart("related");

			// String workingDir = System.getProperty("user.dir");
			// String path = workingDir.substring(0,(workingDir.length()-3));

			//BodyPart messageBodyPart = new MimeBodyPart();
			final StringBuilder htmlTextBuilder = new StringBuilder();
			htmlTextBuilder.append("");
			htmlTextBuilder.append("<html>");
			htmlTextBuilder
					.append("<body style='font-family: arial, sans-serif;'>");
			htmlTextBuilder.append("<div id='container'>");
			htmlTextBuilder
					.append("<table align='center' cellpadding='2px' cellspacing='20px' background='http://contest.dreambit.in/Images/body.png'>");
			htmlTextBuilder
					.append("<tr><td><img src='http://www.dreambit.in/Images/logo.png' alt=':( Poor Connection' /> &nbsp;</td>");
			htmlTextBuilder
					.append("<td><h2 style='font-family: Cooper Black font-size: xx-large'>");
			htmlTextBuilder
					.append("DreamBit <br> Technologies</h2></td></tr><tr>");
			htmlTextBuilder
					.append("<td bgcolor='black' align='center' colspan='2'> <font size='7' color='white' face='Times New Roman'>Password Reset</font>");
			htmlTextBuilder
					.append("</td></tr><tr><td padding='2px' width='400px'> Dear <b>"
							+ email + "</b>,<br>");
			htmlTextBuilder
					.append("<br> We have received your request regarding reset of your login password.<br><br>");
			htmlTextBuilder
					.append("This mail contains the password reset link, which will expire after 24 hours.<br>");
			htmlTextBuilder
					.append("<br><a href='http://erp.dreambit.co.in/forgotPassword.jsp?authToken="
							+ URLEncoder.encode(hashToken, "UTF-8")
							+ "'>Click here to reset your password</a> <br>");
			htmlTextBuilder
					.append("<br><br><strong>NOTE : </strong>If you have not requested password reset link,<br> then relax, the link will expire after 24 hours ! </td>");
			htmlTextBuilder
					.append("<td align='right'><img src='http://dreambit.co.in/img/logo/ramfurlogo.jpg' alt=':( Poor Connection' height='120px' /></td>");
			htmlTextBuilder
					.append("</tr></table><div><center><br><br>&copy; 2015 <a href='http://www.dreambit.co.in' target='_blank'>DreamBit Technologies</a> Pvt. Ltd.");
			htmlTextBuilder.append("</div></center></div></body></html>");

			//messageBodyPart.setContent(htmlTextBuilder.toString(), "text/html");
			//multipart.addBodyPart(messageBodyPart);
			//message.setContent(multipart);

			new Thread(new Runnable() {
				public void run() {
					try {
						new Email().sendAPIMail(new String[]{email}, "Ram Furniture", from, "Password Reset", htmlTextBuilder.toString());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out.println("Sent Password reset successfully....");

		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendEmployeeEmail(String name, String type, final String email,
			String password) {
		final String from = "ramfurnitures@gmail.com";

		/*Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "false");
		properties.put("mail.smtp.host", "smtp.mandrillapp.com");
		// // User name
		// // password

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"ramfurnitures@gmail.com",
								"mHBi_QXl5NGLaUHFO7suTg");// Specify
						// the
						// Username
						// and
						// the
						// PassWord
					}
				});*/

		try {
			//final MimeMessage message = new MimeMessage(session);

			//message.setFrom(new InternetAddress(from, "Ram Furniture"));
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(
			//		email));
			//message.setSubject("Employee Registeration");

			//MimeMultipart multipart = new MimeMultipart("related");

			// String workingDir = System.getProperty("user.dir");
			// String path = workingDir.substring(0,(workingDir.length()-3));

			//BodyPart messageBodyPart = new MimeBodyPart();
			final StringBuilder htmlTextBuilder = new StringBuilder();

			htmlTextBuilder.append("<html>");
			htmlTextBuilder
					.append("<body style='font-family: arial, sans-serif;'>");
			htmlTextBuilder.append("<div id='container'>");
			htmlTextBuilder
					.append("<table align='center' cellpadding='2px' cellspacing='20px' background='http://contest.dreambit.in/Images/body.png'>");
			htmlTextBuilder
					.append("<tr><td><img src='http://www.dreambit.in/Images/logo.png' alt=':( Poor Connection' /> &nbsp;</td>");
			htmlTextBuilder
					.append("<td><h2 style='font-family: Cooper Black font-size: xx-large'>");
			htmlTextBuilder.append("DreamBit Technologies</h2></td></tr><tr>");
			htmlTextBuilder
					.append("<td bgcolor='black' align='center' colspan='2'> <font size='7' color='white' face='Times New Roman'>Registration Successful</font>");
			htmlTextBuilder
					.append("</td></tr><tr><td padding='2px' width='400px'> Dear <b>"
							+ name + "</b>,<br>");
			htmlTextBuilder.append("<br> You have now registered as an " + type
					+ " for Dreambit ERP Services.<br><br>");
			htmlTextBuilder.append("Username : " + email + "<br>");
			htmlTextBuilder.append("Password : " + password + " <br>");
			htmlTextBuilder
					.append("<br><a href='http://erp.dreambit.co.in/login.jsp?email="
							+ URLEncoder.encode(email, "UTF-8")
							+ "&password="
							+ URLEncoder.encode(password, "UTF-8")
							+ "'>Click here to Login</a> <br>");
			htmlTextBuilder.append("<br><br> </td>");
			htmlTextBuilder
					.append("<td align='right'><img src='http://dreambit.co.in/img/logo/ramfurlogo.jpg' alt=':( Poor Connection' height='120px' /></td>");
			htmlTextBuilder
					.append("</tr></table><div><center><br><br>&copy; 2015 <a href='http://www.dreambit.co.in' target='_blank'>DreamBit Technologies</a> Pvt. Ltd.");
			htmlTextBuilder.append("</div></center></div></body></html>");

			//messageBodyPart.setContent(htmlTextBuilder.toString(), "text/html");
			//multipart.addBodyPart(messageBodyPart);
			//message.setContent(multipart);

			new Thread(new Runnable() {
				public void run() {
					try {
						
						new Email().sendAPIMail(new String[]{email}, "Ram Furniture", from, "Employee Registration Successful", htmlTextBuilder.toString());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out.println("Sent Employee Email successfully....");

			} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		//new Email().sendOrderDetails(new String[]{"ggauravag@gmail.com"}, null);
		//sendExceptionReport(new Exception());
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("email.txt")));
			String line;
			while((line = reader.readLine()) != null)
			{
				list.add(line);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] emails = new String[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			emails[i] = list.get(i);
		}
		
		System.out.println("Number of mail : "+list.size());
		String body = "Hey Friends,<br /><br />"+
"It's once in a life time chance to fly to San Francisco on a completely<br />sponsored trip by HackerEarth.<br /><br />"+
"I am hereby feeling excited to introduce you to India's Largest Hackathon<br />"+
"being conducted by HackerEarth starting from 8th January 2016.<br /><br />"+
"You have ample opportunities to give yourself a chance to win a lot of<br />"+
"exciting goodies and trip to San Francisco by showcasing your talent in<br />"+
"programming and more.<br /><br />"+
"You can represent SKIT, Jaipur at HackerEarth in the following tracks:<br /><br />"+
"1. Algorithms &amp; Programming<br />"+
"2. Bot Challenge<br />"+
"3. Machine Learning<br />"+
"4. Application Development in Hackathons<br />"+
"<br /><b>Currently, the participation from SKIT is not very good in numbers, as compared to colleges like Poornima and others, hence I request you to kindly share this in your group and make more SKITians to register for the same.</b><br/><br/>"+
"Check out details here : <a href='http://hck.re/dJ283T' rel='noreferrer' target='_blank'>http://hck.re/dJ283T</a><br /><br />"+
"<b>P.S. :</b> Do not forget to register for the India Hacks, click above link for registration.<br /><br /><b>Cheers !</b><br /><b>Gaurav Agarwal</b><br />";
		
		//new Email().sendAPIMail(emails,"Gaurav Agarwal","ggauravag@gmail.com" ,"India-Hacks : India's Largest Hackathon By HackerEarth (SKIT, Jaipur)", body);
	}

	public static void sendExceptionReport(Exception e) {
		String from = "ramfurnitures@gmail.com";
		e.printStackTrace();
		StackTraceElement[] elements = e.getStackTrace();

		/*Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "false");
		properties.put("mail.smtp.host", "smtp.mandrillapp.com");
		// // User name
		// // password

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"ramfurnitures@gmail.com",
								"grgsow-rdPAqZnMDELMCtA");// Specify
						// the
						// Username
						// and
						// the
						// PassWord
					}
				});
*/
		try {
			//final MimeMessage message = new MimeMessage(session);

			//message.setFrom(new InternetAddress(from, "DreamBit ERP"));
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(
			//		"ggauravag@gmail.com"));
			//message.setSubject("Exception Report");

			//MimeMultipart multipart = new MimeMultipart("related");

			// String workingDir = System.getProperty("user.dir");
			// String path = workingDir.substring(0,(workingDir.length()-3));

			//BodyPart messageBodyPart = new MimeBodyPart();
			final StringBuilder htmlTextBuilder = new StringBuilder();

			htmlTextBuilder.append("<html>");
			htmlTextBuilder
					.append("<body style='font-family: arial, sans-serif;'>");
			htmlTextBuilder.append("<div id='container'>");
			htmlTextBuilder
					.append("<table align='center' cellpadding='2px' cellspacing='20px' background='http://contest.dreambit.in/Images/body.png'>");
			htmlTextBuilder
					.append("<tr><td><img src='http://www.dreambit.in/Images/logo.png' alt=':( Poor Connection' /> &nbsp;</td>");
			htmlTextBuilder
					.append("<td><h2 style='font-family: Cooper Black font-size: xx-large'>");
			htmlTextBuilder.append("DreamBit Technologies</h2></td></tr><tr>");
			htmlTextBuilder
					.append("<td bgcolor='black' align='center' colspan='2'> <font size='7' color='white' face='Times New Roman'>Exception Report</font>");
			htmlTextBuilder
					.append("</td></tr><tr><td padding='2px' width='400px'> Dear Admin, Please find the exception report : <b><br>Exception Message - "
							+ e.getMessage() + "<br></b>,<br>");
			for (StackTraceElement ele : elements) {
				htmlTextBuilder.append("- " + ele.getFileName() + " : "
						+ ele.getClassName() + " : " + ele.getMethodName()
						+ " : Line " + ele.getLineNumber() + "<br>");
			}

			htmlTextBuilder.append("<br><br> </td>");
			htmlTextBuilder
					.append("<td align='right'><img src='http://dreambit.co.in/img/logo/ramfurlogo.jpg' alt=':( Poor Connection' height='120px' /></td>");
			htmlTextBuilder
					.append("</tr></table><div><center><br><br>&copy; 2015 <a href='http://www.dreambit.co.in' target='_blank'>DreamBit Technologies</a> Pvt. Ltd.");
			htmlTextBuilder.append("</div></center></div></body></html>");

			//messageBodyPart.setContent(htmlTextBuilder.toString(), "text/html");
			//multipart.addBodyPart(messageBodyPart);
			//message.setContent(multipart);

			new Thread(new Runnable() {
				public void run() {
					try {
						new Email().sendAPIMail(new String[]{"ggauravag@gmail.com"}, "DreamBit ERP", "noreply@dreambit.co.in", "Exception Report", htmlTextBuilder.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out.println("Sent Exception Email successfully....");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void sendException(Exception e) {

	}

	public static void sendExceptionReport(String s) {

	}

	public void sendOrderDetails(final String[] email, final Order order, final Merchant merchant) {

		/*Properties properties = System.getProperties();
		// properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.starttls.enable", "false");
		properties.put("mail.smtp.host", "smtp.mandrillapp.com");
		// // User name
		// // password

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"ramfurnitures@gmail.com",
								"mHBi_QXl5NGLaUHFO7suTg");// Specify
						// the
						// Username
						// and
						// the
						// PassWord
					}
				});
*/
		try {
			//final MimeMessage message = new MimeMessage(session);

			//message.setFrom(new InternetAddress(merchant.getEmail(), merchant
					//.getName()));
			/*InternetAddress[] addresses = new InternetAddress[email.length];
			int i = 0;
			for (String e : email) {
				if (e != null)
					addresses[i] = new InternetAddress(e);
				i++;
			}

			message.addRecipients(Message.RecipientType.BCC, addresses);
			message.setSubject("Order Confirmation");

			MimeMultipart multipart = new MimeMultipart("related");

			// String workingDir = System.getProperty("user.dir");
			// String path = workingDir.substring(0,(workingDir.length()-3));

			BodyPart messageBodyPart = new MimeBodyPart();*/
			final StringBuilder htmlTextBuilder = new StringBuilder();
			htmlTextBuilder.append("<doctype html>\r\n");
			htmlTextBuilder.append("<html lang=\"en\">\r\n");
			htmlTextBuilder.append("<head>\r\n");
			htmlTextBuilder.append("<meta charset=\"UTF-8\">\r\n");
			htmlTextBuilder.append("<title>Order Confirmation</title>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("</head>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("<body\r\n");
			htmlTextBuilder
					.append("\tstyle=\"padding: 0px; margin: 0px; font-family: 'Bree Serif', serif;\">\r\n");
			htmlTextBuilder
					.append("\t<table height=\"100%\" width=\"100%\"\r\n");
			htmlTextBuilder
					.append("\t\tstyle=\"border: 2px solid #eee; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- First Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>TIN : " + merchant.getTin()
					+ "</td>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder
					.append("\t\t\t<td style=\"font-style: italic;\" align=\"right\" colspan=\"2\">\r\n");
			htmlTextBuilder.append("\t\t\t\tOriginal</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Second Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\"><img height=\"10%\"\r\n");
			htmlTextBuilder.append("\t\t\t\tsrc=\"http://erp.dreambit.co.in"
					+ merchant.getLogo() + "\" align=\"left\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\tstyle=\"padding-top: 2%;\" /> <b><u> ORDER CONFIRMATION </u></b> <br>\r\n");
			Address address = merchant.getAddress();
			htmlTextBuilder.append("\t\t\t\t<font size=\"5%\"><b>"
					+ merchant.getName().toUpperCase() + "</b></font><br> "
					+ address.getHouseNo() + ",\r\n");
			htmlTextBuilder.append("\t\t\t\t" + address.getLine1() + " <br> "
					+ address.getLine2() + ", " + address.getCity() + "\r\n");
			htmlTextBuilder.append("\t\t\t\t- " + address.getZip()
					+ " <br> <b>Phone : " + merchant.getMobile()
					+ "</b><br> <b>Deals\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\tIn : All Kinds of Furniture</b></td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Row for line -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\">\r\n");
			htmlTextBuilder.append("\t\t\t\t<hr noshade=\"noshade\">\r\n");
			htmlTextBuilder.append("\t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Third Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<table style=\"border: none; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td colspan=\"2\"><b><i>Party Details : </i></b> <br>\r\n");

			Customer customer = order.getCustomer();
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + customer.getName()
					+ ", <br>\r\n");

			address = customer.getAddress();
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + address.getHouseNo()
					+ ", " + address.getLine1() + " <br>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + address.getLine2() + ", "
					+ address.getCity() + " - " + address.getZip() + ", "
					+ address.getState() + " <br> \t\t</td>\t\t\t\t\t\r\n");

			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td colspan=\"2\">&nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td align=\"left\">Party TIN</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp; "
					+ customer.getTin() + "</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td></td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td></td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t</table>\r\n");
			htmlTextBuilder.append("\t\t\t</td>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t<td colspan=\"2\">\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<table style=\"border: none; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>Order No.</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp; #" + order.getId()
					+ "</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>Date</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp; "
					+ order.getDatetime().toString() + "</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td></td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td></td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td></td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td></td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");

			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t</table>\r\n");
			htmlTextBuilder.append("\t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Fourth Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td colspan=\"3\">\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<table height=\"100%\" width=\"100%\" cellspacing=\"0\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\tstyle=\"border: 1px solid #a5a5a5; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">S.No.</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Description\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t\tof Goods</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Quantity</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Unit</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Price</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Amount(Rs)</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");

			List<Order_item> items = order.getOrderitems();
			int total = 0;
			for (int j = 0; j < items.size(); j++) {
				Order_item item = items.get(j);
				total += item.getQuantity() * item.getAmount();
				htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td style=\"border-right: 1px solid #a5a5a5\" align=\"center\">"
								+ (j + 1) + ".</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td style=\"border-right: 1px solid #a5a5a5\" align=\"left\">"
								+ item.getProduct_name() + "\r\n");
				htmlTextBuilder.append("\t\t\t\t\t\t\t<br> <i></i>\r\n");
				htmlTextBuilder.append("\t\t\t\t\t\t</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right: 1px solid #a5a5a5\">"
								+ item.getQuantity() + "</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right: 1px solid #a5a5a5\">Pcs.</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right: 1px solid #a5a5a5\">"
								+ Utils.customFormat(item.getAmount())
								+ "</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"right\" style=\"border-right: 1px solid #a5a5a5\">"
								+ Utils.customFormat(item.getAmount()
										* item.getQuantity()) + "</td>\r\n");
				htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");

			}
			htmlTextBuilder.append("\r\n");

			htmlTextBuilder
					.append("</table></td></tr><tr><td align=\"right\" colspan=\"2\">Order Total <br> <u></u> </td>\r\n");
			htmlTextBuilder.append("<td align=\"right\">"
					+ Utils.customFormat(total) + " <br></td></tr>\r\n");

			htmlTextBuilder
					.append("<tr><td colspan=\"2\" style=\"text-align: right; font: arial; font-size: large; color: #17a6b2\"><b>  </b>\r\n");
			htmlTextBuilder
					.append("</td><td style=\"text-align: right; font: arial; font-size: large; color: #17a6b2\"></td></tr>\r\n");
			htmlTextBuilder
					.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>\r\n");
			htmlTextBuilder
					.append("</tr><tr><td align=\"center\" colspan=\"3\"><hr noshade=\"noshade\"></td></tr><tr><td>&nbsp;</td>\r\n");
			htmlTextBuilder
					.append("<td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>\r\n");
			htmlTextBuilder
					.append(" </td><td>&nbsp;</td><td>&nbsp;</td></tr>\r\n");
			htmlTextBuilder
					.append("<tr><td>&nbsp;</td></tr><tr><td><b><u>Terms & Conditions</u></b> <br> E. & O.E. <br>\r\n");
			htmlTextBuilder
					.append("1. This is Order confirmation not an Order Invoice. <br>2. Order fulfillment is subject to stock availablity.<br>3. If applicable, VAT & Taxes would be exclusive to the Order Total.</td></tr></table></body><style>\r\n");
			htmlTextBuilder
					.append("@import url(http://fonts.googleapis.com/css?family=Bree+Serif);</style></html>");

			//messageBodyPart.setContent(htmlTextBuilder.toString(), "text/html");
			//multipart.addBodyPart(messageBodyPart);
			//message.setContent(multipart);

			new Thread(new Runnable() {
				public void run() {
					try {
						new Email().sendAPIMail(email, merchant.getName(), merchant.getEmail(), "Order Confirmation",	htmlTextBuilder.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out
					.println("Sent Order Confirmation on Email successfully....");

			} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendReceiptDetails(final String[] email, Payment payment,
			final Merchant merchant) {

		/*Properties properties = System.getProperties();
		// properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.starttls.enable", "false");
		properties.put("mail.smtp.host", "smtp.mandrillapp.com");
		// // User name
		// // password

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"ramfurnitures@gmail.com",
								"mHBi_QXl5NGLaUHFO7suTg");// Specify
						// the
						// Username
						// and
						// the
						// PassWord
					}
				});*/

		try {
			/*//final MimeMessage message = new MimeMessage(session);

			//message.setFrom(new InternetAddress(merchant.getEmail(), merchant
					//.getName()));
			InternetAddress[] addresses = new InternetAddress[email.length];
			int i = 0;
			for (String e : email) {
				if (e != null)
					addresses[i] = new InternetAddress(e);
				i++;
			}

			message.addRecipients(Message.RecipientType.BCC, addresses);
			message.setSubject("Payment Receipt");

			MimeMultipart multipart = new MimeMultipart("related");

			// String workingDir = System.getProperty("user.dir");
			// String path = workingDir.substring(0,(workingDir.length()-3));

			BodyPart messageBodyPart = new MimeBodyPart();*/
			final StringBuilder htmlTextBuilder = new StringBuilder();
			htmlTextBuilder.append("<doctype html>\r\n");

			htmlTextBuilder.append("<html lang=\"en\">\r\n");
			htmlTextBuilder.append("<head>\r\n");
			htmlTextBuilder.append("<meta charset=\"UTF-8\">\r\n");
			htmlTextBuilder.append("<title>Payment Receipt</title>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("</head>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("<body\r\n");
			htmlTextBuilder
					.append("\tstyle=\"padding: 0px; margin: 0px; font-family: 'Bree Serif', serif;\">\r\n");
			htmlTextBuilder
					.append("\t<table height=\"100%\" width=\"100%\"\r\n");
			htmlTextBuilder
					.append("\t\tstyle=\"border: 2px solid #eee; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- First Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>TIN : " + merchant.getTin()
					+ "</td>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder
					.append("\t\t\t<td style=\"font-style: italic;\" align=\"right\" colspan=\"2\">\r\n");
			htmlTextBuilder.append("\t\t\t\tOriginal</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Second Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\"><img height=\"10%\"\r\n");
			htmlTextBuilder.append("\t\t\t\tsrc=\"http://erp.dreambit.co.in"
					+ merchant.getLogo() + "\" align=\"left\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\tstyle=\"padding-top: 2%;\" /> <b><u> PAYMENT RECEIPT </u></b> <br>\r\n");
			Address address = merchant.getAddress();
			htmlTextBuilder.append("\t\t\t\t<font size=\"5%\"><b>"
					+ merchant.getName().toUpperCase() + "</b></font><br> "
					+ address.getHouseNo() + ",\r\n");
			htmlTextBuilder.append("\t\t\t\t" + address.getLine1() + " <br> "
					+ address.getLine2() + ", " + address.getCity() + "\r\n");
			htmlTextBuilder.append("\t\t\t\t- " + address.getZip()
					+ " <br> <b>Phone : " + merchant.getMobile()
					+ "</b><br> <b>Deals\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\tIn : All Kinds of Furniture</b></td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\"><hr noshade=\"noshade\"></td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td colspan=\"3\"><table width=\"100%\">\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td><strong style=\"color: black;\">Amount (In\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\t\tFigures) :</strong></td>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td style=\"color: black;\" align=\"left\"><span class=\"WebRupee\">&#x20B9;\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t</span>");
			htmlTextBuilder.append(payment.getPrintableAmount());
			htmlTextBuilder.append("</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td><strong style=\"color: black;\">Amount (In Words):</strong></td>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td style=\"color: black;\" align=\"left\">");
			htmlTextBuilder.append(payment.getAmountInWords());
			htmlTextBuilder.append("</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td><strong style=\"color: black;\">Paid By :</strong></td>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td style=\"color: black;\" align=\"left\">");
			htmlTextBuilder.append(payment.getPaidBy());
			htmlTextBuilder.append("</strong></td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td><strong style=\"color: black;\">Order ID :</strong></td>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td style=\"color: black;\" align=\"left\">");
			htmlTextBuilder.append(payment.getOrderId());
			htmlTextBuilder.append("</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td><strong style=\"color: black;\">Mode :</strong></td>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td style=\"color: black;\" align=\"left\">");
			htmlTextBuilder.append(payment.getMode());
			htmlTextBuilder.append("</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td><strong style=\"color: black;\">Description :</strong></td>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td style=\"color: black;\" align=\"left\">");
			htmlTextBuilder.append(payment.getDescription());
			htmlTextBuilder.append("</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t</table></td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\"><hr noshade=\"noshade\"></td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td><b><u>Terms & Conditions</u></b> <br> E. & O.E. <br>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t1. This receipt is for acknowledgement purpose only. <br> 2.This\r\n");
			htmlTextBuilder
					.append("\t\t\t\treceipt may represent partial payment for the order.<br></td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t</table>\r\n");
			htmlTextBuilder.append("\t<style>\r\n");
			htmlTextBuilder
					.append("@import url(http://fonts.googleapis.com/css?family=Bree+Serif);\r\n");
			htmlTextBuilder.append("</style>\r\n");
			htmlTextBuilder.append("</body>\r\n");
			htmlTextBuilder.append("</html>");

			/*messageBodyPart.setContent(htmlTextBuilder.toString(), "text/html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
*/
			new Thread(new Runnable() {
				public void run() {
					try {
						new Email().sendAPIMail(email, merchant.getName(), merchant.getEmail(), "Payment Receipt", htmlTextBuilder.toString());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out.println("Sent Receipt on Email successfully....");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendInvoice(final String[] email, Invoice invoice, Order order,
			final Merchant merchant) {
		//Properties properties = System.getProperties();
		
		if(email == null || email.length == 0)
			return;
		
		// properties.put("mail.smtp.starttls.enable", "true");
		/*properties.put("mail.smtp.starttls.enable", "false");
		properties.put("mail.smtp.host", "smtp.mandrillapp.com");
		// // User name
		// // password

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"ramfurnitures@gmail.com",
								"mHBi_QXl5NGLaUHFO7suTg");// Specify
						// the
						// Username
						// and
						// the
						// PassWord
					}
				});
*/
		try {
			//final MimeMessage message = new MimeMessage(session);

			/*message.setFrom(new InternetAddress(merchant.getEmail(), merchant
					.getName()));
			InternetAddress[] addresses = new InternetAddress[email.length];
			int i = 0;
			for (String e : email) {
				
				addresses[i] = new InternetAddress(e);
				i++;
			}

			message.addRecipients(Message.RecipientType.BCC, addresses);
			message.setSubject("Order Invoice");

			MimeMultipart multipart = new MimeMultipart("related");

			// String workingDir = System.getProperty("user.dir");
			// String path = workingDir.substring(0,(workingDir.length()-3));

			BodyPart messageBodyPart = new MimeBodyPart();
			*/final StringBuilder htmlTextBuilder = new StringBuilder();
			htmlTextBuilder.append("<doctype html>\r\n");
			htmlTextBuilder.append("<html lang=\"en\">\r\n");
			htmlTextBuilder.append("<head>\r\n");
			htmlTextBuilder.append("<meta charset=\"UTF-8\">\r\n");
			htmlTextBuilder.append("<title>Invoice Details</title>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("</head>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("<body\r\n");
			htmlTextBuilder
					.append("\tstyle=\"padding: 0px; margin: 0px; font-family: 'Bree Serif', serif;\">\r\n");
			htmlTextBuilder
					.append("\t<table height=\"100%\" width=\"100%\"\r\n");
			htmlTextBuilder
					.append("\t\tstyle=\"border: 2px solid #eee; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- First Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>TIN : " + merchant.getTin()
					+ "</td>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder
					.append("\t\t\t<td style=\"font-style: italic;\" align=\"right\" colspan=\"2\">\r\n");
			htmlTextBuilder.append("\t\t\t\tOriginal</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Second Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\"><img height=\"10%\"\r\n");
			htmlTextBuilder.append("\t\t\t\tsrc=\"http://erp.dreambit.co.in"
					+ merchant.getLogo() + "\" align=\"left\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\tstyle=\"padding-top: 2%;\" /> <b><u> RETAIL INVOICE </u></b> <br>\r\n");
			Address address = merchant.getAddress();
			htmlTextBuilder.append("\t\t\t\t<font size=\"5%\"><b>"
					+ merchant.getName().toUpperCase() + "</b></font><br> "
					+ address.getHouseNo() + ",\r\n");
			htmlTextBuilder.append("\t\t\t\t" + address.getLine1() + " <br> "
					+ address.getLine2() + ", " + address.getCity() + "\r\n");
			htmlTextBuilder.append("\t\t\t\t- " + address.getZip()
					+ " <br> <b>Phone : " + merchant.getMobile()
					+ "</b><br> <b>Deals\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\tIn : All Kinds of Furniture</b></td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Row for line -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\">\r\n");
			htmlTextBuilder.append("\t\t\t\t<hr noshade=\"noshade\">\r\n");
			htmlTextBuilder.append("\t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Third Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<table style=\"border: none; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td colspan=\"2\"><b><i>Party Details : </i></b> <br>\r\n");

			Customer customer = order.getCustomer();
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + customer.getName()
					+ ", <br>\r\n");

			address = customer.getAddress();
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + address.getHouseNo()
					+ ", " + address.getLine1() + " <br>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + address.getLine2() + ", "
					+ address.getCity() + " - " + address.getZip() + ", "
					+ address.getState() + " <br> \t\t</td>\t\t\t\t\t\r\n");

			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td colspan=\"2\">&nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<td align=\"left\">Party TIN</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp; "
					+ customer.getTin() + "</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>Party CST No.</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t</table>\r\n");
			htmlTextBuilder.append("\t\t\t</td>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t<td colspan=\"2\">\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<table style=\"border: none; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>Invoice No.</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp; #"
					+ invoice.getId() + "</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>Dated</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp; "
					+ invoice.getPrintableDate() + "</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>GR/RR No.</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>Transport</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<td>: &nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");

			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t\t\t</table>\r\n");
			htmlTextBuilder.append("\t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- Fourth Row -->\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td colspan=\"3\">\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<table height=\"100%\" width=\"100%\" cellspacing=\"0\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\tstyle=\"border: 1px solid #a5a5a5; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">S.No.</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Description\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t\tof Goods</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Quantity</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Unit</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Price</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t<th align=\"center\"\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\tstyle=\"color: #fff; padding: 8px; background-color: #17a6b2; border-right: 1px solid #fff\">Amount(Rs)</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\r\n");

			List<Order_item> items = order.getOrderitems();
			for (int j = 0; j < items.size(); j++) {
				Order_item item = items.get(j);
				htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td style=\"border-right: 1px solid #a5a5a5\" align=\"center\">"
								+ (j + 1) + ".</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td style=\"border-right: 1px solid #a5a5a5\" align=\"left\">"
								+ item.getProduct_name() + "\r\n");
				htmlTextBuilder.append("\t\t\t\t\t\t\t<br> <i></i>\r\n");
				htmlTextBuilder.append("\t\t\t\t\t\t</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right: 1px solid #a5a5a5\">"
								+ item.getQuantity() + "</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right: 1px solid #a5a5a5\">Pcs.</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right: 1px solid #a5a5a5\">"
								+ Utils.customFormat(item.getAmount())
								+ "</td>\r\n");
				htmlTextBuilder.append("\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"right\" style=\"border-right: 1px solid #a5a5a5\">"
								+ Utils.customFormat(item.getAmount()
										* item.getQuantity()) + "</td>\r\n");
				htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");

			}
			htmlTextBuilder.append("\r\n");

			if (invoice.hasVAT()) {
				htmlTextBuilder
						.append("</table></td></tr><tr><td align=\"right\" colspan=\"2\">Total <br> VAT  @ "
								+ invoice.getVatPercent() + "%</td>\r\n");
				htmlTextBuilder.append("<td align=\"right\">"
						+ invoice.getTotal() + " <br> " + invoice.getTax()
						+ "</td></tr>\r\n");
			} else {
				htmlTextBuilder
						.append("</table></td></tr><tr><td align=\"right\" colspan=\"2\">Total <br> <u>Under Composition Scheme</u> </td>\r\n");
				htmlTextBuilder.append("<td align=\"right\">"
						+ invoice.getTotal() + " <br></td></tr>\r\n");
			}

			htmlTextBuilder
					.append("<tr><td colspan=\"2\" style=\"text-align: right; font: arial; font-size: large; color: #17a6b2\"><b> GRAND TOTAL - </b>\r\n");
			htmlTextBuilder
					.append("</td><td style=\"text-align: right; font: arial; font-size: large; color: #17a6b2\">Rs "
							+ invoice.getGrandTotal() + "</td></tr>\r\n");
			htmlTextBuilder
					.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>\r\n");
			htmlTextBuilder
					.append("</tr><tr><td align=\"center\" colspan=\"3\"><hr noshade=\"noshade\"></td></tr><tr><td>&nbsp;</td>\r\n");
			htmlTextBuilder
					.append("<td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>Amount In Words <br> <b>\r\n");
			htmlTextBuilder.append("" + invoice.getTotalInWords()
					+ " </b></td><td>&nbsp;</td><td>&nbsp;</td></tr>\r\n");
			htmlTextBuilder
					.append("<tr><td>&nbsp;</td></tr><tr><td><b><u>Terms & Conditions</u></b> <br> E. & O.E. <br>\r\n");
			htmlTextBuilder
					.append("1. The goods once sold will not be taken back. <br></td></tr></table><style>@import url(http://fonts.googleapis.com/css?family=Bree+Serif);</style></body>\r\n");
			htmlTextBuilder.append("</html>");

			/*messageBodyPart.setContent(htmlTextBuilder.toString(), "text/html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
*/
			new Thread(new Runnable() {
				public void run() {
					try {
					new Email().sendAPIMail(email, merchant.getName(), merchant.getEmail(), "Order Invoice", htmlTextBuilder.toString());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out.println("Sent Order Invoice on Email successfully....");

			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendShipment(final String[] email, Shipment shipment, Order order,
			final Merchant merchant) {

		/*Properties properties = System.getProperties();
		// properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.starttls.enable", "false");
		properties.put("mail.smtp.host", "smtp.mandrillapp.com");
		// // User name
		// // password

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"ramfurnitures@gmail.com",
								"mHBi_QXl5NGLaUHFO7suTg");// Specify
						// the
						// Username
						// and
						// the
						// PassWord
					}
				});*/

		try {
			/*final MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(merchant.getEmail(), merchant
					.getName()));
			InternetAddress[] addresses = new InternetAddress[email.length];

			for (int k = 0; k < email.length; k++) {
				addresses[k] = new InternetAddress(email[k]);

			}

			message.addRecipients(Message.RecipientType.BCC, addresses);
			message.setSubject("Order Shipped : Delivery Challan");

			MimeMultipart multipart = new MimeMultipart("related");

			// String workingDir = System.getProperty("user.dir");
			// String path = workingDir.substring(0,(workingDir.length()-3));

			BodyPart messageBodyPart = new MimeBodyPart();*/
			final StringBuilder htmlTextBuilder = new StringBuilder();
			htmlTextBuilder.append("<doctype html>\r\n");
			htmlTextBuilder.append("<html lang=\"en\">\r\n");
			htmlTextBuilder.append("<head>\r\n");
			htmlTextBuilder.append("<meta charset=\"UTF-8\">\r\n");
			htmlTextBuilder.append("<title>Delivery Challan</title>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("</head>\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder
					.append("<body style=\"padding: 0px; margin : 0px;font-family: 'Bree Serif', serif;\">\r\n");
			htmlTextBuilder
					.append("\t<table height=\"100%\" width=\"100%\" style=\"border: 2px solid #eee; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\r\n");
			htmlTextBuilder.append("\t\t<!-- First Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>\r\n");
			htmlTextBuilder.append("\t\t\t\tTIN : " + merchant.getTin()
					+ "\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\r\n");
			htmlTextBuilder
					.append(" \t\t\t<td style=\"font-style: italic;\" align=\"right\" colspan=\"2\">\r\n");
			htmlTextBuilder.append(" \t\t\t\tOriginal\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Second Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\">\r\n");
			htmlTextBuilder.append("\t\t\t\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<img height=\"10%\" src=\"http://dreambit.co.in"
							+ merchant.getLogo()
							+ "\" align=\"left\" style=\"padding-top: 2%;\" />\r\n");
			htmlTextBuilder.append("\t\t\t\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<b><u>DELIVERY CHALLAN</u></b>  <br>\r\n");
			htmlTextBuilder.append("\t\t\t\t<font size=\"5%\"><b>"
					+ merchant.getName().toUpperCase() + "</b></font><br>\r\n");
			Address address = merchant.getAddress();
			htmlTextBuilder.append("\t\t\t\t" + address.getHouseNo() + ", "
					+ address.getLine1() + " <br>\r\n");
			htmlTextBuilder.append("\t\t\t\t" + address.getLine2() + ", "
					+ address.getCity() + " - " + address.getZip() + " ("
					+ address.getState() + ") <br>\r\n");
			htmlTextBuilder.append("\t\t\t\t<b><i>Phone : "
					+ merchant.getMobile() + "</i></b><br>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<b>Deals in All Kinds of Furniture</b>\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Row for line -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\">\r\n");
			htmlTextBuilder.append("\t\t\t\t<hr noshade=\"noshade\">\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Third Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>\r\n");
			htmlTextBuilder.append("\t\t\t\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<table style=\"border: none; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td colspan=\"2\">\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t\t<b><i>Party Details : </i></b> <br>\r\n");
			Customer customer = order.getCustomer();
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + customer.getName()
					+ ", <br>\r\n");

			address = customer.getAddress();
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + address.getHouseNo()
					+ ", " + address.getLine1() + " <br>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\t\t" + address.getLine2() + ", "
					+ address.getCity() + " - " + address.getZip() + ", "
					+ address.getState() + " <br> \t\t\t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append(" \t\t\t\t\t\t<td colspan=\"2\"> &nbsp; </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append(" \t\t\t\t\t\t<td align=\"left\">Party TIN </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>: &nbsp; "
					+ customer.getTin() + " </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>Party CST No. </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>: &nbsp; </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t</table>\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t<td  colspan=\"2\">\r\n");
			htmlTextBuilder
					.append(" \t\t\t\t<table style=\"border: none; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");

			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>Dated </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>: &nbsp; "
					+ shipment.getPrintableTime() + "</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>Contact No. </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>: &nbsp; "
					+ shipment.getContact() + " </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append(" \t\t\t\t\t\t<td>Transport / Driver </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>: &nbsp; "
					+ shipment.getMediumName() + " </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder
					.append(" \t\t\t\t\t\t<td>Vehicle No. / GR-RR No. </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>: &nbsp; "
					+ shipment.getMediumNumber() + "</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>Medium </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>: &nbsp; "
					+ shipment.getMedium() + " </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t<tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>Way Bill No.</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\t<td>: &nbsp; </td>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append(" \t\t\t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t\t</table>\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Fourth Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td colspan=\"3\" class=\"borderLeft borderBottom borderTop borderRight\">\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<table height=\"100%\" width=\"100%\" cellspacing=\"0\" style=\"border:1px solid #a5a5a5; border-collapse: collapse;\">\r\n");
			htmlTextBuilder.append("\t\t\t\t\t<tr >\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<th align=\"center\" style=\"color:#fff;padding:8px;background-color:#17a6b2;border-right:1px solid #fff\">S.No.</th>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<th align=\"center\" style=\"color:#fff;padding:8px;background-color:#17a6b2;border-right:1px solid #fff\">Description of Goods</th>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<th align=\"center\" style=\"color:#fff;padding:8px;background-color:#17a6b2;border-right:1px solid #fff\">Quantity</th>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<th align=\"center\" style=\"color:#fff;padding:8px;background-color:#17a6b2;border-right:1px solid #fff\">Unit</th>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<th align=\"center\" style=\"color:#fff;padding:8px;background-color:#17a6b2;border-right:1px solid #fff\">Price</th>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t\t\t<th align=\"center\" style=\"color:#fff;padding:8px;background-color:#17a6b2;border-right:1px solid #fff\">Amount(Rs)</th>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\t\t\t\r\n");

			java.util.List<Order_item> items = order.getOrderitems();
			int totalPcs = 0;
			for (int j = 0; j < items.size(); j++) {
				Order_item item = items.get(j);
				htmlTextBuilder.append("\t\t\t\t\t<tr>\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td style=\"border-right:1px solid #a5a5a5\" align=\"center\">"
								+ (j + 1) + ".</td>\r\n");
				System.out.println("Value of J is : " + j);
				htmlTextBuilder.append("\t\t\t\t\t\t\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td style=\"border-right:1px solid #a5a5a5\" align=\"left\">"
								+ item.getProduct_name()
								+ " <br><i></i></td>\r\n");
				htmlTextBuilder.append("\t\t\t\t\t\t\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right:1px solid #a5a5a5\">"
								+ item.getQuantity() + "</td>\r\n");
				totalPcs += item.getQuantity();
				htmlTextBuilder.append("\t\t\t\t\t\t\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right:1px solid #a5a5a5\">Pcs.</td>\r\n");
				htmlTextBuilder.append("\t\t\t\t\t\t\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right:1px solid #a5a5a5\">"
								+ Utils.customFormat(item.getAmount())
								+ "</td>\r\n");
				htmlTextBuilder.append("\t\t\t\t\t\t\r\n");
				htmlTextBuilder
						.append("\t\t\t\t\t\t<td align=\"center\" style=\"border-right:1px solid #a5a5a5\">"
								+ Utils.customFormat(item.getAmount()
										* item.getQuantity()) + "</td>\r\n");
				htmlTextBuilder.append("\t\t\t\t\t</tr>\r\n");
			}

			htmlTextBuilder.append("\t\t\t\t\t\r\n");
			htmlTextBuilder.append("\t\t\t\t</table>\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Fifth Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"right\" colspan=\"2\">\r\n");
			htmlTextBuilder.append("\t\t\t\tTOTAL <br>\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\r\n");
			htmlTextBuilder.append(" \t\t\t<td align=\"right\">\r\n");
			htmlTextBuilder.append(" \t\t\t\t"
					+ Utils.customFormat(order.getAmount()) + " <br>\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Sixth Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>\r\n");
			htmlTextBuilder.append("\t\t\t\t&nbsp;\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\r\n");
			htmlTextBuilder
					.append(" \t\t\t<td style=\"text-align:right;font:arial;font-size:large;color:#17a6b2\">\r\n");
			htmlTextBuilder.append(" \t\t\t\t<b> CHALLAN TOTAL : Pcs - "
					+ Utils.customFormat(totalPcs) + " </b>\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append(" \t\t\t\r\n");
			htmlTextBuilder
					.append(" \t\t\t<td style=\"text-align:right;font:arial;font-size:large;color:#17a6b2\">\r\n");
			htmlTextBuilder
					.append(" \t\t\t\t<span class='WebRupee'>&#x20B9; </span> "
							+ Utils.customFormat(order.getAmount()) + "\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Empty Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>&nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t\t<td>&nbsp;</td>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td class=\"borderRight\">&nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Row for line -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td align=\"center\" colspan=\"3\">\r\n");
			htmlTextBuilder.append("\t\t\t\t<hr noshade=\"noshade\">\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Empty Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>&nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t\t<td>&nbsp;</td>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td class=\"borderRight\">&nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Seventh Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>\r\n");
			htmlTextBuilder.append("\t\t\t\t<br>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<b> "
							+ new NumberToWord().convertNumberToWords(order
									.getAmount()) + " </b>\r\n");
			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append(" \t\t\t<td>&nbsp;</td>\r\n");
			htmlTextBuilder
					.append("\t\t\t<td class=\"borderRight\">&nbsp;</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Eighth Row -->\r\n");
			htmlTextBuilder.append("\t\t<tr><td>&nbsp;</td></tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<!-- Ninth Row -->\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t<tr>\r\n");
			htmlTextBuilder.append("\t\t\t<td>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t<b><u>Terms & Conditions</u></b> <br>\r\n");
			htmlTextBuilder.append("\t\t\t\tE. & O.E. <br>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t1. Goods once sold will not be taken back. <br>\r\n");
			htmlTextBuilder
					.append("\t\t\t\t2. If applicable, VAT & Taxes would be exclusive to Challan Amount. <br><br><br>\r\n");

			htmlTextBuilder.append(" \t\t\t</td>\r\n");
			htmlTextBuilder.append("\t\t</tr>\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t\t\r\n");
			htmlTextBuilder.append("\t</table>\r\n");
			htmlTextBuilder.append("</body>\r\n");
			htmlTextBuilder.append("<style>\r\n");
			htmlTextBuilder
					.append("\t@import url(http://fonts.googleapis.com/css?family=Bree+Serif;\r\n");
			htmlTextBuilder.append("</style>\r\n");
			htmlTextBuilder.append("</html>\r\n");

			/*
			 * DataSource fds = new
			 * FileDataSource(path+"webapps\\OnlineTest\\images\\rat.png");
			 * DataSource fds1 = new
			 * FileDataSource(path+"webapps\\OnlineTest\\images\\s1.jpg");
			 * DataSource fds2 = new
			 * FileDataSource(path+"webapps\\OnlineTest\\images\\s2.jpg");
			 * DataSource fds3 = new
			 * FileDataSource(path+"webapps\\OnlineTest\\images\\bg.jpg");
			 * 
			 * messageBodyPart = new MimeBodyPart();
			 * messageBodyPart.setDataHandler(new DataHandler(fds));
			 * messageBodyPart.addHeader("Content-ID","<logo>");
			 * multipart.addBodyPart(messageBodyPart);
			 * 
			 * messageBodyPart = new MimeBodyPart();
			 * messageBodyPart.setDataHandler(new DataHandler(fds1));
			 * messageBodyPart.addHeader("Content-ID","<imagea>");
			 * multipart.addBodyPart(messageBodyPart);
			 * 
			 * messageBodyPart = new MimeBodyPart();
			 * messageBodyPart.setDataHandler(new DataHandler(fds2));
			 * messageBodyPart.addHeader("Content-ID","<imageb>");
			 * multipart.addBodyPart(messageBodyPart);
			 * 
			 * messageBodyPart = new MimeBodyPart();
			 * messageBodyPart.setDataHandler(new DataHandler(fds3));
			 * messageBodyPart.addHeader("Content-ID","<bg>");
			 * multipart.addBodyPart(messageBodyPart);
			 */

			/*messageBodyPart.setContent(htmlTextBuilder.toString(), "text/html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);*/

			new Thread(new Runnable() {
				public void run() {
					try {
						new Email().sendAPIMail(email, merchant.getName(), merchant.getEmail(), "Order Shipped : Delivery Challan", htmlTextBuilder.toString());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out.println("Sent Challan on Email successfully....");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
