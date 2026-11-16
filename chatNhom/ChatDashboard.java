package chatNhom;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ChatDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	static final String HOST = "localhost";
    static final int PORT = 1601;

    private final JLabel lblSummary = new JLabel("Đang kết nối...");
    private final JLabel lblStatus = new JLabel(" ");
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"Phòng", "Số client", "Danh sách client"}, 0) {
        @Override
        public boolean isCellEditable(int r, int c) {
            return false;
        }
    };

    public ChatDashboard() {
        super("Dashboard - Chat nhóm TCP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 380);
        setLocationRelativeTo(null);

        lblSummary.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblSummary.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable table = new JTable(model);
        table.setRowHeight(26);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(380);

        add(lblSummary, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        Thread poller = new Thread(new StatsPoller(this)); 
        poller.setDaemon(true);
        poller.start();
    }
     String fetchStats() {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(HOST, PORT), 1500);
            s.setSoTimeout(1500);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8), true);
            out.println("/stats");
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("TOTAL=")) {
                    return line;
                }
            }
        } catch (IOException e) {
        }
        return null;
    }

    void update(String line) {
        String time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        if (line == null) {
            lblSummary.setText("Không kết nối được tới server " + HOST + ":" + PORT);
            lblStatus.setText("  Cập nhật lúc " + time);
            model.setRowCount(0);
            return;
        }
        String[] parts = line.split("\\|");
        int total = Integer.parseInt(parts[0].substring("TOTAL=".length()));
        model.setRowCount(0);
        int inRooms = 0;
        for (int i = 1; i < parts.length; i++) {
            int eq = parts[i].indexOf('=');
            String room = parts[i].substring(0, eq);
            String names = parts[i].substring(eq + 1);
            int count = names.isEmpty() ? 0 : names.split(",").length;
            inRooms += count;
            model.addRow(new Object[]{room, count, names.replace(",", ", ")});
        }
        lblSummary.setText("Tổng phòng: " + (parts.length - 1)
                + "   |   Client online: " + total
                + "   |   Đang trong phòng: " + inRooms
                + "   |   Ở sảnh: " + (total - inRooms));
        lblStatus.setText("  Cập nhật lúc " + time);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatDashboard().setVisible(true));
    }
}