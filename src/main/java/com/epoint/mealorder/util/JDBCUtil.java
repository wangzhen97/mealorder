package com.epoint.mealorder.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;


public class JDBCUtil {

	public static Connection getConnection() {
		DruidDataSource dataSource=DataSourceUtil.getDruidDataSource();
		Connection connection=null;
		try {
			connection=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
    
	   public static void closeCon(Connection con, ResultSet rs, PreparedStatement ps) throws SQLException {
	        if (rs != null) {
	            rs.close();
	        }
	        if (ps != null) {
	            ps.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	
	
	
	
}
