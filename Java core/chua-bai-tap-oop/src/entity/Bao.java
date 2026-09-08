package entity;

import java.time.LocalDate;

public class Bao extends TaiLieu {

    private LocalDate ngayPhatHanh;

    public Bao() {}

    public Bao(String tenTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, LocalDate ngayPhatHanh) {
        super(tenTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public LocalDate getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(LocalDate ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }
}
