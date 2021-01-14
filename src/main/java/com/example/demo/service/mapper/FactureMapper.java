package com.example.demo.service.mapper;

import com.example.demo.dto.ClientDto;
import com.example.demo.dto.FactureDto;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import org.springframework.stereotype.Component;

@Component
public class FactureMapper {
    public FactureDto factureDto(Facture facture) {
        return new FactureDto(facture.getId(), facture.getLigneFactures(), facture.getClient());
    }
}
