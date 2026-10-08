package com.example.buoi1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // http://localhost:8080/hello

    // http://127.0.0.1:8080/hello
    // http://192.168.1.152:8080/hello

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

// API: application programing interface  (truyền, nhận dữ liệu)

// FE yêu cầu ds sản phẩm từ BE
// frontend (client)     reactJS           - làm về quản lí bán hàng(muốn hiển thi ds sản phẩm từ DB)

//    API          gọi đến BE để lấy ra ds sản phẩm
    // ds dữ liệu JSON

// backend  (sever)      spring boot Java  - làm về quản lí bán hàng(ds sản phẩm)
// database



// hệ thống bệnh viện (FE, BE riêng)   -- khách hàng đến khám bệnh   - số bảo hiểm   -
    // check xem bảo hiểm còn hạn ko? hạn mức hỗ trợ      truyền mã bảo hiểm

// API   - để check bảo hiểm

// core Bảo việt                - trả ra thoogn tin liên quan




// đồng bộ hệ thống

// hệ thống A (bán bảo hiểm)   - khách hàng mua bảo hiểm(tạo và lưu ở hệ thống A), yêu cầu bổi thường
        // -- gửi các dữ liệu cần lưu trữ vào hệ thống core

// API tạo 1 hợp đồng bảo hiểm ở , API để đồng bộ dữ liệu bồi thường về hệ thống core

// core Bảo việt
        // -- lưu dữ liệu



//       http://localhost:8080/hello


