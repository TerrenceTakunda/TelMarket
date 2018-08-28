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
import com.telmarket.entity.Product;
import com.telmarket.entity.SubCategory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author terrence
 */
@ManagedBean
@Named(value = "productMB")
@SessionScoped
public class ProductMB implements Serializable {

    /**
     * Creates a new instance of ProductMB
     */
    
    Product product = new Product();
    SubCategory subcat = new SubCategory();
    String subcatname;
    String catname = "";
    List<SubCategory> listSubCat;
    UploadedFile file;
    
    public ProductMB() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SubCategory getSubcat() {
        return subcat;
    }

    public void setSubcat(SubCategory subcat) {
        this.subcat = subcat;
    }

    public String getSubcatname() {
        return subcatname;
    }

    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public List<SubCategory> getListSubCat() {
        return listSubCat;
    }

    public void setListSubCat(List<SubCategory> listSubCat) {
        this.listSubCat = listSubCat;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public String addProduct(){
    
        upload();
        listSubCat = new ListDao().subcatListByName(subcatname);
        subcat.setSubCatId(listSubCat.get(0).getSubCatId());
        product.setSubCategory(subcat);
        product.setProName(product.getProName());
        product.setProPrice(product.getProPrice());
        product.setProQty(product.getProQty());
        product.setProPic(product.getProPic());
        product.setProDesc(product.getProDesc());
        boolean status = new AddDao().addProduct(product);
        if(status)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Saved", ""));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Save Failed", ""));
        return null;
    }
    
    public void upload(){
        if(file != null){
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
                String dbPath = servletContext.getRealPath("/");
                InputStream inputStream = file.getInputstream();
                String path = dbPath + "/assets/img/products/" + file.getFileName();
                File destFile = new File(path);
                if(!destFile.exists())
                    FileUtils.copyInputStreamToFile(inputStream, destFile);
                product.setProPic(file.getFileName());
            } catch (IOException ex) {
                Logger.getLogger(ProductMB.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            } 
        }
    }
    
    public List<SelectItem> getSubCategoryName(){
    
        List<SelectItem> subcatName = new ListDao().subcatList(catname);
        return subcatName;
    }
    
    public List<Product> getProductBySubCat(){
        List<Product> products = new ListDao().productListBySubCat(subcatname);
        return products;
    }
}
