package common;                                   // Thuộc package "common"

import java.rmi.Remote;                           // Interface đánh dấu đối tượng từ xa
import java.rmi.registry.LocateRegistry;          // Dùng để kết nối tới registry
import java.rmi.registry.Registry;                // Kiểu đại diện registry

public class RmiClient {                          // Lớp tiện ích cho client
    // <T extends Remote>: T là kiểu bất kỳ nhưng phải là interface từ xa
    // Class<T> type: truyền vào ví dụ Hello.class để hàm biết cần trả về kiểu nào
    public static <T extends Remote> T lookup(String host, String serviceName, Class<T> type)
            throws Exception {
        Registry registry = LocateRegistry.getRegistry(host, RmiConfig.Port); // Kết nối tới registry ở máy host
        return type.cast(registry.lookup(serviceName)); // Tìm theo tên rồi ép về đúng kiểu T (stub)
    }

    // Phiên bản ngắn: tự dùng HOST mặc định (localhost) trong RmiConfig
    public static <T extends Remote> T lookup(String serviceName, Class<T> type) throws Exception {
        return lookup(RmiConfig.Host, serviceName, type); // Gọi lại hàm ở trên với host mặc định
    }
}