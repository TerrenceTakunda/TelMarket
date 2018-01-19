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

package com.telmarket.sendcontrol;

import java.io.IOException;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.XML2003Packager;
import org.jpos.util.LogSource;
import org.jpos.util.SimpleLogListener;

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
    private String name;
    private String cardNumber;
    private String expDate;
    private String pin;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
    // sending payment method
    
    public void sendIt() throws ISOException, IOException{
       ISOMsg incoming = null;
        
        try{
        org.jpos.util.Logger logger = new org.jpos.util.Logger();
        logger.addListener(new SimpleLogListener(System.out));
        
        //GenericPackager packager = new GenericPackager("visapack.xml");
        
        ISOChannel channel = new XMLChannel("localhost",1990, new XML2003Packager());
        ((LogSource)channel).setLogger(logger,"client-logger");
        
       
        
            channel.connect();
            ISOMsg m = new ISOMsg();
            
            m.setMTI("2100");
            m.set(2,cardNumber);                     // PAN                         
            m.set(3,"003000");                       //proccesing code
            m.set(4,total);                          //amount, transaction
            m.set(7,"0102141155");                   // TRANSMISSION DATE AND TIME
            m.set(11, "000001");                     //System trace audit number (STAN)
            m.set(14,expDate);                       // date, expiration YYMM       
            m.set(24, "100");                       //Function Code - Original authorization - amount accurate
            //m.set(25, "");                          //Reason Code
            m.set(26,"5141");                        //merchant type, merchant category
            m.set(41, "00000001");                   //Card acceptor terminal identification
            //Card acceptor name/location
            m.set("43.2", "TelOne TelMarket");      //name
            m.set("43.3", "Robert Mugabe road");        //street
            m.set("43.4", "Harare");                //city
            m.set("43.7", "263");                   //country code
            
            // cardholder jCard Data Elements
            m.set("113.3",name);                     //customer first Name
            m.set("113.11",addLine1);                //adderess line1
            m.set("113.12",addLine2);                //adderess line2
            m.set("113.13",city);                    //city
            //m.set(109,state);                       //reserved for iso use  //state
            m.set("113.17",phone);                  //customer phone
            //m.set(110,country);                     //reserved for iso use  //country
            //m.set("113.16","")    //country code
            
            m.setPackager(new XML2003Packager());
            
            byte[] data = m.pack();
            System.out.println("======================================================================");
            System.out.println(new String(data));
            System.out.println("======================================================================");
    
            channel.send(data);
            
            incoming = channel.receive();
            
            System.out.println(incoming.pack());
            
        }
        catch(ISOException | IOException e){
            
            //e.printStackTrace();
            java.util.logging.Logger.getLogger(FlowBean.class.getName()).log(Level.SEVERE, null, e);
        }
        
        if(incoming.hasField(39)==true){
            
            if("0000".equals(String.valueOf(incoming.getValue(39)))){
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("success.xhtml");
            }
            else if("1810".equals(String.valueOf(incoming.getValue(39)))){
                FacesContext.getCurrentInstance().getExternalContext().redirect("error_amount.xhtml");
            }
            else if("1001".equals(String.valueOf(incoming.getValue(39)))){
                FacesContext.getCurrentInstance().getExternalContext().redirect("error_expired.xhtml");
            }
            else if("1002".equals(String.valueOf(incoming.getValue(39)))){
                FacesContext.getCurrentInstance().getExternalContext().redirect("error_suspicious.xhtml");
            }
            else if("1015".equals(String.valueOf(incoming.getValue(39)))){
                FacesContext.getCurrentInstance().getExternalContext().redirect("error_reqInvalid.xhtml");
            }
            else
                FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
        }
        else
            FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
    }
}
