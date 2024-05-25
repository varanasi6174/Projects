package com.qrcode.qrcode;

import java.io.File;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcode.entities.Student;

public class QrCodeGenrated {

    public static void generateQrcode(Student student) {
        String qrCodePath = "src/main/resources/";
        String qrCodeName = qrCodePath + student.getFirstName() + student.getId() + "QRCODE.png";

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bitMatrix = qrCodeWriter.encode("ID: " + student.getId() + "\n"
                                                    + "First Name: " + student.getFirstName() + "\n"
                                                    + "Last Name: " + student.getLastName() + "\n"
                                                    + "Email: " + student.getEmail() + "\n"
                                                    + "Mobile: " + student.getMobile(),
                                                    BarcodeFormat.QR_CODE,
                                                    400,
                                                    400);

            File qrCodeFile = new File(qrCodeName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodeFile.toPath());
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}

