/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Klasa;
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
@Named(value = "uczniowieBeansAdmin")
@SessionScoped
public class UczniowieBeansAdmin implements Serializable {

    private List<Uczen> uczniowie;
    private List<Uczen> uczniowie2;
    private Uczen uczen;
    private Uczen selectedUczen;
    private List<Uczen> selectedUczniowie;
    private boolean delete=false;
    private boolean view=true;
    private boolean edit=false;
    private boolean dodaj=false;
    private List<Klasa> klasy;
    
    public List<Klasa> getKlasy() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Klasa").list();
         klasy=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return klasy;
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

    public List<Uczen> getSelectedUczniowie() {
        return selectedUczniowie;
    }

    public void setSelectedUczniowie(List<Uczen> selectedUczniowie) {
        this.selectedUczniowie = selectedUczniowie;
    }
    
    public Uczen getUczen() {
        return uczen;
    }

    public Uczen getSelectedUczen() {
        return selectedUczen;
    }

    public void setSelectedUczen(Uczen selectedUczen) {
        this.selectedUczen = selectedUczen;
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
    
    public void zapisz(Uczen kla)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Uczen where id=:id") ;
         query.setInteger("id",kla.getId());
         Uczen result = (Uczen) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setImie(kla.getImie());
        result.setNazwisko(kla.getNazwisko());
        result.setKlasa(kla.getKlasa());
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

    public List<Uczen> getUczniowie() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Uczen").list();
         uczniowie=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return uczniowie;
    }
    
    public List<Uczen> getUczniowie2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Uczen").list();
         uczniowie2=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return uczniowie2;
    }
    
    public void deleteUczen() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Uczen selKla:selectedUczniowie)
        {
        Query query = session.createQuery("from Uczen where id=:id") ;
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
        uczniowie2.removeAll(selectedUczniowie);
        selectedUczniowie = null;
        view=true;
        delete=false;
    }
    /**
     * Creates a new instance of UczniowieBeansAdmin
     */
    public UczniowieBeansAdmin() {
    }
    
}
