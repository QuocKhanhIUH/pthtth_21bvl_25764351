package bai1;
import java.rmi.Remote; // Interface đánh dấu đối tượng từ xa
import java.rmi.RemoteException;  // Lỗi liên quan đến mạng / RMI

public interface Hello extends Remote { // Phải kế thừa Remote thì client mới gọi từ xa được
	String sayHello() throws RemoteException;  // Hàm từ xa; bắt buộc khai báo throws RemoteException vì gọi qua mạng có thể lỗi

}
