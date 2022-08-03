package hibernate;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
/**
 * Examples of various different ways to print PDFs using PDFBox.
 */
public class Printing {

    Printing() throws IOException {
        File fstream = File.createTempFile("tmpDirectory", ".pdf");
        FileOutputStream fos = new FileOutputStream(fstream);
        new PDFprinter(fos);
        PDDocument document = new PDDocument();
        document = PDDocument.load(fstream);
        try {
            printWithDialog(document);
            fstream.deleteOnExit();

        } catch (Exception e) {

        }
    }
    /**
     * Prints the document at its actual size. This is the recommended way to print.
     */
    /**
     * Prints with a print preview dialog.
     */
    private static boolean printWithDialog(PDDocument document) throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        if (job.printDialog()) {
            job.print();
            return true;
        }
        return false;
    }
    /**
     * Prints with a print preview dialog and custom PrintRequestAttribute values.
     */
}
