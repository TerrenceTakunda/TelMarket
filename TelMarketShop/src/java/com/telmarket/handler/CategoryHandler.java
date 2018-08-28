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

import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *This class handles categories. Its responsible for parsing a category label to the respective sub_category DAO so that products
 * can be returned which belong to that particular category.
 * @author terrence
 */
@ManagedBean
@Named(value = "categoryHandler")
@SessionScoped
public class CategoryHandler implements Serializable {

    private final String mobile = "Mobile";
    private final String computers = "Computers";
    private final String accessories = "Accessories";
    private final String servers = "Servers";
    private final String technology = "Technology";
    private final String software = "Software";
    private final String services = "Professional Services";
    
    /**
     * Creates a new instance of SubCatHandler
     */
    public CategoryHandler() {
    }

    public String getMobile() {
        return mobile;
    }

    public String getComputers() {
        return computers;
    }

    public String getAccessories() {
        return accessories;
    }

    public String getServers() {
        return servers;
    }

    public String getTechnology() {
        return technology;
    }

    public String getSoftware() {
        return software;
    }

    public String getServices() {
        return services;
    }
    
}
