package com.telmarket.sendcontrol;

import java.io.IOException;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.SimpleLogListener;

/**
 *
 * @author terrence
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
    
    public void sendIt() throws ISOException, IOException{
       
        FlowBean PAN = new FlowBean();
        
        try{
        org.jpos.util.Logger logger = new org.jpos.util.Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ISOChannel channel = new ASCIIChannel("localhost",1990,new ISO87APackager());
        ((LogSource)channel).setLogger(logger,"client-logger");
        
       
        
            channel.connect();
            ISOMsg m = new ISOMsg();
            
            m.setMTI("0100");
            m.set(2,cardNumber);                     // PAN                         
            m.set(3,"003000");                       //proccesing code
            m.set(4,total);                          //amount, transaction
            m.set(7,"0102141155");                   // transmisson date and time MMDDhhmmss
            m.set(14,expDate);                       // date, expiration YYMM
            m.set(18,"5141");                        //merchant type, merchant category
            m.set(19,"263");                         //Acquiring instituition country code
            m.set(20,"263");                         //PAN extended country code
            m.set(59,name);                          //reserved, national use    //cardName
            m.set(105,addLine1);                    //reserved for iso use  //adderess line1
            m.set(106,addLine2);                    //reserved for iso use  //adderess line2
            m.set(107,city);                        //reserved for iso use  //city
            m.set(108,state);                       //reserved for iso use  //state
            m.set(109,country);                     //reserved for iso use  //country
            //m.set(110,"");
            
            m.setPackager(new ISO87APackager());
            
            byte[] data = m.pack();
            System.out.println("======================================================================");
            System.out.println(new String(data));
            System.out.println("======================================================================");
    
            channel.send(m);
            channel.send(data);
            
            ISOMsg incoming = channel.receive();
            System.out.println((incoming.pack()));
            
        }
        catch(ISOException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("success.xhtml");
    }
}
