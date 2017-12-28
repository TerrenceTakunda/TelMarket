package com.telmarket.service;

import java.io.IOException;
import java.util.Arrays;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

/**
 *
 * @author terrence
 */
public class SendPayment {
    
    public static void sendIt() throws ISOException, IOException{
       
        try{
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ISOChannel channel = new ASCIIChannel("localhost",1990,new ISO87APackager());
        ((LogSource)channel).setLogger(logger,"client-logger");
        
       
        
            channel.connect();
            ISOMsg m = new ISOMsg();
            
            m.setMTI(MsgConstants.MTI_AUTH_REQ);
            m.set(2,MsgConstants.PAN);                          
            m.set(3,MsgConstants.PROCESSING_CODE);              
            m.set(4,MsgConstants.AMNT_TRANSCTN);                 
            m.set(7,MsgConstants.TRANSN_DATE_TIME);             
            m.set(14,MsgConstants.EXP_DATE_CARD);               
            m.set(18,MsgConstants.MERCH_TYPE);                 
            m.set(19,MsgConstants.ACQ_INST_COUTNRY_CODE);       
            m.set(20,MsgConstants.PAN_EXT_COUNTRY_CODE);        
            m.set(59,MsgConstants.ADDITIONAL_DATA);  
            m.set(105,MsgConstants.ADDITIONAL_DATA_1);
            m.set(106,MsgConstants.ADDITIONAL_DATA_2);
            m.set(107,MsgConstants.ADDITIONAL_DATA_3);
            m.set(108,MsgConstants.ADDITIONAL_DATA_4);
            m.set(109,MsgConstants.ADDITIONAL_DATA_5);
            m.set(110,MsgConstants.ADDITIONAL_DATA_6);
            
            m.setPackager(new ISO87APackager());
            
            byte[] data = m.pack();
            System.out.println("======================================================================");
            System.out.println(new String(data));
            System.out.println(MsgConstants.ADDITIONAL_DATA);
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
    }
}
