package com.example.demo.service.export;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ClientRepository;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClientExportXLSXService {

    @Autowired
    private ClientRepository clientRepository;

    public void export(OutputStream outputStream) {
        try {
            //CRéation du workbook (excel)
            Workbook wb = new XSSFWorkbook();
            //Création de la feuille excel
            Sheet sheet = wb.createSheet("sheet");
            //Création de la ligne de titre
            Row rowHeader = sheet.createRow(0);
            //Instanciation du style de cellule
            CellStyle style = wb.createCellStyle();
            //Création des cellule du header
            Cell cellTNom = rowHeader.createCell(0);
            cellTNom.setCellValue("Nom");
            Cell cellTPrenom = rowHeader.createCell(1);
            cellTPrenom.setCellValue("Prenom");
            Cell cellTAge = rowHeader.createCell(2);
            cellTAge.setCellValue("Age");

            //----------------Modification du style-----------------------//
            //Modif concernant des contours des cellules"

            style.setBorderBottom(BorderStyle.THICK);
            style.setBottomBorderColor(IndexedColors.BLUE.getIndex());
            style.setBorderLeft(BorderStyle.THICK);
            style.setLeftBorderColor(IndexedColors.BLUE.getIndex());
            style.setBorderRight(BorderStyle.THICK);
            style.setRightBorderColor(IndexedColors.BLUE.getIndex());
            style.setBorderTop(BorderStyle.THICK);
            style.setTopBorderColor(IndexedColors.BLUE.getIndex());

            //Instanciation d'un object font pour modifier le texte d'un style
            Font font = wb.createFont();
            //Couleur pink pour le texte
            font.setColor(HSSFColor.PINK.index);
            //Police d'écriture
            font.setFontName("Helvetica");
            //Taille de la police d'ecriture
            font.setFontHeightInPoints((short)10);
            //Modif concernant "Aligner en haut"
            style.setVerticalAlignment(VerticalAlignment.TOP);
            //Mise en gras des libelles
            font.setBold(true);
            //On lie le font au style
            style.setFont(font);
            //On lie le style aux cellules
            cellTNom.setCellStyle(style);
            cellTPrenom.setCellStyle(style);
            cellTAge.setCellStyle(style);

            List<Client> clients = clientRepository.findAll();
            //Variable pour créer une ligne a chaque object
            int x = 1;
            for (Client client : clients) {
                int age;
                //Création de la row
                Row row = sheet.createRow(x);
                //Création des cellules (recuperation de chaque clients)
                Cell cellNom = row.createCell(0);
                cellNom.setCellValue(client.getNom());
                Cell cellPrenom = row.createCell(1);
                cellPrenom.setCellValue(client.getPrenom());
                Cell cellAge = row.createCell(2);
                cellAge.setCellValue(age = Period.between(client.getDateNaissance(), LocalDate.now()).getYears());
                //Creation du style1 pour les cellules des clients
                CellStyle style1 = wb.createCellStyle();
                //Style1 : border des cellules -> bleu foncé
                style1.setBorderBottom(BorderStyle.THICK);
                style1.setBottomBorderColor(IndexedColors.BLUE.getIndex());
                style1.setBorderLeft(BorderStyle.THICK);
                style1.setLeftBorderColor(IndexedColors.BLUE.getIndex());
                style1.setBorderRight(BorderStyle.THICK);
                style1.setRightBorderColor(IndexedColors.BLUE.getIndex());
                style1.setBorderTop(BorderStyle.THICK);
                style1.setTopBorderColor(IndexedColors.BLUE.getIndex());
                style1.setVerticalAlignment(VerticalAlignment.TOP);
                //liaison des cellules au style1
                cellNom.setCellStyle(style1);
                cellPrenom.setCellStyle(style1);
                cellAge.setCellStyle(style1);
                x++;
            }
            //Automatisation de la largeur en fonction du texte
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            wb.write(outputStream);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
