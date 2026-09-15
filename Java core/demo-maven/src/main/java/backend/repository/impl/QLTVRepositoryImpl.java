package backend.repository.impl;

import backend.repository.IQLTVRepository;
import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLTVRepositoryImpl implements IQLTVRepository {
    @Override
    public List<TaiLieu> getTaiLieus() {
        List<TaiLieu> list = new ArrayList<>();

        try {
            Connection connection = JDBCUtils.getConnection();

            String sql = "SELECT * FROM tai_lieu;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String tenNXB = resultSet.getString("ten_nxb");
                int soBanPhatHanh = resultSet.getInt("so_ban_phat_hanh");

                TaiLieu taiLieu = new TaiLieu(maTaiLieu, tenNXB, soBanPhatHanh, null);
                list.add(taiLieu);
            }
            JDBCUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<TaiLieu> timKiemTheoLoai(String value) {
        List<TaiLieu> list = new ArrayList<>();
        try {
            Connection connection = JDBCUtils.getConnection();

            String sql = "SELECT * FROM tai_lieu where loai_tai_lieu = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String tenNXB = resultSet.getString("ten_nxb");
                int soBanPhatHanh = resultSet.getInt("so_ban_phat_hanh");
                TaiLieu taiLieu = new TaiLieu(maTaiLieu, tenNXB, soBanPhatHanh, null);
                list.add(taiLieu);
            }
            JDBCUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean xoaTheoMaTaiLieu(String maTaiLieu) {
        try {
            // kết nối
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM tai_lieu where ma_tai_lieu = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maTaiLieu);

            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
//            if (c > 0) {
//                return true;
//            } else {
//                return false;
//            }
            return c > 0;
        } catch (SQLException e) {
            e.printStackTrace();// show ra lỗi
        }
        return false;
    }

    @Override
    public boolean suaTaiLieuTheoMa(String maTaiLieu, String tenNXB) {
        try {
            // kết nối
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE tai_lieu SET ten_nxb= ? WHERE ma_tai_lieu= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tenNXB);
            statement.setString(2, maTaiLieu);
            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection);

            return c > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean themTaiLieu(TaiLieu taiLieu) {
        String sql ="";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement;

            if (taiLieu instanceof Sach) {
                sql = "INSERT INTO tai_lieu (ma_tai_lieu, ten_nxb, so_ban_phat_hanh, loai_tai_lieu, ten_tac_gia, so_trang) \n" +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(5, ((Sach) taiLieu).getTenTacGia());
                preparedStatement.setInt(6, ((Sach) taiLieu).getSoTrang());

            } else if (taiLieu instanceof Bao) {
                sql = "INSERT INTO tai_lieu (ma_tai_lieu, ten_nxb, so_ban_phat_hanh, loai_tai_lieu, ngay_phat_hanh) \n" +
                        "VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(5, String.valueOf(((Bao) taiLieu).getNgayPhatHanh()));
            } else {
                sql = "INSERT INTO tai_lieu (ma_tai_lieu, ten_nxb, so_ban_phat_hanh, loai_tai_lieu, so_phat_hanh, thang_phat_hanh) \n" +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(5, ((TapChi) taiLieu).getSoPhatHanh());
                preparedStatement.setInt(6, ((TapChi) taiLieu).getThangPhatHanh());
            }

            preparedStatement.setString(1, taiLieu.getMaTaiLieu());
            preparedStatement.setString(2, taiLieu.getTenNhaXuatBan());
            preparedStatement.setInt(3, taiLieu.getSoBanPhatHanh());
            preparedStatement.setString(4, taiLieu.getLoaiTaiLieu().name());

            int c = preparedStatement.executeUpdate();
            return c > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean checkExist(String maTaiLieu) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "SELECT * FROM tai_lieu where ma_tai_lieu = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maTaiLieu);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return true;// tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { // cho dùng thực thi try hay catch()   thì  luôn luôn chạy finally
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }
}
