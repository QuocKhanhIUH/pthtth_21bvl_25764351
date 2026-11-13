package bai8;
import java.rmi.Remote;  // Interface đánh dấu đối tượng từ xa
import java.rmi.RemoteException; // Lỗi liên quan đến mạng / RMI

public interface FlightService extends Remote { //kế thừa Remote để client gọi được từ xa
	boolean bookTicket(String flightNumber, int seats) throws Exception; // Đặt vé; true = thành công
	int  availableSeats (String flightNumber) throws Exception; // Số ghế còn trống; -1 nếu chuyến không tồn tại
	boolean cancelBooking(String flightNumber, int seats) throws Exception; // Hủy vé; true = thành công

}
