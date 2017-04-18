package com.jtds.jtdsDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description: All Rights Reserved.
 * 
 * @version 1.0 2015-2-26 下午3:07:35 by 张富成（fc.zhang@zuche.com）创建
 */
public class ConnectionDemo {

	/**
	 * Description: 创建连接测试
	 * 
	 * @Version1.0 2015-2-26 下午3:07:38 by 张富成（fc.zhang@zuche.com）创建
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 *
	 */
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		// jtds
		Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();




  //第三个分支



		String url = "jdbc:jtds:sqlserver://10.101.21.12:6666;DatabaseName=CDMS_NEW";
		String user = "cdms";
		String password = "cdms";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "select top 10 * from t_scd_order"; // titles为表名;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("id=" + rs.getString("id") + " passenger="
						+ rs.getString("passenger"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 关闭连接
		closeConnection(stmt, conn, rs);
	}

	/**
	 * Description: 关闭数据库连接
	 * @Version1.0 2015-2-26 下午3:37:59 by 张富成（fc.zhang@zuche.com）创建
	 * @param ps
	 * @param conn
	 * @param rs
	 */
	public static void closeConnection(Statement ps, Connection conn,ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException sqlerror) {
			sqlerror.printStackTrace();
		}
	}
}
