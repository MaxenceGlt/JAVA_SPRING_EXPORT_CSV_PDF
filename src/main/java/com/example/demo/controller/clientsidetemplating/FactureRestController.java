package com.example.demo.controller.clientsidetemplating;

import com.example.demo.dto.ClientDto;
import com.example.demo.dto.FactureDto;
import com.example.demo.service.ClientService;
import com.example.demo.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/factures")
public class FactureRestController {
    @Autowired
    private FactureService factureService;

    /**
     * Exposition d'une api déclenchée sur l'url http://..../articles.
     *
     * @return la liste des articles au format JSON. Voir la classe ArticleDto pour le détail du format.
     */
    @GetMapping
    public List<FactureDto> getFacture() {
        return factureService.findAll();
    }
}
