package com.backend.repository.impl;

import com.backend.repository.IAccountRepository;
import com.entity.Account;
import com.entity.Department;
import com.entity.Position;
import com.entity.PositionName;
import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountRepositoryImpl implements IAccountRepository {

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Connection connection = JDBCUtils.getConnection();
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
                if (Objects.nonNull(resultSet.getString("department_id"))) {
                    int departmentId = resultSet.getInt("department_id");
                    String departmentName = resultSet.getString("department_name");
                    department = new Department(departmentId, departmentName);
                }
                if (Objects.nonNull(resultSet.getString("position_name"))) {
                    int positionId = resultSet.getInt("position_id");
                    PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                    position = new Position(positionId, positionName);
                }
                Account account = new Account(id, username, fullName, email, department, position);
                accounts.add(account);
            }
            JDBCUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean create(Account account) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "insert into account(email, username, fullname, department_id, position_id)\n" +
                    "values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getFullName());
            statement.setInt(4, account.getDepartment().getId());
            statement.setInt(5, account.getPosition().getId());

            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, String username) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE account SET username = ? where account_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, id);

            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            return c > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String  sql = "delete from account where account_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
