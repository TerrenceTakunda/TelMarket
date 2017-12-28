/*
 *implementing a bank  server that is going to listen to the port and receive a message
 */
package telmarketserver;

import java.io.IOException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

/**
 * @author terrence
 */
public class TelMarketServer implements ISORequestListener{

     public static void main(String[] args) throws ISOException, IOException {
         
         GenericPackager packager = new GenericPackager("Packager.xml");
         //String data = "011008220000001038640000004500020400006708000402000604000340000103050000650002034000A0R0053";
         
         Logger logger = new Logger();
         logger.addListener(new SimpleLogListener(System.out));
         ServerChannel schannel = new ASCIIChannel("localhost",1990,new ISO87APackager());  //server channel takes param ip,port,packager
         ((LogSource)schannel).setLogger(logger,"server-channel-logger");       
         
         ISOServer server = new ISOServer(1990,schannel,null);      //takes param port,serverchannel,null
         server.setLogger(logger, "server-logger");
         server.addISORequestListener(new TelMarketServer());       //assigning a iso listener to server object
         new Thread(server).start();
         
         ISOMsg m = new ISOMsg();
         m.setPackager(packager);
         
         //logISOMsg(m);
         
    }

    @Override
    public boolean process(ISOSource isos, ISOMsg isomsg) {
        
        ISOMsg m = (ISOMsg) isomsg.clone();
        
        try{
            m.setResponseMTI();
            m.setMTI("0110");
            m.set(999,"REPLY TO CLIENT");
            isos.send(m);
        }
        catch(ISOException e){
        
            e.printStackTrace();
        }
        catch(IOException e){
        
            e.printStackTrace();
        }
        
        return true;
    }
   /* public static void logISOMsg(ISOMsg msg){
        
        System.out.println("_______________ISO MESSAGE________________");
        try{
        
            System.out.println("MTI: "+msg.getMTI());
            for(int i=1;i<msg.getMaxField();i++)
                if(msg.hasField(i))
                    System.out.println("Field-"+i+":"+msg.getString(i));
        }
        catch(ISOException e){
        
            e.printStackTrace();
        }
        finally{
        
            System.out.println("________________________________________");
        }
    }*/
}
