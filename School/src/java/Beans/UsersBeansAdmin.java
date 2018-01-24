/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Users;
import Entities.Uczen;
import Entities.Nauczyciel;
import Entities.Dyrektor;
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
@Named(value = "usersBeansAdmin")
@SessionScoped
public class UsersBeansAdmin implements Serializable {

    private List<Users> users;
    private List<Users> users2;
    private Users user;
    private Users selectedUser;
    private List<Users> selectedUsers;
    private boolean delete=false;
    private boolean view=true;
    private boolean edit=false;
    private boolean dodaj=false;
    private List<Uczen> uczniowie;
    private List<Nauczyciel> nauczyciele;
    private List<Dyrektor> dyrektory;
    
    public List<Uczen> getUczniowie() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Uczen").list();
         uczniowie=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return uczniowie;
    }
    public List<Nauczyciel> getNauczyciele() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Nauczyciel").list();
         nauczyciele=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return nauczyciele;
    }
    public List<Dyrektor> getDyrektory() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Dyrektor").list();
         dyrektory=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return dyrektory;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Users> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<Users> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

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
    
    public void zapisz(Users kla)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Users where id=:id") ;
         query.setInteger("id",kla.getId());
         Users result = (Users) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setLogin(kla.getLogin());
        result.setPassword(kla.getPassword());
        result.setNauczyciel(kla.getNauczyciel());
        result.setUczen(kla.getUczen());
        result.setDyrektor(kla.getDyrektor());
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

    public List<Users> getUsers() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Users").list();
         users=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return users;
    }
    
    public List<Users> getUsers2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Users").list();
         users2=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return users2;
    }
    
    public void deleteUsers() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Users selKla:selectedUsers)
        {
        Query query = session.createQuery("from Users where id=:id") ;
        query.setInteger("id",selKla.getId());
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
        users2.removeAll(selectedUsers);
        selectedUsers = null;
        view=true;
        delete=false;
    }
    /**
     * Creates a new instance of UsersBeansAdmin
     */
    public UsersBeansAdmin() {
    }
    
}
