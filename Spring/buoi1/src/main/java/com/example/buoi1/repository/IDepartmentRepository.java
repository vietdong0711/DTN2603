package com.example.buoi1.repository;

import com.example.buoi1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// object JPA ho tro,  kieu dư lieu cua khoa chinh
public interface IDepartmentRepository extends JpaRepository<Department,Integer> {

//    public List<Department> findByNameContains(String name);// like %name%

//    // tìm kiếm    findBy + tên thuộc tính
//    public List<Department> findByNameContainsAndQuantity(String name, int quantity);
//
//    // kiểm tra tồn tại   existBy
//    public boolean existsByName(String name);
    //

    @Query(value = "select * from department where department_name like concat('%', :nameParam , '%')", nativeQuery = true)
    public List<Department> findByNameCon(@Param("nameParam") String name);
}
