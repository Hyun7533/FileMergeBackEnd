package com.example.demo.CTL;

import ch.qos.logback.core.pattern.color.BoldYellowCompositeConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("*")
public class FileController {

    @ResponseBody
    @PostMapping(value = "/mergeFile")
    public void FileMerge(@RequestParam(value = "file") List<byte[]> file) {

        try {


            System.out.println("file.get(0) = " + file.get(0));
            System.out.println("file.get(0) = " + file.get(1));

            List<InputStream> list = new ArrayList<>();
            InputStream inputStream1 = new ByteArrayInputStream(file.get(0));
            list.add(inputStream1);

            InputStream inputStream2 = new ByteArrayInputStream(file.get(1));
            list.add(inputStream2);

            OutputStream outputStream = new FileOutputStream(new File("mergedPdf.pdf"));

            Document document = new Document(); // 문서 만들고
            PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

            for (InputStream inputStream : list)
            {
                PdfReader pdfReader = new PdfReader(inputStream);
                for (int i = 1; i <= pdfReader.getNumberOfPages(); i++)
                {
                    document.newPage();
                    PdfImportedPage page = pdfWriter.getImportedPage(pdfReader, i);
                    pdfContentByte.addTemplate(page, 0, 0);
                }
            }

            outputStream.flush();
            document.close();
            outputStream.close();


        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

}
