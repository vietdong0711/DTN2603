import java.util.Date;

public class Person {
//    tên, ngày sinh, địa chỉ, cccd, sdt, email, giới tính
    String fullName;
    Date birthOfDate;
    String address;
    String cccd;
    String phone;
    String email;
    Gender gender;
    int[] points;

//    public enum GioiTinh {
//        MALE, FEMALE, UNKNOWN;
//    }
    public void an() {
        System.out.println("Person đang đi ăn");
    }

    public void diChoi() {
        System.out.println("Person đang đi chơi");
    }
}
