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
public class Teacher implements Serializable {

    private final String tchId;
    private final String usrId;
    private final String name;
    private final String dateOfBirth;
    private final String address;
    private final String email;
    private final String qualification;
    private final String gender;
    private final String courseAssigned;

    public Teacher(String tchId, String usrId, String name, String dateOfBirth, String address,
            String email, String qualification, String gender,
            String courseAssigned) {
        this.tchId = tchId;
        this.usrId = usrId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.qualification = qualification;
        this.gender = gender;
        this.courseAssigned = courseAssigned;
    }

    public String getTchId() {
        return tchId;
    }

    public String getUsrId() {
        return usrId;
    }
    
    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getQualification() {
        return qualification;
    }

    public String getGender() {
        return gender;
    }

    public String getCourseAssigned() {
        return courseAssigned;
    }

}
