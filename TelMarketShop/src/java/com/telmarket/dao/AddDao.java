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
package com.telmarket.dao;

import com.telmarket.entity.Category;
import com.telmarket.entity.Product;
import com.telmarket.entity.SubCategory;
import com.telmarket.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author terrence
 */
public class AddDao {
    
    public AddDao(){
    }
    
    public boolean addCategory(Category category){
        try{
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(HibernateException e){
        }
        return false;
    }
    
    public boolean addSubCategory(SubCategory sub_cat){
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(sub_cat);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
        }
        return false;
    }
    
    public boolean addProduct(Product product){
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
        }
        return false;
    }
    
}
