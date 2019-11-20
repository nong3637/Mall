package model;

import java.sql.*;

public class DBConnection {
	public Connection getConnection() throws SQLException {
		String jdbc_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName(jdbc_driver);
			Connection conn = DriverManager.getConnection(db_url, "system", "hrdkorea");
			return conn;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void closeDBResource(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void closeDBResource(ResultSet rs, Statement stmt, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null) {
			try {
				stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void closeDBResource(Statement stmt, Connection conn) {
		if(stmt!=null) {
			try {
				stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
