/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Ocena;
import Entities.Sprawdzian;
import Entities.Uczen;
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
@Named(value = "ocenyBeansAdmin")
@SessionScoped
public class OcenyBeansAdmin implements Serializable {

    private List<Ocena> oceny;
    private List<Ocena> oceny2;
    private Ocena ocena;
    private Ocena selectedOcena;
    private List<Ocena> selectedOceny;
    private boolean delete=false;
    private boolean view=true;
    private boolean edit=false;
    private boolean dodaj=false;
    private List<Sprawdzian> sprawdziany;
    private List<Uczen> uczniowie;
    
    public List<Sprawdzian> getSprawdziany() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Sprawdzian").list();
         sprawdziany=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return sprawdziany;
    }
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

    public List<Ocena> getSelectedOceny() {
        return selectedOceny;
    }

    public void setSelectedOceny(List<Ocena> selectedOceny) {
        this.selectedOceny = selectedOceny;
    }
    
    public Ocena getOcena() {
        return ocena;
    }

    public Ocena getSelectedOcena() {
        return selectedOcena;
    }

    public void setSelectedOcena(Ocena selectedOcena) {
        this.selectedOcena = selectedOcena;
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
    
    public void zapisz(Ocena kla)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Ocena where id=:id") ;
         query.setInteger("id",kla.getId());
         Ocena result = (Ocena) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
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

    public List<Ocena> getOceny() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Ocena").list();
         oceny=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return oceny;
    }
    
    public List<Ocena> getOceny2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Ocena").list();
         oceny2=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return oceny2;
    }
    
    public void deleteOcena() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Ocena selKla:selectedOceny)
        {
        Query query = session.createQuery("from Ocena where id=:id") ;
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
        oceny2.removeAll(selectedOceny);
        selectedOceny = null;
        view=true;
        delete=false;
    }
    /**
     * Creates a new instance of OcenyBeansAdmin
     */
    public OcenyBeansAdmin() {
    }
    
}
