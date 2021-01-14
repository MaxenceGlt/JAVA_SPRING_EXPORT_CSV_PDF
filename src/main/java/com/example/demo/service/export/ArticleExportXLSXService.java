package com.example.demo.service.export;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ArticleExportXLSXService {

    @Autowired
    private ArticleRepository articleRepository;

    public void export(OutputStream outputStream){
        try {
            List<Article> articles = articleRepository.findAll();

            Workbook wb = new HSSFWorkbook();
            Sheet sheet1 = wb.createSheet("new sheet");
            int x = 0;
            for(Article article : articles){
                Row row = sheet1.createRow(x);
                row.createCell(0).setCellValue(article.getLibelle().toString());
                row.createCell(1).setCellValue(article.getPrix());
                row.createCell(2).setCellValue(article.getStock());
                x++;
            }
            wb.write(outputStream);
            wb.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
