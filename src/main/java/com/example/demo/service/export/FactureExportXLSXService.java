package com.example.demo.service.export;

import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class FactureExportXLSXService {

    @Autowired
    private FactureRepository  factureRepository;

    public void export(OutputStream outputStream) {
        try {
            List<Facture> factures = factureRepository.findAll();
            Workbook wb = new HSSFWorkbook();
            String previousClientSheet = "";
            for (Facture facture: factures ) {
                if ( !previousClientSheet.equals(facture.getClient().getNom())) {
                    // Création première feuille
                    String safeName = WorkbookUtil.createSafeSheetName(facture.getClient().getNom()+ " " + facture.getClient().getPrenom());
                    // Si le sheet n'existe pas au nom du client alors il le fait
                    Sheet sheet = wb.createSheet(safeName);
                    // Font body
                    Font bodyFont = creationFontStyle(wb, IndexedColors.BLACK.getIndex(), false, "Trebuchet MS", (short) 11);
                    // Style body
                    CellStyle bodyStyle = wb.createCellStyle();
                    bodyStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
                    bodyStyle.setFont(bodyFont);

                    for (int i = 0; i <= 3; i++) {
                        // Création des lignes
                        Row newRow = sheet.createRow(i);
                        switch (i) {
                            case 0:
                                creationCell(newRow, 0, "Nom :");
                                creationCell(newRow, 1, facture.getClient().getNom());
                                break;
                            case 1:
                                creationCell(newRow, 0, "Prenom :");
                                creationCell(newRow, 1, facture.getClient().getPrenom());
                                break;
                            case 2:
                                creationCell(newRow, 0, "Année de naissance :");
                                creationCell(newRow, 1, String.valueOf(facture.getClient().getDateNaissance().getYear()));
                                break;
                            case 3:
                                StringBuilder str = new StringBuilder();
                                CellStyle cellStyle = wb.createCellStyle();
                                cellStyle.setFont(creationFontStyle(wb, IndexedColors.BLACK.getIndex(), true, "Trebuchet MS", (short) 11));
                                // Pour récupérer le nb de facture du client et leurs id
                                List<Long> listIdFactures = new ArrayList<>();
                                for (Facture fac: factures) {
                                    if (fac.getClient().getId().equals(facture.getClient().getId())){
                                        listIdFactures.add(fac.getId());
                                    }
                                }
                                creationCell(newRow, 0, str.append(listIdFactures.size()).append(" facture(s)").toString(), cellStyle);
                                for (int j = 0; j < listIdFactures.size(); j++) {
                                    creationCell(newRow, j + 1, listIdFactures.get(j).toString());
                                }
                                break;
                        }
                    }
                    sheet.autoSizeColumn(0, true);
                    sheet.autoSizeColumn(1, true);
                    previousClientSheet = facture.getClient().getNom();
                }


                String safeNameLigneFacture = WorkbookUtil.createSafeSheetName("Facture n°"+ facture.getId());

                Sheet sheetFacture = wb.createSheet(safeNameLigneFacture);

                // Font header
                Font headerFont  = creationFontStyle(wb, IndexedColors.BLACK.getIndex(), true, "Trebuchet MS", (short) 11);
                CellStyle headerStyle = wb.createCellStyle();
                headerStyle.setFont(headerFont);

                // Ligne pour nom des colonnes
                Row firstRow = sheetFacture.createRow(0);
                creationCell(firstRow, 0, "Désignation", headerStyle);
                creationCell(firstRow, 1, "Quantité", headerStyle);
                creationCell(firstRow, 2, "Prix unitaire", headerStyle);

                // Ligne pour les articles
                int count = 1;
                for (LigneFacture ligneFacture : facture.getLigneFactures()
                ) {
                    Row newRow = sheetFacture.createRow(count);
                    creationCell(newRow, 0, ligneFacture.getArticle().getLibelle());
                    creationCell(newRow, 1, String.valueOf(ligneFacture.getQuantite()));
                    creationCell(newRow, 2, String.valueOf(ligneFacture.getArticle().getPrix()));
                    count++;
                }
                // Ligne total
                Row rowTotal = sheetFacture.createRow(count);
                CellStyle totalStyle = wb.createCellStyle();
                totalStyle.setAlignment(HorizontalAlignment.RIGHT);
                totalStyle.setFont(headerFont);
                creationCell(rowTotal, 0, "Total", totalStyle);
                creationCell(rowTotal, 2, facture.getTotal().toString());
                sheetFacture.addMergedRegion(new CellRangeAddress(count, count, 0, 1));
                sheetFacture.autoSizeColumn(0, true);
                sheetFacture.autoSizeColumn(1, true);
                sheetFacture.autoSizeColumn(2, true);
            }
            wb.write(outputStream);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Font creationFontStyle(Workbook wb, Short indexColor, Boolean isBold, String fontName, short heightFont) {
        Font font = wb.createFont();
        font.setColor(indexColor);
        font.setBold(isBold);
        font.setFontName(fontName);
        font.setFontHeightInPoints(heightFont);
        return font;
    }

    private void creationCell(Row myRow, Integer indexCell, String nomCell) {
        Cell myCell = myRow.createCell(indexCell);
        myCell.setCellValue(nomCell);
    }

    private void creationCell(Row myRow, Integer indexCell, String nomCell, CellStyle cellStyle) {
        Cell myCell = myRow.createCell(indexCell);
        myCell.setCellValue(nomCell);
        myCell.setCellStyle(cellStyle);
    }

}
