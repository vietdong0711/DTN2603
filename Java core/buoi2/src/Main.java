import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Department 1";

        Department department2 = new Department();
        department2.id = 2;
        department2.name = "Department 2";

        Position position1 = new Position();
        position1.id = 1;
        position1.name = PositionName.DEV;

        Position position2 = new Position();
        position2.id = 2;
        position2.name = PositionName.TEST;

        Account account1 = new Account();
        account1.id = 1;
        account1.fullName = "FullName 1";
        account1.username = "Username 1";
        account1.email = "Email 1";
        account1.position = position1;
        account1.department = department1;

        Account account2 = new Account();
        account2.id = 2;
        account2.fullName = "FullName 2";
        account2.username = "Username 2";
        account2.email = "Email 2";
        account2.position = position2;
        account2.department = department2;

        Account account3 = new Account();
        account3.id = 3;
        account3.fullName = "FullName 3";
        account3.username = "Username 3";
        account3.email = "Email 3";
        account3.position = position1;
        account3.department = department2;


        Account[] accounts = {account1, account2, account3};

        System.out.println("Danh sách account là: ");// 6 cột  id 5, fullname, username, email, ... 30
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", "ID", "Username", "FullName", "Email", "Deparment Name", "PositionName");
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (Account account : accounts) {
            System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", account.id, account.username, account.fullName, account.email, account.department.name, account.position.name);
        }
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");

    }

    public static int tinhTong(int a, int b) {
        return a + b;
    }

    // cho ng dufng nhaajp so, nếu ng dùng nhập sai định dạng thì bắt nhập đến khi nào đúng thì thôi
    public static int tinhTong() {
        Scanner sc = new Scanner(System.in);
        int a = 0;
        int b = 0;
        System.out.println("Nhập số thứ nhất: ");
        while (true) {
            if (sc.hasNextInt()) {
                a = sc.nextInt();
                break;
            }
            System.out.println("Nhập sai, nhập lại: ");
            sc.nextLine();
        }
        System.out.println("Nhập số thứ hai: ");
        while (true) {
            if (sc.hasNextInt()) {
                b = sc.nextInt();
                break;
            }
            System.out.println("Nhập sai, nhập lại: ");
            sc.nextLine();
        }
        sc.close();
        return a + b;
    }

}
