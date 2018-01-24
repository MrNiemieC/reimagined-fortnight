/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Sprawdzian;
import Entities.PrzedmiotKlas;
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
@Named(value = "addSprawdzianBeansAdmin")
@RequestScoped
public class AddSprawdzianBeansAdmin {

    private String nazwa;
    private Date dataSpr;
    private PrzedmiotKlas przedmiotKlas;
    private List<PrzedmiotKlas> przedmiotKlasy;

    public Date getDataSpr() {
        return dataSpr;
    }

    public void setDataSpr(Date dataSpr) {
        this.dataSpr = dataSpr;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public PrzedmiotKlas getPrzedmiotKlas() {
        return przedmiotKlas;
    }

    public void setPrzedmiotKlas(PrzedmiotKlas przedmiotKlas) {
        this.przedmiotKlas = przedmiotKlas;
    }
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
    
    @Inject
    private SprawdzianyBeansAdmin sprawdzianyBeansAdmin;
    public void add()
    {
    Sprawdzian d = new Sprawdzian(nazwa,dataSpr,przedmiotKlas);
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
    sprawdzianyBeansAdmin.setDodaj(false);
    sprawdzianyBeansAdmin.setView(true);
    }
    /**
     * Creates a new instance of AddSprawdzianBeansAdmin
     */
    public AddSprawdzianBeansAdmin() {
    }
    
}
