package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class QLAccount implements IQLAccount {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void hienThi() {
        List<Account> accounts = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/dtn2603_testing_system";
        String user = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "select acc.*, de.department_name, po.position_name \n" +
                    "from account acc\n" +
                    "left join department de on acc.department_id = de.department_id\n" +
                    "left join position po on acc.position_id = po.position_id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                String username = resultSet.getString("username");
                String fullName = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                Department department = null;
                Position position = null;

                if (Objects.nonNull(resultSet.getString("department_id"))) {// obj != null
                    int departmentId = resultSet.getInt("department_id");
                    String departmentName = resultSet.getString("department_name");
                    department = new Department(departmentId, departmentName);
                }
//                if (resultSet.getString("position_name") != null) {
                if (Objects.nonNull(resultSet.getString("position_name"))) {
                    int positionId = resultSet.getInt("position_id");
                    PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                    position = new Position(positionId, positionName);
                }
                Account account = new Account(id, username, fullName, email, department, position);
                accounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        show(accounts);
    }

    @Override
    public void them() {
        System.out.println("Nhập username: ");
        String username = scanner.nextLine();
        System.out.println("Nhập fullname: ");
        String fullName = scanner.nextLine();
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();

        // nhập phòng ban
        // C2: nhập tên phòng ban muốn thêm vào? nếu tồn tại rồi thì add vào phòng ban đó, chưa tồn tại thì tạo mới phòng ban
        // C1: show ra toàn bộ phòng ban(lấy dữ liệu từ DB), chọn phòng ban theo số tương ứng
        int departmentId = 0;
        while (true) {
            System.out.println("Chọn ID phòng ban muốn thêm vào: ");
            List<Department> departments = this.getDepartments();
            for (Department dep : departments) {
                System.out.printf("ID: %s - Name: %s\n", dep.getId(), dep.getName());
            }
            if (scanner.hasNextInt()) { // nhập ko phải là số
                String choiceDep = scanner.nextLine();
//                for (Department dep : departments) {
//                    if (dep.getId() == Integer.parseInt(choiceDep)) {
//                        department = dep;
//                        break;
//                    }
//                }
//                if (Objects.isNull(department)) { // nhập ID ko tồn tại
//                    System.out.println("Chọn sai. Chọn lại phòng ban!");
//                } else {
//                    break;
//                }
                Department department = departments.stream().filter(dep -> dep.getId() == Integer.parseInt(choiceDep))
                        .findFirst().orElse(null);// lambda
                if (Objects.isNull(department)) { //department == null
                    System.out.println("Chọn sai. Chọn lại phòng ban!");
                } else {
                    departmentId = department.getId();
                    break;
                }
            } else {
                System.out.println("Chọn sai. Chọn lại phòng ban!");
                scanner.nextLine();
            }
        }

        int positionId = 0;
        while (true) {
            System.out.println("Chọn ID chức vụ muốn thêm vào: ");
            List<Position> positions = this.getPositions();
            for (Position pos : positions) {
                System.out.printf("ID: %s - Name: %s\n", pos.getId(), pos.getName());
            }
            if (scanner.hasNextInt()) { // nhập ko phải là số
                Position position = null;
                String choiceDep = scanner.nextLine();
                for (Position pos : positions) {
                    if (pos.getId() == Integer.parseInt(choiceDep)) {
                        position = pos;
                        break;
                    }
                }
                if (Objects.isNull(position)) { // nhập ID ko tồn tại
                    System.out.println("Chọn sai. Chọn lại chức vụ!");
                } else {
                    positionId = position.getId();
                    break;
                }
//                position = positions.stream().filter(pos -> pos.getId() == Integer.parseInt(choiceDep))
//                        .findFirst().orElse(null);// lambda
//                if (Objects.isNull(position)) { //position == null
//                    System.out.println("Chọn sai. Chọn lại chức vụ!");
//                }  else {
//                    break;
//                }
            } else {
                System.out.println("Chọn sai. Chọn lại chức vụ!");
                scanner.nextLine();
            }
        }

        String url = "jdbc:mysql://localhost:3306/dtn2603_testing_system";
        String user = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "insert into account(email, username, fullname, department_id, position_id)\n" +
                    "values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, fullName);
            statement.setInt(4, departmentId);
            statement.setInt(5, positionId);

            int c = statement.executeUpdate();
            if (c > 0) {
                System.out.println("Thêm mới account thành công");
            } else {
                System.out.println("Thêm mới account thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sua() {
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
        // kiểm tra username này có dc sử dụng k

        String url = "jdbc:mysql://localhost:3306/dtn2603_testing_system";
        String user = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "UPDATE account SET username = ? where account_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, id);

            int c = statement.executeUpdate();
            if (c > 0) {
                System.out.println("Update thành công!");
            } else {
                System.out.println("Update không thành công!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xoa() {
        System.out.println("Mời bạn nhập ID account muốn xóa: ");
        int id = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                break;
            } else {
                System.err.println("Vui lòng nhập số! Nhập lại: ");
                scanner.nextLine();
            }
        }

        String url = "jdbc:mysql://localhost:3306/dtn2603_testing_system";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String  sql = "delete from account where account_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int c = statement.executeUpdate();
            if (c > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Xóa không thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show(List<Account> accounts) {
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n", "ID", "Username", "Full Name", "Email", "Department", "Position");
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        for (Account acc : accounts) {
            System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n", acc.getId(), acc.getUsername(), acc.getFullName(), acc.getEmail(), Objects.nonNull(acc.getDepartment()) ? acc.getDepartment().getName() : "", Objects.nonNull(acc.getPosition()) ? acc.getPosition().getName() : "");
        }
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
    }

    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/dtn2603_testing_system";
        String user = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from department";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("department_id");
                String name = resultSet.getString("department_name");
                Department department = new Department(id, name);
                departments.add(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }

    public List<Position> getPositions() {
        List<Position> positions = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/dtn2603_testing_system";
        String user = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from position";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("position_id");
                PositionName name = PositionName.valueOf(resultSet.getString("position_name"));
                Position position = new Position(id, name);
                positions.add(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }

    public static void main(String[] args) {
        QLAccount qlAccount = new QLAccount();
//        qlAccount.hienThi();
//        qlAccount.them();
//        qlAccount.xoa();
        qlAccount.sua();
    }
}
