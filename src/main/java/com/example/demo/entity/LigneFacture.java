package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class LigneFacture {

    @ManyToOne
    private Facture facture;

    @ManyToOne
    private Article article;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public Double getPrix(){
        Double prix = article.getPrix() * quantite;
        return prix;
    }
}
