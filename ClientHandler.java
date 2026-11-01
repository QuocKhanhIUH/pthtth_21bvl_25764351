package chatNhom;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private String room;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void send(String msg) {
        out.println(msg);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            if (!askName()) {
                return;
            }

            boolean running = true;
            while (running) {
                String chosen = chooseRoom();
                if (chosen == null) { 
                    break;
                }
                joinRoom(chosen);
                String line;
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }
                    if (line.equalsIgnoreCase("/quit")) {
                        leaveRoom();
                        send("Bạn đã rời phòng.");
                        break;
                    }
                    if (line.equalsIgnoreCase("/exit")) {
                        running = false;
                        break;
                    }
                    ChatServer.log("[" + room + "] " + name + ": " + line);
                    ChatServer.broadcast(room, this, "[" + room + "] " + name + ": " + line);
                }
                if (line == null) { // client ngắt đột ngột
                    running = false;
                }
            }
        } catch (IOException e) {
            ChatServer.log("Lỗi với client " + name + ": " + e.getMessage());
        } finally {
            cleanup();
        }
    }
    private boolean askName() throws IOException {
        send("=== CHAT NHÓM ===");
        while (true) {
            send("Nhập tên của bạn:");
            String n = in.readLine();
            if (n == null) {
                return false;
            }
            n = n.trim();
            if (n.equalsIgnoreCase("/stats")) { // yêu cầu từ Dashboard
                send(ChatServer.statsLine());
                return false;
            }
            if (n.equalsIgnoreCase("/exit")) {
                return false;
            }
            if (n.isEmpty() || n.contains(" ") || n.startsWith("/")) {
                send("Tên không hợp lệ (không để trống, không có khoảng trắng, không bắt đầu bằng '/').");
            } else if (!ChatServer.usedNames.add(n)) {
                send("Tên '" + n + "' đã có người dùng, hãy chọn tên khác.");
            } else {
                name = n;
                ChatServer.log(name + " đã đăng nhập");
                return true;
            }
        }
    }
    private String chooseRoom() throws IOException {
        while (true) {
            send("");
            send("--- DANH SÁCH PHÒNG ---");
            int i = 1;
            for (Map.Entry<String, Set<ClientHandler>> e : ChatServer.rooms.entrySet()) {
                send(i + ". " + e.getKey() + " (" + e.getValue().size() + " người)");
                i++;
            }
            send("Chọn phòng (nhập số hoặc tên phòng), /exit để thoát:");

            String s = in.readLine();
            if (s == null) {
                return null;
            }
            s = s.trim();
            if (s.equalsIgnoreCase("/exit")) {
                return null;
            }
            if (s.equalsIgnoreCase("/quit") || s.isEmpty()) {
                continue;
            }
            // chọn theo số
            try {
                int idx = Integer.parseInt(s);
                if (idx >= 1 && idx <= ChatServer.ROOM_NAMES.length) {
                    return ChatServer.ROOM_NAMES[idx - 1];
                }
            } catch (NumberFormatException ignored) {
                for (String r : ChatServer.ROOM_NAMES) {
                    if (r.equalsIgnoreCase(s)) {
                        return r;
                    }
                }
            }
            send("Phòng không tồn tại, hãy chọn lại.");
        }
    }

    private void joinRoom(String r) {
        room = r;
        ChatServer.rooms.get(r).add(this);
        ChatServer.log(name + " vào phòng " + r);
        send("Bạn đã vào phòng " + r + ". Gõ nội dung để chat, /quit để rời phòng, /exit để thoát.");
        ChatServer.broadcast(r, this, ">>> " + name + " đã vào phòng.");
    }

    private void leaveRoom() {
        if (room != null) {
            ChatServer.rooms.get(room).remove(this);
            ChatServer.log(name + " rời phòng " + room);
            ChatServer.broadcast(room, this, "<<< " + name + " đã rời phòng.");
            room = null;
        }
    }

    private void cleanup() {
        leaveRoom();
        if (name != null) {
            ChatServer.usedNames.remove(name);
            ChatServer.log(name + " đã ngắt kết nối");
        }
        try {
            if (out != null) {
                send("Tạm biệt!");
            }
            socket.close();
        } catch (IOException ignored) {
        }
    }
}