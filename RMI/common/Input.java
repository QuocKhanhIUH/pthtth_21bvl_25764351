package common;
import java.util.Scanner; // Lớp đọc dữ liệu từ bàn phím

public class Input {
	private static final Scanner sc = new Scanner(System.in); // Một Scanner duy nhất dùng cho cả chương trình
	public static String line(String promot) {
		System.out.println(promot); // In lời nhắc (không xuống dòng)
		return sc.nextLine().trim();   // Đọc cả dòng và cắt khoảng trắng đầu/cuối
	}
	public static int integer(String promot) { // Nhập một số nguyên
		while(true) {  // Lặp mãi cho đến khi nhập đúng
			try {
				return Integer.parseInt(line(promot)); // Đọc chuỗi rồi đổi sang int; đúng thì trả về luôn
			}catch(NumberFormatException e) { // Nếu chuỗi không phải số thì rơi vào đây
				System.out.println("Vui lòng nhập số nguyên!");// Báo lỗi rồi vòng lặp hỏi lại
			}
		}
	}
	public static double real(String promot) { // Nhập một số thực
		while(true) { // Lặp cho đến khi nhập đúng
			try {
				return Double.parseDouble(line(promot)); // Đổi chuỗi sang double; đúng thì trả về
			}catch(NumberFormatException e) { // Sai định dạng số
				System.out.println("Vui lòng nhập số!"); // Báo lỗi rồi hỏi lại
			}
		}
	}

}
