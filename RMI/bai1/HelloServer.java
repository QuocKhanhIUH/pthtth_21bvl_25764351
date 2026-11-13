package bai1;
import common.RmiServer; // Dùng lớp tiện ích khởi động server đã viết ở "common"

public class HelloServer {

	public static void main(String[] args) throws Exception { // Điểm bắt đầu chương trình
		// TODO Auto-generated method stub
		RmiServer.start("HelloService", new HelloImpl()); // Tạo đối tượng HelloImpl, đăng ký với tên "HelloService"

	}

}
