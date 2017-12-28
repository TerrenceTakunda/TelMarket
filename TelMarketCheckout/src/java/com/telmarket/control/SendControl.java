package com.telmarket.control;

import com.telmarket.service.SendPayment;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jpos.iso.ISOException;

/**
 *
 * @author terrence
 */
public class SendControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        
        try{
            SendPayment.sendIt();
        } catch (ISOException ex) {
            Logger.getLogger(SendControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("Success.jsp");
    }
}
