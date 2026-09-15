package com.frontend;

import com.backend.controller.AccountController;
import com.backend.controller.DepartmentController;
import com.backend.controller.PositionController;
import com.entity.Account;
import com.entity.Department;
import com.entity.Position;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Function {
    private Scanner scanner;
    private AccountController accountController;
    private DepartmentController departmentController;
    private PositionController positionController;


    public Function() {
        scanner = new Scanner(System.in);
        accountController = new AccountController();
        departmentController = new DepartmentController();
        positionController = new PositionController();
    }

    public void findAll() {
        System.out.println("==== CHỨC NĂNG HIỂN THỊ ACCOUNT ====");
        List<Account> accounts = accountController.findAll();
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n", "ID", "Username", "Full Name", "Email", "Department", "Position");
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        for (Account acc : accounts) {
            System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n", acc.getId(), acc.getUsername(), acc.getFullName(), acc.getEmail(), Objects.nonNull(acc.getDepartment()) ? acc.getDepartment().getName() : "", Objects.nonNull(acc.getPosition()) ? acc.getPosition().getName() : "");
        }
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
    }

    public void create() {
        System.out.println("==== CHỨC NĂNG TẠO ACCOUNT ====");
        Account account = new Account();

        System.out.println("Nhập username: ");
        String username = scanner.nextLine();
        account.setUsername(username);

        System.out.println("Nhập fullname: ");
        String fullName = scanner.nextLine();
        account.setFullName(fullName);

        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        account.setEmail(email);

        while (true) {
            System.out.println("Chọn ID phòng ban muốn thêm vào: ");
            List<Department> departments = departmentController.findAll();
            for (Department dep : departments) {
                System.out.printf("ID: %s - Name: %s\n", dep.getId(), dep.getName());
            }
            if (scanner.hasNextInt()) {
                String choiceDep = scanner.nextLine();
                Department department = departments.stream().filter(dep -> dep.getId() == Integer.parseInt(choiceDep.trim()))
                        .findFirst().orElse(null);
                if (Objects.isNull(department)) {
                    System.out.println("Chọn sai. Chọn lại phòng ban!");
                } else {
                    account.setDepartment(department);
                    break;
                }
            } else {
                System.out.println("Chọn sai. Chọn lại phòng ban!");
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("Chọn ID chức vụ muốn thêm vào: ");
            List<Position> positions = positionController.findAll();
            for (Position pos : positions) {
                System.out.printf("ID: %s - Name: %s\n", pos.getId(), pos.getName());
            }
            if (scanner.hasNextInt()) {
                Position position = positions.stream().filter(pos -> pos.getId() == Integer.parseInt(scanner.nextLine().trim())).findFirst().orElse(null);
                if (Objects.isNull(position)) {
                    System.out.println("Chọn sai. Chọn lại chức vụ!");
                } else {
                    account.setPosition(position);
                    break;
                }
            } else {
                System.out.println("Chọn sai. Chọn lại chức vụ!");
                scanner.nextLine();
            }
        }
        boolean check = accountController.create(account);
        if (check) {
            System.out.println("Create successfully!");
        } else {
            System.out.println("Create failed!");
        }
    }

    public void update() {
        System.out.println("==== CHỨC NĂNG UPDATE ====");
        System.out.println("Nhập ID muốn update: ");
        int id = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.err.println("Vui lòng nhập số! Nhập lại: ");
                scanner.nextLine();
            }
        }
        System.out.println("Nhập username muốn update: ");
        String username = scanner.nextLine();
        boolean check = accountController.update(id, username);
        if (check) {
            System.out.println("Update successfully!");
        } else {
            System.out.println("Update failed!");
        }
    }

    public void delete() {
        System.out.println("==== CHỨC NĂNG XÓA ACCOUNT ====");
        System.out.println("Mời bạn nhập ID account muốn xóa: ");
        int id = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.err.println("Vui lòng nhập số! Nhập lại: ");
                scanner.nextLine();
            }
        }

        boolean canDelete = accountController.delete(id);
        if (canDelete) {
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Delete failed!");
        }
    }

    public void menu() {
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Hiển thị toàn bộ account");
            System.out.println("2. Thêm account");
            System.out.println("3. Xóa account theo id");
            System.out.println("4. Update account theo id");
            System.out.println("5. Thoát");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    this.findAll();
                    break;
                case "2":
                    this.create();
                    break;
                case "3":
                    this.delete();
                    break;
                case "4":
                    this.update();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("Chọn sai! Chọn lại!");
            }
        }
    }
}
