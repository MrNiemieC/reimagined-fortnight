/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Roles;
import Util.HibernateUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author niemi
 */
@Named(value = "addRoleBeansAdmin")
@RequestScoped
public class AddRoleBeansAdmin {

    private String login;
    private String roleName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Inject
    private RoleBeansAdmin roleBeansAdmin;
    public void add()
    {
    Roles d = new Roles(login,roleName);
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.openSession();
    Transaction tx = null;
    try {
     tx = session.beginTransaction();
    Serializable id = session.save(d);
     tx.commit();
    }
    catch (Exception e) {
     if (tx!=null) tx.rollback();
     throw e;
    }
    finally {
     session.close();
    }
    roleBeansAdmin.setDodaj(false);
    roleBeansAdmin.setView(true);
    }
    /**
     * Creates a new instance of AddRoleBeansAdmin
     */
    public AddRoleBeansAdmin() {
    }
    
}
