package frontend;

import backend.IQLCB;
import backend.QLCB;
import entity.GioiTinh;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        IQLCB iqlcb = new QLCB();
        while (true) {
            System.out.println("====Mời bạn chọn chức năng====");
            System.out.println("1. Thêm mới cán bộ.");
            System.out.println("2. Tìm kiếm theo họ tên.");
            System.out.println("3. Hiện thị thông tin về danh sách các cán bộ.");
            System.out.println("4. Nhập vào tên của cán bộ và delete cán bộ đó");
            System.out.println("5. Thoát khỏi chương trình.");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    iqlcb.themMoiCanBo();
                    break;
                case "2":
                    iqlcb.timKiemTheoHoTen();
                    break;
                case "3":
                    iqlcb.hienThiDS();
                    break;
                case "4":
                    iqlcb.xoaCanBoTheoTen();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("Nhập sai, nhập lại");
            }
        }

    }
}