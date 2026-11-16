package bai9;                                     // Thuộc package "bai9"

import java.rmi.RemoteException;                  // Lỗi RMI, bắt khi gọi callback về client
import java.rmi.server.UnicastRemoteObject;       // Lớp cha giúp xuất đối tượng ra mạng
import java.util.ArrayList;                       // Danh sách để trả về cho client
import java.util.HashMap;                         // Map lưu callback theo tên người dùng
import java.util.LinkedHashMap;                   // Map giữ thứ tự thêm sản phẩm
import java.util.List;                            // Kiểu List
import java.util.Map;                             // Kiểu Map

public class AuctionServiceImpl extends UnicastRemoteObject implements AuctionService { // Đối tượng từ xa cài đặt AuctionService
    private static final double Port = 0;
	private final Map<String, Item> items = new LinkedHashMap<>();      // Tên sản phẩm -> Item (giữ thứ tự thêm vào)
    private final Map<String, BidListener> listeners = new HashMap<>(); // Tên người dùng -> callback để server gọi ngược

    public AuctionServiceImpl() throws RemoteException { // Constructor, phải throws vì xuất đối tượng có thể lỗi
        super();                                  // Gọi constructor cha để mở cổng và xuất đối tượng
    }

    @Override                                     // Ghi đè hàm của interface
    public synchronized boolean addItem(String seller, String itemName, double startPrice)
            throws RemoteException {
        if (itemName == null || itemName.isBlank()) return false; // Tên trống thì từ chối
        if (!(startPrice > 0)) return false;      // Giá phải lớn hơn 0; viết kiểu !(x > 0) để loại luôn NaN
        if (items.containsKey(itemName)) return false; // Trùng tên sản phẩm thì từ chối
        items.put(itemName, new Item(itemName, seller, startPrice, startPrice, itemName)); // Thêm sản phẩm mới
        System.out.println("[+] " + seller + " đăng bán '" + itemName + "', giá khởi điểm " + startPrice); // Log ở server
        return true;                              // Thêm thành công
    }

    @Override                                     // Ghi đè hàm của interface
    public synchronized List<Item> listItems() throws RemoteException {
        List<Item> copy = new ArrayList<>();      // Danh sách bản sao sẽ trả về
        for (Item it : items.values()) {          // Duyệt từng sản phẩm trong khi đang giữ khóa
            Item c = new Item(it.name, it.seller, it.startPrice, Port, null); // Tạo bản sao mới
            c.price = it.price;                   // Chép giá hiện tại
            c.highestBidder = it.highestBidder;   // Chép người dẫn đầu
            copy.add(c);                          // Thêm bản sao vào danh sách
        }
        return copy;                              // Trả bản sao; không trả object đang bị placeBid sửa, tránh đọc giữa chừng
    }

    @Override                                     // Ghi đè hàm của interface
    public boolean placeBid(String itemName, String bidder, double amount) throws RemoteException {
        String previous;                          // Người dẫn đầu trước khi có giá mới
        Map<String, BidListener> snapshot;        // Bản sao callback, dùng để thông báo sau khi nhả khóa

        synchronized (this) {                     // Chỉ khóa phần kiểm tra và cập nhật giá
            Item it = items.get(itemName);        // Tìm sản phẩm theo tên
            if (it == null || !(amount > it.price)) return false; // Không tồn tại, hoặc giá không cao hơn giá hiện tại (cũng loại NaN)
            previous = (String) it.highestBidder;          // Ghi nhớ người vừa bị vượt giá
            it.price = amount;                    // Cập nhật giá cao nhất
            it.highestBidder = bidder;            // Người này trở thành người dẫn đầu
            snapshot = new HashMap<>(listeners);  // Chụp lại danh sách callback tại thời điểm này
        }                                         // Hết khóa: client khác có thể vào ngay

        System.out.println("[BID] " + bidder + " đặt " + amount + " cho '" + itemName + "'"); // Log ở server
        broadcast(itemName, bidder, amount, previous, snapshot); // Thông báo cho client, làm ngoài khối khóa
        return true;                              // Đặt giá thành công
    }

    // Gửi thông báo tới các client đã đăng ký
    private void broadcast(String itemName, String bidder, double amount,
                           String previous, Map<String, BidListener> snapshot) {
        for (Map.Entry<String, BidListener> entry : snapshot.entrySet()) { // Duyệt từng người đã đăng ký
            String user = entry.getKey();         // Tên người nhận
            BidListener listener = entry.getValue(); // Callback của người đó
            if (user.equals(bidder)) continue;    // Không báo cho chính người vừa đặt giá

            String msg = user.equals(previous)    // Nếu là người vừa bị vượt giá thì gửi thông báo riêng
                ? "Bạn đã bị vượt giá ở '" + itemName + "': " + bidder + " đặt " + amount
                : "'" + itemName + "' có giá mới: " + amount + " (bởi " + bidder + ")"; // Người khác thì chỉ báo giá mới

            try {
                listener.onBidUpdate(msg);        // Gọi ngược về client qua RMI
            } catch (RemoteException e) {         // Client đã thoát hoặc mất kết nối
                removeListener(user);             // Xóa callback chết để lần sau không gọi nữa
                System.out.println("[-] Bỏ callback của " + user + " (không liên lạc được)"); // Log ở server
            }
        }
    }

    // Xóa callback của một người dùng
    private synchronized void removeListener(String user) {
        listeners.remove(user);                   // Xóa khỏi map
    }

    @Override                                     // Ghi đè hàm của interface
    public synchronized void registerListener(String username, BidListener listener) throws RemoteException {
        listeners.put(username, listener);        // Lưu callback theo tên; đăng ký lại cùng tên thì thay callback cũ
        System.out.println("[*] " + username + " đã đăng ký nhận thông báo"); // Log ở server
    }

	@Override
	public List<Item> listItem() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}