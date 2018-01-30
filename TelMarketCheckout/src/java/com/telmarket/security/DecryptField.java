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
package com.telmarket.security;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;

/**
 *
 * @author terrence takunda munyunguma
 */
public class DecryptField {
    
    private byte[] keyValue = null;

    public DecryptField() {
        String key = "lv3d9jg37mfbc5cr";
        keyValue = key.getBytes();
    }
    
    public String decrypt(String encryptedData) throws Exception{
        
            Key key = generateKey();
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
            byte[] decVal = c.doFinal(decodedValue);
            String decryptedValue = new String(decVal);
            return decryptedValue;
    }
    
    private Key generateKey(){
        Key key = new SecretKeySpec(keyValue, "AES");
        return key;
    }
    
}
