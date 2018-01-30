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
package com.telmarket.validate.login;

import com.telmarket.validate.LoginValidator;
import com.telmarket.validate.util.SessionUtils;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author terrence takunda munyunguma
 */
@Named(value = "loginOrRegister")
@SessionScoped
@ManagedBean
public class LoginOrRegister implements Serializable {

    private String name;
    private String email;
    private String password;
    
    public LoginOrRegister() {
    }
    
    public String login() throws Exception{
    
        boolean valid = LoginValidator.validate(email, password);
	if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", email);
            return "/checkoutFlow/shipping.xhtml?faces-redirect=true";
	} 
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Username or Password, Please Retry", ""));
            return "/signInSignUp/login.xhtml";
	}
    }
    
    public String logout() {
        
	HttpSession session = SessionUtils.getSession();
	session.invalidate();
	return "/signInSignUp/login.xhtml?faces-redirect=true";
    } 
    
    public String createAccount() throws Exception{
    
        boolean signup = DoRegistration.add(name, email, password);
        if(signup){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Successful", ""));
            return "/signInSignUp/login.xhtml?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went wrong. your account was not created, Please try again", ""));
            return "/signInSignUp/register.xhtml";
        }    
    }
    
    
    
/******************************************************************************************************************/    
/***@return***************************************************************************************************************/    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
