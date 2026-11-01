package bai8;
import common.Input;
import common.RmiClient;

public class FlightClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FlightService fs = RmiClient.lookup("FlightService", FlightService.class);
		while(true) {
			System.out.println("==== ĐẶT VÉ MÁY BAY =====");
			System.out.println("1. Xem ghế trống");
			System.out.println("2. Đặt vé");
			System.out.println("3. Hủy vé");
			System.out.println("0. Thoát");
			String c = Input.line("Chọn: ");
			if(c.equals("0")) break;
			String code = Input.line("Mã chuyến bay (VN123, VJ456, QJ789, QK16012003): ").toUpperCase();
			switch(c) {
			case "1":{
				int n = fs.availableSeats(code);
				System.out.println( n < 0 ? "Chuyến bay không tồn tại." : "Ghế trống : " + n); break;
			}
			case "2" :{
				int seats = Input.integer("Số ghế muốn đặt: ");
				boolean ok = fs.bookTicket(code, seats);
				System.out.println(ok ? "Đặt vé thành công" : "Đặt vé thất bại"); break;
			}
			case "3" :{
				int seats = Input.integer("Số ghế muốn hủy: ");
				boolean ok = fs.cancelBooking(code, seats);
				System.out.println(ok  ? "Hủy vé thành công." : "Hủy vé thất bại."); break;
			}
			default : 
				System.out.println("Lựa chọn không hợp lệ!");
		}

	} 
		}
}


