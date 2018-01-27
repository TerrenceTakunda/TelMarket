package com.telmarket.validate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author terrence takunda munyunguma
 */

public class DatabaseConnection {
    
    public static Connection getConnection() {
        
	try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TelMarket", "root", "");
            return con;
	} 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database Connection Error -->" + ex.getMessage());
            return null;
	}
    }
    
    public static void close(Connection con) {
        
	try {
            con.close();
	} 
        catch (SQLException ex) {
	}
    }
}
