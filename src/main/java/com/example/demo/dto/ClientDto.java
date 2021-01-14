package com.example.demo.dto;

import com.example.demo.entity.Client;

import java.time.LocalDate;

public class ClientDto {

    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    public ClientDto(){

    }

    public ClientDto(Long id, String nom,String prenom){
        this.id = id;
        this.nom=nom;
        this.prenom=prenom;
    }

    public ClientDto(Long id, String nom,String prenom,LocalDate dateNaissance){
        this.id = id;
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaissance=dateNaissance;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance(){
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance){
        this.dateNaissance=dateNaissance;
    }
}
