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

import com.telmarket.flow.FlowBean;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMUX;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

/**
 *
 * @author terrence takunda munyunguma
 */
@Named(value = "sendPayment")
@SessionScoped
@ManagedBean
public class SendPayment implements Serializable {

    private ISOMsg resp = null;
//    private GenericPackager packager;
    private ISO87APackager packager;
    private ASCIIChannel channel;
    private ISOMUX mux;
    static String HOST = "127.0.0.1";
    static int PORT = 1990;
    static int TIMEOUT = 30000;
    static DateFormat date = new SimpleDateFormat("MMdd");
    static DateFormat time = new SimpleDateFormat("HHmmss");
    static DateFormat temp = new SimpleDateFormat("MMddHHmmss");
    static String bit43 = "TelMarket_Online_Store Harare_Harare ZWE";

    public SendPayment() {
        try {
//            this.packager = new GenericPackager("MasterCardPackager.xml");
            this.packager = new ISO87APackager();
            this.channel = new ASCIIChannel(HOST, PORT, packager);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SendPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() throws IOException {

    }

    public void sendIt(FlowBean bean) {

        try {
            System.out.println(">> CONNECTION");
            this.channel.setHost(HOST);
            this.channel.setPort(PORT);
            this.channel.connect();

            this.mux = new ISOMUX(channel);
            Thread muxThread = new Thread(mux);
            muxThread.start();

            System.out.println("Connected with " + channel.getHost() + ":" + channel.getPort() + " ? " + mux.isConnected());
            System.out.println(channel.getSocket());
            System.out.println(">> SENDING");

            Logger logger = new Logger();
            logger.addListener(new SimpleLogListener(System.out));
            ((LogSource) channel).setLogger(logger, "channel-logger");
            ISOMsg req = authorizationRequest(bean);
            req.setPackager(packager);
            System.out.println("Request message: " + req);        //for debug
            byte[] data = req.pack();
            System.out.println("Request byte message: " + data);
            req.setDirection(ISOMsg.OUTGOING);

            System.out.println("MUX Connection status: " + mux.isConnected());   //for debug

            resp = mux.request(req, TIMEOUT);
            System.out.println("Success");      //for debug

            if (resp != null) {

                System.out.println("Responce message: " + String.valueOf(resp));   //for debug
                
                if ((resp.hasField(39)) && ("00".equals(String.valueOf(resp.getValue(39))))) {
                    ISOMsg settle = settlementRequest(bean);
                    settle.setPackager(packager);
                    System.out.println(settle);    //for debug
                    settle.setDirection(ISOMsg.OUTGOING);
                    mux.send(settle);
                }
            } else {
                ISOMsg rev = reversalRequest(bean);
                rev.setPackager(packager);
                System.out.println(rev);        //for debug
                rev.setDirection(ISOMsg.OUTGOING);
                mux.send(rev);
            }

        } catch (ISOException | IOException ex) {
            java.util.logging.Logger.getLogger(SendPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ISOMsg authorizationRequest(FlowBean bean) {

        ISOMsg message = new ISOMsg();
        String bit120 = String.valueOf(bean.getCardName() + bean.getAddLine1() + bean.getCity() + bean.getCountry() + bean.getPhone());

        try {
            message.setMTI("0100");
            message.set(2, bean.getCardNumber());     //to be provided from form
            message.set(3, "003000");
//            message.set("3.1", "00");       //purchase
//            message.set("3.2", "30");       //from account : credit
//            message.set("3.3", "00");       //to account : default
            message.set(4, bean.getTotal());        //to be provided by shopping cart
            message.set(7, temp.format(new Date()));
//            message.set("7.1", date.format(new Date()));        //MMdd
//            message.set("7.2", time.format(new Date()));        //HHmmss
            message.set(11, String.valueOf(new Random().nextInt(999999)));  //STAN to be uique for the day. Use time stamp preferably
            message.set(14, bean.getExpDate());    //to be provided from form
            message.set(18, "4814");    //Telecommunication Services including but not limited to prepaid phone services and recurring phone services
            message.set(22, "812");
//            message.set("22.1", "81");       //PAN entry via electronic commerce, including chip.
//            message.set("22.2", "2");        //Terminal does not have PIN entry capability
            message.set(32, "123456");      //A MasterCard customer ID number that MasterCard assigned to the entity acting as the acquiring institution for a transaction. Contain a six-digit customer ID number assigned by MasterCard that identifies the institution acting as the “acquiring bank” or “merchant bank” for a transaction.
            message.set(42, "2345hfts=5682jf"); //Number assigned by the acquirer. required for POS transaction types containing DE 3 (Processing Code), subfield 1 (Cardholder Transaction Type Code), values 00 (Purchase)
            message.set(43, bit43);
//            message.set("43.1", "TelMarket_Online_Store");  //Merchant Name ("Doing Business As" name)
//            message.set("43.2", " ");   //Space
//            message.set("43.3", "Harare_Harare");  //Merchant's City
//            message.set("43.4", " ");   //Space
//            message.set("43.5", "ZWE"); //Merchant's State (or Country Code, if not U.S.)
//            message.set("48.42.01", "210");      //Channel encryption; cardholder certificate not used; UCAF data collection is not supported
//            message.set("48.47", "MC-MPG/W");   //indicates that the transaction is a MasterCard Payment Gateway transaction
            message.set(49, "840");     //United States Dollar
            message.set(61, "12310006600716");
//            message.set("61.1", "1");       //Unattended terminal
//            message.set("61.3", "2");       //Off premises of card acceptor facility
//            message.set("61.4", "5");       //Electronic order (home PC, Internet, mobile phone, PDA)
//            message.set("61.5", "1");       //Card not present
//            message.set("61.6", "0");       //Terminal/operator has no card capture capability
//            message.set("61.7", "0");       //Normal request (original presentment)
//            message.set("61.8", "0");       //No security concern
//            message.set("61.10", "6");      //Authorized Level 6 CAT: Electronic commerce
//            message.set("61.11", "6");      //Key entry only
//            message.set("61.12", "00");
//            message.set("61.13", "716");    //country code Zimbabwe
            message.set(120, bit120);
//            message.set("120.1", bean.getCardName());    //full name on credid card
//            message.set("120.2", bean.getAddLine1());      //customer address
//            message.set("120.3", bean.getCity());     //city
//            message.set("120.4", bean.getCountry());       //country
//            message.set("120.5", bean.getPhone());   //phone number

            return message;

        } catch (ISOException ex) {
            java.util.logging.Logger.getLogger(SendPayment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static ISOMsg reversalRequest(FlowBean bean) {

        ISOMsg message = new ISOMsg();
        String bit120 = String.valueOf(bean.getCardName() + bean.getAddLine1() + bean.getCity() + bean.getCountry() + bean.getPhone());

        try {
            message.setMTI("0400");
            message.set(2, bean.getCardNumber());     //to be provided from form
            message.set(3, "003000");
//            message.set("3.1", "00");       //purchase
//            message.set("3.2", "30");       //from account : credit
//            message.set("3.3", "00");       //to account : default
            message.set(4, bean.getTotal());        //to be provided by shopping cart
            message.set(7, temp.format(new Date()));
//            message.set("7.1", date.format(new Date()));        //MMdd
//            message.set("7.2", time.format(new Date()));        //HHmmss
            message.set(11, String.valueOf(new Random().nextInt(999999)));  //STAN to be uique for the day. Use time stamp preferably
            message.set(14, bean.getExpDate());    //to be provided from form
            message.set(18, "4814");    //Telecommunication Services including but not limited to prepaid phone services and recurring phone services
            message.set(22, "812");
//            message.set("22.1", "81");       //PAN entry via electronic commerce, including chip.
//            message.set("22.2", "2");        //Terminal does not have PIN entry capability
            message.set(32, "123456");      //A MasterCard customer ID number that MasterCard assigned to the entity acting as the acquiring institution for a transaction. Contain a six-digit customer ID number assigned by MasterCard that identifies the institution acting as the “acquiring bank” or “merchant bank” for a transaction.
            message.set(39, "68");
            message.set(42, "2345hfts=5682jf"); //Number assigned by the acquirer. required for POS transaction types containing DE 3 (Processing Code), subfield 1 (Cardholder Transaction Type Code), values 00 (Purchase)
            message.set(43, bit43);
//            message.set("43.1", "TelMarket_Online_Store");  //Merchant Name ("Doing Business As" name)
//            message.set("43.2", " ");   //Space
//            message.set("43.3", "Harare_Harare");  //Merchant's City
//            message.set("43.4", " ");   //Space
//            message.set("43.5", "ZWE"); //Merchant's State (or Country Code, if not U.S.)
//            message.set("48.42.01", "210");      //Channel encryption; cardholder certificate not used; UCAF data collection is not supported
//            message.set("48.47", "MC-MPG/W");   //indicates that the transaction is a MasterCard Payment Gateway transaction
            message.set(49, "840");     //United States Dollar
            message.set(61, "12310006600716");
//            message.set("61.1", "1");       //Unattended terminal
//            message.set("61.3", "2");       //Off premises of card acceptor facility
//            message.set("61.4", "5");       //Electronic order (home PC, Internet, mobile phone, PDA)
//            message.set("61.5", "1");       //Card not present
//            message.set("61.6", "0");       //Terminal/operator has no card capture capability
//            message.set("61.7", "0");       //Normal request (original presentment)
//            message.set("61.8", "0");       //No security concern
//            message.set("61.10", "6");      //Authorized Level 6 CAT: Electronic commerce
//            message.set("61.11", "6");      //Key entry only
//            message.set("61.12", "00");
//            message.set("61.13", "716");    //country code Zimbabwe
            message.set(120, bit120);
//            message.set("120.1", bean.getCardName());    //full name on credid card
//            message.set("120.2", bean.getAddLine1());      //customer address
//            message.set("120.3", bean.getCity());     //city
//            message.set("120.4", bean.getCountry());       //country
//            message.set("120.5", bean.getPhone());   //phone number

            return message;

        } catch (ISOException ex) {
            java.util.logging.Logger.getLogger(SendPayment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static ISOMsg settlementRequest(FlowBean bean) {

        ISOMsg message = new ISOMsg();
        String bit120 = String.valueOf(bean.getCardName() + bean.getAddLine1() + bean.getCity() + bean.getCountry() + bean.getPhone());

        try {
            message.setMTI("0500");
            message.set(2, bean.getCardNumber());     //to be provided from form
            message.set(3, "003000");
//            message.set("3.1", "00");       //purchase
//            message.set("3.2", "30");       //from account : credit
//            message.set("3.3", "00");       //to account : default
            message.set(4, bean.getTotal());        //to be provided by shopping cart
            message.set(7, temp.format(new Date()));
//            message.set("7.1", date.format(new Date()));        //MMdd
//            message.set("7.2", time.format(new Date()));        //HHmmss
            message.set(11, String.valueOf(new Random().nextInt(999999)));  //STAN to be uique for the day. Use time stamp preferably
            message.set(14, bean.getExpDate());    //to be provided from form
            message.set(18, "4814");    //Telecommunication Services including but not limited to prepaid phone services and recurring phone services
            message.set(22, "812");
//            message.set("22.1", "81");       //PAN entry via electronic commerce, including chip.
//            message.set("22.2", "2");        //Terminal does not have PIN entry capability
            message.set(32, "123456");      //A MasterCard customer ID number that MasterCard assigned to the entity acting as the acquiring institution for a transaction. Contain a six-digit customer ID number assigned by MasterCard that identifies the institution acting as the “acquiring bank” or “merchant bank” for a transaction.
            message.set(42, "2345hfts=5682jf"); //Number assigned by the acquirer. required for POS transaction types containing DE 3 (Processing Code), subfield 1 (Cardholder Transaction Type Code), values 00 (Purchase)
            message.set(43, bit43);
//            message.set("43.1", "TelMarket_Online_Store");  //Merchant Name ("Doing Business As" name)
//            message.set("43.2", " ");   //Space
//            message.set("43.3", "Harare_Harare");  //Merchant's City
//            message.set("43.4", " ");   //Space
//            message.set("43.5", "ZWE"); //Merchant's State (or Country Code, if not U.S.)
//            message.set("48.42.01", "210");      //Channel encryption; cardholder certificate not used; UCAF data collection is not supported
//            message.set("48.47", "MC-MPG/W");   //indicates that the transaction is a MasterCard Payment Gateway transaction
            message.set(49, "840");     //United States Dollar
            message.set(61, "12310006600716");
//            message.set("61.1", "1");       //Unattended terminal
//            message.set("61.3", "2");       //Off premises of card acceptor facility
//            message.set("61.4", "5");       //Electronic order (home PC, Internet, mobile phone, PDA)
//            message.set("61.5", "1");       //Card not present
//            message.set("61.6", "0");       //Terminal/operator has no card capture capability
//            message.set("61.7", "0");       //Normal request (original presentment)
//            message.set("61.8", "0");       //No security concern
//            message.set("61.10", "6");      //Authorized Level 6 CAT: Electronic commerce
//            message.set("61.11", "6");      //Key entry only
//            message.set("61.12", "00");
//            message.set("61.13", "716");    //country code Zimbabwe
            message.set(120, bit120);
//            message.set("120.1", bean.getCardName());    //full name on credid card
//            message.set("120.2", bean.getAddLine1());      //customer address
//            message.set("120.3", bean.getCity());     //city
//            message.set("120.4", bean.getCountry());       //country
//            message.set("120.5", bean.getPhone());   //phone number

            return message;

        } catch (ISOException ex) {
            java.util.logging.Logger.getLogger(SendPayment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * **************************************************************************************************************************
     */
    /**
     * @return ***************************************************************************************************************
     */
    public ISOMsg getResp() {
        return resp;
    }

    public void setResp(ISOMsg resp) {
        this.resp = resp;
    }
}
