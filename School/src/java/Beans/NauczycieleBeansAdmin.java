/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Nauczyciel;
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
@Named(value = "nauczycieleBeansAdmin")
@SessionScoped
public class NauczycieleBeansAdmin implements Serializable {

    private List<Nauczyciel> nauczyciele;
    private List<Nauczyciel> nauczyciele2;
    private Nauczyciel nauczyciel;
    private Nauczyciel selectedNauczyciel;
    private List<Nauczyciel> selectedNauczyciele;
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

    public List<Nauczyciel> getSelectedNauczyciele() {
        return selectedNauczyciele;
    }

    public void setSelectedNauczyciele(List<Nauczyciel> selectedNauczyciele) {
        this.selectedNauczyciele = selectedNauczyciele;
    }
    
    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public Nauczyciel getSelectedNauczyciel() {
        return selectedNauczyciel;
    }

    public void setSelectedNauczyciel(Nauczyciel selectedNauczyciel) {
        this.selectedNauczyciel = selectedNauczyciel;
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
    
    public void zapisz(Nauczyciel nau)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Nauczyciel where id=:id") ;
         query.setInteger("id",nau.getId());
         Nauczyciel result = (Nauczyciel) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setImie(nau.getImie());
        result.setNazwisko(nau.getNazwisko());
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
    
    public List<Nauczyciel> getNauczyciele2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Nauczyciel").list();
         nauczyciele2=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return nauczyciele2;
    }
    
    public void deleteNauczyciel() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Nauczyciel selDyr:selectedNauczyciele)
        {
        Query query = session.createQuery("from Nauczyciel where id=:id") ;
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
        nauczyciele2.removeAll(selectedNauczyciele);
        selectedNauczyciele = null;
        view=true;
        delete=false;
    }
    /**
     * Creates a new instance of NauczycieleBeansAdmin
     */
    public NauczycieleBeansAdmin() {
    }
    
}
