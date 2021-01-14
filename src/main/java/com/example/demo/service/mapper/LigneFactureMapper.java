package com.example.demo.service.mapper;

import com.example.demo.dto.FactureDto;
import com.example.demo.dto.LigneFactureDto;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.stereotype.Component;

@Component
public class LigneFactureMapper {
    public LigneFactureDto ligneFactureDto(LigneFacture ligneFacture) {
        return new LigneFactureDto(ligneFacture.getId(), ligneFacture.getArticle(), ligneFacture.getFacture(),ligneFacture.getQuantite());
    }
}
