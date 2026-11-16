package chatNhom;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    static final int PORT = 1601;
    static final String[] ROOM_NAMES = {"Study", "Game", "TraiNghiem", "Music", "Cinema", "WorkShop"};

    static final Map<String, Set<ClientHandler>> rooms = new LinkedHashMap<>();
    static final Set<String> usedNames = ConcurrentHashMap.newKeySet();

    static {
        for (String r : ROOM_NAMES) {
            rooms.put(r, ConcurrentHashMap.newKeySet());
        }
    }
    static void log(String msg) {
        String t = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        System.out.println("[" + t + "] " + msg);
    }
    static void broadcast(String room, ClientHandler sender, String message) {
        for (ClientHandler c : rooms.get(room)) {
            if (c != sender) {
                c.send(message);
            }
        }
    }
    static String statsLine() {
        StringBuilder sb = new StringBuilder("TOTAL=" + usedNames.size());
        for (Map.Entry<String, Set<ClientHandler>> e : rooms.entrySet()) {
            StringJoiner sj = new StringJoiner(",");
            for (ClientHandler c : e.getValue()) {
                sj.add(c.getName());
            }
            sb.append("|").append(e.getKey()).append("=").append(sj);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        try (ServerSocket server = new ServerSocket(PORT)) {
            log("Server đang chạy tại port " + PORT);
            while (true) {
                Socket socket = server.accept();
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }
        }
    }
}