package bai8;
import java.rmi.RemoteException; // Lỗi RMI
import java.rmi.server.UnicastRemoteObject;  // Lớp cha giúp xuất đối tượng ra mạng
import java.util.HashMap; // Cấu trúc lưu cặp key-value
import java.util.Map; // Kiểu Map dùng để khai báo biến

public class FlightServiceImpl extends UnicastRemoteObject implements FlightService  {
	private final Map<String, Integer> available = new HashMap<>(); // Mã chuyến -> số ghế còn trống (thay đổi khi đặt/hủy)
	private final Map<String, Integer> capacity = new HashMap<>(); // Mã chuyến -> tổng số ghế ban đầu (không đổi)

	public FlightServiceImpl() throws RemoteException { // Constructor, phải throws vì xuất đối tượng có thể lỗi
		super(); // Gọi constructor cha để mở cổng và xuất đối tượng
		addFlight("VN123", 100); // Khởi tạo sẵn chuyến VN123 có 100 ghế
		addFlight("VJ456", 150);
		addFlight("QJ789", 200);
		addFlight("QK16012003", 320);
		// TODO Auto-generated constructor stub
	}

	private void addFlight(String code, int total) { // Hàm phụ thêm chuyến bay, chỉ dùng nội bộ trong server
		// TODO Auto-generated method stub
		available.put(code, total); // Lúc mới tạo, số ghế trống bằng tổng số ghế
		capacity.put(code, total); // Lưu tổng số ghế để kiểm tra khi hủy vé
	}

	@Override
	public synchronized boolean bookTicket(String flightNumber, int seats) throws Exception {
		// TODO Auto-generated method stub
		Integer left = available.get(flightNumber); // Lấy số ghế trống; không có chuyến này thì trả null
		if(left == null || seats <= 0 || seats > left) return false; // Sai mã, số ghế <= 0, hoặc không đủ ghế thì từ chối
		available.put(flightNumber, seats - left); // Đủ ghế thì trừ đi số ghế vừa đặt
		return true; // Đủ ghế thì trừ đi số ghế vừa đặt
	}

	@Override
	public synchronized int availableSeats(String flightNumber) throws Exception {
		// TODO Auto-generated method stub
		Integer left = available.get(flightNumber); // Lấy số ghế trống của chuyến
		return left == null ? -1 : left;  // Không có chuyến thì trả -1 (quy ước báo lỗi); có thì trả số ghế
	
	}

	@Override
	public synchronized boolean cancelBooking(String flightNumber, int seats) throws Exception {
		// TODO Auto-generated method stub
		Integer left = available.get(flightNumber); // Lấy số ghế trống hiện tại
		if(left ==  null || seats <= 0) return false; // Chuyến không tồn tại hoặc số ghế hủy không hợp lệ thì từ chối
		if(left + seats > capacity.get(flightNumber)) return false; // Sau khi hủy mà vượt tổng số ghế nghĩa là hủy nhiều hơn số đã đặt, từ chối
		available.put(flightNumber, left + seats); // Hợp lệ thì cộng số ghế hủy vào số ghế trống
		return true; // Báo hủy thành công
	}

}
