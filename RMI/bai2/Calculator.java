package bai2;
import java.rmi.Remote; // Interface đánh dấu đối tượng từ xa
import java.rmi.RemoteException; // Lỗi liên quan đến mạng / RMI

public interface Calculator extends Remote {  // Kế thừa Remote để client gọi được từ xa
	int add(int a, int b) throws RemoteException; //Hàm cộng hai số nguyên; bắt buộc throws RemoteException

}
