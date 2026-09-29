package com.backend.service;

import com.entity.Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public interface ImportCSV<T, K> {

    void validation(String line, List<T> entities, List<String> lineErrors, K context);
    void saveAll(List<T> entities);
    void exportErrors(String header, List<String> lineErrors, String pathErrors);


    // logic  xu lý
    default String importCSV(String pathName, K context, String pathErrors) {
        // check định dạng file, file tồn tại ko,..
        File file = new File(pathName);
        if (!file.exists()) {
            return "File không tồn tại";
        }
        if (!pathName.endsWith(".csv")) {
            return "File ko đúng định dạng!";
        }
        List<T> entities = new ArrayList<>();// list ko có lỗi
        List<String> lineErrors = new ArrayList<>();// list  có lỗi
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            String line;
            String header = br.readLine();// header

            while ((line = br.readLine()) != null) {// lặp qua từn row để dọc dữ liệu
                this.validation(line, entities, lineErrors, context);
            }
            // lưu vào entities DB
            this.saveAll(entities);
            // xuất file lỗi
            this.exportErrors(header, lineErrors, pathErrors);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineErrors.isEmpty() ? "Import thành công!" : "Import thành công, các lỗi đã xuất ra file lỗi!";
    }
}
