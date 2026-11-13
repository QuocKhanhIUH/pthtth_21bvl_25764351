package bai3;
import common.Input; // Lớp tiện ích nhập từ bàn phím
import common.RmiClient; // Lớp tiện ích tìm dịch vụ trên server

public class PrimeClient {

	public static void main(String[] args) throws Exception { // Điểm bắt đầu chương trình
		// TODO Auto-generated method stub
		PrimeService svc = RmiClient.lookup("PrimeService", PrimeService.class); // Tìm dịch vụ, nhận stub kiểu PrimeService
		int n = Input.integer("Nhập số n: "); // Nhập số nguyên n (nhập sai thì hỏi lại)
		boolean result = svc.isPrime(n); // Gọi isPrime từ xa, server tính và trả true/false
		System.out.println(n+ (result ? " là số nguyên tố" : " không phải là số nguyên tố")); // In kết quả theo true/false

	}

}
