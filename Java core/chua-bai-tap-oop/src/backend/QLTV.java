package backend;

import entity.LoaiTaiLieu;
import entity.TaiLieu;
import utils.JDBCUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTV implements IQLTV {
    private Scanner scanner;

    public QLTV() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void themTaiLieu() {
        System.out.println("Nhập mã tài liệu: ");
        String maTaiLieu = scanner.nextLine();
        System.out.println("Nhập tên NXB: ");
        String tenNhaXuatBan = scanner.nextLine();
        System.out.println("Nhập số bản phát hành: ");
        int soBanPhatHanh = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Mời bạn chọn loại tài liệu: 1.Sách  2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine();
        TaiLieu taiLieu;
        LoaiTaiLieu loaiTaiLieu = null;
        String tenTacGia = null;
        Integer soTrang = null;
        String soPhatHanh = null;
        Integer thangPhatHanh = null;
        StringBuilder sub_column = new StringBuilder();
        StringBuilder sub_value = new StringBuilder();
        switch (choice) {
            case "1":
                System.out.println("Nhập tên tác giả: ");
                tenTacGia = scanner.nextLine();
                System.out.println("Nhập số trang: ");
                soTrang = scanner.nextInt();
                scanner.nextLine();
                loaiTaiLieu = LoaiTaiLieu.SACH;
                sub_column.append(" ten_tac_gia, so_trang");
                sub_value.append(tenTacGia).append(", ").append(soTrang);
                break;
            case "2":
                System.out.println("Nhập ngày phát hành: ");
                int ngayPH = scanner.nextInt();
                System.out.println("Nhập tháng phát hành: ");
                int thangPH = scanner.nextInt();
                System.out.println("Nhập năm phát hành: ");
                int namPH = scanner.nextInt();
                scanner.nextLine();
                loaiTaiLieu = LoaiTaiLieu.BAO;
                sub_column.append(" ngay_phat_hanh");
                sub_value.append(String.format("'%d-%d-%d'", namPH, thangPH, ngayPH));
                break;
            default:
                System.out.println("Nhập số phát hành: ");
                soPhatHanh = scanner.nextLine();
                System.out.println("Nhập tháng phát hành: ");
                thangPhatHanh = scanner.nextInt();
                loaiTaiLieu = LoaiTaiLieu.TAP_CHI;
                sub_column.append(" so_phat_hanh, thang_phat_hanh");
                sub_value.append(soPhatHanh).append(", ").append(thangPhatHanh);
                scanner.nextLine();
        }
        // lưu đối tượng taiLieu vào DB
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO tai_lieu (ma_tai_lieu, ten_nxb, so_ban_phat_hanh, loai_tai_lieu, " + sub_column + ") \n" +
                    "\tVALUES (?, ?, ?, ?, " + sub_value + ")";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maTaiLieu);
            preparedStatement.setString(2, tenNhaXuatBan);
            preparedStatement.setInt(3, soBanPhatHanh);
            preparedStatement.setString(4, loaiTaiLieu.name());

            int c = preparedStatement.executeUpdate();// c: trả ra số row thay đổi khi thêm sửa xóa
            if (c > 0) {
                System.out.println("Thêm tài liệu thành công!");
            } else {
                System.out.println("Thêm tài liệu không thành công!");
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xoaTaiLieu() {
        System.out.println("Nhập mã tài liệu muốn xóa: ");
        String maTaiLieu = scanner.nextLine();
        try {
            // kết nối
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM tai_lieu where ma_tai_lieu = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maTaiLieu);

            int c = preparedStatement.executeUpdate();// c: trả ra số row thay đổi khi thêm sửa xóa
            if (c > 0) {
                System.out.println("Xóa tài liệu thành công!");
            } else {
                System.out.println("Xóa tài liệu không thành công!");
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();// show ra lỗi
        }
    }

    @Override
    public void hienThiThongTin() {
        List<TaiLieu> list = new ArrayList<>();
        try {
            // kết nối
            Connection connection = JDBCUtils.getConnection();
            if (connection != null) {
                System.out.println("Kết nối DB thành công");
            } else {
                System.out.println("Kết nối DB không thành công");
            }
            // tạo viết 1 câu sql để xem toàn bộ bảng tai_lieu
            String sql = "SELECT * FROM tai_lieu;";
            // Statement đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kêt quá
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy cau sql
            // lấy dữ liệu  từ ResultSet
            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String tenNXB = resultSet.getString("ten_nxb");
                int soBanPhatHanh = resultSet.getInt("so_ban_phat_hanh");

                TaiLieu taiLieu = new TaiLieu(maTaiLieu, tenNXB, soBanPhatHanh, null);
                // them vao ds TaiLieu để hiển thị ra
                list.add(taiLieu);
            }
            JDBCUtils.closeConnection(connection);
        } catch (Exception e) {
        }

        // b2: hiển thị
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|%25s|\n", "Mã tài liệu", "Tên NXB", "Số bản phát hành");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        if (list.size() > 0) {
            for (TaiLieu taiLieu : list) {
                System.out.printf("|%25s|%25s|%25s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
    }

    @Override
    public void timKiemTaiLieu() {
        // ds các tài liệu sẽ dc hiển thị
        List<TaiLieu> rs = new ArrayList<>();

        String sql = "SELECT * FROM tai_lieu where loai_tai_lieu = ?";// ? là biến
        System.out.println("Chọn loại tài liệu muốn hiển thị: 1. Sách   2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine(); //,,...
        String value = "";
        switch (choice) {
            case "1":
                value = "SACH";
                break;
            case "2":
                value = "BAO";
                break;
            default:
                value = "TAP_CHI";
        }
        try {
            Connection connection = JDBCUtils.getConnection();
            //PreparedStatement đây là đối tượng hỗ trợ thực thi câu lệnh sql động và trả về kêt quá
            //Statement         đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh
            PreparedStatement statement = connection.prepareStatement(sql);
            // gán gtri cho dấu ? = value
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();// resultSet chứa dữ liệu khi chạy cau sql
            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String tenNXB = resultSet.getString("ten_nxb");
                int soBanPhatHanh = resultSet.getInt("so_ban_phat_hanh");
                TaiLieu taiLieu = new TaiLieu(maTaiLieu, tenNXB, soBanPhatHanh, null);
                // them vao ds rs để hiển thị ra
                rs.add(taiLieu);
            }
            JDBCUtils.closeConnection(connection);
        } catch (Exception e) {
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|%25s|\n", "Mã tài liệu", "Tên NXB", "Số bản phát hành");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        if (rs.size() > 0) {
            for (TaiLieu taiLieu : rs) {
                System.out.printf("|%25s|%25s|%25s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
    }

    // sửa tên nhà xuất bản theo mã tài liệu
    @Override
    public void suaTenNXBTheoMaTaiLieu() {
        System.out.println("Nhập mã tài liệu cần sửa: ");
        String maTaiLieu = scanner.nextLine();
        System.out.println("Nhập mã tên NXB cần sửa: ");
        String tenNXB = scanner.nextLine();
        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "root";
        try {
            // kết nối
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE tai_lieu SET ten_nxb= ? WHERE ma_tai_lieu= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tenNXB);
            statement.setString(2, maTaiLieu);

            int c = statement.executeUpdate();
            if (c > 0) {
                System.out.println("Update thành công");
            } else {
                System.out.println("Update không thành công");
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void thongKe() {
        try {
            // kết nối
            Connection connection = JDBCUtils.getConnection();
            String sql = "{CALL p1()}";
            CallableStatement statement = connection.prepareCall(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String loaiTaiLieu = rs.getString("loai_tai_lieu");
                String soLuong = rs.getString("count(1)");
                System.out.println(loaiTaiLieu + " - " + soLuong);
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QLTV qltv = new QLTV();
        qltv.thongKe();
    }
}
