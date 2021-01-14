package com.example.demo.dto;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;

import java.time.LocalDate;
import java.util.List;

public class FactureDto {

    private Long id;
    private List<LigneFacture> ligneFactures;
    private Client client;

    public FactureDto(Long id, Article article, Facture facture, int quantite) {

    }

    public FactureDto(Long id, List<LigneFacture> ligneFactures, Client client) {
        this.id = id;
        this.ligneFactures = ligneFactures;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LigneFacture> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(List<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
