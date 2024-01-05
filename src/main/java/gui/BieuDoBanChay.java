package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import custom.barChart.Chart;
import custom.barChart.ModelChart;
import custom.barChart.PanelShadow;
import custom.barChart.ScrollChart;
import custom.barChart.ThongKeBanChay;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BieuDoBanChay extends JPanel {

	private PanelShadow panelShadow1;
    public BieuDoBanChay() {
    	setSize(1219, 310);
		setVisible(true);
        initComponents();
        //chart.addLegend("Income", new Color(245, 189, 135));
        //chart.addLegend("Expense", new Color(135, 189, 245));
        //chart.addLegend("Profit", new Color(189, 135, 245));
        //chart.addLegend("Cost", new Color(139, 229, 222));
//        chart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
//        chart.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
//        chart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
//        chart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
//        chart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
//        chart.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
        
        chart.start();
    }
    public void ThongKeNgayHomNay(List<ThongKeBanChay> ds) {
    	chart.clear();
    	initComponents();
    	/*
    	int t = 0;
    	int size = ds.size();
    	double[] arr = new double[size];
    	int i = 0;
    	for (ThongKeBanChay ma : ds) {
    		arr[i++] = (double)ma.getTongSoLuong(); 
    	}
    	
    	for (ThongKeBanChay ma : ds) {
    		chart.addLegend(ma.getMaSP(), new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	}
		chart.addData(new ModelChart("Ngày Hôm Nay",arr ));
    	chart.start();
    	*/
    	chart.addLegend("Ngày hôm nay", new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	for (ThongKeBanChay thongKeBanChay : ds) {
			chart.addData(new ModelChart(thongKeBanChay.getMaSP(), new double[] {thongKeBanChay.getTongSoLuong()}));
		}
    	chart.start();
    }
    
    public void ThongKe7NgayGanNhat(List<ThongKeBanChay> ds) {
    	chart.clear();
    	initComponents();
    	
    	/*int t = 0;
    	int size = ds.size();
    	double[] arr = new double[size];
    	int i = 0;
    	for (ThongKeBanChay ma : ds) {
    		arr[i++] = (double)ma.getTongSoLuong(); 
    	}
    	
    	for (ThongKeBanChay ma : ds) {
    		chart.addLegend(ma.getMaSP(), new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	}
		chart.addData(new ModelChart("7 Ngày Gần Nhất",arr ));
    	*/
    	chart.addLegend("7 ngày gần nhất", new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	for (ThongKeBanChay thongKeBanChay : ds) {
			chart.addData(new ModelChart(thongKeBanChay.getMaSP(), new double[] {thongKeBanChay.getTongSoLuong()}));
		}
    	chart.start();
    }
    
    public void ThongKe1Thang(int thang,List<ThongKeBanChay> ds) {
    	chart.clear();
    	initComponents();
    	/*
    	int t = 0;
    	int size = ds.size();
    	double[] arr = new double[size];
    	int i = 0;
    	for (ThongKeBanChay ma : ds) {
    		arr[i++] = (double)ma.getTongSoLuong(); 
    	}
    	
    	for (ThongKeBanChay ma : ds) {
    		chart.addLegend(ma.getMaSP(), new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	}
		chart.addData(new ModelChart("1 Tháng",arr ));
		*/
    	chart.addLegend("Tháng "+thang,  new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	for (ThongKeBanChay thongKeBanChay : ds) {
			chart.addData(new ModelChart(thongKeBanChay.getMaSP(), new double[] {thongKeBanChay.getTongSoLuong()}));
		}
    	chart.start();
    }
    
    public void ThongKe1Nam(List<ThongKeBanChay> ds) {
    	chart.clear();
    	initComponents();
    	/*
    	int t = 0;
    	int size = ds.size();
    	double[] arr = new double[size];
    	int i = 0;
    	for (ThongKeBanChay ma : ds) {
    		arr[i++] = (double)ma.getTongSoLuong(); 
    	}
    	
    	for (ThongKeBanChay ma : ds) {
    		chart.addLegend(ma.getMaSP(), new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	}
		chart.addData(new ModelChart("1 Năm",arr ));
    	*/
    	chart.addLegend("Năm nay",  new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	for (ThongKeBanChay thongKeBanChay : ds) {
			chart.addData(new ModelChart(thongKeBanChay.getMaSP(), new double[] {thongKeBanChay.getTongSoLuong()}));
		}
    	chart.start();
    }
    
    public void ThongKeTuyChinh(List<ThongKeBanChay> ds) {
    	chart.clear();
    	initComponents();
    	/*
    	int t = 0;
    	int size = ds.size();
    	double[] arr = new double[size];
    	int i = 0;
    	for (ThongKeBanChay ma : ds) {
    		arr[i++] = (double)ma.getTongSoLuong(); 
    	}
    	
    	for (ThongKeBanChay ma : ds) {
    		chart.addLegend(ma.getMaSP(), new Color(randomIntColor(),randomIntColor(), randomIntColor()));
    	}
		chart.addData(new ModelChart("Tùy Chỉnh",arr ));
    	*/
    	chart.addLegend("Tùy chỉnh", Color.cyan);
    	for (ThongKeBanChay thongKeBanChay : ds) {
			chart.addData(new ModelChart(thongKeBanChay.getMaSP(), new double[] {thongKeBanChay.getTongSoLuong()}));
		}
    	chart.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new PanelShadow();
        panelShadow1.setShadowColor(new Color(65, 105, 225));
        panelShadow1.setShadowSize(2);
        chart = new Chart();
        //chart = new ScrollChart();

        panelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelShadow1.setColorGradient(new java.awt.Color(190, 240, 255));
        //panelShadow1.setColorGradient(new Color(255,255,255));

        chart.setForeground(Color.BLACK);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 1219, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        chart.start();
    }//GEN-LAST:event_formWindowOpened


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BieuDoBanChay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BieuDoBanChay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BieuDoBanChay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BieuDoBanChay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Chart chart;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
    private int randomIntColor() {
    	double randomDouble = Math.random();
        randomDouble = randomDouble * 100 + 0;
        return (int) randomDouble;
    }
}