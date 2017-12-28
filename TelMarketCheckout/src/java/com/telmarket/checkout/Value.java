/*
 */
package com.telmarket.checkout;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;

/**
 *
 * @author terrence
 */
@Named(value = "value")
@ApplicationScoped
@ManagedBean(eager=true)
public class Value {

    private String total;
    
    public Value() {
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
