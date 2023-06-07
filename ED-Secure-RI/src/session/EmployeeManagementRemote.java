/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;
import entity.EmployeeDTO;


@Remote
public interface EmployeeManagementRemote {

    boolean hasEmployee(String empid);
    
    boolean addEmployee(EmployeeDTO empDTO);

    boolean updateEmpolyeeDetails(EmployeeDTO empDTO);

    boolean updateEmployeePassword(String empid, String newPassword);

    EmployeeDTO getEmployeeDetails(String empid);
    
    EmployeeDTO getStudentDetails(String empid);

    boolean deleteEmployee(String empid);
    
    boolean removeEmployee(String empid);
    
    boolean updateUserPassword(String empId, String pwd, String nPwd);
    
    EmployeeDTO getUserDetails(String empId, String pwd);
    
    boolean checkUserIdPwd(String empId, String pwd);
}
