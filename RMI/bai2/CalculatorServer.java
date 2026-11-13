package bai2;
import common.RmiServer; // Lớp tiện ích khởi động server


public class CalculatorServer {

	public static void main(String[] args) throws Exception { // Điểm bắt đầu chương trình
		// TODO Auto-generated method stub
		RmiServer.start("CalculatorServer", new CalculatorImpl());  // Tạo đối tượng và đăng ký với tên "CalculatorService"

	}

}
