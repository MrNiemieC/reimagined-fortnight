/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Users;
import Entities.Roles;
import Util.HibernateUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author niemi
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Size(min=1,message = "{login_not_empty}")
    private String login;
    @Size(min=1,message = "{password_not_empty}")
    private String password;
    private String role;
    private boolean logged = false;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logIn() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List users = session.createQuery("FROM Users").list(); 
         for (Iterator iterator = users.iterator(); iterator.hasNext();){
            Users user = (Users) iterator.next(); 
            if (user.getLogin().equals(login)&&user.getPassword().equals(password)) {
                List roles = session.createQuery("FROM Roles").list(); 
         for (Iterator iterato = roles.iterator(); iterato.hasNext();){
            Roles rola = (Roles) iterato.next();
            if (rola.getLogin().equals(login)) role=rola.getRoleName();
         }
            logged = true;
            return "/index";
        }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        logged = false;
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Logowanie nie powiodło się");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
        return null;
    }

    public String getRole() {
        return role;
    }

    public boolean isLogged() {
        return logged;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().
                invalidateSession();
        logged = false;
        role = null;
        return "/index";
    }
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
}
