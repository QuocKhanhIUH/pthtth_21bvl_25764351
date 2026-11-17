package lab08.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class SanPhamDemo {
    private static final String URL = "jdbc:sqlite:sanpham.db";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(URL);
            stmt = conn.createStatement();

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS SanPham (
                    maSP TEXT PRIMARY KEY NOT NULL,
                    tenSP TEXT NOT NULL,
                    gia REAL NOT NULL,
                    soluong INTEGER NOT NULL
                )
                """);

            them(conn, "SP01", "Bút bi", 10000, 20);
            them(conn, "SP02", "Vở", 15000, 10);
            them(conn, "SP03", "Thước", 8000, 5);

            System.out.println("\n1. Danh sách sau khi thêm");
            hienThi(conn, "");

            /*System.out.println("\n2. Tìm sản phẩm có tên chứa 'Bút'");
            hienThi(conn, "Bút");*/
            System.out.print("\n2. Nhập tên sản phẩm cần tìm: ");
            try (Scanner scanner = new Scanner(System.in)) {
                String tuKhoa = scanner.nextLine().trim();
                hienThi(conn, tuKhoa);
            }

            System.out.println("\n3. Cập nhật SP01");
            capNhat(conn, "SP01", 25000, 12);
            hienThi(conn, "");

            System.out.println("\n4. Xóa SP03");
            xoa(conn, "SP03");
            hienThi(conn, "");

            // Câu 2: bỏ dấu // ở hai dòng này khi làm đến transaction.
           // doiGiaTrongGiaoDich(conn, false);
           // doiGiaTrongGiaoDich(conn, true);

        } catch (SQLException e) {
            System.err.println("Lỗi SQLite: " + e.getMessage());
        } finally {
            close(stmt);
            close(conn);
        }
    }

    private static void them(Connection conn, String ma, String ten,
                             double gia, int soLuong) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("""
                INSERT INTO SanPham (maSP, tenSP, gia, soluong)
                VALUES (?, ?, ?, ?)
                ON CONFLICT(maSP) DO NOTHING
                """);
            ps.setString(1, ma);
            ps.setString(2, ten);
            ps.setDouble(3, gia);
            ps.setInt(4, soLuong);
            ps.executeUpdate();
        } finally {
            close(ps);
        }
    }

    private static void hienThi(Connection conn, String tuKhoa) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("""
                SELECT maSP, tenSP, gia, soluong
                FROM SanPham
                WHERE tenSP LIKE ?
                ORDER BY maSP
                """);
            ps.setString(1, "%" + tuKhoa + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.printf("%s | %s | %.0f | %d%n",
                    rs.getString("maSP"), rs.getString("tenSP"),
                    rs.getDouble("gia"), rs.getInt("soluong"));
            }
        } finally {
            close(rs);
            close(ps);
        }
    }

    private static void capNhat(Connection conn, String ma, double gia,
                               int soLuong) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                "UPDATE SanPham SET gia = ?, soluong = ? WHERE maSP = ?"
            );
            ps.setDouble(1, gia);
            ps.setInt(2, soLuong);
            ps.setString(3, ma);
            System.out.println("Số dòng cập nhật: " + ps.executeUpdate());
        } finally {
            close(ps);
        }
    }

    private static void xoa(Connection conn, String ma) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM SanPham WHERE maSP = ?");
            ps.setString(1, ma);
            System.out.println("Số dòng đã xóa: " + ps.executeUpdate());
        } finally {
            close(ps);
        }
    }

    private static void close(AutoCloseable resource) {
        if (resource == null) {
            return;
        }
        try {
            resource.close();
            System.out.println("Đã đóng: " + resource.getClass().getSimpleName());
        } catch (Exception e) {
            System.err.println("Lỗi đóng tài nguyên: " + e.getMessage());
        }
    }
    @SuppressWarnings("unused")
	private static void doiGiaTrongGiaoDich(Connection conn, boolean taoLoi)
            throws SQLException {
        PreparedStatement ps = null;
        conn.setAutoCommit(false);

        try {
            ps = conn.prepareStatement("UPDATE SanPham SET gia = ? WHERE maSP = ?");

            ps.setDouble(1, taoLoi ? 99000 : 30000);
            ps.setString(2, "SP01");
            ps.executeUpdate();

            if (taoLoi) {
                ps.setNull(1, Types.REAL);
            } else {
                ps.setDouble(1, 18000);
            }
            ps.setString(2, "SP02");
            ps.executeUpdate();

            conn.commit();
            System.out.println("\nCOMMIT: Đã lưu giá của cả hai sản phẩm.");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("\nROLLBACK: " + e.getMessage());
        } finally {
            close(ps);
        }

        conn.setAutoCommit(true);
        hienThi(conn, "");
    }
    
}