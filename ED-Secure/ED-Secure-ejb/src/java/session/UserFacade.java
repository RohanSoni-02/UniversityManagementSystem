/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.User;

import javax.ejb.Stateless;

/**
 *
 * @author rohansoni
 */
@Stateless
public class UserFacade implements UserFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    public UserFacade() {
    }

    private void create(User entity) {
        em.persist(entity);
    }

    private void edit(User entity) {
        em.merge(entity);
    }

    private void remove(User entity) {
        em.remove(em.merge(entity));
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public User find(String id) {
        return em.find(User.class, id);
    }

    /**
     * checks whether an user exist using empId
     *
     * @param usrId
     * @return true if exist, false otherwise
     */
    @Override
    public boolean hasUser(String usrid) {
        return (find(usrid) != null);
    }

    /**
     * add an user to the system
     *
     * @param user
     * @return true if addition is successful, false otherwise
     */
    @Override
    public boolean addUser(User user) {
        // check again - just to play it safe
        User e = find(user.getUsrid());
        
        if (e != null) {
            // could not add one
            return false;
        }

        create(user);

        return true;
    }

    /**
     * update user details without changing password
     *
     * @param user
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updateUserDetails(User user) {
        // find the employee, just in case
        User e = find(user.getUsrid());

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        // no need to update the primary key - empId
        edit(user);
        return true;
    }

    /**
     * update user's password
     *
     * @param usrId
     * @param password
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updatePassword(String usrId, String password) {
        // find the employee
        User e = find(usrId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        // only need to update the password field
        e.setPassword(password);
        return true;
    }

    /**
     * delete the user by setting active to false - cannot physically
     * removing the record due to taxation purposes
     *
     * @param usrId
     * @return true if successful, false otherwise
     */
    @Override
    public boolean deleteUser(String usrId) {
        // find the employee
        User e = find(usrId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        if (!e.isActive()) {
            // employee not active already
            return false;
        }

        e.setActive(false);
        return true;
    }

    /**
     * physically remove the employee record from database table
     * 
     * this is only for lab purposes - never ever do this in real world applications
     *
     * @param usrId
     * @return true if successful, false otherwise
     */
    @Override
    public boolean removeUser(String usrId) {
        // find the employee
        User e = find(usrId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        em.remove(e);
        return true;
    }
}
