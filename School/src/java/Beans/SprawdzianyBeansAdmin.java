/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.PrzedmiotKlas;
import Entities.Sprawdzian;
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
@Named(value = "sprawdzianyBeansAdmin")
@SessionScoped
public class SprawdzianyBeansAdmin implements Serializable {

    private List<Sprawdzian> sprawdziany;
    private List<Sprawdzian> sprawdziany2;
    private Sprawdzian sprawdzian;
    private Sprawdzian selectedSprawdzian;
    private List<Sprawdzian> selectedSprawdziany;
    private boolean delete=false;
    private boolean view=true;
    private boolean edit=false;
    private boolean dodaj=false;
    private List<PrzedmiotKlas> przedmiotKlasy;
    
    public List<PrzedmiotKlas> getPrzedmiotKlasy() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM PrzedmiotKlas").list();
         przedmiotKlasy=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return przedmiotKlasy;
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

    public List<Sprawdzian> getSelectedSprawdziany() {
        return selectedSprawdziany;
    }

    public void setSelectedSprawdziany(List<Sprawdzian> selectedSprawdziany) {
        this.selectedSprawdziany = selectedSprawdziany;
    }
    
    public Sprawdzian getSprawdzian() {
        return sprawdzian;
    }

    public Sprawdzian getSelectedSprawdzian() {
        return selectedSprawdzian;
    }

    public void setSelectedSprawdzian(Sprawdzian selectedSprawdzian) {
        this.selectedSprawdzian = selectedSprawdzian;
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
    
    public void zapisz(Sprawdzian kla)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Sprawdzian where id=:id") ;
         query.setInteger("id",kla.getId());
         Sprawdzian result = (Sprawdzian) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setNazwa(kla.getNazwa());
        result.setDataSpr(kla.getDataSpr());
        result.setPrzedmiotKlas(kla.getPrzedmiotKlas());
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

    public List<Sprawdzian> getSprawdziany() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Sprawdzian").list();
         sprawdziany=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return sprawdziany;
    }
    
    public List<Sprawdzian> getSprawdziany2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Sprawdzian").list();
         sprawdziany2=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return sprawdziany2;
    }
    
    public void deleteSprawdzian() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Sprawdzian selKla:selectedSprawdziany)
        {
        Query query = session.createQuery("from Sprawdzian where id=:id") ;
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
        sprawdziany2.removeAll(selectedSprawdziany);
        selectedSprawdziany = null;
        view=true;
        delete=false;
    }
    /**
     * Creates a new instance of SprawdzianyBeansAdmin
     */
    public SprawdzianyBeansAdmin() {
    }
    
}
