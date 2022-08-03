package hibernate;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.awt.*;
import java.awt.print.*;

public class Printer implements Printable {


    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {

        Document document = new Document();
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


        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        /*
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping

         */

        /*
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        int i=1;
        String id="id";
        String imie ="imie";
        String nazwisko = "nazwisko";
        String wstep=String.format("|%-6s|%-15s|%-15s|",id, imie,nazwisko);

        g.drawString(wstep,100,80);
        for(int x=0; x<1000; x++){
            g.drawString("-",x,100);
        }
        for (UserToString s : MenuFrame.deps)
        {
            id=String.format("|%-6s", s.number);
            imie=String.format("|%-15s", s.name);
            nazwisko=String.format("|%-15s", s.nawisko);
            g.drawString(id, 100, 100+(i*20));
            g.drawString(imie, 126, 100+(i*20));
            g.drawString(nazwisko, 188, 100+(i*20));
            g.drawString("|",264,100+(i*20));
            i++;
        }


        /*
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }


    Printer() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }
    }
}