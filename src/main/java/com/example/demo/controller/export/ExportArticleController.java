package com.example.demo.controller.export;

import com.example.demo.service.export.ArticleExportCVSService;
import com.example.demo.service.export.ArticleExportXLSXService;
import com.example.demo.service.export.ExportPDFITextService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Controller pour réaliser export des articles.
 */
@Controller
@RequestMapping("export")
public class ExportArticleController {

    @Autowired
    private ArticleExportCVSService articleExportCVSService;

    @Autowired
    private ArticleExportXLSXService articleExportXLSXService;

    @Autowired
    private ExportPDFITextService exportPDFITextService;
    /**
     * Export des articles au format CSV.
     */
    @GetMapping("/articles/csv")
    public void articlesCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-articles.csv\"");
        PrintWriter writer = response.getWriter();
        articleExportCVSService.export(writer);
    }

    @GetMapping("/articles/xlsx")
    public void articlesXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-articles.xlsx\"");
        OutputStream outputStream = response.getOutputStream();
        articleExportXLSXService.export(outputStream);
    }

    /**
     * Export de la liste des articles au format PDF.
     */
    @GetMapping("/articles/pdf")
    public void facturesPDF(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.pdf\"");
        exportPDFITextService.export(response.getOutputStream());
    }

}
