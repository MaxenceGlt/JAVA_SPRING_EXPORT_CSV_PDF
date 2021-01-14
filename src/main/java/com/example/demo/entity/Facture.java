package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Facture {

    @ManyToOne
    @JoinColumn(name = "id")
    private Client client;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("facture")
    private List<LigneFacture> ligneFactures;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneFacture> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(List<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal(){
        Double prixTotal = null;
        for(LigneFacture ligneFacture : ligneFactures){
            prixTotal = ligneFacture.getPrix();
        }
        return prixTotal;
    }
}
