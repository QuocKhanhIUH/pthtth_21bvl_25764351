package BT_yeucau;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DigitClient {
	private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (
	            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
	            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
	            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name())
	        ) {
	            System.out.println("Đã kết nối tới Server. Nhập một chữ số (0-9) hoặc gõ QUIT để thoát.");

	            while (true) {
	                System.out.print("Client > ");
	                String input = scanner.nextLine();
	                
	              
	                out.println(input);
	                
	                if (input.equalsIgnoreCase("QUIT")) {
	                    System.out.println("Đã thoát.");
	                    break;
	                }
	                
	                String response = in.readLine();
	                if (response == null) {
	                    System.out.println("Server đã đóng kết nối đột ngột.");
	                    break;
	                }
	                
	                System.out.println("Server > " + response);
	            }
	        } catch (IOException e) {
	            System.err.println("Lỗi kết nối: " + e.getMessage());
	        }

	}

}
