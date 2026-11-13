package bai4;
import common.RmiServer;

public class ContactServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RmiServer.start("ContactService", new ContactServiceImpl()); // Đăng ký với tên "ContactService" (client phải dùng đúng tên này)

	}

}
