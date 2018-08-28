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
import com.telmarket.entity.SubCategory;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author terrence
 */
@ManagedBean
@Named(value = "subCategoryMB")
@SessionScoped
public class SubCategoryMB implements Serializable {

    /**
     * Creates a new instance of SubCategoryMB
     */
    SubCategory subcat = new SubCategory();
    Category category = new Category();
    String catName;
    List<Category> listCat;
    
    public SubCategoryMB() {
    }

    public SubCategory getSubcat() {
        return subcat;
    }

    public void setSubcat(SubCategory subcat) {
        this.subcat = subcat;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<Category> getListCat() {
        return listCat;
    }

    public void setListCat(List<Category> listCat) {
        this.listCat = listCat;
    }
    
    public String addSubCategory(){
    
        listCat = new ListDao().catListByName(catName);
        category.setCatId(listCat.get(0).getCatId());
        subcat.setCategory(category);
        subcat.setSubCatName(subcat.getSubCatName());
        subcat.setSubCatDesc(subcat.getSubCatDesc());
        boolean status = new AddDao().addSubCategory(subcat);
        if(status)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Saved", ""));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Save Failed", ""));
        return null;
    }
    
    public List<SelectItem> getCategoryName(){
    
        List<SelectItem> catname = new ListDao().catList();
        return catname;
    }
    
    public String viewSubCat(){
        return ("subcategory.xhtml");
    }
}
