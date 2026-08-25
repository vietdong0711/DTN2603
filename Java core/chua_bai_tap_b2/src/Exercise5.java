import java.util.Random;
import java.util.Scanner;

public class Exercise5 {

    public static Scanner sc = new Scanner(System.in);
    //    5:
//    Viết lệnh cho phép người dùng tạo account (viết thành method)
//    Đối với property Position, Người dùng nhập vào 1 2 3 4 5 và vào
//    chương trình sẽ chuyển thành Position.Dev, Position.Test, Position.ScrumMaster, Position.PM.
    public static void question5(Department[] departments, Position[] positions) {
        Account account = new Account();

        System.out.println("Nhập id: ");
        while (true) {
            if (sc.hasNextInt()) {
                account.id = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("Nhập sai định dạng id. Nhập lại: ");
            }
            sc.nextLine();
        }
        System.out.println("Nhập email: ");
        account.email = sc.nextLine();
        System.out.println("Nhập username: ");
        account.username = sc.nextLine();
        System.out.println("Nhập fullName: ");
        account.fullName = sc.nextLine();
        System.out.println("Nhập id phòng ban: ");
        while (true) {
            for (Department department : departments) {
                System.out.printf("%s. %s   ", department.id, department.name);
            }
            // check đầu vào có phải la số ko
            int depId = sc.nextInt();
            boolean checkExist = false;
            for (Department department : departments) {
                if (department.id == depId) {
                    account.department = department;
                    checkExist = true;
                }
            }
            if (!checkExist) {
                System.out.println("Chọn lại phòng ban: ");
            } else {
                break;
            }
        }
        System.out.println("Nhập id chức vụ: ");
        while (true) {
            for (Position position : positions) {
                System.out.printf("%s. %s   ", position.id, position.name);
            }
            // check đầu vào có phải la số ko
            int posId = sc.nextInt();
            sc.nextLine();
            boolean checkExist = false;
            for (Position position : positions) {
                if (position.id == posId) {
                    account.position = position;
                    checkExist = true;
                }
            }
            if (!checkExist) {
                System.out.println("Chọn lại chức vụ: ");
            } else {
                break;
            }
        }

        System.out.println("Đã tạo thành công account");
        System.out.printf("ID: %s, Username: %s, Email: %s, Fullname: %s, Department Name: %s, Position Name: %s",
                account.id, account.username, account.email, account.fullName, account.department.name, account.position.name, account.position.name);
    }

    public static void addGroupToAccount(Account[] accounts, Group[] groups) {
        Account accSelect = new Account();
        Group groupSelect = new Group();
        // in ra ds group  -> chọn
        System.out.println("Mời bạn chọn group: ");
        while (true) {
            for (Group group : groups) {
                System.out.printf("%s. %s   ", group.id, group.name);
            }
            // check đầu vào có phải la số ko
            int grID = sc.nextInt();
            boolean checkExist = false;
            for (Group group : groups) {
                if (group.id == grID) {
                    groupSelect = group;
                    checkExist = true;
                }
            }
            if (!checkExist) {
                System.out.println("Chọn lại group: ");
            } else {
                break;
            }
        }

        // in ra ds account  -> chọn
        System.out.println("Mời bạn chọn account: ");
        while (true) {
            for (Account account : accounts) {
                System.out.printf("%s. %s   ", account.id, account.username);
            }
            // check đầu vào có phải la số ko
            int accID = sc.nextInt();
            boolean checkExist = false;
            for (Account account : accounts) {
                if (account.id == accID) {
                    accSelect = account;
                    checkExist = true;
                }
            }
            if (!checkExist) {
                System.out.println("Chọn lại account: ");
            } else {
                break;
            }
        }

        // thêm group đó vào account   thêm vào mảng groups của account,    thêm vào mảng accouts của group
        Group[] groupInAccountCurrent = accSelect.groups;// ds group ban đầu của acc
        Account[] accountInGroupCurrent = groupSelect.accounts;// ds account ban dầu của group

        Group[] newGroups = new Group[groupInAccountCurrent.length + 1];
        // clone lại mảng cũ
        for (int i = 0; i < newGroups.length - 1; i++) {
            newGroups[i] = groupInAccountCurrent[i];
        }
        // thêm group vao cuối cung
        newGroups[newGroups.length - 1] = groupSelect;

        Account[] newAccounst = new Account[accountInGroupCurrent.length + 1];
        for (int i = 0; i < newAccounst.length - 1; i++) {
            newAccounst[i] = accountInGroupCurrent[i];
        }
        newAccounst[newAccounst.length - 1] = accSelect;

        accSelect.groups = newGroups;
        groupSelect.accounts = newAccounst;
        System.out.printf("Đã thêm account có username %s  vào group có tên %s.\n", accSelect.username, groupSelect.name);
    }

    public static void addAccountToRandomGroup(Account[] accounts, Group[] groups) {
        Account accSelect = new Account();
        Group groupSelect = new Group();
        // in ra ds group  -> chọn
        System.out.println("Mời bạn chọn group: ");
        Random rand = new Random();

        groupSelect = groups[rand.nextInt(groups.length + 1)];
        // in ra ds account  -> chọn
        System.out.println("Mời bạn chọn account: ");
        while (true) {
            for (Account account : accounts) {
                System.out.printf("%s. %s   ", account.id, account.username);
            }
            // check đầu vào có phải la số ko
            int accID = sc.nextInt();
            boolean checkExist = false;
            for (Account account : accounts) {
                if (account.id == accID) {
                    accSelect = account;
                    checkExist = true;
                }
            }
            if (!checkExist) {
                System.out.println("Chọn lại account: ");
            } else {
                break;
            }
        }

        // thêm group đó vào account   thêm vào mảng groups của account,    thêm vào mảng accouts của group
        Group[] groupInAccountCurrent = accSelect.groups;// ds group ban đầu của acc
        Account[] accountInGroupCurrent = groupSelect.accounts;// ds account ban dầu của group

        Group[] newGroups = new Group[groupInAccountCurrent.length + 1];
        // clone lại mảng cũ
        for (int i = 0; i < newGroups.length - 1; i++) {
            newGroups[i] = groupInAccountCurrent[i];
        }
        // thêm group vao cuối cung
        newGroups[newGroups.length - 1] = groupSelect;

        Account[] newAccounst = new Account[accountInGroupCurrent.length + 1];
        for (int i = 0; i < newAccounst.length - 1; i++) {
            newAccounst[i] = accountInGroupCurrent[i];
        }
        newAccounst[newAccounst.length - 1] = accSelect;

        accSelect.groups = newGroups;
        groupSelect.accounts = newAccounst;
        System.out.printf("Đã thêm account có username %s  vào group có tên %s.\n", accSelect.username, groupSelect.name);
    }

    public static void question11(Account[] accounts, Group[] groups, Department[] departments, Position[] positions) {
        while (true) {
            sc.nextLine();
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. tạo account");
            System.out.println("2. tạo department(tương tự tạo account)");
            System.out.println("3. thêm 1 group vào account");
            System.out.println("4. thêm acount vào 1 group ngẫu nhiên");
            System.out.println("5. Thoát");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    System.out.println("Chức năng tạo account");
                    question5(departments, positions);
                    break;
                case "2":
                    System.out.println("Chức năng tạo department(tương tự tạo account)");
                    break;
                case "3":
                    System.out.println("Chức năng thêm 1 group vào account");
                    addGroupToAccount(accounts, groups);
                    break;
                case "4":
                    System.out.println("Chức năng thêm 1 account vào group random");
                    addAccountToRandomGroup(accounts, groups);
                    break;
                case "5":
                    System.out.println("Thoát!!!");
                    return;
                default:
                    System.out.println("Chọn sai, chọn lại: ");
            }
        }



    }


}
