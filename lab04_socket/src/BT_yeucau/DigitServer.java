package BT_yeucau;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class DigitServer {
	private static final int PORT = 5000;
    private static final String[] DIGIT_WORDS = {
        "không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"
    };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("Server đang chạy ở port " + PORT + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client kết nối: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Lỗi Server: " + e.getMessage());
        }
    }
    private static void handleClient(Socket socket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equalsIgnoreCase("QUIT")) {
                    System.out.println("Client " + socket.getInetAddress().getHostAddress() + " đã ngắt kết nối.");
                    break;
                }
                if (line.length() == 1 && Character.isDigit(line.charAt(0))) {
                    int digit = line.charAt(0) - '0';
                    out.println(DIGIT_WORDS[digit]);
                } else {
                    out.println("ERR INVALID_DIGIT");
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi giao tiếp với Client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Lỗi khi đóng socket: " + e.getMessage());
            }
        }

	}

}
