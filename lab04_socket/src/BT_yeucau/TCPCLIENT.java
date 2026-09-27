package BT_yeucau;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class TCPCLIENT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (
	            Socket socket = new Socket("localhost", 5001);
	            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
	            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name())
	        ) {
	            System.out.println("Đã kết nối TCP. Lệnh: DATE, TIME, DATETIME, QUIT");
	            while (true) {
	                System.out.print("TCP Client > ");
	                String command = scanner.nextLine();
	                out.println(command);
	                
	                if (command.equalsIgnoreCase("QUIT")) break;
	                
	                String response = in.readLine();
	                if (response == null) {
	                    System.out.println("Máy chủ TCP đã đóng kết nối.");
	                    break;
	                }
	                System.out.println("Server > " + response);
	            }
	        } catch (IOException e) {
	            System.err.println("Lỗi TCP: " + e.getMessage());
	        }

	}

}
