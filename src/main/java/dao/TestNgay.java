package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestNgay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate ngayHomNay = LocalDate.now();
		LocalDate ngay1 = ngayHomNay.minusDays(1);
		LocalDate ngay2 = ngay1.minusDays(1);
		LocalDate ngay3 = ngay2.minusDays(1);
		LocalDate ngay4 = ngay3.minusDays(1);
		LocalDate ngay5 = ngay4.minusDays(1);
		LocalDate ngay6 = ngay5.minusDays(1);
		System.out.println(ngayHomNay.toString());
		System.out.println(ngay1.toString());
		System.out.println(ngay2.toString());
		System.out.println(ngay3.toString());
		System.out.println(ngay4.toString());
		System.out.println(ngay5.toString());
		System.out.println(ngay6.toString());
		LocalDateTime t = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
		System.out.println(dtf.format(t));
		
	}

}
