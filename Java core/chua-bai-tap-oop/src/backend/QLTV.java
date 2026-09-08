package backend;

import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTV implements IQLTV {
    private List<TaiLieu> taiLieus;
    private Scanner scanner;

    public QLTV() {
        scanner = new Scanner(System.in);
        // khởi tạo các gtri cho ds tai lieu
        this.taiLieus = new ArrayList<>();
        taiLieus.add(new Bao("bao1", "NXB1", 100, LocalDate.of(2020, 1, 1)));
        taiLieus.add(new Sach("sach1", "NXB2", 200, "ABC", 55));
        taiLieus.add(new TapChi("tapchi1", "NXB3", 300, "Số 1", 1));
    }


    @Override
    public void themTaiLieu() {
        System.out.println("Nhập mã tài liệu: ");
        String maTaiLieu = scanner.nextLine();
        System.out.println("Nhập tên NXB: ");
        String tenNhaXuatBan = scanner.nextLine();
        System.out.println("Nhập số bản phát hành: ");
        int soBanPhatHanh = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Mời bạn chọn loại tài liệu: 1.Sách  2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Nhập tên tác giả: ");
                String tenTacGia = scanner.nextLine();
                System.out.println("Nhập số trang: ");
                int soTrang = scanner.nextInt();
                scanner.nextLine();
                TaiLieu sach = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, tenTacGia, soTrang);
                taiLieus.add(sach);
                System.out.println("Tạo sách thành công!");
                break;
            case "2":
                System.out.println("Nhập ngày phát hành: ");
                int ngayPH = scanner.nextInt();
                System.out.println("Nhập tháng phát hành: ");
                int thangPH = scanner.nextInt();
                System.out.println("Nhập năm phát hành: ");
                int namPH = scanner.nextInt();
                scanner.nextLine();
                LocalDate ngayPhatHanh = LocalDate.of(namPH, thangPH, ngayPH);
                TaiLieu bao = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, ngayPhatHanh);
                taiLieus.add(bao);
                System.out.println("Tạo báo thành công!");
                break;
            default:
                System.out.println("Nhập số phát hành: ");
                String soPhatHanh = scanner.nextLine();
                System.out.println("Nhập tháng phát hành: ");
                int thangPhatHanh = scanner.nextInt();
                scanner.nextLine();
                TaiLieu tapChi = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, soPhatHanh, thangPhatHanh);
                taiLieus.add(tapChi);
                System.out.println("Tạo tạp chí thành công!");
        }
    }

    @Override
    public void xoaTaiLieu() {
        System.out.println("Nhập mã tài liệu muốn xóa: ");
        String maTaiLieu = scanner.nextLine();
        boolean rs = taiLieus.removeIf(tl -> tl.getMaTaiLieu().equals(maTaiLieu));
        if (rs) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Không có dữ liệu tương ứng để xóa");
        }
    }

    @Override
    public void hienThiThongTin() {
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

    @Override
    public void timKiemTaiLieu() {
        // ds các tài liệu sẽ dc hiển thị
        List<TaiLieu> rs = new ArrayList<>();
        System.out.println("Chọn loại tài liệu muốn hiển thị: 1. Sách   2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine(); //,,...
        switch (choice) {
            case "1":
                for (TaiLieu taiLieu : taiLieus) {
                    if (taiLieu instanceof Sach) {
                        rs.add(taiLieu);
                    }
                }
                break;
            case "2":
                for (TaiLieu taiLieu : taiLieus) {
                    if (taiLieu instanceof Bao) {
                        rs.add(taiLieu);
                    }
                }
                break;
            default:
                for (TaiLieu taiLieu : taiLieus) {
                    if (taiLieu instanceof TapChi) {
                        rs.add(taiLieu);
                    }
                }
        }

        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|%25s|\n", "Mã tài liệu", "Tên NXB", "Số bản phát hành");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        if (rs.size() > 0) {
            for (TaiLieu taiLieu : rs) {
                System.out.printf("|%25s|%25s|%25s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
    }
}
