package custom;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import connectDB.ConnectDB;
import dao.DonDatHang_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhaCungCap_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;

public class GeneratorID {
	
	public static String getFirstCharacter(String input) {
		return input.replaceAll("[a-z|\\ |"+tiengVietLow()+"]", "");

	}
	
	public static String tiengVietFull() {
		String tv = "A-ZĐ|Ắ|Ắ|Ắ|Ằ|Ằ|Ằ|Ẳ|Ẳ|Ẳ|Ẵ|Ẵ|Ẵ|Ặ|Ặ|Ặ|Ặ|Ặ|Ặ|Ă|Ă|Ấ|Ấ|Ấ|Ầ|Ầ|Ầ|Ẩ|Ẩ|Ẩ|Ẫ|Ẫ|Ẫ|Ậ|Ậ|Ậ|Ậ|Ậ|Ậ|Â|Â|Á|Á|À|À|Ã|Ã|Ả|Ả|Ạ|Ạ|Ế|Ế|Ế|Ề|Ề|Ề|Ể|Ể|Ể|Ễ|Ễ|Ễ|Ệ|Ệ|Ệ|Ệ|Ệ|Ệ|Ê|Ê|É|É|È|È|Ẻ|Ẻ|Ẽ|Ẽ|Ẹ|Ẹ|Í|Í|Ì|Ì|Ỉ|Ỉ|Ĩ|Ĩ|Ị|Ị|Ố|Ố|Ố|Ồ|Ồ|Ồ|Ổ|Ổ|Ổ|Ỗ|Ỗ|Ỗ|Ộ|Ộ|Ộ|Ộ|Ộ|Ộ|Ô|Ô|Ớ|Ớ|Ớ|Ớ|Ớ|Ớ|Ờ|Ờ|Ờ|Ờ|Ờ|Ờ|Ở|Ở|Ở|Ở|Ở|Ở|Ỡ|Ỡ|Ỡ|Ỡ|Ỡ|Ỡ|Ợ|Ợ|Ợ|Ợ|Ợ|Ợ|Ơ|Ơ|Ó|Ó|Ò|Ò|Õ|Õ|Ỏ|Ỏ|Ọ|Ọ|Ứ|Ứ|Ứ|Ứ|Ứ|Ứ|Ừ|Ừ|Ừ|Ừ|Ừ|Ừ|Ử|Ử|Ử|Ử|Ử|Ử|Ữ|Ữ|Ữ|Ữ|Ữ|Ữ|Ự|Ự|Ự|Ự|Ự|Ự|Ư|Ư|Ú|Ú|Ù|Ù|Ủ|Ủ|Ũ|Ũ|Ụ|Ụ|Ý|Ý|Ỳ|Ỳ|Ỷ|Ỷ|Ỹ|Ỹ|Ỵ|Ỵ";
		String ht = "["+tv.toLowerCase() + tv.toUpperCase() + "]";
		return ht;
	}

	public static String tiengVietLow() {
		String tv = "A-ZĐ|Ắ|Ắ|Ắ|Ằ|Ằ|Ằ|Ẳ|Ẳ|Ẳ|Ẵ|Ẵ|Ẵ|Ặ|Ặ|Ặ|Ặ|Ặ|Ặ|Ă|Ă|Ấ|Ấ|Ấ|Ầ|Ầ|Ầ|Ẩ|Ẩ|Ẩ|Ẫ|Ẫ|Ẫ|Ậ|Ậ|Ậ|Ậ|Ậ|Ậ|Â|Â|Á|Á|À|À|Ã|Ã|Ả|Ả|Ạ|Ạ|Ế|Ế|Ế|Ề|Ề|Ề|Ể|Ể|Ể|Ễ|Ễ|Ễ|Ệ|Ệ|Ệ|Ệ|Ệ|Ệ|Ê|Ê|É|É|È|È|Ẻ|Ẻ|Ẽ|Ẽ|Ẹ|Ẹ|Í|Í|Ì|Ì|Ỉ|Ỉ|Ĩ|Ĩ|Ị|Ị|Ố|Ố|Ố|Ồ|Ồ|Ồ|Ổ|Ổ|Ổ|Ỗ|Ỗ|Ỗ|Ộ|Ộ|Ộ|Ộ|Ộ|Ộ|Ô|Ô|Ớ|Ớ|Ớ|Ớ|Ớ|Ớ|Ờ|Ờ|Ờ|Ờ|Ờ|Ờ|Ở|Ở|Ở|Ở|Ở|Ở|Ỡ|Ỡ|Ỡ|Ỡ|Ỡ|Ỡ|Ợ|Ợ|Ợ|Ợ|Ợ|Ợ|Ơ|Ơ|Ó|Ó|Ò|Ò|Õ|Õ|Ỏ|Ỏ|Ọ|Ọ|Ứ|Ứ|Ứ|Ứ|Ứ|Ứ|Ừ|Ừ|Ừ|Ừ|Ừ|Ừ|Ử|Ử|Ử|Ử|Ử|Ử|Ữ|Ữ|Ữ|Ữ|Ữ|Ữ|Ự|Ự|Ự|Ự|Ự|Ự|Ư|Ư|Ú|Ú|Ù|Ù|Ủ|Ủ|Ũ|Ũ|Ụ|Ụ|Ý|Ý|Ỳ|Ỳ|Ỷ|Ỷ|Ỹ|Ỹ|Ỵ|Ỵ";
		String ht = "["+tv.toLowerCase()+"]";
		return ht;
	}
	
	public static String generateIDNhanVien(String chucVu) {
		return NhanVien_DAO.taoMaNhanVienMoi(chucVu);
	}
	
	public static String generateIDDonDatHang() {		
		return DonDatHang_DAO.taoMaDonDatHang();
	}
	
	public static String generateIDKhachHang() {
		return KhachHang_DAO.taoMaKhachHang();
	}
	
	public static String generateIDHoaDon() {
		return HoaDon_DAO.taoMaHoaDon();
	}
	
	public static String generateIDSanPham(String maLoai, String kichCo) {
		return SanPham_DAO.taoMaSanPham(maLoai, kichCo);
	}
	
	public static String generateIDNhaCungCap(String tenNCC) {
		String ten = getFirstCharacter(tenNCC);
		return NhaCungCap_DAO.taoMaNhaCungCap(ten);
	}
	/*
	public static void main(String[] args) {
		 try {
				ConnectDB.getInstance().connect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		GeneratorID test = new GeneratorID();
		System.out.println(test.generateIDNhanVien("Quản Lí"));
		System.out.println(test.generateIDDonDatHang());
		System.out.println(test.generateIDKhachHang());
		System.out.println(test.generateIDHoaDon());
		System.out.println(test.generateIDSanPham("L003", "XL"));
		System.out.println(test.generateIDNhaCungCap("Dirty Coin"));
	}*/
}
