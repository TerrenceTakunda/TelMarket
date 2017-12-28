package com.telmarket.service;

import com.telmarket.checkout.Value;
import com.telmarket.checkout.Shipping;
import com.telmarket.checkout.PaymentDetails;

public class MsgConstants {

    private static final Value total = new Value();
    private static final Shipping fullName = new Shipping();
    private static final Shipping addLine1 = new Shipping();
    private static final Shipping addLine2 = new Shipping();
    private static final Shipping city = new Shipping();
    private static final Shipping state = new Shipping();
    private static final Shipping country = new Shipping();
    private static final Shipping phone = new Shipping();
    private static final PaymentDetails name = new PaymentDetails();
    private static final PaymentDetails cardNumber = new PaymentDetails();
    private static final PaymentDetails expDate = new PaymentDetails();
    private static final PaymentDetails pin = new PaymentDetails();
    
    
    
    public static final String MTI_AUTH_REQ = "0100";   
    public static final String PAN = cardNumber.getCardNumber();                                        // PAN
    public static final String PROCESSING_CODE = "003000";                      // Proccessing code
    public static final String AMNT_TRANSCTN = total.getTotal();                              // Amount, transaction
    public static final String TRANSN_DATE_TIME = "0412085532";                 // Transmission date and time (MMDDhhmmss)
    public static final String EXP_DATE_CARD = expDate.getExpDate();                              // Expiry date card (YYMM)
    public static final String MERCH_TYPE = "5414";                             // Merchant type/category 5414->internet payment portal credit card
    public static final String ACQ_INST_COUTNRY_CODE = "263";                   // Acquiring instituition country code
    public static final String PAN_EXT_COUNTRY_CODE = "263";                    // PAN extended country code
    public static final String ADDITIONAL_DATA = fullName.getFullName();       // Reserved for national use---->Full Name
    public static final String ADDITIONAL_DATA_1 = addLine1.getAddLine1();      //Reserved for Iso use----->address line 1
    public static final String ADDITIONAL_DATA_2 = addLine2.getAddLine2();      //Reserved for Iso use----->address line 2
    public static final String ADDITIONAL_DATA_3 = city.getCity();              //Reserved for Iso use----->city
    public static final String ADDITIONAL_DATA_4 = state.getState();            //Reserved for Iso use----->state
    public static final String ADDITIONAL_DATA_5 = country.getCountry();        //Reserved for Iso use----->country
    public static final String ADDITIONAL_DATA_6 = phone.getPhone();            //Reserved for Iso use----->phone number
}
