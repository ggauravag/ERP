package com.dbt.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.dbt.dao.DocumentDAO;
import com.dbt.support.DropBoxConfig;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		JSONObject responseJSON = new JSONObject();
		if (ServletFileUpload.isMultipartContent(request)) 
		{
			String fileName = null;
			int id = 0;
			String type = null;
			String urlAndName = "";
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;
			try 
			{
				items = upload.parseRequest(request);
				Iterator iter = items.iterator();
				while (iter.hasNext()) 
				{
					FileItem i = (FileItem) iter.next();
					if(!i.isFormField())
					{
						fileName = i.getName();
						long size = i.getSize();
						System.out.println("File Name : "+fileName+", Size : "+size);
						DropBoxConfig config = new DropBoxConfig();
						urlAndName = config.uploadToDropbox(i.getInputStream(), size, fileName);
					}
					else
					{
						if(i.getFieldName().equals("type"))
							type = i.getString();
						if(i.getFieldName().equals("id"))
							id = Integer.parseInt(i.getString());
					}
				}
				
				DocumentDAO dao = new DocumentDAO();
				String[] urlName = urlAndName.split(";");
				int docid = dao.uploadDocument(urlName[1], urlName[0], type, id);
				if(docid != 0)
				{
					responseJSON.put("status", 1);
					responseJSON.put("url", urlName[0]);
					responseJSON.put("name", urlName[1]);
					responseJSON.put("id",docid);
				}
				else
				{
					responseJSON.put("status", 0);
				}
			
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		} 
		else
		{
			System.out.println("No Multipart");
			responseJSON.put("status", -1);
		}
		
		String jsonResponse = responseJSON.toJSONString();
		System.out.println("AjaxActionServlet - uploadDocument() : "
				+ jsonResponse);
		response.setContentType("text/json");
		PrintWriter writer = response.getWriter();
		writer.write(jsonResponse);
		writer.flush();

	}

}
