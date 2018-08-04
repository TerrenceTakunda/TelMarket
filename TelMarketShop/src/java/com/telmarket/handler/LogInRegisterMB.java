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
package com.telmarket.handler;

import com.telmarket.dao.AddDao;
import com.telmarket.dao.ListDao;
import com.telmarket.entity.Users;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author terrence
 */
@ManagedBean
@Named(value = "logInRegisterMB")
@SessionScoped
public class LogInRegisterMB implements Serializable {

    /**
     * Creates a new instance of LogInRegisterMB
     */
    Users user = new Users();
    
    public LogInRegisterMB() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public String register() throws Exception{
        
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setContactNo(user.getContactNo());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setRegDate(user.getRegDate());
        boolean status = new AddDao().registerUser(user);
        if(status){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration Successful", "Please Log In"));
            return "index.xhtml?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration Failed!", "Please try again"));
            return "register.xhtml?faces-redirect=true";
        }
    }
    
    public String login() throws Exception{
        
//        String firstName = user.getFirstName();
        String email = user.getEmail();
        String password = user.getPassword();
//        System.out.println("1*************"+email+" "+password+"****************"); //debug
        boolean status = new ListDao().checkUser(email, password);
        if(status){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login Successfull", "Welcome "+user.getFirstName()));
            return "index.xhtml?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login Failed!", "Please try again"));
            return "login.xhtml?faces-redirect=true";
        }
    }
    
}
