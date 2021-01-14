package com.example.demo.controller.clientsidetemplating;

import com.example.demo.dto.FactureDto;
import com.example.demo.dto.LigneFactureDto;
import com.example.demo.service.FactureService;
import com.example.demo.service.LigneFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("rest/ligneFactures")
public class LigneFactureRestController {
    @Autowired
    private LigneFactureService ligneFactureService;

    /**
     * Exposition d'une api déclenchée sur l'url http://..../articles.
     *
     * @return la liste des articles au format JSON. Voir la classe ArticleDto pour le détail du format.
     */
    @GetMapping
    public List<LigneFactureDto> getLigneFacture() {
        return ligneFactureService.findAll();
    }
}
