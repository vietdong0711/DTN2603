package backend.repository;

import entity.TaiLieu;

import java.util.List;

public interface IQLTVRepository {
    List<TaiLieu> getTaiLieus();

    List<TaiLieu> timKiemTheoLoai(String value);

    boolean xoaTheoMaTaiLieu(String maTaiLieu);

    boolean suaTaiLieuTheoMa(String maTaiLieu, String tenNXB);

    boolean themTaiLieu(TaiLieu taiLieu);
}
