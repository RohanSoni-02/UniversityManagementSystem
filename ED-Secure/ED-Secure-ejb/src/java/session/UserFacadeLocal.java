/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import javax.ejb.Local;
import entity.User;


@Local
public interface UserFacadeLocal {

    User find(String id);

    boolean hasUser(String usrid);
    
    boolean addUser(User user);
    
    boolean updateUserDetails(User user);
    
    boolean updatePassword(String usrId, String password);

    boolean deleteUser(String usrId);
    
    boolean removeUser(String usrId);

}
