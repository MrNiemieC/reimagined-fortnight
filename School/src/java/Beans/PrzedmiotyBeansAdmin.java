/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Przedmiot;
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
@Named(value = "przedmiotyBeansAdmin")
@SessionScoped
public class PrzedmiotyBeansAdmin implements Serializable {
    private List<Przedmiot> przedmioty;
    private List<Przedmiot> przedmioty2;
    private Przedmiot przedmiot;
    private Przedmiot selectedPrzedmiot;
    private List<Przedmiot> selectedPrzedmioty;
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

    public List<Przedmiot> getPrzedmioty() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Przedmiot").list();
         przedmioty=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return przedmioty;
    }

    public List<Przedmiot> getPrzedmioty2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Przedmiot").list();
         przedmioty2=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return przedmioty2;
    }

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    public Przedmiot getSelectedPrzedmiot() {
        return selectedPrzedmiot;
    }

    public void setSelectedPrzedmiot(Przedmiot selectedPrzedmiot) {
        this.selectedPrzedmiot = selectedPrzedmiot;
    }

    public List<Przedmiot> getSelectedPrzedmioty() {
        return selectedPrzedmioty;
    }

    public void setSelectedPrzedmioty(List<Przedmiot> selectedPrzedmioty) {
        this.selectedPrzedmioty = selectedPrzedmioty;
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
    
    public void zapisz(Przedmiot kla)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Przedmiot where id=:id") ;
         query.setInteger("id",kla.getId());
         Przedmiot result = (Przedmiot) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setNazwa(kla.getNazwa());
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
    
    public void deletePrzedmiot() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Przedmiot selKla:selectedPrzedmioty)
        {
        Query query = session.createQuery("from Przedmiot where id=:id") ;
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
        przedmioty2.removeAll(selectedPrzedmioty);
        selectedPrzedmioty = null;
        view=true;
        delete=false;
    }

    /**
     * Creates a new instance of PrzedmiotyBeansAdmin
     */
    public PrzedmiotyBeansAdmin() {
    }
    
}
