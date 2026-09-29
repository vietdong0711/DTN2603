package com.backend.service.impl;

import com.backend.repository.IAccountRepository;
import com.backend.repository.IDepartmentRepository;
import com.backend.repository.IPositionRepository;
import com.backend.repository.impl.AccountRepositoryImpl;
import com.backend.repository.impl.DepartmentRepositoryImpl;
import com.backend.repository.impl.PositionRepositoryImpl;
import com.backend.service.IAccountService;
import com.backend.service.ImportCSV;
import com.common.StringCommon;
import com.context.AccountContext;
import com.entity.Account;
import com.entity.Department;
import com.entity.Position;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountServiceImpl implements IAccountService, ImportCSV<Account, AccountContext> {
    private IAccountRepository repository;
    private IDepartmentRepository departmentRepository;
    private IPositionRepository positionRepository;

    public AccountServiceImpl() {
        repository = new AccountRepositoryImpl();
        departmentRepository = new DepartmentRepositoryImpl();
        positionRepository = new PositionRepositoryImpl();
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean create(Account account) {
        return repository.create(account);
    }

    @Override
    public boolean update(int id, String username) {
        return repository.update(id, username);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean checkUsernameExists(String username, Integer id) {
        return repository.checkUsernameExists(username, id);
    }

    @Override
    public boolean checkEmailExists(String email, Integer id) {
        return repository.checkEmailExists(email, id);
    }

    @Override
    public String importCSV(String pathName) {
        // dữ liệu để validation
        List<Department> departments = departmentRepository.findAll();
        List<Position> positions = positionRepository.findAll();
        List<Account> accounts = repository.findAll();

        Map<String, Account> mapByUsername = accounts.stream()
                .collect(Collectors.toMap(acc -> acc.getUsername(), acc -> acc));
        Map<String, Account> mapByEmail = accounts.stream()
                .collect(Collectors.toMap(acc -> acc.getEmail(), acc -> acc));
        AccountContext accountContext = new AccountContext(mapByUsername, mapByEmail,  departments, positions);
        String parthError = "C:\\Users\\Admin\\Desktop\\DTN2603\\Java core\\csv\\output_err.csv";
        return this.importCSV(pathName, accountContext, parthError);
    }

    @Override
    public boolean login(String username, String password) {
        return repository.login(username, password);
    }

    @Override
    public void validation(String line, List<Account> entities, List<String> lineErrors, AccountContext context) {
        String[] fields = line.split(",", -1);
        String username = fields[0];
        String email = fields[1];
        String fullName = fields[2];
        String departmentId = fields[3];
        String positionId = fields[4];

        List<String> errors = new ArrayList<>();// các lỗi xảy ở trong line này

        //validation
        if (Objects.isNull(username) || username.trim().isEmpty()) {
            errors.add("Username không dc để trống");
        } else if (context.getMapByUsername().get(username) != null) {// map.get(key) -> value
            errors.add("Username đã tồn tại");
        }

        if (Objects.isNull(email) || email.trim().isEmpty()) {
            errors.add("Email không dc để trống");
        } else if (!email.matches(StringCommon.EMAIL_REGEX)) {
            errors.add("Email không đúng định dạng");
        } else if (context.getMapByEmail().get(email) != null) {// map.get(key) -> value   O(1)
            errors.add("Email đã tồn tại");
        }

        if (Objects.isNull(fullName) || fullName.trim().isEmpty()) {
            errors.add("FullName không dc để trống");
        }

        // kiêm tra xem day phai la so hay ko
        Department department = null;
        if (!departmentId.matches(StringCommon.NUMBER_REGEX)) {
            errors.add("DepartmentId phải là số");
        } else {
            // kiểm tra xem departmentId có tồn tại ko
            boolean checkDepartment = false;
            for (Department de : context.getDepartments()) {       // O(n)
                if (de.getId() == Integer.parseInt(departmentId)) {
                    department = de;
                    checkDepartment = true;
                    break;
                }
            }
            if (!checkDepartment) {
                errors.add("DepartmentId này không tồn tại");
            }
        }

//        synchronized ()
        // kiêm tra xem day phai la so hay ko
        Position position = null;
        if (!positionId.matches(StringCommon.NUMBER_REGEX)) {
            errors.add("PositionId phải là số");
        } else {
            // kiểm tra xem positionId có tồn tại ko
            boolean checkPosition = false;
            for (Position po : context.getPositions()) {
                if (po.getId() == Integer.parseInt(positionId)) {
                    position = po;
                    checkPosition = true;
                    break;
                }
            }
            if (!checkPosition) {
                errors.add("PositionId này không tồn tại");
            }
        }
        if (errors.isEmpty()) {// nếu ko có 1 loi nao
            Account account = new Account(username, fullName, email, department, position);
            entities.add(account);
            context.getMapByEmail().put(email, account);// thêm cặp key value liên quan đến email
            context.getMapByUsername().put(username, account);// thêm cặp key value liên quan đến username
            // nếu có 2 row trùng username thì map.get(key) != null  -> username này đã tồn tại
        } else {
            lineErrors.add(line + "," + String.join("|", errors));
        }
    }

    @Override
    public void saveAll(List<Account> entities) {
        if (!entities.isEmpty()) {
            repository.createAccounts(entities);// thêm 1 list
        }
    }

    @Override
    public void exportErrors(String header, List<String> lineErrors, String pathErrors) {
        if (!lineErrors.isEmpty()) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(pathErrors));
                bw.write(header + ",error_message");
                bw.newLine();
                for (String ln : lineErrors) {
                    bw.write(ln);
                    bw.newLine();
                }
                bw.flush();
            } catch (Exception e) {
            }
        }
    }
}
