package frontend;

import backend.controller.QLTVController;
import entity.Bao;
import entity.LoaiTaiLieu;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Function {// chứa các chức năng project có
    private Scanner scanner;
    private QLTVController controller;


    public Function() {
        scanner = new Scanner(System.in);
        controller = new QLTVController();
    }

    public void hienThi() {
        System.out.println("==== Hiển thị toàn bộ tài liệu ====");
        // ds tài liệu để hieern thị
        List<TaiLieu> taiLieus = controller.getTaiLieus();
        this.show(taiLieus);
    }

    public void them() {
        TaiLieu taiLieu = new TaiLieu();
        System.out.println("Nhập mã tài liệu: ");
        String maTaiLieu = scanner.nextLine();
        System.out.println("Nhập tên NXB: ");
        String tenNhaXuatBan = scanner.nextLine();
        System.out.println("Nhập số bản phát hành: ");
        int soBanPhatHanh = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Mời bạn chọn loại tài liệu: 1.Sách  2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine();
        LoaiTaiLieu loaiTaiLieu = null;
        String tenTacGia = null;
        Integer soTrang = null;
        String soPhatHanh = null;
        Integer thangPhatHanh = null;
        switch (choice) {
            case "1":
                System.out.println("Nhập tên tác giả: ");
                tenTacGia = scanner.nextLine();
                System.out.println("Nhập số trang: ");
                soTrang = scanner.nextInt();
                scanner.nextLine();
                loaiTaiLieu = LoaiTaiLieu.SACH;
                taiLieu = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, tenTacGia, soTrang);
                break;
            case "2":
                System.out.println("Nhập ngày phát hành: ");
                int ngayPH = scanner.nextInt();
                System.out.println("Nhập tháng phát hành: ");
                int thangPH = scanner.nextInt();
                System.out.println("Nhập năm phát hành: ");
                int namPH = scanner.nextInt();
                scanner.nextLine();
                loaiTaiLieu = LoaiTaiLieu.BAO;

                taiLieu = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, LocalDate.of(namPH, thangPH, ngayPH));
                break;
            default:
                System.out.println("Nhập số phát hành: ");
                soPhatHanh = scanner.nextLine();
                System.out.println("Nhập tháng phát hành: ");
                thangPhatHanh = scanner.nextInt();
                loaiTaiLieu = LoaiTaiLieu.TAP_CHI;
                taiLieu = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, soPhatHanh, thangPhatHanh);
                scanner.nextLine();
        }

        boolean check = controller.themTaiLieu(taiLieu);
        if (check) {
            System.out.println("Thêm tài liệu thành công!");
        } else {
            System.out.println("Thêm tài liệu không thành công!");
        }
    }

    public void sua() {
        System.out.println("Nhập mã tài liệu cần sửa: ");
        String maTaiLieu = scanner.nextLine();
        System.out.println("Nhập mã tên NXB cần sửa: ");
        String tenNXB = scanner.nextLine();

        boolean check = controller.suaTaiLieuTheoMa(maTaiLieu, tenNXB);
        if (check) {
            System.out.println("Sửa tài liệu thành công!");
        } else {
            System.out.println("Sửa tài liệu không thành công!");
        }
    }

    public void xoa() {
        System.out.println("==== Chức năng xóa tài liệu ====");
        System.out.println("Nhập mã tài liệu muốn xóa: ");
        String maTaiLieu = scanner.nextLine();
        boolean check = controller.xoaTheoMaTaiLieu(maTaiLieu);
        if (check) {
            System.out.println("Xóa tài liệu thành công!");
        } else {
            System.out.println("Xóa tài liệu không thành công!");
        }
    }

    public void timKiem() {
        System.out.println("==== Hiển thị toàn bộ tài liệu ====");

        System.out.println("Chọn loại tài liệu muốn hiển thị: 1. Sách   2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine();
        String value = "";
        switch (choice) {
            case "1":
                value = "SACH";
                break;
            case "2":
                value = "BAO";
                break;
            default:
                value = "TAP_CHI";
        }

        List<TaiLieu> taiLieus = controller.timKiemTheoLoai(value);
        this.show(taiLieus);
    }

    public void show(List<TaiLieu> taiLieus) {
        System.out.println("==== Tìm kiếm tài liệu ====");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|%25s|\n", "Mã tài liệu", "Tên NXB", "Số bản phát hành");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        if (taiLieus.size() > 0) {
            for (TaiLieu taiLieu : taiLieus) {
                System.out.printf("|%25s|%25s|%25s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
    }

    public void menu() {
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Thêm mới tài liêu: Sách, tạp chí, báo.");
            System.out.println("2. Xoá tài liệu theo mã tài liệu.");
            System.out.println("3. Hiện thị thông tin về tài liệu.");
            System.out.println("4. Tìm kiếm tài liệu theo loại: Sách, tạp chí, báo.");
            System.out.println("5. UPdate NXB cho tài liệu theo mã tài liệu.");
            System.out.println("6. Thoát khỏi chương trình.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    this.them();
                    break;
                case "2":
                    this.xoa();
                    break;
                case "3":
                    this.hienThi();
                    break;
                case "4":
                    this.timKiem();
                    break;
                case "5":
                    this.sua();
                    break;
                case "6":
                    System.exit(0);
                default:
                    System.out.println("Chọn sai! chọn lại!");
            }
        }
    }
}
