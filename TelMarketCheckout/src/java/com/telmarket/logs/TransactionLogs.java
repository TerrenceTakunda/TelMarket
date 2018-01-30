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
package com.telmarket.logs;

import com.telmarket.security.EncryptField;
import com.telmarket.sendcontrol.FlowBean;
import com.telmarket.validate.util.DatabaseConnection;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author terrence takunda munyunguma
 */
@Named(value = "transactionLogs")
@SessionScoped
@ManagedBean
public class TransactionLogs implements Serializable {
    
    public TransactionLogs() {
    }
    
    public void createLog(FlowBean bean){
    
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps;
        EncryptField field = new EncryptField();
        
        if(con != null){
            try{
                String sql = "INSERT INTO transaction_log(full_name, address_line1, address_line2,"
                        + " city, state, country, phone, card_name, PAN, expiration_date, amount,"
                        + " transaction_date_and_time) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                
                ps.setString(1, bean.getFullName());
                ps.setString(2, bean.getAddLine1());
                ps.setString(3, bean.getAddLine2());
                ps.setString(4, bean.getCity());
                ps.setString(5, bean.getState());
                ps.setString(6, bean.getCountry());
                ps.setString(7, bean.getPhone());
                ps.setString(8, bean.getCardName());
                ps.setString(9, field.encrypt(bean.getCardNumber()));
                ps.setString(10, bean.getExpDate());
                ps.setString(11, bean.getTotal());
                ps.setString(12, transactionTime());
                
                ps.executeUpdate();
            
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            finally{
                DatabaseConnection.close(con);
            }
        }
    }
    
    public String transactionTime(){
    
        Date date = new Date();
        return String.valueOf(date);
    }
    
}
