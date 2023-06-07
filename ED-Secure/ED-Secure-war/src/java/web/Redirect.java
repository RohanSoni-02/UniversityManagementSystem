/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rohansoni
 */
@Named(value = "Redirect")
@SessionScoped
public class Redirect extends HttpServlet {

    /**
     * Creates a new instance of MyLoginManagedBean
     */
    public Redirect() {
    }

    public void mainmenu() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if (request.isUserInRole("ED-APP-ADMIN")) {
            context.getExternalContext().redirect("faces/admin/mainmenu.xhtml");
        } else if (request.isUserInRole("ED-APP-USERS")) {
            context.getExternalContext().redirect("faces/user/mainmenu.xhtml");
        }
        else if (request.isUserInRole("ED-APP-STUDENT")) {
            context.getExternalContext().redirect("faces/student/mainmenu.xhtml");
        }
        else if (request.isUserInRole("ED-APP-TEACHER")) {
            context.getExternalContext().redirect("faces/teacher/mainmenu.xhtml");
        }
    }
}