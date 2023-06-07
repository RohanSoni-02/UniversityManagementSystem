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
public class Student implements Serializable {

    private final String StuId;
    private final String UsrId;
    private final String name;
    private final String date_of_birth;
    private final String address;
    private final String emergency_contact;
    private final String course_enrolled;

    public Student(String StuId, String UsrId, String name, String date_of_birth,
            String address, String emergency_contact, String course_enrolled) {
        this.StuId = StuId;
        this.UsrId = UsrId;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.emergency_contact = emergency_contact;
        this.course_enrolled = course_enrolled;
    }

    public String getStuId() {
        return StuId;
    }

    public String getUsrId() {
        return UsrId;
    }
    
    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public String getEmergencyContact() {
        return emergency_contact;
    }

    public String getCourseEnrolled() {
        return course_enrolled;
    }

}
