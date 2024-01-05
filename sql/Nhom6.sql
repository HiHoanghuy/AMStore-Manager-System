use master

drop database Nhom6_QLCHTT

create database Nhom6_QLCHTT

use Nhom6_QLCHTT

create table ChatLieu(
	maChatLieu nvarchar(20) not null primary key,
	tenChatLieu nvarchar(20) not null
);

create table KichCo(
	maKichCo nvarchar(20) not null primary key,
	tenKichCo nvarchar(10) not null
);

create table MauSac(
	maMauSac nvarchar(20) not null primary key,
	tenMauSac nvarchar(20) not null
);

create table LoaiSanPham(
	maLoai nvarchar(20) not null primary key,
	tenLoai nvarchar(20) not null
);

create table NhaCungCap(
	maNCC nvarchar(20) not null primary key,
	tenNCC nvarchar(50) not null,
	sdtNCC nvarchar(10),
	diaChiNCC nvarchar(80)
);

create table SanPham(
	maSP nvarchar(20) not null primary key,
	tenSP nvarchar(50) not null,
	giaNhap money not null,
	giaBan money not null,
	ngayNhap datetime not null,
	maLoai nvarchar(20) not null,
	maMauSac nvarchar(20) not null,
	maChatLieu nvarchar(20) not null,
	maKichCo nvarchar(20) not null,
	maNCC nvarchar(20) not null,
	hinhAnh varbinary(max),
	soLuongTon int not null,
	trangThai nvarchar(30) check (trangThai = N'Hết hàng' or trangThai = N'Ngừng kinh doanh' or trangThai =  N'Đang kinh doanh'),
	foreign key (maLoai) references LoaiSanPham(maLoai) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maMauSac) references MauSac(maMauSac) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maChatLieu) references ChatLieu(maChatLieu) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maKichCo) references KichCo(maKichCo) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maNCC) references NhaCungCap(maNCC) ON UPDATE CASCADE ON DELETE CASCADE, 
	
);

create table NhanVien(
	maNV nvarchar(20) not null primary key,
	tenNV nvarchar(50) not null,
	CCCD nvarchar (20) not null,
	sdtNV nvarchar(10) not null,
	gioiTinh int check (gioiTinh in (1,0)),
	ngaySinh datetime not null,
	diaChi nvarchar(80) not null,
	email nvarchar(50),
	chucVu nvarchar(20) not null,
	caLamViec nvarchar(10) not null,
	phuCap money,
	heSoLuong real,
	luongCoBan money
);

create table TaiKhoan(
	maNV nvarchar(20) not null primary key,
	matKhau nvarchar(20) not null,
	foreign key (maNV) references NhanVien(maNV) ON UPDATE CASCADE ON DELETE CASCADE
);

create table KhachHang(
	maKH nvarchar(20) not null primary key,
	tenKH nvarchar(50) not null,
	gioiTinh int check (gioiTinh in (1,0)),
	diaChi nvarchar(80) not null,
	sdtKH nvarchar(10) not null,
	email nchar(50)
);

create table ChuongTrinhKhuyenMai(
	maKM nvarchar(20) not null primary key,
	phanTramKhuyenMai real not null,
	ngayBatDau datetime not null,
	ngayKetThuc datetime not null,
	trangThai nvarchar(20) check (trangThai = N'Chưa kích hoạt' or trangThai = N'Hết hạn' or trangThai =  N'Đang diễn ra')
);

create table HoaDon(
	maHD nvarchar(20) not null primary key,
	maKH nvarchar(20),
	maNV nvarchar(20) not null,
	ngayLap datetime not null,
	maKM nvarchar(20),
	tongTienHD money not null,
	tienKhachTra money not null,
	foreign key (maKH) references KhachHang(maKH) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maNV) references NhanVien(maNV) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maKM) references ChuongTrinhKhuyenMai(maKM) ON UPDATE CASCADE ON DELETE CASCADE
);

create table DonDatHang(
	maDDH nvarchar(20) not null primary key,
	maKH nvarchar(20),
	maNV nvarchar(20) not null,
	ngayLap datetime not null,
	tongTienHD money not null,
	tienKhachTra money not null,
	trangThai nvarchar(20) check (trangThai = N'Chưa thanh toán' or trangThai = N'Đã thanh toán' or trangThai = N'Đã hủy')
	foreign key (maKH) references KhachHang(maKH) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maNV) references NhanVien(maNV) ON UPDATE CASCADE ON DELETE CASCADE,
);


create table ChiTietHoaDon(
	maHD nvarchar(20) not null,
	maSP nvarchar(20) not null,
	primary key (maHD,maSP),
	giaBan money not null,
	soLuong int not null,
	foreign key (maHD) references HoaDon(maHD) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maSP) references SanPham(maSP) ON UPDATE CASCADE ON DELETE CASCADE
);

create table ChiTietDonDatHang(
	maDDH nvarchar(20) not null,
	maSP nvarchar(20) not null,
	primary key (maDDH,maSP),
	giaBan money not null,
	soLuong int not null,
	foreign key (maDDH) references DonDatHang(maDDH) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maSP) references SanPham(maSP) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ChiTietHoaDonTra(
	maDT nvarchar(30) NOT NULL,
	maSP nvarchar(20) NOT NULL,
	primary key (maDT,maSP),
	giaBan money NOT NULL,
	soLuong int NOT NULL,
	Foreign key (maDT) references HoaDonTra(maDT),
	Foreign key (maSP) references SanPham(maSP)
)


CREATE TABLE HoaDonTra(
	maDT nvarchar(30) PRIMARY KEY,
	maNV nvarchar(20) NOT NULL,
	maKH nvarchar(20) ,
	tongTienTra money NOT NULL,
	ngayTra datetime NOT NULL,
	maHD nvarchar(20) NOT NULL,
	Foreign key (maHD) references HoaDon(maHD),
	foreign key (maKH) references KhachHang(maKH) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (maNV) references NhanVien(maNV) ON UPDATE CASCADE ON DELETE CASCADE,
)


--Tài Khoản
INSERT INTO TaiKhoan ([maNV], [matKhau]) VALUES ('NV-23-00001','123456')
INSERT INTO TaiKhoan ([maNV], [matKhau]) VALUES ('QL-23-00001','123456')
INSERT INTO TaiKhoan ([maNV], [matKhau]) VALUES ('QL-23-00002','123456')


--Nhân Viên
INSERT INTO NhanVien([maNV], [tenNV], [gioiTinh], [diaChi], [CCCD], [sdtNV], [ngaySinh],[caLamViec],[chucVu],[phuCap],[luongCoBan],[heSoLuong] ) VALUES ('NV-23-00001',  N'Hồ Huỳnh Hoài Thịnh', 0,  N'24 Đường số 1, P.Bình Trị Đông, Q.Bình Thạnh, TP.HCM','077203000478', '090735144', '2001-11-05','Ca 1','Nhân viên', 500000, 1800000, 3.5)
INSERT INTO NhanVien([maNV], [tenNV], [gioiTinh], [diaChi], [CCCD], [sdtNV], [ngaySinh],[caLamViec],[chucVu],[phuCap],[luongCoBan],[heSoLuong] ) VALUES ('QL-23-00001',  N'Ngô Thiên Bâng', 1,  N'146 Lầu 4 Hùng Vương, P.12, Q.5, TP.HCM', '077203000123', '0938100552', '2000-8-25','Ca 2','Quản lí', 500000, 1800000, 5)
INSERT INTO NhanVien([maNV], [tenNV], [gioiTinh], [diaChi], [CCCD], [sdtNV], [ngaySinh],[caLamViec],[chucVu],[phuCap],[luongCoBan],[heSoLuong] ) VALUES ('QL-23-00002',  N'Nguyễn Hoàng Huy', 1,  N'146 Lầu 4 Hùng Vương, P.12, Q.5, TP.HCM', '077203000123', '0938100552', '2000-8-25','Ca 2','Quản lí', 500000, 1800000, 5)

select*from KhachHang
--Khách Hàng
INSERT INTO KhachHang ([maKH], [tenKH], [diaChi], [sdtKH], [gioiTinh]) VALUES	(N'KH-23-00001', N'Lê Kế Hậu', N'372/12A Lê Đức Thọ, P.16, Q.Gò Vấp, TP.HCM', '0909232169',1 )
INSERT INTO KhachHang ([maKH], [tenKH], [diaChi], [sdtKH], [gioiTinh]) VALUES	(N'KH-23-00002', N'Nguyễn Thái Bình', N'157 Hậu Giang, P.5, Q.6, TP HCM', '0903636243',1)
INSERT INTO KhachHang ([maKH], [tenKH], [diaChi], [sdtKH], [gioiTinh]) VALUES	(N'KH-23-00003', N'Mai Văn Tân', N'76/25 Phan Tây Hồ, P.7, Q.Phú Nhuận, TP HCM', '0908555368' ,1)
INSERT INTO KhachHang ([maKH], [tenKH], [diaChi], [sdtKH], [gioiTinh]) VALUES	(N'KH-23-00004', N'Hoàng Thị Hà Giang', N'159D Nguyễn Hữu Cảnh, P.22, Q.Bình Tân, TP HCM', '0958003981' ,0)
INSERT INTO KhachHang ([maKH], [tenKH], [diaChi], [sdtKH], [gioiTinh]) VALUES	(N'KH-23-00005', N'Lê Thị Ngọc Phượng', N'758 Điện Biên Phủ, P.10, Q.5, TP HCM', '0934327017' ,0)
insert into KhachHang values (N'KHACHLE',N'Khách lẻ',1,N'Không',N'Không',null) 

--Chất Liệu
INSERT INTO ChatLieu ([maChatLieu],[tenChatLieu]) VALUES (N'CL001', N'Cotton 2 chiều')
INSERT INTO ChatLieu ([maChatLieu],[tenChatLieu]) VALUES (N'CL002', N'Vải dù')
INSERT INTO ChatLieu ([maChatLieu],[tenChatLieu]) VALUES (N'CL003', N'French Terry')
INSERT INTO ChatLieu ([maChatLieu],[tenChatLieu]) VALUES (N'CL004', N'Vải dệt kim')
INSERT INTO ChatLieu ([maChatLieu],[tenChatLieu]) VALUES (N'CL005', N'Kaki')

--Kích Cỡ
INSERT INTO KichCo ( [maKichCo], [tenKichCo]) VALUES(N'KC001', N'S')
INSERT INTO KichCo ( [maKichCo],[tenKichCo]) VALUES(N'KC002', N'M')
INSERT INTO KichCo ( [maKichCo],[tenKichCo]) VALUES(N'KC003', N'L')
INSERT INTO KichCo ( [maKichCo],[tenKichCo]) VALUES(N'KC004', N'XL')
--Danh Mục
INSERT INTO LoaiSanPham( [maLoai], [tenLoai]) VALUES (N'L001', N'Áo Thun')
INSERT INTO LoaiSanPham( [maLoai], [tenLoai]) VALUES (N'L002', N'Áo sơ mi')
INSERT INTO LoaiSanPham( [maLoai], [tenLoai]) VALUES (N'L003', N'Quần Thun')

--Màu Sắc
INSERT INTO MauSac ( [maMauSac],[tenMauSac] ) VALUES (N'MS001', N'Đen')
INSERT INTO MauSac ( [maMauSac],[tenMauSac] ) VALUES (N'MS002', N'Trắng')
INSERT INTO MauSac ( [maMauSac],[tenMauSac] ) VALUES (N'MS003', N'Đỏ')

--Nhà Cung Cấp
INSERT INTO NhaCungCap ( [tenNCC], [diaChiNCC], [maNCC], [sdtNCC] ) VALUES ( N'MISSOUT', N'403 Sư Vạn Hạnh, Q.10, TP.HCM', 'CC001', '0772011702')
INSERT INTO NhaCungCap ( [tenNCC], [diaChiNCC], [maNCC], [sdtNCC] ) VALUES ( N'YAME', N'770F Sư Vạn Hạnh, P.12, Q.10, TP.HCM', 'CC002', '02873071441')

select * from SanPham
--Sảm Phẩm
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP001',N'BEARMINATOR TEE','L001',  '345000', '26', 'CL001', 'MS001', 'KC003', 'CC001', (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\Bearminator-tee.jpg', Single_Blob)As SanPham) ,10000, N'Đang kinh doanh', '2023-10-15')
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP002',N'DREAM TEE MST','L001',  '199000', '42', 'CL001', 'MS001', 'KC002', 'CC001',  (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\Dream-tee-mst-den.jpg', Single_Blob)As SanPham) ,10000, N'Đang kinh doanh', '2023-10-15' )
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP003',N'DREAM TEE MST','L001',  '199000', '42', 'CL001', 'MS003', 'KC003', 'CC001',  (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\DREAM-TEE-MST-Kem.jpg', Single_Blob)As SanPham)  ,10000, N'Đang kinh doanh', '2023-10-15')
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP004',N'SLOGAN TEE MST','L001',  '249000', '15', 'CL001', 'MS001', 'KC001', 'CC001', (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\SLOGAN TEE MST den.jpg', Single_Blob)As SanPham) ,10000, N'Đang kinh doanh', '2023-10-15' )
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP005',N'SLOGAN TEE MST','L001',  '249000', '23', 'CL001', 'MS003', 'KC002', 'CC001', (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\SLOGAN TEE MST kem.jpg', Single_Blob)As SanPham) ,10000, N'Đang kinh doanh', '2023-10-15' )
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP006',N'SLOGAN TEE MST','L001',  '249000', '51', 'CL001', 'MS004', 'KC003', 'CC001', (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\SLOGAN TEE MST xanh.jpg', Single_Blob)As SanPham) ,10000, N'Đang kinh doanh', '2023-10-15' )
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP007',N'SLOGAN TEE MST','L001',  '249000', '12', 'CL001', 'MS005', 'KC002', 'CC001' , (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\SLOGAN TEE MST nau.jpg', Single_Blob)As SanPham)  ,10000, N'Đang kinh doanh', '2023-10-15')
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP008',N'PLUS TEE MST','L001',  '319000', '16', 'CL001', 'MS001', 'KC001', 'CC001', (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\PLUS TEE MST den.jpg', Single_Blob)As SanPham) ,10000, N'Đang kinh doanh', '2023-10-15' )
INSERT INTO SanPham ([maSP] ,[tenSP], [maLoai], [giaBan], [soLuongTon], [maChatLieu], [maMauSac], [maKichCo], [maNCC], [hinhAnh], [giaNhap], [trangThai], [ngayNhap] ) VALUES (N'SP010',N'PLUS TEE MST 01','L001',  '319000', '25', 'CL001', 'MS002', 'KC002', 'CC001', (Select * from openrowset (bulk N'C:\Nhom6\Anh_SP\PLUS TEE MST trang.jpg', Single_Blob)As SanPham) ,10000, N'Đang kinh doanh', '2023-11-07' )

--chương trình khuyến mãi
INSERT INTO ChuongTrinhKhuyenMai ([maKM], [phanTramKhuyenMai],[ngayBatDau],[ngayKetThuc],[trangThai]) VALUES ('KM001',5,'2023-10-19','2023-10-25',N'Đang diễn ra')
INSERT INTO ChuongTrinhKhuyenMai ([maKM], [phanTramKhuyenMai],[ngayBatDau],[ngayKetThuc],[trangThai]) VALUES ('KM002',10,'2023-10-19','2023-10-25',N'Đang diễn ra')
INSERT INTO ChuongTrinhKhuyenMai ([maKM], [phanTramKhuyenMai],[ngayBatDau],[ngayKetThuc],[trangThai]) VALUES ('KM003',10,'2023-10-19','2023-11-05',N'Đang diễn ra')
INSERT INTO ChuongTrinhKhuyenMai ([maKM], [phanTramKhuyenMai],[ngayBatDau],[ngayKetThuc],[trangThai]) VALUES ('KM004',10,'2023-11-19','2023-11-25',N'Đang diễn ra')
insert into ChuongTrinhKhuyenMai values (N'MACDINH',0,'2023-01-01','2023-01-01',N'Hết hạn')

select * from DonDatHang
--Hóa Đơn
INSERT INTO HoaDon ([maHD] ,[maNV], [maKH] ,[tongTienHD], [ngayLap], [tienKhachTra] ) VALUES ('HD-02-12-23-00001','NV-23-00001','KH-23-00001',1977000,'2023-12-02 17:22',2000000)


--Chi Tiết Hóa Đơn
INSERT INTO ChiTietHoaDon ([maHD],[maSP], [soLuong], [giaBan]) VALUES ('HD-02-12-23-00001','SP001','4',345000)
INSERT INTO ChiTietHoaDon ([maHD],[maSP], [soLuong], [giaBan]) VALUES ('HD-02-12-23-00001','SP002','3',199000)

--Đơn đặt hàng
INSERT INTO DonDatHang ([maDDH] ,[maNV], [maKH] ,[tongTienHD], [ngayLap], [tienKhachTra], [trangThai] ) VALUES ('DDH-23-12-00001','NV-23-00001','KH-23-00002',1977000,'2023-12-14 22:53',2000000,N'Chưa thanh toán')


--Chi tiết đơn đặt hàng
INSERT INTO ChiTietDonDatHang ([maDDH],[maSP], [soLuong], [giaBan]) VALUES ('DDH-23-12-00001','SP001','4',345000)
INSERT INTO ChiTietDonDatHang ([maDDH],[maSP], [soLuong], [giaBan]) VALUES ('DDH-23-12-00001','SP002','3',199000)


