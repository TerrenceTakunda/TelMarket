/*
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

package com.telmarket.flow;

import com.telmarket.logs.TransactionLogs;
import com.telmarket.sendcontrol.SendPayment;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author terrence takunda munyunguma
 */

@Named(value = "flowBean")
@SessionScoped
@ManagedBean
public class FlowBean implements Serializable {
    
    private String total;
    private String fullName;
    private String addLine1;
    private String addLine2;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String cardName;
    private String cardNumber;
    private String expDate;
    private String pin;
    private String transmissionDateAndTime;
    List<String> countryOptions;

    public FlowBean() {
        
        countryOptions = new ArrayList<>();
        countryOptions.add("Zimbabwe");
        countryOptions.add("Botswana");
        countryOptions.add("Malawi");
        countryOptions.add("South Africa");
        countryOptions.add("Zambia");
        countryOptions.add("Namibia");
    
    }
    
    public String send(){
        
        TransactionLogs log = new TransactionLogs();
        SendPayment pay = new SendPayment();
        
        try {
            
            pay.sendIt(this);
        } 
        catch (Exception ex) {
            Logger.getLogger(FlowBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            log.createLog(this);
        }
        
        ISOMsg incoming = pay.getResp();
        
        if(incoming != null){
            if(incoming.hasField(39)==true){
                if(null == String.valueOf(incoming.getValue(39)))return "/responce/error.xhtml";
                
                    else switch (String.valueOf(incoming.getValue(39))) {
                        case "00":
                            return "/responce/success.xhtml";
                        case "13":
                            return "/responce/error_amount.xhtml";
                        case "54":
                            return "/responce/error_expired.xhtml";
                        case "78":
                            return "/responce/error_suspicious.xhtml";
                        case "12":
                            return "/responce/error_reqInvalid.xhtml";
                        case "51":
                            return "/responce/error_insuficientFunds.xhtml";    
                        default:
                            return "/responce/error.xhtml";
                    }
            }
            else
                return "/responce/error.xhtml";  
        }
        else
            return "/responce/error_noresponce.xhtml";
    }

    
/****************************************************************************************************************************/  
    //ACCESSOR
/***@return**************************************************************************************************************************/    

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getCountryOptions() {
        return countryOptions;
    }

    public void setCountryOptions(List<String> countryOptions) {
        this.countryOptions = countryOptions;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTransmissionDateAndTime() {
        return transmissionDateAndTime;
    }

    public void setTransmissionDateAndTime(String transmissionDateAndTime) {
        this.transmissionDateAndTime = transmissionDateAndTime;
    }

}
