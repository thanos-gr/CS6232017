package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;


public class Transaction {
	
	Connection conn = null;
	
	Connection getDBConnection () {
		if (this.conn == null) {
			/*
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("root");
			dataSource.setPassword("12345");
			dataSource.setServerName("localhost:3306/EXERCISE4");
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EXERCISE4", "root", "12345");
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
		return conn;
	}
	
	void deleteProduct(String prod_id) {
		Connection conn = getDBConnection ();
		PreparedStatement stmt;
		
		String deleteSql1 = "delete from stock where prod_id = ?";
		String deleteSql2 = "delete from product where prod_id = ?";
		try {
			stmt = conn.prepareStatement(deleteSql1);
			stmt.setString(1, prod_id);
			stmt.execute();
			stmt.close();
			
			stmt = conn.prepareStatement(deleteSql2);
			stmt.setString(1, prod_id);
			stmt.execute();
			stmt.close();
						
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
	}
	
	
	void deleteDepot(String dep_id) {
		Connection conn = getDBConnection ();
		PreparedStatement stmt;

		String deleteSql1 = "delete from stock where dep_id = ?";
		String deleteSql2 = "delete from depot where dep_id = ?";
		try {
			stmt = conn.prepareStatement(deleteSql1);
			stmt.setString(1, dep_id);
			stmt.execute();
			stmt.close();
			
			stmt = conn.prepareStatement(deleteSql2);
			stmt.setString(1, dep_id);
			stmt.execute();
			stmt.close();			
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}		
	}
	
	void modifyProduct(String old_id, String new_id) {
		Connection conn = getDBConnection ();
		PreparedStatement stmt;

		String sql1 = "update product set prod_id = ? where prod_id = ?";
		String sql2 = "update stock set prod_id = ? where prod_id = ?";
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, new_id);
			stmt.setString(2, old_id);
			stmt.execute();
			stmt.close();
			
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1, new_id);
			stmt.setString(2, old_id);
			stmt.execute();
			stmt.close();			
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}		
	}
	
	void modifyDepot(String old_id, String new_id) {
		Connection conn = getDBConnection ();
		PreparedStatement stmt;

		String sql1 = "update depot set dep_id = ? where dep_id = ?";
		String sql2 = "update stock set dep_id = ? where dep_id = ?";
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, new_id);
			stmt.setString(2, old_id);
			stmt.execute();
			stmt.close();
			
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1, new_id);
			stmt.setString(2, old_id);
			stmt.execute();
			stmt.close();			
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}		
	}
	
		
	public static void main(String[] args) {
		Transaction trans = new Transaction ();
		
		Connection conn = trans.getDBConnection ();
		trans.deleteProduct("p1");
		trans.deleteDepot("d1");
		trans.modifyProduct("p2", "p4");
		trans.modifyDepot("d2", "d22");
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
 
