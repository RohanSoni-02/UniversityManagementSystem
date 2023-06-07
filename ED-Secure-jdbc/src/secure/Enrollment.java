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
public class Enrollment implements Serializable {

    private final String eid;
    private final String StuId;
    private final String cid;
    private final double grade;

    public Enrollment(String eid, String StuId, String cid, double grade) {
        this.eid = eid;
        this.StuId = StuId;
        this.cid = cid;
        this.grade = grade;
    }

    public String getEid() {
        return eid;
    }

    public String getStuId() {
        return StuId;
    }

    public String getCid() {
        return cid;
    }

    public double getGrade() {
        return grade;
    }

}
