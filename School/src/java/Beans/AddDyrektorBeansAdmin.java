/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Dyrektor;
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
@Named(value = "addDyrektorBeansAdmin")
@RequestScoped
public class AddDyrektorBeansAdmin {
    private String imie;
    private String nazwisko;

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
    
    @Inject
    private DyrektorzyBeansAdmin dyrektorzyBeansAdmin;
    public void add()
    {
    Dyrektor d = new Dyrektor(imie,nazwisko);
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
    dyrektorzyBeansAdmin.setDodaj(false);
    dyrektorzyBeansAdmin.setView(true);
    }

    /**
     * Creates a new instance of AddDyrektorBeansAdmin
     */
    public AddDyrektorBeansAdmin() {
    }
    
}
