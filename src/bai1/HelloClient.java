package bai1;
import common.RmiClient; // Dùng lớp tiện ích tìm dịch vụ đã viết ở "common"
public class HelloClient {

	public static void main(String[] args) throws Exception { // Điểm bắt đầu chương trình
		// TODO Auto-generated method stub
		Hello stub = RmiClient.lookup("HelloService", Hello.class); // Tìm "HelloService" trên server, nhận về stub kiểu Hello
		System.out.println("Server trả về: "+stub.sayHello()); // Gọi sayHello() từ xa rồi in kết quả

	}

}
