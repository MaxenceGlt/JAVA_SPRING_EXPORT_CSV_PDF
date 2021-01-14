package com.example.demo.service.export;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class ArticleExportCVSService {

    @Autowired
    private ArticleRepository articleRepository;

    public void export(PrintWriter writer) {
        List<Article> articles = articleRepository.findAll();
        writer.println("nom article;prix article;stock article");
        for(Article article : articles ){
             writer.println(article.getLibelle().replace(";","")+ ";" + article.getPrix() + ";" + article.getStock());
        }



    }


}
