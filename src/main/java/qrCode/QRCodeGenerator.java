package qrCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {
	
    public static void main(String[] args) {
    	String projectPath = System.getProperty("user.home");
        String data = "a;a;";
        String filePath = "C://MaQRNhanVien/";
        String fileName = "NhânVien1";        
        System.out.println("Project Path: " + projectPath);
        generateQRCodeImage(data, filePath, fileName);
    }

    public static void generateQRCodeImage(String data, String filePath, String fileName) {
        try {
        	Path directoryPath = Paths.get(filePath);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
        	
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200, hints);

            Path path = FileSystems.getDefault().getPath(filePath,fileName+".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("QR Code generated successfully.");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
