/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import entity.User;
import entity.UserDTO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

@DeclareRoles({"ED-APP-ADMIN", "ED-APP-STUDENT", "ED-APP-TEACHER"})
@Stateless
public class UserManagement implements UserManagementRemote {

    @EJB
    private UserFacadeLocal userFacade;

    private User userDTO2Entity(UserDTO usrDTO) {
        if (usrDTO == null) {
            // just in case
            return null;
        }

        String usrid = usrDTO.getUsrid();
        String name = usrDTO.getName();
        String phone = usrDTO.getPhone();
        String email = usrDTO.getEmail();
        String password = usrDTO.getPassword();
        String appGroup = usrDTO.getAppGroup();
        Boolean active = usrDTO.isActive();

        User user = new User(usrid, name, phone, email,
                password, appGroup, active);

        return user;
    }

    private UserDTO userEntity2DTO(User user) {
        if (user == null) {
            // just in case
            return null;
        }

        UserDTO usrDTO = new UserDTO(
                user.getUsrid(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword(),
                user.getAppGroup(),
                user.isActive()
        );

        return usrDTO;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * check whether the employee is in the system
     *
     * @param usrId
     * @return true if the employee is in the system, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN", "ED-APP-TEACHER"})
    public boolean hasUser(String usrId) {
        return userFacade.hasUser(usrId);
    }

    /**
     * add an employee to the system
     *
     * @param usrDTO
     * @return true if addition is successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean addUser(UserDTO usrDTO) {

        if (usrDTO == null) {
            // just in case
            return false;
        }

        // check employee exist?
        if (hasUser(usrDTO.getUsrid())) {
            // employee exists, cannot add one
            return false;
        }

        // employee not exist
        // convert to entity
        User usr = this.userDTO2Entity(usrDTO);
        // add one
        return userFacade.addUser(usr);
    }

    /**
     * update employee details without updating password
     *
     * @param usrDTO
     * @return true if update is successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN", "ED-APP-STUDENT", "ED-APP-TEACHER"})
    public boolean updateUserDetails(UserDTO usrDTO) {
        // check employee exist?
        if (!hasUser(usrDTO.getUsrid())) {
            return false;
        }

        // employee exist, update details
        // convert to entity class
        User user = this.userDTO2Entity(usrDTO);
        // update details
        return userFacade.updateUserDetails(user);
    }

    /**
     * update employee's password
     *
     * @param usrId
     * @param newPassword
     * @return true if update successful, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean updateUserPassword(String usrId, String newPassword) {
        return userFacade.updatePassword(usrId, newPassword);
    }

    /**
     * get employee details and use a DTO to transmit the details
     *
     * @param usrId
     * @return a DTO containing the information of the employee if exists, null
     * otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public UserDTO getUserDetails(String usrId) {
        // get the employee
        User user = userFacade.find(usrId);

        if (user == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            UserDTO usrDTO = new UserDTO(user.getUsrid(),
                    user.getName(), user.getPhone(),
                    user.getEmail(), user.getPassword(), user.getAppGroup(), user.isActive());

            return usrDTO;
        }
    }

    /**
     * set the employee's active status to false
     *
     * @param usrId
     * @return true if it can be set to inactive and have set to inactive; false
     * otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean deleteUser(String usrId) {
        return userFacade.deleteUser(usrId);
    }

    /**
     * physically remove an employee's record from database
     *
     * This is for lab purposes - never ever do this in real world applications
     *
     * @param usrId
     * @return true if the employee record has been physically removed from the
     * database, false otherwise
     */
    @Override
    @RolesAllowed({"ED-APP-ADMIN"})
    public boolean removeUser(String usrId) {
        return userFacade.removeUser(usrId);
    }

    @Override
    @RolesAllowed({"ED-APP-ADMIN", "ED-APP-STUDENT", "ED-APP-TEACHER"})
    public boolean checkUserIdPwd(String usrId, String pwd) {
        User e = userFacade.find(usrId);
        if (e == null) {
            return false;
        }

        if (!(e.getAppGroup().equals("ED-APP-STUDENT"))) {
            return false;
        }
        
        if (!(e.getAppGroup().equals("ED-APP-TEACHER"))) {
            return false;
        }

        return (e.getPassword().equals(pwd));
    }

    @Override
    @RolesAllowed({"ED-APP-STUDENT", "ED-APP-TEACHER"})
    public UserDTO getUsrDetails(String usrId, String pwd) {
        if (checkUserIdPwd(usrId, pwd)) {

            return getUsrDetailsPrivate(usrId);
        }
        return null;
    }

    private UserDTO getUsrDetailsPrivate(String usrId) {
        User e = userFacade.find(usrId);
        if (e == null) {
            return null;
        } else {
            UserDTO usrDTO = new UserDTO(e.getUsrid(), e.getName(), e.getPhone(), e.getEmail(), e.getPassword(), e.getAppGroup(),
                    e.isActive());
            return usrDTO;
        }
    }

    @Override
    @RolesAllowed({"ED-APP-STUDENT", "ED-APP-TEACHER"})
    public boolean updateUsrPassword(String usrId, String nPwd) {
        return userFacade.updatePassword(usrId, nPwd);
    }

    @Override
    @RolesAllowed({"ED-APP-ADMIN", "ED-APP-STUDENT", "ED-APP-TEACHER"})
    public String getPasswordByUsrId(String usrId) {
        User user = userFacade.find(usrId);
        if (user != null) {
            return user.getPassword();
        } else {
            return null;
        }
    }
}
