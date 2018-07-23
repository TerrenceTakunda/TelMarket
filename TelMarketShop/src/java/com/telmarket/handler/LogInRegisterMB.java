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
import com.telmarket.entity.Users;
import com.telmarket.security.EncryptField;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
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
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate date = LocalDate.now();
    private Users user;
    
    public LogInRegisterMB() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setPassword(String password) throws Exception {
        EncryptField field = new EncryptField();
        this.password = field.encrypt(password);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public String register(){
        
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setRegDate(user.getRegDate());
        boolean status = new AddDao().registerUser(user);
        if(status){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Successfull", ""));
            return "index.xhtml?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Failed! Please try again", ""));
            return "register.xhtml?faces-redirect=true";
        }
    }
    
}
