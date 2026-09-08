package bai2_ptth;

public class GiangVien extends Person {

	private String maGiangVien;
	private String chuyenMon;
	private double luongCoBan;
	private double heSoLuong;
	
	public GiangVien(String hoTen, int namSinh, String diaChi,String maGiangVien, String chuyenMon,double luongCoBan, double heSoLuong) {
		super(hoTen, namSinh, diaChi);
		this.maGiangVien = maGiangVien;
		this.chuyenMon = chuyenMon;
		this.luongCoBan = luongCoBan;
		this.heSoLuong = heSoLuong;
		// TODO Auto-generated constructor stub
	}
	
	public double tinhLuong() {
		return luongCoBan * heSoLuong;
	}
	
	@Override
	public void hienThiThongTin() {
		System.out.println("*Thông tin của Giảng Viên theo chuyên môn: "+chuyenMon);
		System.out.println("------------------------------------------------------");
		//System.out.println();
		super.hienThiThongTin();
		System.out.println("Mã giảng viên : "+maGiangVien);
		System.out.println("Chuyên Môn: "+chuyenMon);
		System.out.println("Lương Cơ Bản: "+luongCoBan);
		System.out.println("Hệ Số Lương: "+heSoLuong);
		System.out.println("Lương Giảng Viên: "+tinhLuong());
		System.out.println();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        GiangVien gv1 = new GiangVien("Trần A", 1984, "Tân Phú", "GV01", "Kế Toán", 1000, 1.5);
        gv1.hienThiThongTin();
        GiangVien gv2 = new GiangVien("Trần B", 1980, "Bình Tân", "GV02", "Điện", 8500, 1.2);
        gv2.hienThiThongTin();
        GiangVien gv3 = new GiangVien("Trần C", 1992, "Hóc Môn", "GV03", "CNTT", 1200, 2);
        gv3.hienThiThongTin();
        GiangVien gv4 = new GiangVien("Trần D", 1987, "Phú Nhuận", "GV04", "Truyền Thông", 7800, 1.3);
        gv4.hienThiThongTin();
	}

	public String getMaGiangVien() {
		return maGiangVien;
	}

	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}

	public String getChuyenMon() {
		return chuyenMon;
	}

	public void setChuyenMon(String chuyenMon) {
		this.chuyenMon = chuyenMon;
	}

	public double getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}

	public double getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

}
