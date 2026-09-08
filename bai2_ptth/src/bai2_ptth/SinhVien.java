package bai2_ptth;

public class SinhVien extends Person {
	private String maSinhVien;
	private String nganhHoc;
	private double diemTrungBinh;
	 
    //contructor
	public SinhVien(String hoTen, int namSinh, String diaChi,String maSinhVien, String nganhHoc, double diemTrungBinh) {
		super(hoTen, namSinh, diaChi);
		this.maSinhVien = maSinhVien;
		this.nganhHoc= nganhHoc;
		this.diemTrungBinh = diemTrungBinh;
		// TODO Auto-generated constructor stub
	}
    
	// Hàm Xếp Loại
	public String xepLoai() {
		if(this.diemTrungBinh >= 8.5) 
			return "Giỏi";
		else if(this.diemTrungBinh >= 7 && this.diemTrungBinh < 8.5) 
			return "Khá";
		else if(this.diemTrungBinh >= 5 && this.diemTrungBinh < 7)
			return "Trung Bình";
		else
			return "Yếu";	
	}
	@Override
	public void hienThiThongTin() {
		System.out.println("*Thông Tin Sinh Viên của Ngành "+nganhHoc);
		System.out.println();
		super.hienThiThongTin();
		System.out.println("Mã Sinh Viên: "+maSinhVien);
		System.out.println("Ngành Học: "+nganhHoc);
		System.out.println("Điểm Trung Bình: "+diemTrungBinh);
		System.out.println("Xếp Loại: "+xepLoai());
		System.out.println("-----------------------------");
		
	}
	
	public String getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public String getNganhHoc() {
		return nganhHoc;
	}

	public void setNganhHoc(String nganhHoc) {
		this.nganhHoc = nganhHoc;
	}

	public double getDiemTrungBinh() {
		return diemTrungBinh;
	}

	public void setDiemTrungBinh(double diemTrungBinh) {
		this.diemTrungBinh = diemTrungBinh;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SinhVien sv1 = new SinhVien("Nguyễn Văn A", 2000, "Long An", "SV0001", "CNTT", 9.0);
		sv1.hienThiThongTin();
		SinhVien sv2 = new SinhVien("Nguyễn Văn B", 1990, "Tây Ninh", "SV0002", "IOT", 7.2);
		sv2.hienThiThongTin();
		SinhVien sv3 = new SinhVien("Nguyễn Văn C", 2005, "Tiền Giang", "SV0003", "NETWORK", 6.5);
		sv3.hienThiThongTin();
		SinhVien sv4 = new SinhVien("Nguyễn Văn D", 2004, "TP HCM", "SV0004", "DATA",4.5);
		sv4.hienThiThongTin();
	}

}
 