package frontend;

import backend.controller.QLTVController;
import entity.Bao;
import entity.LoaiTaiLieu;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Function {// chứa các chức năng project có
    private Scanner scanner;
    private QLTVController controller;


    public Function() {
        scanner = new Scanner(System.in);
        controller = new QLTVController();
    }

    public String checkLength(int min, int max) {
        while (true) {
            String text = scanner.nextLine();
            if (text.trim().length() < min || text.trim().length() > max) {
                System.err.println(String.format("Vui lòng nhập từ %d đến %d kí tự!\n", min, max));
                continue;
            }
            return text;
        }
    }

    public void hienThi() {
        System.out.println("==== Hiển thị toàn bộ tài liệu ====");
        // ds tài liệu để hieern thị
        List<TaiLieu> taiLieus = controller.getTaiLieus();
        this.show(taiLieus);
    }

    public void them() {
        TaiLieu taiLieu = new TaiLieu();
        System.out.println("Nhập mã tài liệu: ");
        String maTaiLieu = "";
        while (true) {
            System.out.println("Nhập mã tài liệu muốn thêm: ");
            maTaiLieu = scanner.nextLine();
            // check length
//            boolean checkLength = true;
//            if (maTaiLieu.trim().length() < 3 || maTaiLieu.trim().length() > 50) {
//                checkLength = false;
//            }
//            if(!checkLength) {
//                System.err.println("Vui lòng nhập từ 3 đến 50 kí tự!");
//                continue;
//            }
            maTaiLieu = this.checkLength(3, 50);
            // check xem trong DB đã tồn tajii mã đó chưa, tồn tại rồi -> lỗi
            boolean checkExist = controller.checkExist(maTaiLieu);
            if (checkExist) {// true -> báo lỗi
                System.err.println("Mã tài liệu này đã tồn tại. Nhập lại!");
                continue;
            }
            break;
        }

        String tenNhaXuatBan = "";
        while (true) {
            System.out.println("Nhập mã tên NXB cần sửa: ");
            tenNhaXuatBan = scanner.nextLine();
            // check length
//            boolean checkLength = true;
//            if (tenNhaXuatBan.trim().length() < 3 || tenNhaXuatBan.trim().length() > 50) {
//                checkLength = false;
//            }
//            if(!checkLength) {
//                System.err.println("Vui lòng nhập từ 3 đến 50 kí tự!");
//                continue;
//            }
            tenNhaXuatBan = this.checkLength(3, 50);
            break;
        }
        int soBanPhatHanh = 0;
        while (true) {
            System.out.println("Nhập số bản phát hành: ");
            if (!scanner.hasNextInt()) {// kiểm tra có phải số hay ko?
                System.err.println("Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            soBanPhatHanh = scanner.nextInt();
            scanner.nextLine();
            if (soBanPhatHanh <= 0) {
                System.err.println("Vui lòng nhập số lớn hơn 0!");
                continue;
            }
            break;
        }

        System.out.println("Mời bạn chọn loại tài liệu: 1.Sách  2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine();
        LoaiTaiLieu loaiTaiLieu = null;
        String tenTacGia = null;
        Integer soTrang = null;
        String soPhatHanh = null;
        Integer thangPhatHanh = null;
        switch (choice) {
            case "1":
                while (true) {
                    System.out.println("Nhập tên tác giả: ");
                    tenTacGia = scanner.nextLine();
                    // check length
                    boolean checkLength = true;
                    if (tenTacGia.trim().length() < 3 || tenTacGia.trim().length() > 50) {
                        checkLength = false;
                    }
                    if (!checkLength) {
                        System.err.println("Vui lòng nhập từ 3 đến 50 kí tự!");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.println("Nhập số trang: ");
                    if (!scanner.hasNextInt()) {// kiểm tra có phải số hay ko?
                        System.err.println("Vui lòng nhập số!");
                        scanner.nextLine();
                        continue;
                    }
                    soTrang = scanner.nextInt();
                    scanner.nextLine();
                    if (soTrang <= 0) {
                        System.err.println("Vui lòng nhập số lớn hơn 0!");
                        continue;
                    }
                    break;
                }
                loaiTaiLieu = LoaiTaiLieu.SACH;
                taiLieu = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, tenTacGia, soTrang);
                break;
            case "2":
                // dùng biểu thức chính quy để check
                // ^\d{4}-\d{2}-\d{2}$: check định dạng 4so-2so-2so
                // ^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$: đúng định dạng năm(0000-9999) tháng(1-12) ngày(1-31)// 2020-02-31 sai
                // ^(?:(?:19|20)\d\d-(?:(?:0[13578]|1[02])-(?:0[1-9]|[12]\d|3[01])|(?:0[469]|11)-(?:0[1-9]|[12]\d|30)|02-(?:0[1-9]|1\d|2[0-8]))|(?:(?:19|20)(?:[02468][048]|[13579][26])|(?:20)00)-02-29)$
                String ngayPhatHanh = "";
                while (true) {
                    System.out.println("Nhập ngày phát hành(yyyy-MM-dd): ");
                    ngayPhatHanh = scanner.nextLine();

                    if (!ngayPhatHanh.trim().matches("^(?:(?:19|20)\\d\\d-(?:(?:0[13578]|1[02])-(?:0[1-9]|[12]\\d|3[01])|(?:0[469]|11)-(?:0[1-9]|[12]\\d|30)|02-(?:0[1-9]|1\\d|2[0-8]))|(?:(?:19|20)(?:[02468][048]|[13579][26])|(?:20)00)-02-29)$")) {// 4 số   - 2 số - 2 số : 9999-99-99
                        System.err.println("Nhập ngày ko hợp lệ");
                        continue;
                    }
                    break;
                }
                scanner.nextLine();
                loaiTaiLieu = LoaiTaiLieu.BAO;

                taiLieu = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, LocalDate.parse(ngayPhatHanh));

                break;
            default:
                System.out.println("Nhập số phát hành: ");
                soPhatHanh = scanner.nextLine();
                while (true) {
                    System.out.println("Nhập tháng phát hành: ");
                    if (!scanner.hasNextInt()) {// kiểm tra có phải số hay ko?
                        System.err.println("Vui lòng nhập số!");
                        scanner.nextLine();
                        continue;
                    }
                    thangPhatHanh = scanner.nextInt();
                    scanner.nextLine();
                    if (thangPhatHanh <= 0 || thangPhatHanh > 12) {
                        System.err.println("Vui lòng tháng > 0 và nhỏ hơn 12!");
                        continue;
                    }
                    break;
                }
                loaiTaiLieu = LoaiTaiLieu.TAP_CHI;
                taiLieu = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, soPhatHanh, thangPhatHanh);
                scanner.nextLine();
        }

        boolean check = controller.themTaiLieu(taiLieu);
        if (check) {
            System.out.println("Thêm tài liệu thành công!");
        } else {
            System.out.println("Thêm tài liệu không thành công!");
        }
    }

    public void sua() {
        String maTaiLieu = "";
        while (true) {
            System.out.println("Nhập mã tài liệu muốn update: ");
            maTaiLieu = scanner.nextLine();
            // check length
            boolean checkLength = true;
            if (maTaiLieu.trim().length() < 3 || maTaiLieu.trim().length() > 50) {
                checkLength = false;
            }
            if (!checkLength) {
                System.err.println("Vui lòng nhập từ 3 đến 50 kí tự!");
                continue;
            }
            // check dữ liệu ở DB
            boolean checkExist = controller.checkExist(maTaiLieu);
            if (!checkExist) {
                System.err.println("Mã tài liệu này ko tồn tại. Nhập lại!");
                continue;
            }
            break;
        }
        String tenNXB = "";
        while (true) {
            System.out.println("Nhập mã tên NXB cần sửa: ");
            tenNXB = scanner.nextLine();
            // check length
            boolean checkLength = true;
            if (tenNXB.trim().length() < 3 || tenNXB.trim().length() > 50) {
                checkLength = false;
            }
            if (!checkLength) {
                System.err.println("Vui lòng nhập từ 3 đến 50 kí tự!");
                continue;
            }
            break;
        }

        boolean check = controller.suaTaiLieuTheoMa(maTaiLieu, tenNXB);
        if (check) {
            System.out.println("Sửa tài liệu thành công!");
        } else {
            System.out.println("Sửa tài liệu không thành công!");
        }
    }

    public void xoa() {
        System.out.println("==== Chức năng xóa tài liệu ====");
        String maTaiLieu = "";

        while (true) {
            System.out.println("Nhập mã tài liệu muốn xóa: ");
            maTaiLieu = scanner.nextLine();
            // check length
            boolean checkLength = true;
            if (maTaiLieu.trim().length() < 3 || maTaiLieu.trim().length() > 50) {
                checkLength = false;
            }
            if (!checkLength) {
                System.err.println("Vui lòng nhập từ 3 đến 50 kí tự!");
                continue;
            }
            // check dữ liệu ở DB
            boolean checkExist = controller.checkExist(maTaiLieu);
            if (!checkExist) {
                System.err.println("Mã tài liệu này ko tồn tại. Nhập lại!");
                continue;
            }
            break;
        }

        boolean check = controller.xoaTheoMaTaiLieu(maTaiLieu);
        if (check) {
            System.out.println("Xóa tài liệu thành công!");
        } else {
            System.out.println("Xóa tài liệu không thành công!");
        }
    }

    public void timKiem() {
        System.out.println("==== Hiển thị toàn bộ tài liệu ====");

        System.out.println("Chọn loại tài liệu muốn hiển thị: 1. Sách   2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine();
        String value = "";
        switch (choice) {
            case "1":
                value = "SACH";
                break;
            case "2":
                value = "BAO";
                break;
            default:
                value = "TAP_CHI";
        }

        List<TaiLieu> taiLieus = controller.timKiemTheoLoai(value);
        this.show(taiLieus);
    }

    public void show(List<TaiLieu> taiLieus) {
        System.out.println("==== Tìm kiếm tài liệu ====");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|%25s|\n", "Mã tài liệu", "Tên NXB", "Số bản phát hành");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        if (taiLieus.size() > 0) {
            for (TaiLieu taiLieu : taiLieus) {
                System.out.printf("|%25s|%25s|%25s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
    }

    public void menu() {
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Thêm mới tài liêu: Sách, tạp chí, báo.");
            System.out.println("2. Xoá tài liệu theo mã tài liệu.");
            System.out.println("3. Hiện thị thông tin về tài liệu.");
            System.out.println("4. Tìm kiếm tài liệu theo loại: Sách, tạp chí, báo.");
            System.out.println("5. UPdate NXB cho tài liệu theo mã tài liệu.");
            System.out.println("6. Thoát khỏi chương trình.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    this.them();
                    break;
                case "2":
                    this.xoa();
                    break;
                case "3":
                    this.hienThi();
                    break;
                case "4":
                    this.timKiem();
                    break;
                case "5":
                    this.sua();
                    break;
                case "6":
                    System.exit(0);
                default:
                    System.out.println("Chọn sai! chọn lại!");
            }
        }
    }
}
