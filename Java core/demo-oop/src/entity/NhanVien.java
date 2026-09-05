package entity;

public class NhanVien extends CanBo {
    private String congViec;

    public NhanVien() {
    }

    public NhanVien(int age, String diaChi, GioiTinh gioiTinh, String name, String congViec) {
        super(age, diaChi, gioiTinh, name);
        this.congViec = congViec;
    }

    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }
}
