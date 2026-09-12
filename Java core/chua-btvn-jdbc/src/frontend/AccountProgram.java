package frontend;

import backend.QLAccount;

import java.util.Scanner;

public class AccountProgram {
    public void menu() {
        QLAccount qlAccount = new QLAccount();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Hiển thị toàn bộ account");
            System.out.println("2. Thêm account");
            System.out.println("3. Xóa account theo id");
            System.out.println("4. Update account theo id");
            System.out.println("5. Thoát");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    qlAccount.hienThi();
                    break;
                case "2":
                    qlAccount.them();
                    break;
                case "3":
                    qlAccount.xoa();
                    break;
                case "4":
                    qlAccount.sua();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Chọn sai! Chọn lại!");
            }
        }
    }

    public static void main(String[] args) {
        AccountProgram ap = new AccountProgram();
        ap.menu();
    }
}
