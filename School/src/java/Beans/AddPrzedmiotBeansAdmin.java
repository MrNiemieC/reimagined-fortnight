/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Entities.Przedmiot;
import Util.HibernateUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author niemi
 */
@Named(value = "addPrzedmiotBeansAdmin")
@RequestScoped
public class AddPrzedmiotBeansAdmin {

    private String nazwa;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    @Inject
    private PrzedmiotyBeansAdmin przedmiotyBeansAdmin;
    public void add()
    {
    Przedmiot d = new Przedmiot(nazwa);
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
    przedmiotyBeansAdmin.setDodaj(false);
    przedmiotyBeansAdmin.setView(true);
    }
    /**
     * Creates a new instance of AddPrzedmiotBeansAdmin
     */
    public AddPrzedmiotBeansAdmin() {
    }
    
}
