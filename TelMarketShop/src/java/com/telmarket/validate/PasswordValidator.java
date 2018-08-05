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
package com.telmarket.validate;

import com.telmarket.entity.Users;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author terrence
 */

@FacesValidator(value = "com.telmarket.validate.PasswordValidator")
public class PasswordValidator implements Validator {

    Users password = new Users();
    String data = password.getPassword();
            
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        
        if(value != null){
            System.out.println("*********Value is not null*******"+value.toString()); //debug
            boolean status = value.toString().equals(data);
            if(status == false){
                System.out.println("*********Stsus is false*******"+data); //
                FacesMessage message = new FacesMessage("Passwords do not match!");
                message.setSeverity(FacesMessage.SEVERITY_WARN);
                throw new ValidatorException(message);
            } else {
            }
        }
    }
    
}
