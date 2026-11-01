package bai1;
import java.rmi.RemoteException; // Lỗi RMI
import java.rmi.server.UnicastRemoteObject; // Lớp cha giúp đối tượng tự "xuất" ra mạng để nhận lời gọi

public class HelloImpl extends UnicastRemoteObject implements Hello { // Kế thừa để thành đối tượng từ xa, và cài đặt interface Hello
	public HelloImpl() throws RemoteException{ // Constructor; phải throws vì việc xuất đối tượng có thể lỗi
		super(); // Gọi constructor cha: bước này mở cổng và xuất đối tượng ra mạng
	}
	
	@Override // Báo rằng hàm này ghi đè hàm trong interface Hello
	public String sayHello() throws RemoteException{ // Hàm được client gọi từ xa
		return "Hello, World!"; // Trả chuỗi về; chuỗi này được gửi qua mạng cho client
	}

}
