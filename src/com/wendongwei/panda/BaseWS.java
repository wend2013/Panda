package com.wendongwei.panda;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseWS extends HttpServlet
{
	private static final long serialVersionUID = -4139401262935956748L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>servlet test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Hello servlet!wdw");
		out.println("</body>");

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		doPost(request, response);
	}
}
