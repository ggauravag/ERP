package com.dbt.support;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class DBTSms {

	public static String retval="";
	/**
	 * @param args
	 */
	
	public static String schedule(String msidn, String msg,String date, String time)
	{
		String rsp="";
		//String user = AESCrypto.decrypt("7fMs2hJHbPeLdaQM93dBJA==");
        String password = "tran";
		String sid = "DEMOOO";
		//String flash = "0";
		//msisdn = "91"+msisdn;
		System.out.println("SMS Sent");
        try {
            // Construct The 11Post Data
        	
        	
			SimpleDateFormat format1 = new SimpleDateFormat ("dd-MM-yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat ("HH:mm:00");
			//java.util.Date date = new java.util.Date();
			//String sendondate = format1.format(date)+"T"+format2.format(date);
			
        	
            String data = "reqid=1&format=text&route_id=loop&username=ramfur";
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("to", "UTF-8") + "=" + URLEncoder.encode(msidn, "UTF-8");
            data += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(msg, "UTF-8");
            data += "&" + URLEncoder.encode("sender", "UTF-8") + "=" + URLEncoder.encode(sid, "UTF-8");
            data += "&" + URLEncoder.encode("sendondate", "UTF-8") + "=" + date + "T" + time;
            //data += "&" + URLEncoder.encode("sendondate", "UTF-8") + "=" + "30-10-2014T23:58:00";
            
            //System.out.println("Data is : "+data);
            //Push the HTTP Request
            URL url = new URL("http://118.139.181.117/API/WebSMS/Http/v1.0a/index.php?username=tran&"+data);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
        
            //OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            //wr.write(data);
            //wr.flush();
           
            //Read The Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
                retval += line;
            }
            //wr.close();
            rd.close();
            
            System.out.println(retval);
            rsp = retval;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  rsp;
	}
	
	public static String sendSMSTeneti(String sNumber, String message)
	{
		try
		{
			String sUserID = "ishashank22"; 
	        String sPwd = "ishashank22";
	        String code = "TP";
	        String sSID = "DBTECH"; 
	        String sMessage = message; 
	        String sURL ="http://sms.dreambit.in/SendSMS.aspx?username="+ sUserID + "&";
	        sURL +=  "password=" + sPwd + "&mobile=" + sNumber + "&sid=" + sSID + "&code="+code+"&msg=" + URLEncoder.encode(sMessage) ;
	        
			URL url = new URL(sURL);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
                retval += line;
            }
            //wr.close();
            rd.close();
            
            System.out.println(retval);
           return retval;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
		
		
		//103.247.98.91/API/SendMsg.aspx?uname=xxxxxxxx&pass=xxxxx&send=xxxxxx&dest=xxxxxxxxxx&msg=xxxxxxx&priority=1&schtm=xxxx-xx-xx xx:xx
	}
	
	
	public static String sendOwnerOTP(String mobile,String name)
	{
		String otp = Utils.getOTP(6);
		
		String msg = name.toUpperCase()+" is trying to access ERP Portal, if he/she is authorised then pass this OTP : "+otp+" to him/her.";
		sendSMS(mobile, msg);
		return otp;
	}
	
	
	
	public static String sendSMS(String msisdn,String msg)    
    {
        String rsp="";
		//String user = AESCrypto.decrypt("7fMs2hJHbPeLdaQM93dBJA==");
        String password = "programmer";
		String sid = "DBTECH";
		//String flash = "0";
		//msisdn = "91"+msisdn;
		System.out.println("India SMS called");
        try {
            // Construct The 11Post Data
        	
        	
			SimpleDateFormat format1 = new SimpleDateFormat ("dd-MM-yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat ("HH:mm:00");
			java.util.Date date = new java.util.Date();
			//String sendondate = format1.format(date)+"T"+format2.format(date);
			//
        	
            String data = "&route_id=2&Unicode=0";
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(msisdn, "UTF-8");
            data += "&" + URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(msg, "UTF-8");
            data += "&" + URLEncoder.encode("senderid", "UTF-8") + "=" + URLEncoder.encode(sid, "UTF-8");
            //data += "&" + URLEncoder.encode("sendondate", "UTF-8") + "=" + "30-10-2014T23:58:00";
            
            //System.out.println("Data is : "+data);
            //Push the HTTP Request
            URL url = new URL("http://198.24.149.4/API/pushsms.aspx?loginID=ramfur"+data);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
        
            //OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            //wr.write(data);
            //wr.flush();
           
            //Read The Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
                retval += line;
            }
            //wr.close();
            rd.close();
            
            System.out.println(retval);
            rsp = retval;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  rsp;
    }
	

	
	
	
	public static void main(String[] args) {
		
		try
		{
		//Connection con = DBInfo.getConn();
		//PreparedStatement ps = con.prepareStatement("select st.st_name,st.mobile,st.email, count(*) as Count from student st, attendance att where st.st_id = att.id group by att.id order by Count desc");
		
		//ResultSet rs = ps.executeQuery();
		
		sendOwnerOTP("9982166368", "Ashok");
		//while(rs.next())
		//{
			
			
        //System.out.println(sendSMS(rs.getString(2), "Dear Student, Your Certification Exam is scheduled in CL-1 & CL-3\n.Two Slots : 1:30 - 2:30 and 2:30 - 3:30\nExam Duration : 1 Hour"));
			//sendSMS(rs.getString(2), "Hi, "+rs.getString(1).split(" ")[0]+",\nYour class is scheduled from 1:30 - 3:30 PM in IAI Lab today.\nIt's mandatory to attend for Certification.\n");
	            //System.out.println(rs.getString(3)+",");
            
		//}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	

}
	}
	
