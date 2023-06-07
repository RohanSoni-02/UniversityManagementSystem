/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;


public class UserDTO implements Serializable {
    
    String usrid;
    String name;
    String phone;
    String email;
    String password;
    String appGroup;
    Boolean active;
    
    public UserDTO(String usrid, String name, String phone,
            String email, String password, String appGroup, 
            Boolean active) {
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

    public Boolean isActive() {
        return active;
    }
    
}
