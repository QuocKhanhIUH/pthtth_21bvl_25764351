package bai4;
import java.rmi.RemoteException; // Lỗi RMI
import java.rmi.server.UnicastRemoteObject; // Lớp cha giúp xuất đối tượng ra mạng
import java.util.HashMap;// Cấu trúc lưu cặp key-value

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService{
       

	   private final HashMap<String, String> contacts = new HashMap<>(); // Danh bạ: key = tên, value = số điện thoại (lưu trong RAM server)
	   public ContactServiceImpl() throws RemoteException { // Constructor, phải throws vì xuất đối tượng có thể lỗi
			super(); // Gọi constructor cha để mở cổng và xuất đối tượng
			// TODO Auto-generated constructor stub
		}
	   @Override
	   public synchronized void addContact(String name, String phone) throws RemoteException { // synchronized: mỗi lúc chỉ 1 thread vào, tránh lỗi khi nhiều client cùng thao tác
		// TODO Auto-generated method stub
		contacts.put(name, phone); // Thêm vào map (tên trùng thì thay số cũ bằng số mới)
		System.out.println("[ADD] "+ name + " - " +phone); // In log ở server để dễ theo dõi
	   }

	   @Override
	   public synchronized String findContact(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return contacts.get(name); // Lấy số theo tên; không có tên đó thì HashMap trả null
	   }

	   @Override
	   public synchronized boolean deleteContact(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return contacts.remove(name) != null; // remove trả về giá trị cũ; khác null nghĩa là đã tồn tại và đã xóa
	   }

	   @Override
	   public synchronized boolean updateContact(String name, String newPhone) throws RemoteException {
		// TODO Auto-generated method stub
		   if(!contacts.containsKey(name)) return false; // Tên chưa có thì không sửa được
		   contacts.put(name, newPhone);  // Có rồi thì ghi số mới đè lên số cũ
		return true; // Báo sửa thành công
	   }
}
