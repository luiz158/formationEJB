/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sebglon.model;

import org.sebglon.modele.Fournisseur;
import org.sebglon.modele.Produit;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sgl
 */
public class ProduitTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx; // ATTENTION ceci n'est pas JTA
    
    public ProduitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("InMemoryPU");
        em = emf.createEntityManager();
    }
    
    @AfterClass
    public static void tearDownClass() {
        em.close();
        emf.close();
    }
    
    @Before
    public void setUp() {
        tx = em.getTransaction();
    }
    
    @After
    public void tearDown() {
        
    }


    @Test
    public void createProduit() throws Exception{
        Produit produit = new Produit("Produit A1", "TV", Calendar.getInstance());
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setLibelle("Fournisseur 1");
        produit.setFournisseur(fournisseur);
        // persist le produit en base
        tx.begin();
        em.persist(fournisseur);
        em.persist(produit);
        tx.commit();
        assertNotNull("ID doit être non null", produit.getId());
        
        // Récupération des produits en base
        List<Produit> produits = em.createNamedQuery(Produit.Q_FINDALL).getResultList();
    }
}
