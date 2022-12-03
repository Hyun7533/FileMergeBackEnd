package com.example.demo.CTL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin("*")
public class FileController {

    @ResponseBody
    @PostMapping(value = "/mergeFile")
    public OutputStream FileMerge(@RequestBody @Nullable List<byte[]> file) throws IOException, DocumentException {
        List<InputStream> list = new ArrayList<>();
//            InputStream inputStream1 = new ByteArrayInputStream(file.get(0));

        InputStream inputStream1 = new FileInputStream(new File("C:\\test1.pdf"));
        list.add(inputStream1);
//            InputStream inputStream2 = new ByteArrayInputStream(file.get(1));
        InputStream inputStream2 = new FileInputStream(new File("C:\\test2.pdf"));
        list.add(inputStream2);

        OutputStream outputStream = new FileOutputStream(new File("testMerge.pdf")); // 합칠 문서가 생성된다 열면 에러남

        Document document = new Document(); // 문서 만들고
        PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

        for (InputStream inputStream : list) {
            PdfReader pdfReader = new PdfReader(inputStream);
            for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
                document.newPage();
                PdfImportedPage page = pdfWriter.getImportedPage(pdfReader, i);
                pdfContentByte.addTemplate(page, 0, 0);
            }
        }

        outputStream.flush();
        document.close();
        outputStream.close();


        return outputStream;

    }

    @ResponseBody
    @PostMapping("/file")
    public void File(@RequestBody HashMap<String, Object> file) {
        System.out.println("file = " + file.get("file"));
    }

    @ResponseBody
    @GetMapping("/test")
    public String test(String num1, String num2) {

        return num1 + num2;
    }
}































