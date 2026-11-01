package common;
import java.rmi.Remote; // Interface đánh dấu đối tượng từ xa
import java.rmi.registry.LocateRegistry; // Lớp để tạo - tìm registry
import java.rmi.registry.Registry; // Kiểu đại diện cho registry (sổ đăng ký tên dịch vụ)
import java.rmi.server.ExportException; // Lỗi ném ra khi cổng đã bị chiếm (registry đã tồn tại)

public class RmiServer {
	// serviceName: tên dịch vụ để client tìm; obj: đối tượng thật cần công khai ra mạng
	public static void start(String serviceName, Remote obj) throws Exception{
		Registry registry; // Biến giữ registry sẽ dùng
		try {
			registry = LocateRegistry.createRegistry(RmiConfig.Port); //// Thử tạo registry mới ở cổng 1099
			
		}catch (ExportException e) {
			registry = LocateRegistry.getRegistry(RmiConfig.Port); // Dùng lại registry đang có
		}
		registry.rebind(serviceName, obj); // Gắn tên với đối tượng (nếu tên đã tồn tại thì ghi đè)
		System.out.println("Server đã sẵn sàng: "+ serviceName + " (cổng " +RmiConfig.Port + ")"); // Báo đã chạy xong
	}

}
