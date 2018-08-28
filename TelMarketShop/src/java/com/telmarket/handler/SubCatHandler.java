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

import com.telmarket.dao.ListDao;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *This class returns lists of all sub_categories in given categories
 * 
 * @author terrence
 */
@ManagedBean
@Named(value = "subCatHandler")
@SessionScoped
public class SubCatHandler implements Serializable {

    CategoryHandler category = new CategoryHandler();
    
    /**
     * Creates a new instance of SubCatHandler
     */
    public SubCatHandler(){
    }
    
    public List mobile(){
        List subList = new ListDao().subcatListByName(category.getMobile());
        return subList;
    }
    
    public List computers(){
        List subList = new ListDao().subcatListByName(category.getComputers());
        return subList;
    }
    
    public List accessories(){
        List subList = new ListDao().subcatListByName(category.getAccessories());
        return subList;
    }
    
    public List servers(){
        List subList = new ListDao().subcatListByName(category.getServers());
        return subList;
    }
    
    public List technology(){
        List subList = new ListDao().subcatListByName(category.getTechnology());
        return subList;
    }
    
    public List software(){
        List subList = new ListDao().subcatListByName(category.getSoftware());
        return subList;
    }
    
    public List services(){
        List subList = new ListDao().subcatListByName(category.getServices());
        return subList;
    }
}
