package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sach extends TaiLieu {

    private String tenTacGia;
    private int soTrang;

    public Sach(String tenTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, LoaiTaiLieu loaiTaiLieu, String tenTacGia, int soTrang) {
        super(tenTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu);
        this.tenTacGia = tenTacGia;
        this.soTrang = soTrang;
    }
}
