public class Exercise1 {

    public static void question1(Account account) {
        if (account.department == null) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là " + account.department.name);
        }
    }

    //    Question 2:
//    Kiểm tra account thứ n
//    Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
//    Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
//    Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
//    Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"
    public static void question2(Account account) {
        if (account.groups.length == 0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (account.groups.length == 1 || account.groups.length == 2) {
            System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
        } else if (account.groups.length == 3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }
    }

    public static void question3(Account account) {
//        if (account.department == null) {
//            System.out.println("Nhân viên này chưa có phòng ban");
//        } else {
//            System.out.println("Phòng ban của nhân viên này là "+ account.department.name);
//        }
        String ketQua = account.department == null ? "Nhân viên này chưa có phòng ban" : "Phòng ban của nhân viên này là " + account.department.name;
        System.out.println(ketQua);
    }

    //    Lấy ra số lượng account trong nhóm thứ n và in ra theo format sau:
//    Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
//    Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
//    Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
//    Còn lại in ra "Nhóm có nhiều thành viên"
    public static void question5(Group group) {
        switch (group.accounts.length) {
            case 0:
                System.out.println("Nhóm này không có thành viên");
                break;
            case 1:
                System.out.println("Nhóm có một thành viên");
                break;
            case 2:
                System.out.println("Nhóm có hai thành viên");
                break;
            case 3:
                System.out.println("Nhóm có ba thành viên");
                break;
            default:
                System.out.println("Nhóm có nhiều thành viên");
        }
    }

    //    Question 8:
//    In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ
    // tạo ra 1 mảng chứa toàn bộ account    accounts
    public static void question8(Account[] accounts) {
        System.out.println("+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|\n", "Email", "FullName", "Tên phòng ban");
        System.out.println("+--------------------+--------------------+--------------------+");
        for (Account account : accounts) {
            String departmentName = account.department == null ? "Không có" : account.department.name;
            System.out.printf("|%20s|%20s|%20s|\n", account.email, account.fullName, departmentName);
        }
        System.out.println("+--------------------+--------------------+--------------------+");
    }


//    Question 9:
//    In ra thông tin các phòng ban bao gồm: id và name
    // tạo 1 mảng departments    rồi dùng foreach in ra

    public static void question10(Account[] accounts) {
        System.out.println("+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|\n", "Email", "FullName", "Tên phòng ban");
        System.out.println("+--------------------+--------------------+--------------------+");
        for (int i = 0; i < accounts.length; i++) {
            String departmentName = accounts[i].department == null ? "Không có" : accounts[i].department.name;
            System.out.printf("|%20s|%20s|%20s|\n", accounts[i].email, accounts[i].fullName, departmentName);
        }
        System.out.println("+--------------------+--------------------+--------------------+");
    }

    //    Question 12:
//    Chỉ in ra thông tin 2 account đầu tiên theo định dạng như Question 10
    public static void question12(Account[] accounts) {
        System.out.println("+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|\n", "Email", "FullName", "Tên phòng ban");
        System.out.println("+--------------------+--------------------+--------------------+");
        for (int i = 0; i < accounts.length; i++) {
            if (i >= 2) {
                break;
            }
            String departmentName = accounts[i].department == null ? "Không có" : accounts[i].department.name;
            System.out.printf("|%20s|%20s|%20s|\n", accounts[i].email, accounts[i].fullName, departmentName);
        }
        System.out.println("+--------------------+--------------------+--------------------+");
    }

    //    Question 13:
//    In ra thông tin tất cả các account ngoại trừ account thứ 2
    public static void question13(Account[] accounts) {
        System.out.println("+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|\n", "Email", "FullName", "Tên phòng ban");
        System.out.println("+--------------------+--------------------+--------------------+");
        for (int i = 0; i < accounts.length; i++) {
            if (i == 1) {
                continue;
            }
            String departmentName = accounts[i].department == null ? "Không có" : accounts[i].department.name;
            System.out.printf("|%20s|%20s|%20s|\n", accounts[i].email, accounts[i].fullName, departmentName);
        }
        System.out.println("+--------------------+--------------------+--------------------+");
    }

    //    Question 14:
//    In ra thông tin tất cả các account có id < 4
    public static void question14(Account[] accounts) {
        System.out.println("+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|\n", "Email", "FullName", "Tên phòng ban");
        System.out.println("+--------------------+--------------------+--------------------+");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].id < 3) {
                String departmentName = accounts[i].department == null ? "Không có" : accounts[i].department.name;
                System.out.printf("|%20s|%20s|%20s|\n", accounts[i].email, accounts[i].fullName, departmentName);
            }
        }
        System.out.println("+--------------------+--------------------+--------------------+");
    }

//    Question 15:
//    In ra các số chẵn nhỏ hơn hoặc bằng 20
    public static void question15(int s) {
        for (int i = 1; i <= s; i++) {
            if (i %2 == 0) {
                System.out.println(i + " ");
            }
        }
    }

}
