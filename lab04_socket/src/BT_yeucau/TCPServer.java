package BT_yeucau;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.charset.StandardCharsets;

public class TCPServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 5001;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH mm ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TCP Server đang chạy ở port " + port + "...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> {
                    try (
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true)
                    ) {
                        String request;
                        while ((request = in.readLine()) != null) {
                            request = request.trim().toUpperCase();
                            if (request.equals("QUIT")) {
                                break;
                            }
                            LocalDateTime now = LocalDateTime.now();
                            switch (request) {
                                case "DATE":
                                    out.println(dateFormatter.format(now));
                                    break;
                                case "TIME":
                                    out.println(timeFormatter.format(now));
                                    break;
                                case "DATETIME":
                                    out.println(dateTimeFormatter.format(now));
                                    break;
                                default:
                                    out.println("ERR INVALID_COMMAND");
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("TCP Client ngắt kết nối: " + e.getMessage());
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
