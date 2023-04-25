/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import Entities.PerformanceC;
import Entities.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author Siwar
 */
public class PDFGenerator {
    
     public void generatePDF(List<PerformanceC> performances, String fileName) {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        // Ajouter un titre en bleu au-dessus du tableau
        Paragraph title = new Paragraph("Les Performances", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(33, 150, 243)));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        // Ajouter une image statique
        Image image = Image.getInstance("C:\\Users\\Siwar\\Documents\\NetBeansProjects\\Pidev\\src\\GUI\\IMG\\342556323_704667194998998_8201126061904933936_n.png");
        image.scaleAbsolute(100, 50);
        image.setAlignment(Element.ALIGN_RIGHT);
        document.add(image);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        // Ajouter des couleurs de fond et de bordure personnalisées pour la table
        table.getDefaultCell().setBackgroundColor(new BaseColor(217, 237, 247));
        table.getDefaultCell().setBorderColor(new BaseColor(33, 150, 243));
        table.getDefaultCell().setBorderWidth(2f);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("ID"));
        cell.setBackgroundColor(new BaseColor(176, 224, 230));
        cell.setBorderWidth(2f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Commpetition"));
        cell.setBackgroundColor(new BaseColor(176, 224, 230));
        cell.setBorderWidth(2f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Joueur"));
        cell.setBackgroundColor(new BaseColor(176, 224, 230));
        cell.setBorderWidth(2f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Note"));
        cell.setBackgroundColor(new BaseColor(176, 224, 230));
        cell.setBorderWidth(2f);
        table.addCell(cell);

        //// performance
        for (PerformanceC r : performances) {
            table.addCell(String.valueOf(r.getId()));
            table.addCell(r.getIdcom().getNom());
            table.addCell(r.getIdjoueur().getNom()+' '+r.getIdjoueur().getPrenom());
            table.addCell(r.getNote());
        }
        document.add(table);
        document.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

    

