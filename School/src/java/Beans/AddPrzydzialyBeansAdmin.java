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
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author niemi
 */
@Named(value = "addPrzydzialyBeansAdmin")
@RequestScoped
public class AddPrzydzialyBeansAdmin {

    private Przedmiot przedmiot;
    private List<Przedmiot> przedmioty;
    private Nauczyciel nauczyciel;
    private List<Nauczyciel> nauczyciele;

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
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

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
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
    
    @Inject
    private PrzydzialyBeansAdmin przydzialyBeansAdmin;
    public void add()
    {
    Przydzialy d = new Przydzialy(nauczyciel,przedmiot);
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.openSession();
    Transaction tx = null;
    try {
     tx = session.beginTransaction();
    Serializable id = session.save(d);
     tx.commit();
    }
    catch (Exception e) {
     if (tx!=null) tx.rollback();
     throw e;
    }
    finally {
     session.close();
    }
    przydzialyBeansAdmin.setDodaj(false);
    przydzialyBeansAdmin.setView(true);
    }
    /**
     * Creates a new instance of AddPrzydzialyBeansAdmin
     */
    public AddPrzydzialyBeansAdmin() {
    }
    
}
