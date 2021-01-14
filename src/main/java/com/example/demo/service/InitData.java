package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe permettant d'insérer des données dans l'application.
 */
@Service
@Transactional
public class InitData implements ApplicationListener<ApplicationReadyEvent> {

    private EntityManager entityManager;

    public InitData(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertTestData();
    }

    private void insertTestData() {
        Article a1 = createArticle("Chargeurs de télép;hones Portables", 10.50, 9);
        Article a2 = createArticle("Playmobil Hydravion de Police", 20.50, 2);
        Article a3 = createArticle("Distributeur de croquettes pour chien", 15.50, 0);
        Client c1 = createClient("prenom1","testprenom",LocalDate.of(2000,03,18));
        Client c2 = createClient("prenom2","testprenom",LocalDate.of(2000,04,20));
        Client c3 = createClient("prenom3","testprenom",LocalDate.of(2000,06,03));
        LigneFacture l1 = createLigneFactures(a1,2);
        LigneFacture l2 = createLigneFactures(a2,1);
        LigneFacture l3 = createLigneFactures(a2,1);
        List<LigneFacture> ligneFactures = null;
        ligneFactures.add(l1);
        ligneFactures.add(l2);
        ligneFactures.add(l3);
        Facture f1 = createFacture(c1,ligneFactures);
       // System.out.println(String.valueOf(f1.getTotal()));

    }

    private Article createArticle(String libelle, double prix, int stock) {
        Article a1 = new Article();
        a1.setLibelle(libelle);
        a1.setPrix(prix);
        a1.setStock(stock);
        entityManager.persist(a1);
        return a1;
    }

    private Client createClient(String nom, String prenom,LocalDate dateNaissance){
        Client c1 = new Client();
        c1.setNom(nom);
        c1.setPrenom(prenom);
        c1.setDateNaissance(dateNaissance);
        entityManager.persist(c1);
        return c1;
    }

    private Facture createFacture(Client client, List<LigneFacture> ligneFactures){
        Facture facture = new Facture();
        facture.setClient(client);
        facture.setLigneFactures(ligneFactures);
        entityManager.persist(facture);
        return facture;
    }

    private LigneFacture createLigneFactures(Article article,int quantite){
        LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setArticle(article);
        ligneFacture.setQuantite(quantite);
        entityManager.persist(ligneFacture);
        return ligneFacture;
    }

}
