package com.mystore_admin.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import com.alibaba.druid.pool.DruidDataSourceFactory;


public class JdbcUtils {

	private static DataSource bs = null;

	public DataSource getDataSource() {
		return bs;
	}

	private static QueryRunner qr = null;

	public QueryRunner getQueryRunner() {
		return qr;
	}

	static {
		Properties params = new Properties();
		InputStream is = JdbcUtils.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
		
		try {
			params.load(is);
			bs = DruidDataSourceFactory.createDataSource(params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		qr = new QueryRunner(bs);
	}
	private Connection conn = null;


	public Connection getConnection() {
		try {
			conn = bs.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close(Connection connection,PreparedStatement ps,ResultSet rs) {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
	}
	
	public void close(){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
