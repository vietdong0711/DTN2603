package backend;

import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLCB implements IQLCB{
    // lấy dữ liệu từ đâu để thêm sửa xóa
//    CanBo[] canBos = new CanBo[1000]; // chứa dc số lượng có hạn
    // dữ liệu dc luuw vào list
    private List<CanBo> canBos = new ArrayList<>();// chứa bao nhiêu cx dc,
    private Scanner scanner = new Scanner(System.in);

    // hàm khởi tạo QLCB
    public QLCB() {
        // thêm dữ liệu cho ds cán bộ
        CanBo cn = new CongNhan(20, "HN", GioiTinh.NAM, "cong nhan 1", 1);
        CanBo ks = new KySu(30, "SG", GioiTinh.NU, "ky su 1", "ngành A");
        CanBo nv = new NhanVien(25, "ĐN", GioiTinh.NAM, "nhan vien 1", "kế toán");
        canBos.add(cn);
        canBos.add(ks);
        canBos.add(nv);
    }

    @Override
    public void hienThiDS() {
        System.out.println("+-------------------------+----------+--------------------+----------+");
        System.out.printf("|%25s|%10s|%20s|%10s|\n", "Tên", "Tuổi", "Địa chỉ", "Giới tính");
        System.out.println("+-------------------------+----------+--------------------+----------+");
        for (CanBo canBo: canBos) {
            System.out.printf("|%25s|%10s|%20s|%10s|\n", canBo.getTen(), canBo.getTuoi(), canBo.getDiaChi(), canBo.getGioiTinh());
        }
        System.out.println("+-------------------------+----------+--------------------+----------+");
    }

    @Override
    public void themMoiCanBo() {
        System.out.println("Nhập tên:");
        String ten = scanner.nextLine();
        System.out.println("Nhập tuổi:");
        int tuoi = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập địa chỉ:");
        String diaChi = scanner.nextLine();
        System.out.println("Chọn giới tính:  1. Nam     2. Nữ       khác. Khác");
        GioiTinh gioiTinh;
        String chooseGT = scanner.nextLine();
        switch (chooseGT) {
            case "1":
                gioiTinh = GioiTinh.NAM;
                break;
            case "2":
                gioiTinh = GioiTinh.NU;
                break;
            default:
                gioiTinh = GioiTinh.KHAC;
        }
        System.out.println("Chọn loại cán bộ: 1. Công nhân      2. Kỹ sư        khác. Nhân viên");
        String chooseCB = scanner.nextLine();
        switch (chooseGT) {
            case "1":
                System.out.println("Nhập bậc: ");
                int bac = scanner.nextInt();
                scanner.nextLine();
                CanBo congNhan = new CongNhan(tuoi, diaChi, gioiTinh, ten, bac);
                canBos.add(congNhan);
                System.out.println("Tạo công nhân thành công");
                break;
            case "2":
                System.out.println("Nhập ngành: ");
                String nganh = scanner.nextLine();
                CanBo kySu = new KySu(tuoi, diaChi, gioiTinh, ten, nganh);
                canBos.add(kySu);
                System.out.println("Tạo kỹ sư thành công");
                break;
            default:
                System.out.println("Nhập công việc: ");
                String congViec = scanner.nextLine();
                CanBo nhanVien = new NhanVien(tuoi, diaChi, gioiTinh, ten, congViec);
                canBos.add(nhanVien);
                System.out.println("Tạo nhân viên thành công");
        }
    }

    @Override
    public void timKiemTheoHoTen() {
        System.out.println("Nhập tên muốn tìm: ");// %abc%
        String name = scanner.nextLine();
        System.out.println("+-------------------------+----------+--------------------+----------+");
        System.out.printf("|%25s|%10s|%20s|%10s|\n", "Tên", "Tuổi", "Địa chỉ", "Giới tính");
        System.out.println("+-------------------------+----------+--------------------+----------+");
        for (CanBo canBo: canBos) {
            if (canBo.getTen().contains(name)) {
                System.out.printf("|%25s|%10s|%20s|%10s|\n", canBo.getTen(), canBo.getTuoi(), canBo.getDiaChi(), canBo.getGioiTinh());
            }
        }
        System.out.println("+-------------------------+----------+--------------------+----------+");

    }

    @Override
    public void xoaCanBoTheoTen() {
        System.out.println("Nhập tên muốn tìm: ");// abc
        String name = scanner.nextLine();
        // dùng for + .remove đế xóa  -- có thể sai
        // chuyển List thành Iterable(tìm hiểu)  + for
        canBos.removeIf(canBo -> canBo.getTen().equals(name));// lambda
    }
}
