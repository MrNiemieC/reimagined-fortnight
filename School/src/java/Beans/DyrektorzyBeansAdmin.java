/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import Entities.Dyrektor;
import Util.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author niemi
 */
@Named(value = "dyrektorzyBeansAdmin")
@SessionScoped
public class DyrektorzyBeansAdmin implements Serializable {

    private List<Dyrektor> dyrektorzy;
    private List<Dyrektor> dyrektorzy2;
    private Dyrektor dyrektor;
    private Dyrektor selectedDyrektor;
    private List<Dyrektor> selectedDyrektorzy;
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

    public List<Dyrektor> getSelectedDyrektorzy() {
        return selectedDyrektorzy;
    }

    public void setSelectedDyrektorzy(List<Dyrektor> selectedDyrektorzy) {
        this.selectedDyrektorzy = selectedDyrektorzy;
    }
    
    public Dyrektor getDyrektor() {
        return dyrektor;
    }

    public Dyrektor getSelectedDyrektor() {
        return selectedDyrektor;
    }

    public void setSelectedDyrektor(Dyrektor selectedDyrektor) {
        this.selectedDyrektor = selectedDyrektor;
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
    
    public void zapisz(Dyrektor dyr)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Dyrektor where id=:id") ;
         query.setInteger("id",dyr.getId());
         Dyrektor result = (Dyrektor) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setImie(dyr.getImie());
        result.setNazwisko(dyr.getNazwisko());
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

    public List<Dyrektor> getDyrektorzy() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List dyrektors = session.createQuery("FROM Dyrektor").list();
         dyrektorzy=dyrektors;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return dyrektorzy;
    }
    
    public List<Dyrektor> getDyrektorzy2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List dyrektors = session.createQuery("FROM Dyrektor").list();
         dyrektorzy2=dyrektors;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return dyrektorzy2;
    }
    
    public void deleteDyrektor() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Dyrektor selDyr:selectedDyrektorzy)
        {
        Query query = session.createQuery("from Dyrektor where id=:id") ;
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
        dyrektorzy2.removeAll(selectedDyrektorzy);
        selectedDyrektorzy = null;
        view=true;
        delete=false;
    }
    /**
     * Creates a new instance of DyrektorzyBeansAdmin
     */
    public DyrektorzyBeansAdmin() {
    }
    
}
