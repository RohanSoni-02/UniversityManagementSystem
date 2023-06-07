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
public class Course implements Serializable {

    private final String cid;
    private final String name;
    private final String description;
    private final int credits;

    public Course(String cid, String name, String description, int credits) {
        this.cid = cid;
        this.name = name;
        this.description = description;
        this.credits = credits;
    }

    public String getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCredits() {
        return credits;
    }

}
