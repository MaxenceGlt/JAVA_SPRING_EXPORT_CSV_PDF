package com.example.demo.controller.export;

import com.example.demo.service.export.FactureExportXLSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("")
public class ExportFactureController {

    @Autowired
    private FactureExportXLSXService factureExportXLSXService;

    @GetMapping("/factures/xlsx")
    public void clientsXLSX(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"factures.xlsx\"");
        factureExportXLSXService.export(response.getOutputStream());
    }
}
