package bai9;
import common.Input;                              // Lớp tiện ích nhập từ bàn phím
import common.RmiClient;                          // Lớp tiện ích tìm dịch vụ trên server
import java.rmi.RemoteException;                  // Lỗi RMI
import java.rmi.server.UnicastRemoteObject;       // Cho phép client nhận lời gọi từ server
import java.util.List; 

public class AuctionClient extends UnicastRemoteObject implements BidListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionClient() throws RemoteException { // Constructor, phải throws vì xuất đối tượng có thể lỗi
        super();                                  // Gọi constructor cha để xuất đối tượng ra mạng
    }

    @Override                                     // Ghi đè hàm của interface BidListener
    public void onBidUpdate(String message) throws RemoteException { // Server gọi hàm này khi có sự kiện
        System.out.println("\n>>> THÔNG BÁO: " + message); // \n để xuống dòng, không đè lên dòng đang nhập
    }

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 AuctionService server = RmiClient.lookup("AuctionService", AuctionService.class); // Tìm dịch vụ, nhận stub

	        String me = Input.line("Nhập tên của bạn: "); // Hỏi tên người dùng
	        while (me.isEmpty()) {                    // Không cho để trống tên
	            me = Input.line("Tên không được để trống, nhập lại: ");
	        }
	        server.registerListener(me, new AuctionClient()); // Gửi callback cho server để nhận thông báo
	        System.out.println("Đã kết nối. Xin chào " + me + "!");

	        while (true) {                            // Lặp để thao tác nhiều lần, đến khi chọn thoát
	            System.out.println("\n===== ĐẤU GIÁ (" + me + ") =====");
	            System.out.println("1. Đăng bán sản phẩm (Seller)");
	            System.out.println("2. Xem danh sách sản phẩm");
	            System.out.println("3. Đặt giá thầu (Bidder)");
	            System.out.println("0. Thoát");
	            String c = Input.line("Chọn: ");      // Nhập lựa chọn
	            if (c.equals("0")) break;             // Chọn 0 thì thoát vòng lặp

	            switch (c) {                          // Rẽ nhánh theo lựa chọn
	                case "1": {                       // Đăng bán sản phẩm
	                    String name = Input.line("Tên sản phẩm: "); // Nhập tên sản phẩm
	                    double start = Input.real("Giá khởi điểm: "); // Nhập giá khởi điểm
	                    System.out.println(server.addItem(me, name, start)
	                        ? "Đã đăng bán sản phẩm."
	                        : "Thất bại (tên trống, trùng tên, hoặc giá không hợp lệ).");
	                    break;
	                }
	                case "2": {                       // Xem danh sách
	                    List<Item> list = server.listItems(); // Lấy bản sao danh sách từ server
	                    if (list.isEmpty()) System.out.println("Chưa có sản phẩm nào.");
	                    for (Item it : list) System.out.println(it); // In từng sản phẩm
	                    break;
	                }
	                case "3": {                       // Đặt giá thầu
	                    String name = Input.line("Tên sản phẩm: "); // Nhập sản phẩm muốn đấu giá
	                    double amount = Input.real("Giá thầu của bạn: "); // Nhập giá muốn đặt
	                    System.out.println(server.placeBid(name, me, amount)
	                        ? "Đặt giá thành công, bạn đang dẫn đầu!"
	                        : "Thất bại (sản phẩm không tồn tại hoặc giá không cao hơn giá hiện tại).");
	                    break;
	                }
	                default:                          // Nhập số khác 0-3
	                    System.out.println("Lựa chọn không hợp lệ.");
	            }
	        }
	        System.exit(0);                           // Bắt buộc: đối tượng callback đã xuất ra mạng sẽ giữ JVM sống mãi
	    }

	}


