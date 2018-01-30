/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2017 jPOS Software SRL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.telmarket.validate.login;

import com.telmarket.security.EncryptField;
import com.telmarket.validate.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author terrence takunda Munyunguma
 */

public class DoRegistration {
    
    public static boolean add(String name, String email, String password) throws Exception{
        
        int i = 0;
        EncryptField field = new EncryptField();
        
        if((name != null) && (email != null) && (password != null)){
        
            PreparedStatement ps;
            Connection con = DatabaseConnection.getConnection();
            
            if(con != null){
            
                try{
                String sql = "INSERT INTO users(name, email, password) VALUES(?,?,?)";
                ps = con.prepareStatement(sql); 
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, field.encrypt(password));
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
