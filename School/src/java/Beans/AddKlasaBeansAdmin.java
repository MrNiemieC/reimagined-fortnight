/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Klasa;
import Entities.Nauczyciel;
import Entities.PrzedmiotKlas;
import Entities.Uczen;
import Util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
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
@Named(value = "addKlasaBeansAdmin")
@RequestScoped
public class AddKlasaBeansAdmin {
    
    private String nazwa;
    private Nauczyciel nauczyciel;
    private List<Nauczyciel> nauczyciele;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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
    private KlasyBeansAdmin klasyBeansAdmin;
    public void add()
    {
    Klasa d = new Klasa(nazwa,nauczyciel);
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
    klasyBeansAdmin.setDodaj(false);
    klasyBeansAdmin.setView(true);
    }

    /**
     * Creates a new instance of AddKlasaBeansAdmin
     */
    public AddKlasaBeansAdmin() {
        
    }
    
}
