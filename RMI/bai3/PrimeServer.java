package bai3;
import common.RmiServer; // Lớp tiện ích khởi động server

public class PrimeServer {

	public static void main(String[] args) throws Exception { // Điểm bắt đầu chương trình
		// TODO Auto-generated method stub
		RmiServer.start("PrimeService", new PrimeServerImpl()); // Tạo đối tượng và đăng ký với tên "PrimeService"

	}

}
 