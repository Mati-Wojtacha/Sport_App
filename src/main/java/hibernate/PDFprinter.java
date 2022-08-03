package hibernate;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;


public class PDFprinter {
    PDFprinter(FileOutputStream f){
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, f);
                document.open();
                createTable(document);

                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    PDFprinter() {
        File fileToSave;
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        String wheresave = null;
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            wheresave = fileToSave.getAbsolutePath();
        }
        if(wheresave!=null){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(wheresave));
            document.open();
            createTable(document);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }


    private static void createTable(Document document)
            throws DocumentException {
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("id"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("imie"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("nazwisko"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        for (UserToString s : MenuFrame.deps)
        {
            table.addCell(String.valueOf(s.number));
            table.addCell(s.name);
            table.addCell(s.nawisko);
        }


        document.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}