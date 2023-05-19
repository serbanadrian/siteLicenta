package com.example.Licenta.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;



@Service
public class PDFGeneratorService {
    private int nr;
    private String figura;
    private String culoare;

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public void crearePDF(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font titlu = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titlu.setSize(23);
        Paragraph paragraph = new Paragraph("Invatarea " + this.corectie() + nr, titlu);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        Paragraph paragraph1 = new Paragraph("Exersarea scrierii " + this.corectie() + nr + this.numar(), font);
        Paragraph paragraph2 = new Paragraph("Exercitii de recunoastere a " + this.corectie() + nr + this.numar() + ":", font);
        Paragraph paragraph3 = new Paragraph("Creati prin incercuire grupe de cate " + this.figuri(), font);
        Paragraph paragraph4 = new Paragraph("Deseneaza ce inseamna pentru tine " + nr + this.numar());
        Paragraph newline = new Paragraph("\n");

        Image writingExerciseImage = Image.getInstance("src/main/resources/static/ScrierePDF " + nr + ".png");
        writingExerciseImage.scaleAbsolute(450, 50);
        Image circlingExerciseImage = Image.getInstance("src/main/resources/static/Incercuire cifra " + nr + ".png");
        circlingExerciseImage.scaleAbsolute(450, 100);
        Image groupExerciseImage = Image.getInstance("src/main/resources/static/" + figura + " " + culoare + ".png");
        groupExerciseImage.scaleAbsolute(450, 100);

        document.add(paragraph);
        document.add(newline);
        document.add(paragraph1);
        document.add(newline);
        document.add(writingExerciseImage);
        document.add(writingExerciseImage);
        document.add(newline);
        document.add(paragraph2);
        document.add(newline);
        document.add(circlingExerciseImage);
        document.add(newline);
        document.add(paragraph3);
        document.add(newline);
        document.add(groupExerciseImage);
        document.add(newline);
        document.add(paragraph4);
        document.close();
    }

    public String numar() {
        if (nr == 1) return "(unu)";
        if (nr == 2) return "(doi)";
        if (nr == 3) return "(trei)";
        if (nr == 4) return "(patru)";
        if (nr == 5) return "(cinci)";
        if (nr == 6) return "(sase)";
        if (nr == 7) return "(sapte)";
        if (nr == 8) return "(opt)";
        if (nr == 9) return "(noua)";
        if (nr == 10) return "(zece)";
        return "invalid";
    }

    public String figuri() {
        if (nr == 1) return "1(individuale):";
        if (nr == 2) return "2(doua):";
        if (nr == 3) return "3(trei):";
        if (nr == 4) return "4(patru):";
        if (nr == 5) return "5(cinci):";
        if (nr == 6) return "6(sase):";
        if (nr == 7) return "7(sapte):";
        if (nr == 8) return "8(opt):";
        if (nr == 9) return "9(noua):";
        if (nr == 10) return "10(zece):";
        return "invalid";
    }

    public String corectie() {
        if (nr == 10) return "numarului ";
        else return "cifrei ";
    }
}
