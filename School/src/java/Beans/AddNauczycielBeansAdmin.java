/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Nauczyciel;
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
@Named(value = "addNauczycielBeansAdmin")
@RequestScoped
public class AddNauczycielBeansAdmin {
    
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
    private NauczycieleBeansAdmin nauczycieleBeansAdmin;
    public void add()
    {
    Nauczyciel d = new Nauczyciel(imie,nazwisko);
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
    nauczycieleBeansAdmin.setDodaj(false);
    nauczycieleBeansAdmin.setView(true);
    }

    /**
     * Creates a new instance of AddNauczycielBeansAdmin
     */
    public AddNauczycielBeansAdmin() {
    }
    
}
