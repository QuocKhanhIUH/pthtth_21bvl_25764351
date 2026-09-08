package bai2_ptth;
import java.time.Year;
public class Person {
	// thuộc tính
	private String hoTen;
	private int namSinh;
	private String diaChi;
	
	//contructor
	public Person(String hoTen, int namSinh, String diaChi) {
		this.hoTen = hoTen;
		this.namSinh = namSinh;
		this.diaChi = diaChi;
	}
	
	public String gethoTen() {
		return hoTen;
	}
	public void sethoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int getnamSinh() {
		return namSinh;
	}
	public void setnamSinh(int namSinh) {
		this.namSinh = namSinh;
	}
	public String getdiaChi() {
		return diaChi;
	}
	public void setdiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	// trả về tuổi của người
	public int tinhTuoi() {
		int namHienTai = Year.now().getValue();
		return namHienTai - this.namSinh;
	}
	public void hienThiThongTin() {
		System.out.println("Họ Tên : "+hoTen);
		System.out.println("Năm Sinh: "+namSinh);
		System.out.println("Địa Chỉ: "+diaChi);
		System.out.println("Tuổi :"+tinhTuoi());
		//System.out.println();
		//System.out.println("-----------------------------");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person("Nguyễn Văn A", 1990, "Phương 1");
		p1.hienThiThongTin();
		Person p2 = new Person("Nguyễn Văn B", 2000, "Phương 2");
		p2.hienThiThongTin();
		Person p3 = new Person("Nguyễn Văn C", 1995, "Phương 3");
        p3.hienThiThongTin();
	}

}
