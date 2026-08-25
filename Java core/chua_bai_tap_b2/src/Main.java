import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Sale";

        Department department2 = new Department();
        department2.id = 2;
        department2.name = "Marketing";

        Department department3 = new Department();
        department3.id = 3;
        department3.name = "Production";

        // Tạo 3 đối tượng cho Position
        Position position1 = new Position();
        position1.id = 1;
        position1.name = Position.PositionName.PM;

        Position position2 = new Position();
        position2.id = 2;
        position2.name = Position.PositionName.DEV;

        Position position3 = new Position();
        position3.id = 3;
        position3.name = Position.PositionName.SCRUM_MASTER;

        Position position4 = new Position();
        position4.id = 4;
        position4.name = Position.PositionName.PM;

        // 3 đối tượng cho Account
        Account account1 = new Account();
        account1.id = 1;
        account1.username = "Minh";
        account1.email = "Minh@gmail.com";
        account1.fullName = "Nguyễn Hào Minh";
        account1.department = department1;
        account1.position = position1;
        account1.createDate = LocalDate.of(2023, 10, 27);

        Account account2 = new Account();
        account2.id = 2;
        account2.username = "Chiến";
        account2.email = "Chien@gmail.com";
        account2.fullName = "Nguyễn Hào Chiến";
        account2.department = department2;
        account2.position = position2;
        account2.createDate = LocalDate.of(2023,1,1);

        Account account3 = new Account();
        account3.id = 3;
        account3.username = "Hân";
        account3.email = "Han@gmail.com";
        account3.fullName = "Nguyễn Hào Hân";
//        account3.department = department3;
        account3.position = position3;
        account3.createDate = LocalDate.of(2023, 3,3);

        // Tạo 3 đối tượng cho Group
        Group group1 = new Group();
        group1.id = 1;
        group1.name = "Star";
        group1.creator = account1;
        group1.createDate = LocalDate.of(2023,4,2);

        Group group2 = new Group();
        group2.id = 2;
        group2.name = "Flower";
        group2.creator = account2;
        group2.createDate = LocalDate.of(2023,5,3);

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "Cat";
        group3.creator = account3;
        group3.createDate = LocalDate.of(2023,7,3);

        Group[] groups1 = new Group[] {group1,group2, group3};
        account1.groups = groups1;

        Group[] groups2 = new Group[] {group1};
        account2.groups = groups2;

        account3.groups = new Group[]{};

        Account[] accounts1 = new Account[] {account1,account2};
        group1.accounts = accounts1;

        Account[] accounts2 = new Account[] {account2};
        group2.accounts = accounts2;
        // toan bo account
        Account[] accounts = new Account[] {account1,account2, account3};





        GroupAccount ga1 = new GroupAccount();
        ga1.group = group1;
        ga1.account = account1;

        GroupAccount ga2 = new GroupAccount();
        ga2.group = group1;
        ga2.account = account2;

        GroupAccount ga3 = new GroupAccount();
        ga3.group = group2;
        ga3.account = account2;

        Department[] departments = new Department[]{department1,department2,department3};
        Position[] positions = new Position[]{position1,position2,position3, position4};


//        Question 1:
//        Kiểm tra account thứ 2
//        Nếu không có phòng ban (tức là department == null) thì sẽ in ra text "Nhân viên này chưa có phòng ban"
//        Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"

//        Exercise1.question1(account2);
//        Exercise1.question1(account1);
//        Exercise1.question1(account3);

//        Exercise1.question2(account3);

//        Exercise1.question3(account3);

//        Exercise1.question5(group2);

//        Exercise1.question10(accounts);
//        Exercise1.question12(accounts);
//        Exercise1.question13(accounts);

//        Exercise1.question14(accounts);
//        Exercise1.question15(20);

//        Exercise2.question2(12345678);

//        Exercise5.question5(departments, positions);

        Exercise5.question11(accounts, groups1, departments, positions);

    }
}
