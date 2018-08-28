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
import com.telmarket.entity.Category;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author terrence
 */
@ManagedBean
@Named(value = "categoryMB")
@SessionScoped
public class CategoryMB implements Serializable {

    /**
     * Creates a new instance of CategoryMB
     */
    Category category = new Category();
    
    public CategoryMB() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public String addCategory(){
        
        category.setCatName(category.getCatName());
        category.setCatDesc(category.getCatDesc());
        boolean status = new AddDao().addCategory(category);
        if(status)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Saved", ""));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Save Failed!", ""));
        
        return null;
    }
    /*
    public List<Category> listCategories(){
        ListDao list = new ListDao();
        list.catList();
    }*/
}
