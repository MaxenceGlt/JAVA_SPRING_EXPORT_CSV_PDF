package com.example.demo.service.export;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.OutputStream;
import java.util.List;

@Service
public class ExportPDFITextService {

    @Autowired
    private ArticleRepository articleRepository;

    public void export(OutputStream outputStream) throws  DocumentException{

        //Permet d'agrandir la taille du document pdf
        Rectangle small = new Rectangle(500,300);

        //Création du style pour les valeurs des headers des colonnes
        Font titleFont = FontFactory.getFont("Times Roman", 16, BaseColor.RED);

        //Création du document
        Document document = new Document(small, 2, 2, 2, 2);
        PdfWriter.getInstance(document, outputStream);

        //Liste avec récupération des articles
        List<Article> articles = articleRepository.findAll();
        document.open();

        //Création du tableau
        PdfPTable table = new PdfPTable(2);

        //Création des cellules du header
        PdfPCell cellTLibelle = new PdfPCell(new Phrase("Libelle article",titleFont));
        cellTLibelle.setFixedHeight(30);
        cellTLibelle.setVerticalAlignment(Element.ALIGN_MIDDLE);
        PdfPCell cellTPrix = new PdfPCell(new Phrase("Prix article",titleFont));
        cellTPrix.setFixedHeight(30);
        cellTPrix.setVerticalAlignment(Element.ALIGN_MIDDLE);

        //Ajout des cellules header dans la table
        table.addCell(cellTLibelle);
        table.addCell(cellTPrix);

        //Recuperation des achats
        for (Article article : articles){
            //Creation cellules des achats
            PdfPCell cellLibelle = new PdfPCell(new Phrase(article.getLibelle()));
            PdfPCell cellPrix = new PdfPCell(new Phrase(String.valueOf(article.getPrix())));
            //Ajout des attributs achats a la table
            table.addCell(cellLibelle);
            table.addCell(cellPrix);
        }
        //Ajout de la table dans le document PDF
        document.add(table);
        document.close();
    }
}
