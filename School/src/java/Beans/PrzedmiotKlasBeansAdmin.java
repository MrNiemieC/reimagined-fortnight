/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.PrzedmiotKlas;
import Entities.Nauczyciel;
import Entities.Klasa;
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
@Named(value = "przedmiotKlasBeansAdmin")
@SessionScoped
public class PrzedmiotKlasBeansAdmin implements Serializable {

    private List<PrzedmiotKlas> przedmiotKlasy;
    private List<PrzedmiotKlas> przedmiotKlasy2;
    private PrzedmiotKlas przedmiotKlasa;
    private PrzedmiotKlas selectedPrzedmiotKlasa;
    private List<PrzedmiotKlas> selectedPrzedmiotKlasy;
    private boolean delete=false;
    private boolean view=true;
    private boolean edit=false;
    private boolean dodaj=false;
    private List<Nauczyciel> nauczyciele;
    private List<Przedmiot> przedmioty;
    private List<Klasa> klasy;
    
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
    public List<Przedmiot> getPrzedmioty() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Przedmiot").list();
         przedmioty=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return przedmioty;
    }
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

    public void setPrzedmiotKlasy(List<PrzedmiotKlas> przedmiotKlasy) {
        this.przedmiotKlasy = przedmiotKlasy;
    }

    public void setPrzedmiotKlasy2(List<PrzedmiotKlas> przedmiotKlasy2) {
        this.przedmiotKlasy2 = przedmiotKlasy2;
    }

    public void setPrzedmiotKlasa(PrzedmiotKlas PrzedmiotKlasa) {
        this.przedmiotKlasa = PrzedmiotKlasa;
    }

    public void setSelectedPrzedmiotKlasa(PrzedmiotKlas selectedPrzedmiotKlasa) {
        this.selectedPrzedmiotKlasa = selectedPrzedmiotKlasa;
    }

    public void setSelectedPrzedmiotKlasy(List<PrzedmiotKlas> selectedPrzedmiotKlasy) {
        this.selectedPrzedmiotKlasy = selectedPrzedmiotKlasy;
    }


    public PrzedmiotKlas getPrzedmiotKlasa() {
        return przedmiotKlasa;
    }

    public PrzedmiotKlas getSelectedPrzedmiotKlasa() {
        return selectedPrzedmiotKlasa;
    }

    public List<PrzedmiotKlas> getSelectedPrzedmiotKlasy() {
        return selectedPrzedmiotKlasy;
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
    
    public void zapisz(PrzedmiotKlas kla)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from PrzedmiotKlas where id=:id") ;
         query.setInteger("id",kla.getId());
         PrzedmiotKlas result = (PrzedmiotKlas) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setPrzedmiot(kla.getPrzedmiot());
        result.setNauczyciel(kla.getNauczyciel());
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

    public List<PrzedmiotKlas> getPrzedmiotKlasy() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM PrzedmiotKlas").list();
         przedmiotKlasy=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return przedmiotKlasy;
    }
    
    public List<PrzedmiotKlas> getPrzedmiotKlasy2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM PrzedmiotKlas").list();
         przedmiotKlasy2=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return przedmiotKlasy2;
    }
    
    public void deletePrzedmiotKlas() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(PrzedmiotKlas selKla:selectedPrzedmiotKlasy)
        {
        Query query = session.createQuery("from PrzedmiotKlas where id=:id") ;
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
        przedmiotKlasy2.removeAll(selectedPrzedmiotKlasy);
        selectedPrzedmiotKlasy = null;
        view=true;
        delete=false;
    }
    public PrzedmiotKlasBeansAdmin() {
    }
    
}
