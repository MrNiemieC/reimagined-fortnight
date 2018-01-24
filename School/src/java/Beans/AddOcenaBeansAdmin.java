/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Ocena;
import Entities.Sprawdzian;
import Entities.Uczen;
import Util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Named(value = "addOcenaBeansAdmin")
@RequestScoped
public class AddOcenaBeansAdmin {
    private BigDecimal wartosc;
    private Sprawdzian sprawdzian;
    private List<Sprawdzian> sprawdziany;
    private Uczen uczen;
    private List<Uczen> uczniowie;

    public Sprawdzian getSprawdzian() {
        return sprawdzian;
    }

    public void setSprawdzian(Sprawdzian sprawdzian) {
        this.sprawdzian = sprawdzian;
    }

    public Uczen getUczen() {
        return uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }

    public BigDecimal getWartosc() {
        return wartosc;
    }

    public void setWartosc(BigDecimal wartosc) {
        this.wartosc = wartosc;
    }
     public List<Sprawdzian> getSprawdziany() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Sprawdzian").list();
         sprawdziany=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return sprawdziany;
    }
      public List<Uczen> getuczniowie() {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List nauczyciels = session.createQuery("FROM Uczen").list();
         uczniowie=nauczyciels;
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        return uczniowie;
    }
    
    
    @Inject
    private OcenyBeansAdmin ocenyBeansAdmin;
    public void add()
    {
    Ocena d = new Ocena(wartosc,sprawdzian,uczen);
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
    ocenyBeansAdmin.setDodaj(false);
    ocenyBeansAdmin.setView(true);
    }

    /**
     * Creates a new instance of AddOcenaBeansAdmin
     */
    public AddOcenaBeansAdmin() {
    }
    
}
