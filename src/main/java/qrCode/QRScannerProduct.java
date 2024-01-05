package qrCode;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class QRScannerProduct extends JPanel {
    private JButton startButton;
    public static JButton stopButton;
    public static Result result;
    public static JButton startCamera;
    public static JLabel resultLabel;
    public static Webcam webcam;
    public static WebcamPanel webcamPanel;
    public static volatile boolean scanning = true;
    public static String ten;
    public static String soLuong ;
    public static String nhaCungCap ;
    public static String giaBan ;
    public static String giaNhap;
    public static String tinhTrang ;
    public static String chatLieu;
    public static String kichCo ;
    public static String mauSac ;
    public static String loaiSanPham ;
    public QRScannerProduct() {
        setLayout(new GroupLayout(this));

        startButton = new JButton("Bắt đầu quét");
        stopButton = new JButton("Dừng quét");
        resultLabel = new JLabel("Kết quả :");
        startCamera = new JButton("Mở camera");
        webcam = Webcam.getDefault();
        webcamPanel = new WebcamPanel(webcam);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread scanThread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startScanning();
                    }
                });
                scanThread2.start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stopScanning();
                    closeDialog();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        startCamera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("Kết quả: ");
                Thread scanThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        scanning = true;
                        webcamPanel.start();
                    }
                });
                scanThread.start();
            }
        });

        GroupLayout layout = (GroupLayout) getLayout();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(startButton)
                                        .addComponent(stopButton)
                                        .addComponent(startCamera)
                                        .addComponent(resultLabel)
                                        .addComponent(webcamPanel))
                                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(startButton)
                                .addComponent(stopButton)
                                .addComponent(startCamera)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resultLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(webcamPanel)
                                .addContainerGap(150, Short.MAX_VALUE))
        );
    }

    public static void startScanning() {
        try {
            scanning = true;
            webcamPanel.start();

            while (scanning) {
                BufferedImage image = webcam.getImage();
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                    displayResult(result.getText());
                    break;
                } catch (NotFoundException ignored) {
                    // Mã QR chưa được tìm thấy, tiếp tục quét
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopScanning() {
        scanning = false;
        webcamPanel.stop();
    }

    private static void displayResult(String result) {
        if (result != null) {
            String[] values = result.split(";");
            if (values.length == 10) {
            	 ten = values[0];
                 soLuong = values[1];
                 nhaCungCap = values[2];
                 giaBan = values[3];
                 giaNhap = values[4];
                 chatLieu = values[5];
                 kichCo = values[6];
                 mauSac = values[7];
                 loaiSanPham = values[8];
                resultLabel.setText(ten+","+soLuong+","+ nhaCungCap+","+ giaBan+","+ 
                			giaNhap+","+","+chatLieu+","+kichCo+","+mauSac+","+loaiSanPham);
            } else {
                resultLabel.setText("Kết quả: " + result);
            }
        } else {
            resultLabel.setText("Không có kết quả.");
        }
    }

    private void closeDialog() {
        Window ancestor = SwingUtilities.getWindowAncestor(this);
        if (ancestor instanceof JDialog) {
            ((JDialog) ancestor).dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("QR Code Scanner");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new QRScannerProduct());
                frame.setSize(400, 300);
                frame.setVisible(true);
            }
        });
    }
}
