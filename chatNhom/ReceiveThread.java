package chatNhom;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveThread implements Runnable {
    private final BufferedReader in;

    public ReceiveThread(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ignored) {
        }
        System.out.println("[Đã ngắt kết nối với server]");
        System.exit(0);
    }
}