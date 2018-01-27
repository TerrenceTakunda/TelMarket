package com.telmarket.validate;

import com.telmarket.validate.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author terrence takunda munyunguma
 */
public class LoginValidator {
    
    public static boolean validate(String email, String password) {
        
	Connection con = DatabaseConnection.getConnection();
	PreparedStatement ps;

        if(con != null){
            try {
                ps = con.prepareStatement("Select email, password from users where email = ? and password = ?");
                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                
                if (rs.next())
                    return true;
            } 
            catch (SQLException ex) {
                System.out.println("Login error -->" + ex.getMessage());
                return false;
            } 
            finally {
                DatabaseConnection.close(con);
            }
        }
        else
            return false;
    return false;
    }
}
