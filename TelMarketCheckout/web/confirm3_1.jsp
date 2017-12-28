<%-- 
    Document   : confirm3_1
    Created on : Dec 26, 2017, 4:57:48 PM
    Author     : terrence
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.telmarket.checkout.Shipping;"
        import="com.telmarket.checkout.PaymentDetails;" import="com.telmarket.checkout.Value"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make payment</title>
    </head>
    <body>
        <form action="SendControl" method="post" >
            
            Thank you for your order 
            <% Shipping name = new Shipping(); %>
            <%= name.getFullName() %> <br/><br/>
            After this transaction, your 
            <% PaymentDetails cardName = new PaymentDetails(); %>
            <%= cardName.getName() %> will be billed for $
            <% Value money = new Value(); %>
            <%= money.getTotal() %><br/>
            Please go ahead and provide your credit card PIN number and make your purchase.<br/><br/>
            <input type="submit" />
        </form>
    </body>
</html>
