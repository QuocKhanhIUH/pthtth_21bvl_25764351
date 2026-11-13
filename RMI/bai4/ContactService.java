package bai4;
import java.rmi.Remote;// Interface đánh dấu đối tượng từ xa
import java.rmi.RemoteException; // Lỗi liên quan đến mạng / RMI

public interface ContactService extends Remote { // Kế thừa Remote để client gọi được từ xa
	void addContact(String name, String phone) throws RemoteException; // Thêm liên hệ (tên đã có thì ghi đè)
	String findContact(String name) throws RemoteException; // Tìm số điện thoại theo tên; không có thì trả null
	boolean deleteContact(String name) throws RemoteException; // Xóa liên hệ; true nếu xóa được, false nếu không tồn tại
	boolean updateContact(String name, String newPhone) throws RemoteException; // Sửa số điện thoại; true nếu sửa được

}
