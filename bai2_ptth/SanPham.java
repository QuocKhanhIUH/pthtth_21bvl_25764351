package bai2_ptth;



public class SanPham {
	// thuộc tính
    String maSp;
    String tenSp;
    Double donGia;
    Double soluongKho;
    
    //contructor
    public SanPham(String maSp, String tenSp, double soluongKho, double donGia) {
    	this.maSp = maSp;
    	this.tenSp = tenSp;
    	this.donGia = donGia;
        this.soluongKho = soluongKho;
       
}
    public String getTenSp() {
		return tenSp;
	}
	
	public void settenSp(String tenSp) {
		this.tenSp = tenSp;
	}
    // phương thức tính tiền
   public double tinhThanhTien() {
    	double thanhtien = donGia * soluongKho;
    	return thanhtien;
    }
   // phương thức nhập hàng
   public void nhapHang(int soluongNhap) {
	   if(soluongNhap > 0)
		   this.soluongKho += soluongNhap;
	   else System.out.println("Lỗi : Số lượng nhập bắt buộc phải lớn hơn 0!");
   }
   // phương thức bán hàng
   public boolean banHang(int soluongBan) {
	   if(soluongBan > 0 && soluongBan <= this.soluongKho) {
		   this.soluongKho -= soluongBan;
	   return true;
   }
	   return false;
   }
   // phương thức hiển thị thông tin
   public void hienThiThongTin() {
	   System.out.println("Mã Sản Phẩm: "+maSp);
		System.out.println("Tên Sản Phẩm: "+tenSp);
		System.out.println("Số Lượng: "+soluongKho);
		System.out.println("Đơn giá: "+donGia);
		System.out.println("Tổng thành tiền = "+tinhThanhTien());
		System.out.println("----------------------------------------------");
	}
   public static void main(String[] args) {
	   // tạo Sản Phẩm
	    SanPham sp1 = new SanPham("MV1", "Thịt Bò", 2, 5400);
	    SanPham sp2 = new SanPham("MV2", "Thịt Gà", 10, 2100);
	    SanPham sp3 = new SanPham("MV3", "Thịt Lợn", 13, 3800);
	    sp1.hienThiThongTin();
	    sp2.hienThiThongTin();
	    sp3.hienThiThongTin();
	    
	    // Nhập hàng thêm cho Sản Phẩm
	    System.out.println("Nhập thêm hàng cho Sản Phẩm MV1 : ");
	    sp1.nhapHang(10);
	    sp1.hienThiThongTin();
	    
	    // Thử bán Sản Phẩm
	    boolean ketquaBan1 = sp1.banHang(3);
	    if(ketquaBan1) System.out.println("Bán hàng thành công!");
	    else System.out.println("Bán hàng thất bại!");
	    sp1.hienThiThongTin();
	    
	    // Thử bán số lượng lớn hơn tồn kho
	    boolean ketquaBan2 = sp2.banHang(15);
	    if(ketquaBan2) System.out.println("Bán hàng thành công!");
	    else System.out.println("Bán hàng thất bại! , tồn kho không đủ để bán!");
	    sp2.hienThiThongTin();
	}
   }
