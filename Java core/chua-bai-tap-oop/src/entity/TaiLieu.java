package entity;

public class TaiLieu {
    public static double PI = 3.14;
    private String maTaiLieu;
    private String tenNhaXuatBan;
    private int soBanPhatHanh;
    private LoaiTaiLieu loaiTaiLieu;

    public TaiLieu() {
    }

    public TaiLieu(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, LoaiTaiLieu loaiTaiLieu) {
        this.maTaiLieu = maTaiLieu;
        this.tenNhaXuatBan = tenNhaXuatBan;
        this.soBanPhatHanh = soBanPhatHanh;
        this.loaiTaiLieu = loaiTaiLieu;
    }

    public String getMaTaiLieu() {
        return maTaiLieu;
    }

    public void setMaTaiLieu(String maTaiLieu) {
        this.maTaiLieu = maTaiLieu;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    public int getSoBanPhatHanh() {
        return soBanPhatHanh;
    }

    public void setSoBanPhatHanh(int soBanPhatHanh) {
        this.soBanPhatHanh = soBanPhatHanh;
    }

    public LoaiTaiLieu getLoaiTaiLieu() {
        return loaiTaiLieu;
    }

    public void setLoaiTaiLieu(LoaiTaiLieu loaiTaiLieu) {
        this.loaiTaiLieu = loaiTaiLieu;
    }

    public static void  an() {
        System.out.println("");
    }

    public static void main(String[] args) {
        TaiLieu     tl          = new TaiLieu();// khởi tạo
        //class     instance
        tl.tenNhaXuatBan = "<UNK>";



        System.out.println(TaiLieu.PI);
        TaiLieu.an();
        // để gọi dc các thông tin liên quan đến instance thì phải khởi tạo đối tuwowjng thì ms dùng dc
        // ko tốn bộ nhớ - muốn dng thì phải khởi tạo đối tượng liên quan
        // các thông tin có chứa static thì sẽ thuộc về class, khi run project sẽ tự động khởi tạo
        // có thể dùng mà ko cần khởi tạo  -   tốn bộ nhớ


        // khi nào dùng static? thuộc tính hoặc phương thức thức đó dùng rất là nhiều lần
    }
}
