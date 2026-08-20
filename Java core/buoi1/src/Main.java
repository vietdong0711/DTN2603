import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.fullName = "Nguyễn Văn A";
        person1.address = "HN";
        person1.cccd = "012345678910";
        person1.phone = "0987654321";
        person1.birthOfDate = new Date(2000, 1, 1);
        person1.email = "nguyenvana@gmail.com";
        person1.gender = Gender.MALE;
        person1.points = new int[]{10, 9, 8};
        // shift 2 lần: tìm kieesm class nhanh
        // ctrl + alt +  mũi tên trái phải để di chuyển qua lại giữa từng vị trí đã ở của trỏ chuột
        // ctrl + shift + mũi teen lên hoặc xuống: di chuyển hàng
        // ctrl + alt + L : format code
        // ctrl + alt + O : xóa đi các import ko dùng tới
        // muốn sửa tên của 1 instance thì shift + f6

        // in ra thông tin
        System.out.println("Họ tên: " + person1.fullName);
        System.out.println("Địa chỉ: " + person1.address);
        System.out.println("CCCD: " + person1.cccd);
        System.out.println("Email: " + person1.email);
        System.out.println("Phone: " + person1.phone);
        System.out.println("Giới tính: " + person1.gender);
        System.out.println("Ngày sinh: " + person1.birthOfDate);
        System.out.println("DS điểm: " );//ln: line
        for (int i = 0; i < person1.points.length; i++) {
            System.out.print(person1.points[i] + " ");
        }
    }
}