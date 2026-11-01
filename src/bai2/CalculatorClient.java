package bai2;
import common.Input; // Lớp tiện ích nhập từ bàn phím
import common.RmiClient; // Lớp tiện ích tìm dịch vụ trên server

public class CalculatorClient {

	public static void main(String[] args) throws Exception {  // Lớp chạy phía client
		// TODO Auto-generated method stub
		Calculator calc = RmiClient.lookup("CalculatorServer", Calculator.class); // Tìm dịch vụ, nhận stub kiểu Calculator
		int a = Input.integer("Nhập a : "); // Nhập số nguyên a (nhập sai thì hỏi lại)
		int b = Input.integer("Nhập b: "); // Nhập số nguyên b
		System.out.println(a + " + " + b + " = " + calc.add(a, b)); // Gọi add từ xa rồi in kết quả

	}

}
