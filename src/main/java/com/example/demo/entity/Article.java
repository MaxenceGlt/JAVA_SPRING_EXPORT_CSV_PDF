package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * Entity représentant un article.
 */
@Entity
public class Article {

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("article")
    private List<LigneFacture> ligneFactures;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String libelle;

    @Column
    private double prix;

    @Column
    private int stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<LigneFacture> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(List<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }

}
