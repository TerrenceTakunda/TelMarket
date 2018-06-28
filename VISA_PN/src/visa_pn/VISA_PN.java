/*
 * Terrence Takunda Munyunguma [https://github.com/TerrenceTakunda]
 *  Copyright (C) 2018 ttmunyunguma@gmail.com
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package visa_pn;

import java.io.IOException;
import java.util.logging.Level;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

/**
 *
 * @author terrence
 */
public class VISA_PN implements ISORequestListener {

    public static void main(String[] args) throws ISOException {
        
//        GenericPackager packager = new GenericPackager("MasterCardPackager.xml");
        ISOPackager packager = new ISO87APackager();
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ServerChannel sc = new ASCIIChannel("localhost", 1234, packager);

        ((LogSource)sc).setLogger(logger, "VISA_PN-server-logger");
        
        ISOServer server = new ISOServer(1234, sc, null);
        server.setLogger(logger, "server-logger");
        server.addISORequestListener(new VISA_PN());
        
        new Thread(server).start();
        
        ISOMsg m = new ISOMsg();
        m.setPackager(packager);
    }

    @Override
    public boolean process(ISOSource isos, ISOMsg isomsg) {
        
        ISOMsg m = (ISOMsg) isomsg.clone();
        
        try{
            m.setResponseMTI();
            m.set(39, "00");
            
            isos.send(m);
        }
        catch(IOException | ISOException ex){
            java.util.logging.Logger.getLogger(VISA_PN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
