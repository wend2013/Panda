package com.wendongwei.panda;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class BaseWS extends HttpServlet
{
	private static final long serialVersionUID = -4139401262935956748L;

	private static Logger logger = Logger.getLogger(BaseWS.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		BaseService baseService = new BaseService();
		Connection conn = baseService.getJdbcConnection();
		List<String> nameList = new ArrayList<String>();

		try
		{
			Statement statement = conn.createStatement();
			String sql = "select id,name from operator";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next())
			{
				String name = rs.getString("id");
				name = name + " " + rs.getString("name");
				nameList.add(name);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace(); // 输出出错信息
		}

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>servlet test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Hello servlet!wdw");
		for (String name : nameList)
		{
			out.println(name);
		}
		out.println("</body>");

		logger.info("request.getAuthType: " + request.getAuthType());
		logger.info("request.getRemoteUser: " + request.getRemoteUser());
		logger.info("request.getRemoteHost: " + request.getRemoteHost());
		logger.info("request.getRemotePort: " + request.getRemotePort());
		logger.info("request.getServerPort: " + request.getServerPort());

		logger.info("request.getLocalAddr: " + request.getLocalAddr());
		logger.info("request.getLocalPort: " + request.getLocalPort());

		logger.info("request.getContextPath: " + request.getContextPath());
		logger.info("request.getServletPath: " + request.getServletPath());

		logger.info("request.getMethod: " + request.getMethod());

		logger.info("HttpServletRequest.BASIC_AUTH: "
				+ HttpServletRequest.BASIC_AUTH);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		doPost(request, response);
	}
}
