/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;
import entity.UserDTO;


@Remote
public interface UserManagementRemote {

    boolean hasUser(String usrid);
    
    boolean addUser(UserDTO usrDTO);

    boolean updateUserDetails(UserDTO usrDTO);

    boolean updateUserPassword(String usrid, String newPassword);

    UserDTO getUserDetails(String usrid);

    boolean deleteUser(String usrid);
    
    boolean removeUser(String usrid);
    
    boolean updateUsrPassword(String usrId, String nPwd);
    
    UserDTO getUsrDetails(String usrId, String pwd);
    
    boolean checkUserIdPwd(String usrId, String pwd);
    
    public String getPasswordByUsrId(String usrId);
}
