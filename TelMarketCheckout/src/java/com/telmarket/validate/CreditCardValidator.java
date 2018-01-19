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
package com.telmarket.validate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author terrence takunda munyunguma
 */

@FacesValidator(value = "com.telmarket.validate.CreditCardValidator")
public class CreditCardValidator implements Validator {

    @Override
    public void validate(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        
        if(value != null){
            
            //here I need to find a way to validate the data integrity before executing the method down below
            
            if(checkLuhn((String) value) == false){
        
                FacesMessage message = new FacesMessage("Please enter a valid Credit Card number!");
                message.setSeverity(FacesMessage.SEVERITY_WARN);
                throw new ValidatorException(message);
            }
        }
            
    }
    
    
    // checks credit card number for Luhn validation
    
    public static boolean checkLuhn(String ccNumber){           

	int sum = 0;
	boolean alternate = false;
		
	for (int i = ccNumber.length()-1; i>=0; i--){
		
        	int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			
		if (alternate){
			
			n *= 2;
			if (n > 9)
                            n = (n % 10) + 1;
		}
		sum += n;
		alternate = !alternate;
	}
    return (sum % 10 == 0);
    }
}
    
