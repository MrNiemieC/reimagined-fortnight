
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import Entities.Dyrektor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author niemi
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SessionFactory factory=HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List dyrektors = session.createQuery("FROM Dyrektor").list(); 
         for (Iterator iterator = dyrektors.iterator(); iterator.hasNext();){
            Dyrektor dyrektor = (Dyrektor) iterator.next(); 
            System.out.println("ID: " + dyrektor.getId()); 
            System.out.println("Imie: " + dyrektor.getImie()); 
            System.out.println("Nazwisko: " + dyrektor.getNazwisko()); 
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
        // TODO code application logic here
    }
    
}
