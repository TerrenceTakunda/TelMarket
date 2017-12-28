/*
 */
package com.telmarket.checkout;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
//import javax.faces.flow.FlowScoped;

/**
 *
 * @author terrence
 */
@Named(value = "paymentDetails")
@ApplicationScoped
@ManagedBean(eager=true)
public class PaymentDetails {
    
    private String name;
    private String cardNumber;
    private String expDate;
    private String pin;

    public PaymentDetails() {
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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
    
    
}
