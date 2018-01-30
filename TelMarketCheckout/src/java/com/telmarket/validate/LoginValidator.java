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
package com.telmarket.validate;

import com.telmarket.security.EncryptField;
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
    
    public static boolean validate(String email, String password) throws Exception {
        
	Connection con = DatabaseConnection.getConnection();
	PreparedStatement ps;
        EncryptField field = new EncryptField();

        if(con != null){
            try {
                ps = con.prepareStatement("Select email, password from users where email = ? and password = ?");
                ps.setString(1, email);
                ps.setString(2, field.encrypt(password));

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
