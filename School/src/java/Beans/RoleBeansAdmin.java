/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Roles;
import Util.HibernateUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author niemi
 */
@Named(value = "roleBeansAdmin")
@SessionScoped
public class RoleBeansAdmin implements Serializable {

    private List<Roles> role;
    private List<Roles> role2;
    private Roles rola;
    private Roles selectedRola;
    private List<Roles> selectedRole;
    private boolean delete=false;
    private boolean view=true;
    private boolean edit=false;
    private boolean dodaj=false;

    public void setDodaj(boolean dodaj) {
        this.view=false;
        this.dodaj = dodaj;
    }

    public boolean isDodaj() {
        return dodaj;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.view=false;
        this.edit = edit;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.view=false;
        this.delete = delete;
    }
    
    
    public void wstecz()
    {
        this.view=true;
        this.delete=false;
    }
    
    public void anuluj()
    {
        this.view=true;
        this.edit=false;
    }
    
    public void anulujDodaj()
    {
        this.view=true;
        this.dodaj=false;
    }

    public Roles getRola() {
        return rola;
    }

    public void setRola(Roles rola) {
        this.rola = rola;
    }

    public Roles getSelectedRola() {
        return selectedRola;
    }

    public void setSelectedRola(Roles selectedRola) {
        this.selectedRola = selectedRola;
    }

    public List<Roles> getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(List<Roles> selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    
    
    public void zapisz(Roles dyr)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Roles where id=:id") ;
         query.setInteger("id",dyr.getId());
         Roles result = (Roles) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setLogin(dyr.getLogin());
        result.setRoleName(dyr.getRoleName());
        session.update(result);
        }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      this.view=true;
      this.edit=false;
    }

    public List<Roles> getRole() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List dyrektors = session.createQuery("FROM Roles").list();
         role=dyrektors;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return role;
    }
    
    public List<Roles> getRole2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List dyrektors = session.createQuery("FROM Roles").list();
         role2=dyrektors;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return role2;
    }
    
    public void deleteRole() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Roles selDyr:selectedRole)
        {
        Query query = session.createQuery("from Roles where id=:id") ;
        query.setInteger("id",selDyr.getId());
        Object results = query.uniqueResult() ;
        System.out.println(results);
        session.delete(results);
        }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        role2.removeAll(selectedRole);
        selectedRole = null;
        view=true;
        delete=false;
    }
    /**
     * Creates a new instance of RoleBeansAdmin
     */
    public RoleBeansAdmin() {
    }
    
}
