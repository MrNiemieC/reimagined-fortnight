/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Uczen;
import Entities.Klasa;
import Util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
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
@Named(value = "addUczniowieBeansAdmin")
@RequestScoped
public class AddUczniowieBeansAdmin {
    
    private String imie;
    private String nazwisko;
    private Klasa klasa;
    private List<Klasa> klasy;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
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
    
    @Inject
    private UczniowieBeansAdmin uczniowieBeansAdmin;
    public void add()
    {
    Uczen d = new Uczen(imie,nazwisko,klasa);
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
    uczniowieBeansAdmin.setDodaj(false);
    uczniowieBeansAdmin.setView(true);
    }

    /**
     * Creates a new instance of AddUczniowieBeansAdmin
     */
    public AddUczniowieBeansAdmin() {
    }
    
}
