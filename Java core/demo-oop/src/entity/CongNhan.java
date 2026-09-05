package entity;

public class CongNhan extends CanBo {
    private int bac;

    public CongNhan() {
    }

    public CongNhan(int age, String diaChi, GioiTinh gioiTinh, String name, int bac) {
        super(age, diaChi, gioiTinh, name);
        this.bac = bac;
    }

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        this.bac = bac;
    }
}
