package com.wendongwei.panda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class BaseService
{
	private static Logger logger = Logger.getLogger(BaseService.class);

	private Context context;

	private DataSource ds;

	private Connection conn;

	public String displayName()
	{
		Connection conn = getJdbcConnection();
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
		logger.info(nameList.get(0));
		return nameList.get(0);
	}

	public Connection getJdbcConnection()
	{
		try
		{
			context = new InitialContext();
			// 通过上下文对象在java默认的命名空间中通过局部数据源的JNDI名称查找数据源
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MyDataSource");
			// 通过数据源连接数据库
			try
			{
				conn = ds.getConnection();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		System.out.println("ds6" + ds);
		System.out.println("conn6" + conn);
		return conn;
	}

	public Context getContext()
	{
		return context;
	}

	public void setContext(Context context)
	{
		this.context = context;
	}

	public DataSource getDs()
	{
		return ds;
	}

	public void setDs(DataSource ds)
	{
		this.ds = ds;
	}

	public Connection getConn()
	{
		return conn;
	}

	public void setConn(Connection conn)
	{
		this.conn = conn;
	}
}
