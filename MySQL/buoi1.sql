
-- tạo Database có tên dtn2603_testing_system
CREATE DATABASE dtn2603_testing_system;
-- sử dụng DATABASE dtn2603_testing_system
use dtn2603_testing_system;

-- Table 1:Department
-- ∙ DepartmentID: định danh của phòng ban (auto increment)
-- ∙ DepartmentName: tên đầy đủ của phòng ban (VD: sale, marketing, …)
CREATE TABLE department(
	department_id 		INT PRIMARY KEY AUTO_INCREMENT, -- auto_increment: giá trị tự tăng
    department_name 	VARCHAR(100)
);

CREATE TABLE `position`(
	position_id 		INT PRIMARY KEY AUTO_INCREMENT, -- auto_increment: giá trị tự tăng
    position_name 		ENUM('DEV', 'TEST', 'SCRUM_MASTER', 'PM')
);

CREATE TABLE `account`(
	account_id 		INT PRIMARY KEY AUTO_INCREMENT, -- auto_increment: giá trị tự tăng
    email 			VARCHAR(100) UNIQUE,
    username 		VARCHAR(100) UNIQUE,
    full_name 		VARCHAR(100),
    department_id 	INT,
    position_id 	INT,
    create_date 	DATETIME,
    CONSTRAINT fk_account_department FOREIGN KEY (department_id) REFERENCES department(department_id),
    CONSTRAINT fk_account_position FOREIGN KEY (position_id) REFERENCES `position`(position_id)
);


