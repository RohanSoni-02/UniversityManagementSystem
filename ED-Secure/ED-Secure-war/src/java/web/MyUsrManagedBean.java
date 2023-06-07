/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rohansoni
 */
package web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import entity.UserDTO;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;
import session.UserManagementRemote;

@Named(value = "myUsrManagedBean")
@ConversationScoped
public class MyUsrManagedBean implements Serializable {

    @Inject
    private Conversation conversation;
    @EJB
    private UserManagementRemote userManagement;

    private String usrId;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String appGroup;
    private Boolean active;

    /**
     * Creates a new instance of MyUsrManagedBean
     */
    public MyUsrManagedBean() {
        usrId = null;
        name = null;
        phone = null;
        email = null;
        password = null;
        newPassword = null;
        confirmPassword = null;
        appGroup = null;
        
        active = false;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAppGroup() {
        return appGroup;
    }

    public void setAppGroup(String appGroup) {
        this.appGroup = appGroup;
    }


    public Boolean isActive() {
        return active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    private boolean hasPermissionOver() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String user = null;
        try {
            user = request.getRemoteUser();
        } catch (Exception e) {
        }
        if (request.isUserInRole("ED-APP-ADMIN")) { // has permission over everyone
            return true;
        }
        
        if (request.isUserInRole("ED-APP-STUDENT")) { // has permission over themselves
            if (usrId.equals(user)) {
                return true;
            }
        }
        if (request.isUserInRole("ED-APP-TEACHER")) { // has permission over themselves
            if (usrId.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public String addUser() {
        // check empId is null
        if (isNull(usrId)) {
            return "debug";
        }

        // all information seems to be valid
        // try add the employee
        UserDTO empDTO = new UserDTO(usrId, name, phone,
                 email, password, appGroup, active);
        boolean result = userManagement.addUser(empDTO);
        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String setUserDetailsForChange() {
        // check empId is null
        if (isNull(usrId) || conversation == null) {
            return "debug";
        }

        if (!userManagement.hasUser(usrId)) {
            return "failure";
        }

        // note the startConversation of the conversation
        startConversation();

        // get employee details
        return setUserDetails();
    }

    public String changeUser() {
        // check empId is null
        if (isNull(usrId)) {
            return "debug";
        }

        UserDTO empDTO = new UserDTO(usrId, name, phone,
                 email, password, appGroup, active);
        boolean result = userManagement.updateUserDetails(empDTO);

        // note the endConversation of the conversation
        endConversation();

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public void validateNewPassword(FacesContext context,
            UIComponent componentToValidate, Object value)
            throws ValidatorException {

        // get new password
        String oldPwd = (String) value;

        // get old password
        UIInput newPwdComponent = (UIInput) componentToValidate.getAttributes().get("newpwd");
        String newPwd = (String) newPwdComponent.getSubmittedValue();

        if (oldPwd.equals(newPwd)) {
            FacesMessage message = new FacesMessage(
                    "Old Password and New Password are the same! They must be different.");
            throw new ValidatorException(message);
        }

    }

    public void validatePasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object pwdValue) throws ValidatorException {

        // get password
        String pwd = (String) pwdValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        System.out.println("password : " + pwd);
        System.out.println("confirm password : " + cnfPwd);

        if (!pwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "Password and Confirm Password are not the same! They must be the same.");
            throw new ValidatorException(message);
        }
    }

    public void validateNewPasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object newValue) throws ValidatorException {

        // get new password
        String newPwd = (String) newValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        System.out.println("new password : " + newPwd);
        System.out.println("confirm password : " + cnfPwd);

        if (!newPwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "New Password and Confirm New Password are not the same! They must be the same.");
            throw new ValidatorException(message);
        }
    }

    public String changeUserPassword() {
        // check empId is null
        if (isNull(usrId)) {
            return "debug";
        }
        
         if (!hasPermissionOver()){
            return "nopermission";
        }

        // newPassword and confirmPassword are the same - checked by the validator during input to JSF form
        boolean result = userManagement.updateUserPassword(usrId, newPassword);

        System.out.println("result = " + result);

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String deleteUser() {
        // check empId is null
        if (isNull(usrId)) {
            return "debug";
        }
        
         if (!hasPermissionOver()){
            return "nopermission";
        }

        boolean result = userManagement.deleteUser(usrId);

        if (result) {
            return "success";
        } else {
            return "failure";
        }

    }

    public String displayUser() {
        // check empId is null
        if (isNull(usrId) || conversation == null) {
            return "debug";
        }

        return setUserDetails();
    }

    private boolean isNull(String s) {
        return (s == null);
    }

    private String setUserDetails() {

        if (isNull(usrId) || conversation == null) {
            return "debug";
        }
        
        if (!hasPermissionOver()){
            return "nopermission";
        }

        UserDTO e = userManagement.getUserDetails(usrId);

        if (e == null) {
            // no such employee
            return "failure";
        } else {
            // found - set details for display
            this.usrId = e.getUsrid();
            this.name = e.getName();
            this.phone = e.getPhone();
            
            this.email = e.getEmail();
            this.password = e.getPassword();
            this.appGroup = e.getAppGroup();
            
            this.active = e.isActive();
            return "success";
        }
    }

    private boolean validAddUserInfo() {
        return (usrId != null && name != null && phone != null 
                && email != null && password != null && appGroup != null
                );
    }

    public void showUserId() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        String employeeId = externalContext.getRemoteUser();
        this.usrId = employeeId;
    }

    public void showUserDetails() {
        System.out.println("SHOW USER DETAIL");
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        String employeeId = externalContext.getRemoteUser();
        System.out.println("USER ID: " + employeeId);
        if (isNull(employeeId) || conversation == null) {
            System.out.println("SOMETHING IS NULL");
        }
        this.usrId = employeeId;
        System.out.println("SETTING EMPLOYEE ID: " + this.usrId);
        setUserDetailsForChange();
    }

    public String displayUsr() {
        // check empId is null
        if (isNull(usrId) || conversation == null) {
            return "debug";
        }

        return setUserDetails();
    }

    private String setUsrDetails() {

        if (isNull(usrId) || conversation == null) {
            return "debug";
        }

        UserDTO e = userManagement.getUsrDetails(usrId, password);//0009

        if (e == null) {
            // no such employee
            return "failure";
        } else {
            // found - set details for display
            this.usrId = e.getUsrid();
            this.name = e.getName();
            this.phone = e.getPhone();
            
            this.email = e.getEmail();
            this.password = e.getPassword();
            this.appGroup = e.getAppGroup();
            
            this.active = e.isActive();
            return "success";
        }
    }

    public String changeUsrPassword() {
        if (isNull(usrId)) {
            return "debug";
        }

        boolean result = userManagement.updateUsrPassword(usrId, newPassword);
        System.out.println("result = " + result);

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String setUsrDetailsForChange() {
        // check empId is null
        if (isNull(usrId) || conversation == null) {
            return "debug";
        }

        if (!userManagement.hasUser(usrId)) {
            return "failure";
        }
        startConversation();
        return setUserDetails();
    }
}
