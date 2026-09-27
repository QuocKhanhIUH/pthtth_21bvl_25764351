package BT_yeucau;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class HostAndURLinspector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 2) {
            System.err.println("Lỗi: Thiếu hoặc thừa tham số.");
            System.out.println("Cách dùng: java HostAndURLinspector <hostname> <URI>");
            return;
        }

        String hostname = args[0];
        String uriString = args[1];

        // 2. Inspect Hostname
        System.out.println("========== THÔNG TIN HOST ==========");
        try {
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (InetAddress address : addresses) {
                System.out.println("- IP: " + address.getHostAddress());
                
                // Xác định loại IPv4 hay IPv6
                if (address instanceof Inet4Address) {
                    System.out.println("  Loại: IPv4");
                } else if (address instanceof Inet6Address) {
                    System.out.println("  Loại: IPv6");
                } else {
                    System.out.println("  Loại: Không xác định");
                }
                
                System.out.println("  Loopback: " + address.isLoopbackAddress());
                System.out.println("  Site local: " + address.isSiteLocalAddress());
                System.out.println();
            }
        } catch (UnknownHostException e) {
            System.err.println("Lỗi Host: Không phân giải được hostname: " + hostname);
        }

        // 3. Inspect URI
        System.out.println("========== THÔNG TIN URI ==========");
        try {
            URI uri = new URI(uriString);
            
            System.out.println("- Chuỗi URI: " + uri.toString());
            System.out.println("  Scheme: " + uri.getScheme());
            System.out.println("  Host: " + uri.getHost());
            System.out.println("  Port: " + uri.getPort());
            System.out.println("  Path: " + uri.getPath());
            System.out.println("  Query: " + uri.getQuery());
            System.out.println("  Fragment: " + uri.getFragment());
            
        } catch (URISyntaxException e) {
            System.err.println("Lỗi URI: Cú pháp URI sai định dạng: " + uriString);
        }
	}

}
