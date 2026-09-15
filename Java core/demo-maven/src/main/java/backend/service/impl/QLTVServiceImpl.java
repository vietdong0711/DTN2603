package backend.service.impl;

import backend.repository.IQLTVRepository;
import backend.repository.impl.QLTVRepositoryImpl;
import backend.service.IQLTVService;
import entity.TaiLieu;

import java.util.List;

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
}
