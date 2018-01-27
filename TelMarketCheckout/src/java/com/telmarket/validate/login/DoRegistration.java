package com.telmarket.validate.login;

import com.telmarket.validate.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author terrence takunda Munyunguma
 */

public class DoRegistration {
    
    public static boolean add(String name, String email, String password){
        
        int i = 0;
        
        if((name != null) && (email != null) && (password != null)){
        
            PreparedStatement ps;
            Connection con = DatabaseConnection.getConnection();
            
            if(con != null){
            
                try{
                String sql = "INSERT INTO users(name, email, password) VALUES(?,?,?)";
                ps = con.prepareStatement(sql); 
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                i = ps.executeUpdate();
                }
                catch(SQLException e){
                }
                finally{
                    DatabaseConnection.close(con);
                }
            }
        }
        return i>0;
    }
}
