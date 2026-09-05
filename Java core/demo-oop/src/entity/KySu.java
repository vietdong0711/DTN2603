package entity;

public class KySu extends CanBo {
    private String nganh;

    public KySu() {
    }

    public KySu(int age, String diaChi, GioiTinh gioiTinh, String name, String nganh) {
        super(age, diaChi, gioiTinh, name);
        this.nganh = nganh;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }
}
