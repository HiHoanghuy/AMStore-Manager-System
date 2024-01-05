package gui;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


import custom.lineChart.CurveLineChart;
import custom.lineChart.ModelChart;
import custom.lineChart.PanelShadow;
import dao.HoaDon_DAO;
import entities.HoaDon;
import entities.ModelData;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;

public class BieuDoDoanhThu extends JPanel{
	
	private CurveLineChart chart;
    private PanelShadow panelShadow1;
    private HoaDon_DAO hoaDon_DAO;
    
    private ArrayList<Double> listH9;
    private ArrayList<Double> listH10;
    private ArrayList<Double> listH11;
    private ArrayList<Double> listH12;
    private ArrayList<Double> listH13;
    private ArrayList<Double> listH14;
    private ArrayList<Double> listH15;
    private ArrayList<Double> listH16;
    private ArrayList<Double> listH17;
    private ArrayList<Double> listH18;
    private ArrayList<Double> listH19;
    private ArrayList<Double> listH20;
    private ArrayList<Double> listH21;
    
    private ArrayList<Double> listDataDay01;
    private ArrayList<Double> listDataDay02;
    private ArrayList<Double> listDataDay03;
    private ArrayList<Double> listDataDay04;
    private ArrayList<Double> listDataDay05;
    private ArrayList<Double> listDataDay06;
    private ArrayList<Double> listDataDay07;
    private ArrayList<Double> listDataDay08;
    private ArrayList<Double> listDataDay09;
    private ArrayList<Double> listDataDay10;
    private ArrayList<Double> listDataDay11;
    private ArrayList<Double> listDataDay12;
    private ArrayList<Double> listDataDay13;
    private ArrayList<Double> listDataDay14;
    private ArrayList<Double> listDataDay15;
    private ArrayList<Double> listDataDay16;
    private ArrayList<Double> listDataDay17;
    private ArrayList<Double> listDataDay18;
    private ArrayList<Double> listDataDay19;
    private ArrayList<Double> listDataDay20;
    private ArrayList<Double> listDataDay21;
    private ArrayList<Double> listDataDay22;
    private ArrayList<Double> listDataDay23;
    private ArrayList<Double> listDataDay24;
    private ArrayList<Double> listDataDay25;
    private ArrayList<Double> listDataDay26;
    private ArrayList<Double> listDataDay27;
    private ArrayList<Double> listDataDay28;
    private ArrayList<Double> listDataDay29;
    private ArrayList<Double> listDataDay30;
    private ArrayList<Double> listDataDay31;
    
    DecimalFormat df;
	
	public BieuDoDoanhThu() {
		//setBounds(0, 0, 1080, 310);
		setSize(1219, 310);
		setVisible(true);
        initComponents();
        hoaDon_DAO = new HoaDon_DAO();
        df = new DecimalFormat("#,##0.## VND");
    }
    /**
     * thống kê ngày hôm nay
     */
    public void thongKeNgayHomNay() {
    	//biến chứa dữ liệu của mỗi giờ
    	listH9 = new ArrayList<>();
        listH10 = new ArrayList<>();
        listH11 = new ArrayList<>();
        listH12 = new ArrayList<>();
        listH13 = new ArrayList<>();
        listH14 = new ArrayList<>();
        listH15 = new ArrayList<>();
        listH16 = new ArrayList<>();
        listH17 = new ArrayList<>();
        listH18 = new ArrayList<>();
        listH19 = new ArrayList<>();
        listH20 = new ArrayList<>();
        listH21 = new ArrayList<>();
        
        LocalDate ngayHomNay = LocalDate.now();
        int nam,thang,ngay;
    	nam = ngayHomNay.getYear();
    	thang = ngayHomNay.getMonthValue();
    	ngay = ngayHomNay.getDayOfMonth();
    	String d = ngay<10?"0"+ngay:ngay+"";
    	String t = thang<10?"0"+thang:thang+"";
    	chart.addLegend(d+"/"+t+"/"+nam, Color.decode("#8e008e"), Color.decode("#ff0000"));
    	//biến chứa tổng dữ liệu của mỗi giờ
    	double h9=0,h10=0,h11=0,h12=0,h13=0,h14=0,h15=0,h16=0,h17=0,h18=0,h19=0,h20=0,h21=0;
    	double total = 0;//biến chứa tổng doanh thu của ngày
        List<HoaDon> dsHD = hoaDon_DAO.getDsHoaDon();
        for (HoaDon hoaDon : dsHD) {
			if (hoaDon.getNgayLap().getYear()==nam && hoaDon.getNgayLap().getMonthValue()==thang && hoaDon.getNgayLap().getDayOfMonth()==ngay) {
				total+=hoaDon.getTongTienHD();
				if (hoaDon.getNgayLap().getHour()==9) {
					h9+=hoaDon.getTongTienHD();	
				}
				else if (hoaDon.getNgayLap().getHour()==10) {
					h10+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==11) {
					h11+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==12) {
					h12+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==13) {
					h13+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==14) {
					h14+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==15) {
					h15+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==16) {
					h16+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==17) {
					h17+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==18) {
					h18+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==19) {
					h19+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==20) {
					h20+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==21) {
					h21+=hoaDon.getTongTienHD();
				}
			}
		}
        listH9.add(h9);
        listH10.add(h10);
        listH11.add(h11);
        listH12.add(h12);
        listH13.add(h13);
        listH14.add(h14);
        listH15.add(h15);
        listH16.add(h16);
        listH17.add(h17);
        listH18.add(h18);
        listH19.add(h19);
        listH20.add(h20);
        listH21.add(h21);
    
        chart.clear();
        chart.setTitle("Tổng doanh thu hôm nay: "+ df.format(total));
        chart.setTitleColor(Color.red);
        
        
        chart.addData(new ModelChart("09:00", listH9));
        chart.addData(new ModelChart("10:00", listH10));
        chart.addData(new ModelChart("11:00", listH11));
        chart.addData(new ModelChart("12:00", listH12));
        chart.addData(new ModelChart("13:00", listH13));
        chart.addData(new ModelChart("14:00", listH14));
        chart.addData(new ModelChart("15:00", listH15));
        chart.addData(new ModelChart("16:00", listH16));
        chart.addData(new ModelChart("17:00", listH17));
        chart.addData(new ModelChart("18:00", listH18));
        chart.addData(new ModelChart("19:00", listH19));
        chart.addData(new ModelChart("20:00", listH20));
        chart.addData(new ModelChart("21:00", listH21));
        chart.start();
        
    }
    /**
     * thống kê 7 ngày gân nhất
     */
    public void thongKe7NgayGanNhat() {
    	LocalDate ngay1 = LocalDate.now();
		LocalDate ngay2 = ngay1.minusDays(1);
		LocalDate ngay3 = ngay2.minusDays(1);
		LocalDate ngay4 = ngay3.minusDays(1);
		LocalDate ngay5 = ngay4.minusDays(1);
		LocalDate ngay6 = ngay5.minusDays(1);
		LocalDate ngay7 = ngay6.minusDays(1);
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	chart.addLegend(dtf.format(ngay7)+" - "+dtf.format(ngay1),Color.decode("#ffff00"),Color.decode("#008e00"));
    	
    	ArrayList<Double> listDataDay1 = new ArrayList<>();
        ArrayList<Double> listDataDay2 = new ArrayList<>();
        ArrayList<Double> listDataDay3 = new ArrayList<>();
        ArrayList<Double> listDataDay4 = new ArrayList<>();
        ArrayList<Double> listDataDay5 = new ArrayList<>();
        ArrayList<Double> listDataDay6 = new ArrayList<>();
        ArrayList<Double> listDataDay7 = new ArrayList<>();
    	
		double total=0;
		double dataN1=0, dataN2=0, dataN3=0, dataN4=0, dataN5=0, dataN6=0, dataN7=0;
		List<HoaDon> dsHD = hoaDon_DAO.getDSHD7NgayGanNhat();
		for (HoaDon hoaDon : dsHD) {
			total+=hoaDon.getTongTienHD();
			if (hoaDon.getNgayLap().toLocalDate().equals(ngay1)) {
				dataN1+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay2)) {
				dataN2+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay3)) {
				dataN3+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay4)) {
				dataN4+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay5)) {
				dataN5+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay6)) {
				dataN6+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay7)) {
				dataN7+=hoaDon.getTongTienHD();
			}
		}
        
		listDataDay1.add(dataN1);
		listDataDay2.add(dataN2);
		listDataDay3.add(dataN3);
		listDataDay4.add(dataN4);
		listDataDay5.add(dataN5);
		listDataDay6.add(dataN6);
		listDataDay7.add(dataN7);
		
        //thongKeTheoNgay(ngay6, "#ff0000", "#ff8e00");
        //thongKeTheoNgay(ngay5, "#ff8e00", "#ffff00");
        //thongKeTheoNgay(ngay1,"#ffff00","#008e00");
        //thongKeTheoNgay(ngay3,"#008e00","#00c0c0");
        //thongKeTheoNgay(ngay2, "#00c0c0", "#400098");
        //thongKeTheoNgay(ngay1, "#400098", "#8e008e");
        //thongKeTheoNgay(ngayHomNay,"#8e008e","#ff0000");
		chart.clear();
		chart.setTitle("Tổng doanh thu 7 ngày gần nhất: "+df.format(total));
		chart.setTitleColor(Color.red);
        
        chart.addData(new ModelChart(dtf.format(ngay7), listDataDay7));
        chart.addData(new ModelChart(dtf.format(ngay6), listDataDay6));
        chart.addData(new ModelChart(dtf.format(ngay5), listDataDay5));
        chart.addData(new ModelChart(dtf.format(ngay4), listDataDay4));
        chart.addData(new ModelChart(dtf.format(ngay3), listDataDay3));
        chart.addData(new ModelChart(dtf.format(ngay2), listDataDay2));
        chart.addData(new ModelChart(dtf.format(ngay1), listDataDay1));
        
        chart.start();
    }
    /**
     * thống kê tùy chỉnh, chọn ngày bắt đầu để thống kê 7 ngày tiếp sau đó
     * @param ngayKT là ngày được lấy dữ liệu trên gui
     */
    public void thongKeTuyChinh(LocalDate ngayKT) {
    	LocalDate ngay1 = ngayKT;
		LocalDate ngay2 = ngay1.minusDays(1);
		LocalDate ngay3 = ngay2.minusDays(1);
		LocalDate ngay4 = ngay3.minusDays(1);
		LocalDate ngay5 = ngay4.minusDays(1);
		LocalDate ngay6 = ngay5.minusDays(1);
		LocalDate ngay7 = ngay6.minusDays(1);
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	chart.addLegend(dtf.format(ngay7)+" - "+dtf.format(ngay1),Color.decode("#00c0c0"),Color.decode("#008e00"));
    	//biến chứa dữ liệu cho mỗi ngày
    	ArrayList<Double> listDataDay1 = new ArrayList<>();
        ArrayList<Double> listDataDay2 = new ArrayList<>();
        ArrayList<Double> listDataDay3 = new ArrayList<>();
        ArrayList<Double> listDataDay4 = new ArrayList<>();
        ArrayList<Double> listDataDay5 = new ArrayList<>();
        ArrayList<Double> listDataDay6 = new ArrayList<>();
        ArrayList<Double> listDataDay7 = new ArrayList<>();
    	
		double total=0;//biến tính tôngr doanh thu
		//biến chứa tổng doanh thu cho mỗi ngày
		double dataN1=0, dataN2=0, dataN3=0, dataN4=0, dataN5=0, dataN6=0, dataN7=0;
		List<HoaDon> dsHD = hoaDon_DAO.getDSHDTuyChinh(ngay7,ngay1.plusDays(1));
		for (HoaDon hoaDon : dsHD) {
			total+=hoaDon.getTongTienHD();
			if (hoaDon.getNgayLap().toLocalDate().equals(ngay1)) {
				dataN1+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay2)) {
				dataN2+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay3)) {
				dataN3+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay4)) {
				dataN4+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay5)) {
				dataN5+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay6)) {
				dataN6+=hoaDon.getTongTienHD();
			}
			else if (hoaDon.getNgayLap().toLocalDate().equals(ngay7)) {
				dataN7+=hoaDon.getTongTienHD();
			}
		}
        
		listDataDay1.add(dataN1);
		listDataDay2.add(dataN2);
		listDataDay3.add(dataN3);
		listDataDay4.add(dataN4);
		listDataDay5.add(dataN5);
		listDataDay6.add(dataN6);
		listDataDay7.add(dataN7);
		
		chart.clear();
		chart.setTitle("Tổng doanh thu từ "+dtf.format(ngay7)+" đến "+dtf.format(ngay1)+": "+df.format(total));
		chart.setTitleColor(Color.red);
        
        chart.addData(new ModelChart(dtf.format(ngay7), listDataDay7));
        chart.addData(new ModelChart(dtf.format(ngay6), listDataDay6));
        chart.addData(new ModelChart(dtf.format(ngay5), listDataDay5));
        chart.addData(new ModelChart(dtf.format(ngay4), listDataDay4));
        chart.addData(new ModelChart(dtf.format(ngay3), listDataDay3));
        chart.addData(new ModelChart(dtf.format(ngay2), listDataDay2));
        chart.addData(new ModelChart(dtf.format(ngay1), listDataDay1));
        
        chart.start();
    }
    /**
     * thống kê theo tháng
     * @param thang là tháng cần thống kê
     */
    public void thongKeTheoThang(int thang) {
    	//tạo biến chứa dữ liệu
    	listDataDay01 = new ArrayList<>();
    	listDataDay02 = new ArrayList<>();
    	listDataDay03 = new ArrayList<>();
    	listDataDay04 = new ArrayList<>();
    	listDataDay05 = new ArrayList<>();
    	listDataDay06 = new ArrayList<>();
    	listDataDay07 = new ArrayList<>();
    	listDataDay08 = new ArrayList<>();
    	listDataDay09 = new ArrayList<>();
    	listDataDay10 = new ArrayList<>();
    	listDataDay11 = new ArrayList<>();
    	listDataDay12 = new ArrayList<>();
    	listDataDay13 = new ArrayList<>();
    	listDataDay14 = new ArrayList<>();
    	listDataDay15 = new ArrayList<>();
    	listDataDay16 = new ArrayList<>();
    	listDataDay17 = new ArrayList<>();
    	listDataDay18 = new ArrayList<>();
    	listDataDay19 = new ArrayList<>();
    	listDataDay20 = new ArrayList<>();
    	listDataDay21 = new ArrayList<>();
    	listDataDay22 = new ArrayList<>();
    	listDataDay23 = new ArrayList<>();
    	listDataDay24 = new ArrayList<>();
    	listDataDay25 = new ArrayList<>();
    	listDataDay26 = new ArrayList<>();
    	listDataDay27 = new ArrayList<>();
    	listDataDay28 = new ArrayList<>();
    	listDataDay29 = new ArrayList<>();
    	listDataDay30 = new ArrayList<>();
    	listDataDay31 = new ArrayList<>();
    	
    	int namNay = LocalDate.now().getYear();
    	//tạo biến chứa dữ liệu cho từng ngày
    	double n01 = 0, n02= 0, n03= 0, n04= 0, n05= 0, n06= 0, n07= 0, n08= 0, n09= 0, n10= 0, n11 = 0, n12= 0, n13= 0, n14= 0, n15= 0, n16= 0, n17= 0, n18= 0, n19= 0, n20= 0, n21 = 0, n22= 0, n23= 0, n24= 0, n25= 0, n26= 0, n27= 0, n28= 0, n29= 0, n30= 0, n31 = 0;
    	double total=0;//biến chứa dữ liệu cho cả tháng
    	List<HoaDon> dsHD = hoaDon_DAO.getDSHDTheoThang(thang);
    	for (HoaDon hoaDon : dsHD) {
			if (hoaDon.getNgayLap().getYear()==namNay) {
				if (hoaDon.getNgayLap().getMonthValue()==thang) {
					total+=hoaDon.getTongTienHD();
					switch (hoaDon.getNgayLap().getDayOfMonth()) {
					case 1: {
						n01+=hoaDon.getTongTienHD();
						break;
					}
					case 2: {
						n02+=hoaDon.getTongTienHD();
						break;
					}
					case 3: {
						n03+=hoaDon.getTongTienHD();
						break;
					}
					case 4: {
						n04+=hoaDon.getTongTienHD();
						break;
					}
					case 5: {
						n05+=hoaDon.getTongTienHD();
						break;
					}
					case 6: {
						n06+=hoaDon.getTongTienHD();
						break;
					}
					case 7: {
						n07+=hoaDon.getTongTienHD();
						break;
					}
					case 8: {
						n08+=hoaDon.getTongTienHD();
						break;
					}
					case 9: {
						n09+=hoaDon.getTongTienHD();
						break;
					}
					case 10: {
						n10+=hoaDon.getTongTienHD();
						break;
					}
					case 11: {
						n11+=hoaDon.getTongTienHD();
						break;
					}
					case 12: {
						n12+=hoaDon.getTongTienHD();
						break;
					}
					case 13: {
						n13+=hoaDon.getTongTienHD();
						break;
					}
					case 14: {
						n14+=hoaDon.getTongTienHD();
						break;
					}
					case 15: {
						n15+=hoaDon.getTongTienHD();
						break;
					}
					case 16: {
						n16+=hoaDon.getTongTienHD();
						break;
					}
					case 17: {
						n17+=hoaDon.getTongTienHD();
						break;
					}
					case 18: {
						n18+=hoaDon.getTongTienHD();
						break;
					}
					case 19: {
						n19+=hoaDon.getTongTienHD();
						break;
					}
					case 20: {
						n20+=hoaDon.getTongTienHD();
						break;
					}
					case 21: {
						n21+=hoaDon.getTongTienHD();
						break;
					}
					case 22: {
						n22+=hoaDon.getTongTienHD();
						break;
					}
					case 23: {
						n23+=hoaDon.getTongTienHD();
						break;
					}
					case 24: {
						n24+=hoaDon.getTongTienHD();
						break;
					}
					case 25: {
						n25+=hoaDon.getTongTienHD();
						break;
					}
					case 26: {
						n26+=hoaDon.getTongTienHD();
						break;
					}
					case 27: {
						n27+=hoaDon.getTongTienHD();
						break;
					}
					case 28: {
						n28+=hoaDon.getTongTienHD();
						break;
					}
					case 29: {
						n29+=hoaDon.getTongTienHD();
						break;
					}
					case 30: {
						n30+=hoaDon.getTongTienHD();
						break;
					}
					case 31: {
						n31+=hoaDon.getTongTienHD();
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + hoaDon.getMaHD());
					}
				}
			}
		}
    	//thêm dữ liệu vào biến chứa dữ liệu
    	listDataDay01.add(n01);
		listDataDay02.add(n02);
		listDataDay03.add(n03);
		listDataDay04.add(n04);
		listDataDay05.add(n05);
		listDataDay06.add(n06);
		listDataDay07.add(n07);
		listDataDay08.add(n08);
		listDataDay09.add(n09);
		listDataDay10.add(n10);
		listDataDay11.add(n11);
		listDataDay12.add(n12);
		listDataDay13.add(n13);
		listDataDay14.add(n14);
		listDataDay15.add(n15);
		listDataDay16.add(n16);
		listDataDay17.add(n17);
		listDataDay18.add(n18);
		listDataDay19.add(n19);
		listDataDay20.add(n20);
		listDataDay21.add(n21);
		listDataDay22.add(n22);
		listDataDay23.add(n23);
		listDataDay24.add(n24);
		listDataDay25.add(n25);
		listDataDay26.add(n26);
		listDataDay27.add(n27);
		listDataDay28.add(n28);
		listDataDay29.add(n29);
		listDataDay30.add(n30);
		listDataDay31.add(n31);
    	
		chart.clear();
    	chart.setTitle("Tổng doanh thu của tháng "+thang+" năm nay: "+df.format(total));
    	chart.setTitleColor(Color.red);
    	chart.addLegend("Tháng "+thang, Color.blue, Color.pink);
    	
    	chart.addData(new ModelChart("01", listDataDay01));
    	chart.addData(new ModelChart("02", listDataDay02));
    	chart.addData(new ModelChart("03", listDataDay03));
    	chart.addData(new ModelChart("04", listDataDay04));
    	chart.addData(new ModelChart("05", listDataDay05));
    	chart.addData(new ModelChart("06", listDataDay06));
    	chart.addData(new ModelChart("07", listDataDay07));
    	chart.addData(new ModelChart("08", listDataDay08));
    	chart.addData(new ModelChart("09", listDataDay09));
    	chart.addData(new ModelChart("10", listDataDay10));
    	chart.addData(new ModelChart("11", listDataDay11));
    	chart.addData(new ModelChart("12", listDataDay12));
    	chart.addData(new ModelChart("13", listDataDay13));
    	chart.addData(new ModelChart("14", listDataDay14));
    	chart.addData(new ModelChart("15", listDataDay15));
    	chart.addData(new ModelChart("16", listDataDay16));
    	chart.addData(new ModelChart("17", listDataDay17));
    	chart.addData(new ModelChart("18", listDataDay18));
    	chart.addData(new ModelChart("19", listDataDay19));
    	chart.addData(new ModelChart("20", listDataDay20));
    	chart.addData(new ModelChart("21", listDataDay21));
    	chart.addData(new ModelChart("22", listDataDay22));
    	chart.addData(new ModelChart("23", listDataDay23));
    	chart.addData(new ModelChart("24", listDataDay24));
    	chart.addData(new ModelChart("25", listDataDay25));
    	chart.addData(new ModelChart("26", listDataDay26));
    	chart.addData(new ModelChart("27", listDataDay27));
    	chart.addData(new ModelChart("28", listDataDay28));
    	
    	if (thang==2) {//nếu là tháng 2
    		if (ktraNamNhuan(LocalDate.now().getYear())) chart.addData(new ModelChart("29", listDataDay29));//nếu năm nhuận thì thêm ngày 29
    	}
    	else if (thang==4 || thang==6 || thang==9 || thang==11 ) {//thang 4,6,9,11 có 30 ngày
    		chart.addData(new ModelChart("29", listDataDay29));
    		chart.addData(new ModelChart("29", listDataDay29));
        	chart.addData(new ModelChart("30", listDataDay30));
    	}
    	else {//cònn lại 31 ngày
    		chart.addData(new ModelChart("29", listDataDay29));
    		chart.addData(new ModelChart("29", listDataDay29));
        	chart.addData(new ModelChart("30", listDataDay30));
        	chart.addData(new ModelChart("31", listDataDay31));
    	}
    	
    	chart.start();
    }
    
    /**
     * thống kê theo năm
     * @param nam là năm cần thống kê
     */
    public void thongKeTheoNam(int nam) {
    	
    	//tạo mảng chứa dữ liệu
    	ArrayList<Double> listDataT1 = new ArrayList<>();
    	ArrayList<Double> listDataT2 = new ArrayList<>();
    	ArrayList<Double> listDataT3 = new ArrayList<>();
    	ArrayList<Double> listDataT4 = new ArrayList<>();
    	ArrayList<Double> listDataT5 = new ArrayList<>();
    	ArrayList<Double> listDataT6 = new ArrayList<>();
    	ArrayList<Double> listDataT7 = new ArrayList<>();
    	ArrayList<Double> listDataT8 = new ArrayList<>();
    	ArrayList<Double> listDataT9 = new ArrayList<>();
    	ArrayList<Double> listDataT10 = new ArrayList<>();
    	ArrayList<Double> listDataT11 = new ArrayList<>();
    	ArrayList<Double> listDataT12 = new ArrayList<>();
    	//tạo biến lưu tổng doanh thu trong tháng
    	double t1=0, t2=0, t3=0, t4=0, t5=0, t6=0, t7=0, t8=0, t9=0, t10=0, t11=0, t12=0;
    	double total=0;//biến lưu tổng doanh thu trong năm
    	List<HoaDon> dsHD = hoaDon_DAO.getDSHDTheoNam(nam);
    	for (HoaDon hoaDon : dsHD) {
    		total+=hoaDon.getTongTienHD();
    		int namLapHD = hoaDon.getNgayLap().getYear();
			if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==1) {
				t1+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==2) {
				t2+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==3) {
				t3+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==4) {
				t4+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==5) {
				t5+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==6) {
				t6+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==7) {
				t7+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==8) {
				t8+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==9) {
				t9+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==10) {
				t10+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==11) {
				t11+=hoaDon.getTongTienHD();
			}
			else if (namLapHD==nam && hoaDon.getNgayLap().getMonthValue()==12) {
				t12+=hoaDon.getTongTienHD();
			}
		}
    	
    	//thêm dữ liệu vào mảng dữ liệu
    	listDataT1.add(t1);
    	listDataT2.add(t2);
    	listDataT3.add(t3);
    	listDataT4.add(t4);
    	listDataT5.add(t5);
    	listDataT6.add(t6);
    	listDataT7.add(t7);
    	listDataT8.add(t8);
    	listDataT9.add(t9);
    	listDataT10.add(t10);
    	listDataT11.add(t11);
    	listDataT12.add(t12);
    	
    	//vẽ biểu đồ
    	chart.clear();
    	chart.setTitle("Tổng doanh thu của năm "+nam+": "+df.format(total));//thông báo doanh thu
    	chart.setTitleColor(Color.red);//màu chữ là màu đỏ
    	chart.addLegend(nam+"",Color.decode("#ff8e00"),Color.decode("#ffff00"));
    	//thêm dữ liệu cho biểu đồ
    	chart.addData(new ModelChart("Tháng 1", listDataT1));
    	chart.addData(new ModelChart("Tháng 2", listDataT2));
    	chart.addData(new ModelChart("Tháng 3", listDataT3));
    	chart.addData(new ModelChart("Tháng 4", listDataT4));
    	chart.addData(new ModelChart("Tháng 5", listDataT5));
    	chart.addData(new ModelChart("Tháng 6", listDataT6));
    	chart.addData(new ModelChart("Tháng 7", listDataT7));
    	chart.addData(new ModelChart("Tháng 8", listDataT8));
    	chart.addData(new ModelChart("Tháng 9", listDataT9));
    	chart.addData(new ModelChart("Tháng 10", listDataT10));
    	chart.addData(new ModelChart("Tháng 11", listDataT11));
    	chart.addData(new ModelChart("Tháng 12", listDataT12));
    	
    	chart.start();//khởi chạy biểu đồ
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new PanelShadow();
        panelShadow1.setShadowColor(new Color(65, 105, 225));
        panelShadow1.setShadowSize(2);
        chart = new CurveLineChart();

        panelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelShadow1.setColorGradient(new java.awt.Color(190, 240, 255));
        //panelShadow1.setColorGradient(new Color(255,255,255));
        
        chart.setForeground(Color.BLACK); //màu chữ là màu đen
        chart.setFillColor(true); 

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
        
    }
    
    /**
     * kiểm tra năm nhuận
     * @param nam là năm cần kiểm tra
     * @return true nếu là năm nhuận ngược lại trả về false
     */
    private boolean ktraNamNhuan(int nam) {
    	if(nam % 4 == 0)//chia hết cho 4 là năm nhuận
        {
            if( nam % 100 == 0)
            //nếu vừa chia hết cho 4 mà vừa chia hết cho 100 thì không phải năm nhuận
            {
                if ( nam % 400 == 0)//chia hết cho 400 là năm nhuận
                    return true;
                else
                    return false;//không chia hết cho 400 thì không phải năm nhuận
            }
            else//chia hết cho 4 nhưng không chia hết cho 100 là năm nhuận
                return true;
        }
    	return false;
    }

    
}
