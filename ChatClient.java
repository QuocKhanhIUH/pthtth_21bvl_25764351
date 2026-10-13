package chatNhom;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 1601;

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        try (Socket socket = new Socket(host, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            Thread receiver = new Thread(new ReceiveThread(in));
            receiver.setDaemon(true);
            receiver.start();

            // Luồng chính gửi tin
            String input;
            while ((input = keyboard.readLine()) != null) {
                out.println(input);
                if (input.trim().equalsIgnoreCase("/exit")) {
                    break;
                }
            }
            receiver.join(1000); 
        } catch (ConnectException e) {
            System.out.println("Không kết nối được tới server " + host + ":" + port);
        } catch (InterruptedException ignored) {
        }
    }
}