package lab08.jdbc;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NhanVienDemo {
    public static void main(String[] args) {
        Path database = Path.of("nhanvien.db").toAbsolutePath();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + database);
            stmt = conn.createStatement();

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS NhanVien (
                    id INTEGER PRIMARY KEY,
                    ten TEXT NOT NULL,
                    chuc_vu TEXT
                )
                """);

            stmt.executeUpdate("""
                INSERT INTO NhanVien (id, ten, chuc_vu)
                VALUES (1, 'An', 'Lập trình viên')
                ON CONFLICT(id) DO NOTHING
                """);
            stmt.executeUpdate("""
                INSERT INTO NhanVien (id, ten, chuc_vu)
                VALUES (2, 'Bình', 'Kiểm thử viên')
                ON CONFLICT(id) DO NOTHING
                """);
            stmt.executeUpdate("""
                INSERT INTO NhanVien (id, ten, chuc_vu)
                VALUES (3, 'Chi', 'Quản lý')
                ON CONFLICT(id) DO NOTHING
                """);
            
            thongKeLuong(stmt);
            rs = stmt.executeQuery(
            	 "SELECT id, ten, chuc_vu FROM NhanVien ORDER BY id"
            );

            System.out.println("File SQLite: " + database);
            while (rs.next()) {
                System.out.printf("%d | %s | %s%n",
                    rs.getInt("id"),
                    rs.getString("ten"),
                    rs.getString("chuc_vu"));
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Eclipse chưa tìm thấy driver sqlite-jdbc.");
        } catch (SQLException e) {
            System.err.println("Lỗi SQLite: " + e.getMessage());
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
    }

    private static void close(AutoCloseable resource) {
        if (resource == null) {
            return;
        }
        try {
            resource.close();
        } catch (Exception e) {
            System.err.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
        }
    }
    private static void thongKeLuong(Statement stmt) throws SQLException {
        boolean daCoCotLuong = false;

        try (ResultSet cot = stmt.executeQuery("PRAGMA table_info(NhanVien)")) {
            while (cot.next()) {
                if ("luong".equalsIgnoreCase(cot.getString("name"))) {
                    daCoCotLuong = true;
                    break;
                }
            }
        }

        if (!daCoCotLuong) {
            stmt.executeUpdate("ALTER TABLE NhanVien ADD COLUMN luong REAL");
        }

        stmt.executeUpdate(
            "UPDATE NhanVien SET luong = 10000000 WHERE id = 1 AND luong IS NULL"
        );
        stmt.executeUpdate(
            "UPDATE NhanVien SET luong = 12000000 WHERE id = 2 AND luong IS NULL"
        );
        stmt.executeUpdate(
            "UPDATE NhanVien SET luong = 15000000 WHERE id = 3 AND luong IS NULL"
        );

        System.out.println("\n--- BÀI 5: THỐNG KÊ LƯƠNG ---");

        try (ResultSet rs = stmt.executeQuery("""
                SELECT SUM(luong) AS tong, AVG(luong) AS trung_binh
                FROM NhanVien
                """)) {
            if (rs.next()) {
                System.out.printf("Tổng lương: %.0f%n", rs.getDouble("tong"));
                System.out.printf("Lương trung bình: %.2f%n",
                    rs.getDouble("trung_binh"));
            }
        }

        System.out.println("\nNhân viên có lương thấp nhất hoặc cao nhất:");
        try (ResultSet rs = stmt.executeQuery("""
                SELECT id, ten, luong
                FROM NhanVien
                WHERE luong = (SELECT MIN(luong) FROM NhanVien)
                   OR luong = (SELECT MAX(luong) FROM NhanVien)
                ORDER BY luong, id
                """)) {
            while (rs.next()) {
                System.out.printf("%d | %s | %.0f%n",
                    rs.getInt("id"), rs.getString("ten"), rs.getDouble("luong"));
            }
        }

        System.out.println("\nSố nhân viên theo chức vụ:");
        try (ResultSet rs = stmt.executeQuery("""
                SELECT chuc_vu, COUNT(*) AS so_luong
                FROM NhanVien
                GROUP BY chuc_vu
                ORDER BY chuc_vu
                """)) {
            while (rs.next()) {
                System.out.printf("%s | %d%n",
                    rs.getString("chuc_vu"), rs.getInt("so_luong"));
            }
        }
    }
}