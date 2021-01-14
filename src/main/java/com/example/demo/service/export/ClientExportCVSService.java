package com.example.demo.service.export;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClientExportCVSService {

    @Autowired
    private ClientRepository clientRepository;

    public void export(PrintWriter writer) {
        List<Client> clients = clientRepository.findAll();
        writer.println("Nom;Prenom;Age");
        for(Client client : clients ){
            int age;
            age = Period.between(client.getDateNaissance(),LocalDate.now()).getYears();
            writer.println(client.getNom()+ ";" + client.getPrenom() + ";" + age);
        }
    }
}
