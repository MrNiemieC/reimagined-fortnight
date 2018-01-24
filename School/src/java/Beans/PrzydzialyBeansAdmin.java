/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Przydzialy;
import Entities.Nauczyciel;
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
@Named(value = "przydzialyBeansAdmin")
@SessionScoped
public class PrzydzialyBeansAdmin implements Serializable {
    
    private List<Przydzialy> przydzialy;
    private List<Przydzialy> przydzialy2;
    private Przydzialy przydzial;
    private Przydzialy selectedPrzydzial;
    private List<Przydzialy> selectedPrzydzialy;
    private boolean delete=false;
    private boolean view=true;
    private boolean edit=false;
    private boolean dodaj=false;
    private List<Nauczyciel> nauczyciele;
    private List<Przedmiot> przedmioty;
    
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

    public void setPrzydzialy(List<Przydzialy> przydzialy) {
        this.przydzialy = przydzialy;
    }

    public void setPrzydzialy2(List<Przydzialy> przydzialy2) {
        this.przydzialy2 = przydzialy2;
    }

    public void setPrzydzial(Przydzialy przydzial) {
        this.przydzial = przydzial;
    }

    public void setSelectedPrzydzial(Przydzialy selectedPrzydzial) {
        this.selectedPrzydzial = selectedPrzydzial;
    }

    public void setSelectedPrzydzialy(List<Przydzialy> selectedPrzydzialy) {
        this.selectedPrzydzialy = selectedPrzydzialy;
    }


    public Przydzialy getPrzydzial() {
        return przydzial;
    }

    public Przydzialy getSelectedPrzydzial() {
        return selectedPrzydzial;
    }

    public List<Przydzialy> getSelectedPrzydzialy() {
        return selectedPrzydzialy;
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
    
    public void zapisz(Przydzialy kla)
    {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("from Przydzialy where id=:id") ;
         query.setInteger("id",kla.getId());
         Przydzialy result = (Przydzialy) query.uniqueResult() ;
        System.out.println(result);
        if(result!=null)
        {
        result.setPrzedmiot(kla.getPrzedmiot());
        result.setNauczyciel(kla.getNauczyciel());
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

    public List<Przydzialy> getPrzydzialy() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Przydzialy").list();
         przydzialy=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return przydzialy;
    }
    
    public List<Przydzialy> getPrzydzialy2() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List klasys = session.createQuery("FROM Przydzialy").list();
         przydzialy2=klasys;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return przydzialy2;
    }
    
    public void deletePrzydzialy() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        for(Przydzialy selKla:selectedPrzydzialy)
        {
        Query query = session.createQuery("from Przydzialy where id=:id") ;
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
        przydzialy2.removeAll(selectedPrzydzialy);
        selectedPrzydzialy = null;
        view=true;
        delete=false;
    }

    /**
     * Creates a new instance of PrzydzialyBeansAdmin
     */
    public PrzydzialyBeansAdmin() {
    }
    
}
