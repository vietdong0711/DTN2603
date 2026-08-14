
-- tạo Database có tên dtn2603_testing_system
CREATE DATABASE IF NOT EXISTS dtn2603_testing_system;
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

drop table if exists `account`;
CREATE TABLE `account`(
	account_id 		INT PRIMARY KEY AUTO_INCREMENT, -- auto_increment: giá trị tự tăng
    email 			VARCHAR(100) UNIQUE not null,
    username 		VARCHAR(100) UNIQUE ,
    full_name 		VARCHAR(100) default 'no_name',
    department_id 	INT,
    position_id 	INT,
    exp_in_year 	INT, -- số năm kinh nghiệm
    create_date 	DATETIME default CURRENT_TIMESTAMP, -- 2026-01-01
    CONSTRAINT fk_account_department FOREIGN KEY (department_id) REFERENCES department(department_id),
    CONSTRAINT fk_account_position FOREIGN KEY (position_id) REFERENCES `position`(position_id),
	CONSTRAINT chk_exp_in_year CHECK (exp_in_year > 0)
);


insert into `account`(email, username, full_name, exp_in_year)
	values ("user2@gmail.com", 'user2', 'user2', 1)
			
    



--  Query Data Statement: dùng để xem dữ liệu và ko làm thay đổi dữ liệu
select * from account; -- dùng để xem dữ liệu của bảng account
-- * xem tất cả các cột
-- xem 1 số cột, chứ ko xem tất cả 
select account_id, email, username from account; -- dùng để xem dữ liệu của bảng account

-- xem theo điều kiện
	-- tìm các account có số năm kinh nghiệm > 3 và nhỏ hơn 6 năm kinh nghiệm
select * from account where exp_in_year > 3 and exp_in_year < 6;
	-- tìm các account có số năm kinh nghiệm < 3 hoặc lớn hơn 6 năm kinh nghiệm
select * from account where exp_in_year < 3 or exp_in_year > 6;

-- dkien1 AND dkien2:  bắt buộc phải thõa mãn 2 ddieuf kiện
-- dkien1 OR dkien2: 	thõa mãn 1 trong 2 dkien là dc
-- dkien1 AND dkien2 AND dkien3 : thõa mãn cả 3  dkien 
-- dkien1 OR dkien2 OR dkien3 : thõa mãn 1 trong 3 là dc

-- lấy ra các account có số năm kinh nghiệm >=1  và <=4
select * from account where exp_in_year >= 1 and exp_in_year <=4;
select * from account where exp_in_year BETWEEN 1 AND 4;-- lấy gia trị trong khoảng(áp dụng với số)

-- tìm ra các account có số năm kinh nghiệm = 1 hoặc = 3 hoặc =5 hoặc =7 .....
select * from account where exp_in_year =1 OR exp_in_year =3 or exp_in_year = 5 or exp_in_year = 7;
select * from account where exp_in_year in (1, 3, 5, 7);
select * from account where exp_in_year not in (1, 3, 5, 7);
-- IN lấy các gtri nằm trong ds
-- NOT IN   ko lấy các gtri nằm trong ds

-- tìm các account có tên là "Nguyễn Văn An"
select * from account where full_name like "%An";
select * from account where full_name like "An%";
select * from account where full_name like "%An%";

select * from account where full_name not like "%An%";

select * from account where full_name like "___________An";
-- tìm kiếm text gần đúng
	-- %   có thể đại diện cho 0 1  n kí tự 
	--  '%an'  tìm các tên có kết thúc là an
	--  'an%'  tìm các tên có bắt đầu là an
    --  '%an%' tìm các tên có chứa chữ an
	-- _   mỗi dấu _ thì diện cho 1 kí tự

-- tìm ra các account chưa có tên
select * from account where full_name is null;

-- tìm các account đã có tên
select * from account where full_name is not null;
-- null ko phải là giá trị, null là trạng thái của 1 cột của 1 row nên kiểm tra có null hay ko
		-- thì dùng is chứ ko dùng =
-- tìm các account có 1 năm kinh nghiệm
select * from account where exp_in_year = 1;
-- tìm các account có khác 1 năm kinh nghiệm
select * from account where exp_in_year <> 1;
select * from account where exp_in_year != 1;


-- liệt kê các năm kinh nghiệm của account
select distinct exp_in_year from account order by exp_in_year;

-- đếm số lượng account trong hệ thống
select count(*) from account;
select count(account_id) from account;
select count(1) from account;

-- tính tổng số năm kinh nghiệm của account trong hệ thống
select max(exp_in_year) from account;

-- hiển thi ds 5 account có số năm kinh nghiệm nhiều nhất
select exp_in_year as 'Số năm kinh nghiệm' from account order by exp_in_year desc limit 5 ;

-- đếm xem với mỗi mức kinh nghiệm thì có bao nhiêu người?
-- b1: xác định các thông tin cần lấy ở bảng nào?  bảng account
-- b2: xác định được yêu cầu? đếm mỗi loại kinh nghiệm có bao nhiêu người
-- nhóm các account có cùng kinh nghiệm lại 1 nhóm
select exp_in_year, count(1)
from account
group by exp_in_year;

-- tìm các mức kinh nghiệm có lớn hơn 1 nhân viên
select exp_in_year,  count(1)
from account
-- where count(1) > 1
group by exp_in_year
having count(1) > 1;

-- đếm xem mỗi id phòng ban có bao nhiêu nhân viên
select department_id, count(1) 
from account
group by department_id;

-- 








--  Modify Data Statement: dùng để thay đổi dữ liệu(thêm, xóa, update)




