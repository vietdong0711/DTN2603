package entity;

public class TapChi extends TaiLieu {

    private String soPhatHanh;
    private int thangPhatHanh;

    public TapChi() {}

    public TapChi(String tenTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, String soPhatHanh, int thangPhatHanh) {
        super(tenTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
    }

    public String getSoPhatHanh() {
        return soPhatHanh;
    }

    public void setSoPhatHanh(String soPhatHanh) {
        this.soPhatHanh = soPhatHanh;
    }

    public int getThangPhatHanh() {
        return thangPhatHanh;
    }

    public void setThangPhatHanh(int thangPhatHanh) {
        this.thangPhatHanh = thangPhatHanh;
    }
}
