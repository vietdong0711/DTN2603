package backend.service.impl;

import backend.repository.IQLTVRepository;
import backend.repository.impl.QLTVRepositoryImpl;
import backend.service.IQLTVService;
import common.StringCommon;
import entity.Bao;
import entity.LoaiTaiLieu;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QLTVServiceImpl implements IQLTVService {
    private IQLTVRepository repository;

    public QLTVServiceImpl() {
        repository = new QLTVRepositoryImpl();// khoi tao gia tri
    }

    @Override
    public List<TaiLieu> getTaiLieus() {
        List<TaiLieu> taiLieus = repository.getTaiLieus();
        return taiLieus;
    }

    @Override
    public List<TaiLieu> timKiemTheoLoai(String value) {
        List<TaiLieu> taiLieus = repository.timKiemTheoLoai(value);
        return taiLieus;
    }

    @Override
    public boolean xoaTheoMaTaiLieu(String maTaiLieu) {
        return repository.xoaTheoMaTaiLieu(maTaiLieu);
    }

    @Override
    public boolean suaTaiLieuTheoMa(String maTaiLieu, String tenNXB) {
        return repository.suaTaiLieuTheoMa(maTaiLieu, tenNXB);
    }

    @Override
    public boolean themTaiLieu(TaiLieu taiLieu) {
        return repository.themTaiLieu(taiLieu);
    }

    @Override
    public boolean checkExist(String maTaiLieu) {
        return repository.checkExist(maTaiLieu);
    }

    @Override
    public String importCSV(String url) {// abc.txt
        // check file có đúng định dạng ko(  có đuôi là .csv ko)
        if (!url.endsWith(".csv")) {
            return "File không đúng định dạng!";
        }
        // check xem file có tồn tại ko
        File file = new File(url);
        if (!file.exists()) {
            return "File không tồn tại!";
        }
        List<TaiLieu> taiLieus = new ArrayList<>();
        List<String> listErrors = new ArrayList<>();// tập hợp các row và message lỗi
        String header = "";
        // đọc file -> ds các tài liệu trong file
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            String line;
            header = br.readLine();// đọc và bỏ qua dòng đầu tiên
            while ((line = br.readLine()) != null) {// đọc 1 dòng và gán vào line, nếu line != null thì mới đọc dữ liệu
                String message = this.validationTaiLieu(line, taiLieus);
                if (Objects.nonNull(message)) {
                    listErrors.add(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // lưu ds các tài liệu ko gặp lỗi vào DB(repo)
        for (TaiLieu taiLieu : taiLieus) {
            repository.themTaiLieu(taiLieu);
        }
        // xuất ra file lỗi ra file csv và đặt tên là input_errors.csv
        if (!listErrors.isEmpty()) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\Desktop\\DTN2603\\Java core\\csv\\input_errors.csv"));
                bw.write(header + ",error_messages");
                bw.newLine();
                for (String error : listErrors) {
                    bw.write(error);
                    bw.newLine();
                }
                bw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listErrors.isEmpty() ? "Import thành công" : "Đã xuất ra file lỗi input_errors.csv";
    }

    //   trả ra lỗi                    row đọc dc             nếu row đó ko có lỗi thì add vào list này
    public String validationTaiLieu(String line, List<TaiLieu> taiLieus) {
        TaiLieu tl = new TaiLieu();
        List<String> errors = new ArrayList<>();// để lưu lại ds lỗi

        String[] values = line.split(",");

        String maTaiLieu = values[0];
        // mã tài liệu
        if (maTaiLieu.length() < 3 || maTaiLieu.length() > 50) {
            errors.add("Mã tài liệu phải >3 và < 50 kí tự");//1.
        } else {
            if (repository.checkExist(maTaiLieu)) {
                errors.add("Mã tài liệu đã tồn tại");//2
            }
        }

        String tenNXB = values[1];
        // ten NXB
        if (tenNXB.length() < 3 || tenNXB.length() > 50) {
            errors.add("Tên nhà xuất bản phải >3 và < 50 kí tự");
        }

        // so ban PH
        int soBanPH = 0;
        if (!values[2].matches(StringCommon.NUMBER_REGEX)) {
            errors.add("Số bản phát hành phải là số");
        } else {
            soBanPH = Integer.parseInt(values[2]);
        }
        if (soBanPH < 0) {
            errors.add("số bản phát hành phải > 0");
        }

        if (LoaiTaiLieu.SACH.name().equals(values[3])) {
            // ten TG
            String tenTG = values[4];
            if (tenTG.length() < 3 || tenTG.length() > 50) {
                errors.add("Tên tác giả bản phải >3 và < 50 kí tự");
            }

            // so trang
            int soTrang = 0;
            if (!values[5].matches(StringCommon.NUMBER_REGEX)) {
                errors.add("Số trang phải là số");
            } else {
                soTrang = Integer.parseInt(values[5]);
            }
            // so bản phát hành
            if (soTrang < 0) {
                errors.add("Số trang phải > 0");
            }

            tl = new Sach(maTaiLieu, tenNXB, soBanPH, LoaiTaiLieu.SACH, tenTG, soTrang);
        } else if (LoaiTaiLieu.BAO.name().equals(values[3])) {
            String ngayPHString = values[8];
            LocalDate ngayPH = null;
            if (!ngayPHString.matches(StringCommon.YYYY_MM_DD_REGEX)) {
                errors.add("Sai định dạng ngày phát hành");
            } else {
                ngayPH = LocalDate.parse(ngayPHString);
            }

            tl = new Bao(maTaiLieu, tenNXB, soBanPH, LoaiTaiLieu.BAO, ngayPH);
        } else if (LoaiTaiLieu.TAP_CHI.name().equals(values[3])) {
            String soPH = values[6];
            int thangPH = 0;
            if (!values[2].matches(StringCommon.NUMBER_REGEX)) {
                errors.add("Tháng phát hành phải là số");
            } else {
                thangPH = Integer.parseInt(values[7]);
            }
            // so bản phát hành
            if (thangPH <= 0 || thangPH > 12) {
                errors.add("Tháng phát hành từ 1 - 12");
            }
            tl = new TapChi(maTaiLieu, tenNXB, soBanPH, LoaiTaiLieu.TAP_CHI, soPH, thangPH);
        }
        if (errors.isEmpty()) {// ko có lỗi gi  : errors.size() ==0
            taiLieus.add(tl);
            return null;
        } else {
            String error = String.join(", ", errors);
            line = line + "," + error;
        }
        return line;
    }
}
