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
 * @author terrence
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
    
    public String login(){
    
        boolean valid = LoginValidator.validate(email, password);
	if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", email);
            return "/checkoutFlow/shipping.xhtml?faces-redirect=true";
	} 
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username or Passowrd", "Please Retry"));
            return "/signInSignUp/login.xhtml";
	}
    }
    
    public String logout() {
        
	HttpSession session = SessionUtils.getSession();
	session.invalidate();
	return "/signInSignUp/login.xhtml?faces-redirect=true";
    } 
    
    public String createAccount(){
    
        boolean signup = DoRegistration.add(name, email, password);
        if(signup)
            return "/signInSignUp/login.xhtml?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Something went wrong. your account was not created", "Please try again"));
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
