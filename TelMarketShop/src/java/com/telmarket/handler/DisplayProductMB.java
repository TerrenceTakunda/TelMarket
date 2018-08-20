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
import com.telmarket.entity.Product;
import com.telmarket.entity.SubCategory;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author terrence
 */
@ManagedBean
@Named(value = "displayProductMB")
@SessionScoped
public class DisplayProductMB implements Serializable {

    /**
     * Creates a new instance of DisplayProductMB
     */
    Product product = new Product();
    private Product selectedProduct;
    private String subcatname;
    
    public DisplayProductMB() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getSubcatname() {
        return subcatname;
    }

    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }
    
    public List<Product> getAllProducts(){
        
        List<Product> pList = new ListDao().allProductsList();
        return pList;
    }
    
    public List<Product> productsBySubCat(){
    
        List<Product> pList = new ListDao().productListBySubCat(subcatname);
        return pList;    
    }
}
