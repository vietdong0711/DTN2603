package backend.controller;

import backend.service.IQLTVService;
import backend.service.impl.QLTVServiceImpl;
import entity.TaiLieu;

import java.util.List;

public class QLTVController {
    private IQLTVService service;

    public QLTVController() {
        service = new QLTVServiceImpl();
    }

    public List<TaiLieu> getTaiLieus() {
        List<TaiLieu> taiLieus = service.getTaiLieus();
        return taiLieus;
    }

    public List<TaiLieu> timKiemTheoLoai(String value) {
        List<TaiLieu> taiLieus = service.timKiemTheoLoai(value);
        return taiLieus;
    }

    public boolean xoaTheoMaTaiLieu(String maTaiLieu) {
        return service.xoaTheoMaTaiLieu(maTaiLieu);
    }

    public boolean suaTaiLieuTheoMa(String maTaiLieu, String tenNXB) {
        return service.suaTaiLieuTheoMa(maTaiLieu, tenNXB);
    }

    public boolean themTaiLieu(TaiLieu taiLieu) {
        return service.themTaiLieu(taiLieu);
    }
}
