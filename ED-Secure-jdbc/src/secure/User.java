/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.io.Serializable;

/**
 *
 * @author rohansoni
 */
public class User implements Serializable {
    
    private final String usrid;
    private final String name;
    private final String phone;
    private final String email;
    private final String password;
    private final String appGroup;
    private final boolean active; 

    public User(String usrid, String name, String phone, 
            String email, String password, String appGroup,
            boolean active) {
        this.usrid = usrid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.appGroup = appGroup;
        this.active = active;     
    }

    public String getUsrid() {
        return usrid;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getAppGroup() {
        return appGroup;
    }

    public boolean isActive() {
        return active;
    }

}
