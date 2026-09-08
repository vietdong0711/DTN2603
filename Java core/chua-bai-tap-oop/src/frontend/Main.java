package frontend;

import backend.IQLTV;
import backend.QLTV;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Main m = new Main();
        m.menu();
    }

    public void menu() {
        IQLTV qltv = new QLTV();
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Thêm mới tài liêu: Sách, tạp chí, báo.");
            System.out.println("2. Xoá tài liệu theo mã tài liệu.");
            System.out.println("3. Hiện thị thông tin về tài liệu.");
            System.out.println("4. Tìm kiếm tài liệu theo loại: Sách, tạp chí, báo.");
            System.out.println("5. Thoát khỏi chương trình.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    qltv.themTaiLieu();
                    break;
                case "2":
                    qltv.xoaTaiLieu();
                    break;
                case "3":
                    qltv.hienThiThongTin();
                    break;
                case "4":
                    qltv.timKiemTaiLieu();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("Chọn sai! chọn lại!");
            }
        }
    }
    //lần 4
}