package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter// ko cần phải viết getter cho các thuoc tinh1
@Setter
@NoArgsConstructor// constructor ko tham số
public class TapChi extends TaiLieu {
    private String soPhatHanh;
    private int thangPhatHanh;

    public TapChi(String tenTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, LoaiTaiLieu loaiTaiLieu, String soPhatHanh, int thangPhatHanh) {
        super(tenTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
    }
}
