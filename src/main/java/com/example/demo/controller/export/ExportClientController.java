package com.example.demo.controller.export;

import com.example.demo.service.export.ClientExportCVSService;
import com.example.demo.service.export.ClientExportXLSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("export")
public class ExportClientController {

    @Autowired
    private ClientExportCVSService exportCVSService;
    @Autowired
    private ClientExportXLSXService clientExportXLSXService;

    @GetMapping("/clients/csv")
    public void articlesCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-clients.csv\"");
        PrintWriter writer = response.getWriter();
        exportCVSService.export(writer);
    }

    @GetMapping("/clients/xlsx")
    public void clientsXLSX(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.xlsx\"");
        clientExportXLSXService.export(response.getOutputStream());
    }

}
