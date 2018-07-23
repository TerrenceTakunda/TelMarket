/*
 * Terrence Takunda Munyunguma [https://github.com/TerrenceTakunda]
 * Copyright (C) 2018 ttmunyunguma@gmail.com
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
package com.telmarket.security;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

/**
 *
 * @author terrence takunda munyunguma
 */

public class EncryptField {

    private byte[] keyValue = null;
    
    public EncryptField() {
        String key = "iQc/beJy$JV=oP+v";
        keyValue = key.getBytes();
    }
    
    public String encrypt(String data) throws Exception{
        
        Key key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
    
    private Key generateKey(){
        Key key = new SecretKeySpec(keyValue, "AES");
        return key;
    }
    
}
