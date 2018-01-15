/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import entities.PrzedmiotKlas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author niemi
 */
@Stateless
public class PrzedmiotKlasFacade extends AbstractFacade<PrzedmiotKlas> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrzedmiotKlasFacade() {
        super(PrzedmiotKlas.class);
    }
    
}
