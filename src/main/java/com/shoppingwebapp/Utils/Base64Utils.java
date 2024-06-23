package com.shoppingwebapp.Utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

public class Base64Utils {
    public static final String converToBase64(MultipartFile file) throws IOException {
        String base64Encoder = "";
        byte[] buffer = null;
        try{
            buffer = file.getBytes();
            System.out.println("buffer" + buffer.length);
            base64Encoder = Base64.encodeBase64String(buffer);
            base64Encoder = base64Encoder.replaceAll("\r", "");
            base64Encoder = base64Encoder.replaceAll("\n", "");
            base64Encoder = base64Encoder.replaceAll("\t ", "");
            base64Encoder = base64Encoder.replaceAll("\s ", "");

        }catch (IOException e){
            System.out.println("e");
        }
        return base64Encoder;
    }

    public static String convertBlobToBase64(Blob blob) throws Exception {
        InputStream inputStream = blob.getBinaryStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        try{
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch (Exception e){
            System.out.println("e");
        }
        return Base64.encodeBase64String(outputStream.toByteArray());

    }
}
