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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
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
@Named(value = "sendPayment")
@SessionScoped
@ManagedBean
public class SendPayment implements Serializable {

    private ISOMsg incoming = null;
    private String transmissionDateAndTime = null;
    
    public SendPayment() {
     
    }
    
    public void sendIt(FlowBean bean) throws ISOException, IOException{
        
       try{
        org.jpos.util.Logger logger = new org.jpos.util.Logger();
        logger.addListener(new SimpleLogListener(System.out));
        
        ISOChannel channel = new XMLChannel("localhost",1990, new XML2003Packager());
        ((LogSource)channel).setLogger(logger,"client-logger");

        channel.connect();
        
        ISOMsg m = new ISOMsg();
            
        m.setMTI("2100");
        m.set(2,bean.getCardNumber());                      
        m.set(3,"003000");
        m.set(4,bean.getTotal());
        m.set(7,transactionTime());
        m.set(11,"000001");
        m.set(14,bean.getExpDate());   
        m.set(24,"100");
        m.set(26,"5141");
        m.set(41,"00000001");
            
        //Card acceptor name/location
        m.set("43.2","TelOne TelMarket");
        m.set("43.3","Robert Mugabe road");
        m.set("43.4","Harare");
        m.set("43.7","263");
        
        //m.set(53, bean.getPin());
            
        // cardholder jCard Data Elements
        m.set("113.3",bean.getCardName());
        m.set("113.11",bean.getAddLine1());
        m.set("113.12",bean.getAddLine2());
        m.set("113.13",bean.getCity());
        m.set("113.17",bean.getPhone());
        
        
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
            
            java.util.logging.Logger.getLogger(FlowBean.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public String transactionTime(){
    
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMDDHHmmss");
        transmissionDateAndTime = dateFormat.format(now);
        return transmissionDateAndTime;
    }
    
    
/*****************************************************************************************************************************/    
/***@return****************************************************************************************************************/
    public ISOMsg getIncoming() {
        return incoming;
    }

    public void setIncoming(ISOMsg incoming) {
        this.incoming = incoming;
    }
    
}
