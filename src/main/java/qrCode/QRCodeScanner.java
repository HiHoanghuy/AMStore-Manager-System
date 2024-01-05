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

public class QRCodeScanner extends JPanel {
    private JButton startButton;
    public static JButton stopButton; // Thêm nút dừng quét
    public static Result result = null;
    public static JButton startCamera;
    public static JLabel resultLabel;
    public static  Webcam webcam; // Giữ webcam mở
    public static WebcamPanel webcamPanel;
    public static volatile boolean scanning = true;

    public QRCodeScanner() {
        setLayout(new GroupLayout(this));
        //getRootPane().setBorder( BorderFactory.createLineBorder(Color.RED) );
        startButton = new JButton("Bắt đầu quét");
        stopButton = new JButton("Dừng quét"); // Thêm nút dừng quét
        resultLabel = new JLabel("Kết quả :");
        startCamera = new JButton("Mở camera");
        webcam = Webcam.getDefault(); // Mở webcam ở đây
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
            	}catch (Exception e1) {
					// TODO: handle exception
            		e1.printStackTrace();
				}
                
            }
        });
        startCamera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	resultLabel.setText("Kết quả: ");
                // Tạo một luồng mới để thực hiện quét mã QR
                
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
                        .addComponent(stopButton) // Thêm nút dừng quét
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
                    .addComponent(stopButton) // Thêm nút dừng quét
                    .addComponent(startCamera)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(resultLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(webcamPanel)
                    .addContainerGap(150, Short.MAX_VALUE))
        );
    }

    public static void startScanning() {
        long startTime = System.currentTimeMillis();
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

                // Kiểm tra thời gian đã trôi qua
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime > 10000) { // 10 giây
                    stopScanning();
                    displayResult("Quét quá thời gian quy định.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopScanning() {
        scanning = false;
        result=null;
        webcamPanel.stop();
    }

    private static void displayResult(String result) {
        resultLabel.setText("Kết quả: " + result);
        if (result != null) {
            resultLabel.setText("Kết quả: " + result);
        } else {
            resultLabel.setText("Không có kết quả.");
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                JFrame frame = new JFrame("QR Code Scanner");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.getContentPane().add(new QRCodeScanner());
//                frame.setSize(400, 300);
//                frame.setVisible(true);
//            }
//        });
//    }


    private void closeDialog() {
        Window ancestor = SwingUtilities.getWindowAncestor(this);
        if (ancestor instanceof JDialog) {
            ((JDialog) ancestor).dispose();
        }
    }
    public static void changeResolution(int width, int height) {
        // Đảm bảo webcam đã được đóng trước khi thay đổi độ phân giải
        if (webcam.isOpen()) {
            webcam.close();
        }

        // Thay đổi độ phân giải
        webcam.setViewSize(new Dimension(width, height));

        // Mở lại webcam sau khi đã thay đổi độ phân giải
        webcam.open();
    }
}