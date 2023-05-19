package com.example.Licenta.Controller;

import com.example.Licenta.Service.PDFGeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFExportController {
    private final PDFGeneratorService pdfGeneratorService;

    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response, @RequestParam(name = "cifra") String cifra, @RequestParam(name = "figura") String figura, @RequestParam(name = "culoare") String culoare) throws IOException, URISyntaxException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content_Disponsition";
        String headerValue = "attachement; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        pdfGeneratorService.setNr(Integer.valueOf(cifra));
        pdfGeneratorService.setFigura(figura);
        pdfGeneratorService.setCuloare(culoare);
        this.pdfGeneratorService.crearePDF(response);
    }
}
