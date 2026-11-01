package bai4;
import common.Input; // Lớp tiện ích nhập từ bàn phím
import common.RmiClient; // Lớp tiện ích tìm dịch vụ trên server

public class ContactClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ContactService svc = RmiClient.lookup("ContactService", ContactService.class);// Tìm dịch vụ, nhận stub kiểu ContactService
		while(true) { // Lặp mãi để dùng được nhiều lần, đến khi chọn thoát
			System.out.println("\n===== DANH BẠ ====="); // Các lựa chọn của menu
			System.out.println("1.Thêm liên hê");
			System.out.println("2.Tìm liên hê");
			System.out.println("3.Sửa xóa điện thoại");
			System.out.println("4.Xóa liên hệ");
			System.out.println("0.Thoát");
			String choice = Input.line("Chọn: "); // Nhập lựa chọn của người dùng
			if(choice.equals("0")) break; // Chọn 0 thì thoát khỏi vòng lặp, kết thúc chương trình
			String name  = Input.line("Tên"); // Cả 4 chức năng đều cần tên nên hỏi trước
			switch(choice) { // Rẽ nhánh theo lựa chọn
			case "1": // 
                String phone = Input.line("Số điện thoại");
				svc.addContact(name, phone); // Nhập số rồi gọi addContact từ xa
				System.out.println("Đã thêm");  // Báo thành công
				System.out.println("Tên: "+name + " | Số điện thoai: "+phone);
				break; // Kết thúc case, tránh chạy tràn xuống case sau
			case "2": // Tìm
				String phone1 = svc.findContact(name); // Gọi findContact từ xa, nhận số hoặc null
				System.out.println(phone1 != null ? "Số điện thoại:" +phone1 : "Không tìm thấy"); // Kiểm tra null trước khi dùng
                break;
			case "3": // Sửa
				boolean updated =svc.updateContact(name, Input.line("Số mới")); // Nhập số mới rồi gọi updateContact từ xa
				System.out.println(updated ? "Đã sửa." : "Không tìm thấy."); // true = sửa được
			case "4" :// Xóa
				System.out.println(svc.deleteContact(name) ? "Đã xóa." : "Không tìm thấy."); // true = xóa được
				break;
			default: // Nhập số khác 0-4
				System.out.println("Lựa chọn không hợp lệ."); // Báo lỗi nhập
			}
		}

	}

}
