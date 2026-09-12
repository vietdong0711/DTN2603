package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Bao extends TaiLieu {

    private LocalDate ngayPhatHanh;

    public Bao(String tenTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, LoaiTaiLieu loaiTaiLieu, LocalDate ngayPhatHanh) {
        super(tenTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu);
        this.ngayPhatHanh = ngayPhatHanh;
    }
}
