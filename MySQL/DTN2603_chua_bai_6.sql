DELIMITER $$
CREATE PROCEDURE question1 (IN v1 INT, OUT o1 INT)
	BEGIN
		-- logic xu ly bai toan
    END $$
DELIMITER ;

CALL question1;

-- b1: xử lý yêu cầu bên trong procedure
-- b2; lắp vào công thức tạo procedure 
-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó.
-- IN: tên phòng ban
	-- in ra tất cả các account thuộc phòng ban alter
    select *
from department dep
inner join account acc on dep.department_id = acc.department_id
where dep.department_name like dep_name;
    
DELIMITER $$
CREATE PROCEDURE question1 (IN dep_name VARCHAR(100))
	BEGIN
		-- logic xu ly bai toan
        select *
		from department dep
		inner join account acc on dep.department_id = acc.department_id
		where dep.department_name like dep_name;
    END $$
DELIMITER ;

CALL question1('SALE');
CALL question1('Tin học');

-- Question 2: Tạo store để in ra số lượng account trong mỗi group.
-- group_name		s0_luong
--  alter			1
--  B				2
--  ca				4

select g.group_name, count(ga.group_id) as so_luong
from `group` g
left join group_account ga on g.group_id = ga.group_id
group by g.group_id;

DELIMITER $$
CREATE PROCEDURE question2 ()
	BEGIN
		-- logic xu ly bai toan
        select g.group_name, count(ga.group_id) as so_luong
		from `group` g
		left join group_account ga on g.group_id = ga.group_id
		group by g.group_id;
    END $$
DELIMITER ;

CALL question2();

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại.
SELECT  t.type_id, t.type_name, COUNT(q.question_id) AS question_count
    FROM type_question t
    LEFT JOIN question q ON t.type_id = q.type_id
							AND MONTH(q.create_date) = MONTH(CURDATE())
							AND YEAR(q.create_date) = YEAR(CURDATE())
    GROUP BY t.type_id;
DELIMITER $$
CREATE PROCEDURE question3 ()
	BEGIN
		-- logic xu ly bai toan
        SELECT  t.type_id, t.type_name, COUNT(q.question_id) AS question_count
		FROM type_question t
		LEFT JOIN question q ON t.type_id = q.type_id
								AND MONTH(q.create_date) = MONTH(CURDATE())
								AND YEAR(q.create_date) = YEAR(CURDATE())
		GROUP BY t.type_id;
    END $$
DELIMITER ;

call question3();


-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất.
SELECT count(q.type_id) as so_luong
FROM type_question t
LEFT JOIN question q ON t.type_id = q.type_id
GROUP BY t.type_id 
order by so_luong desc 
limit 1;

SELECT t.*,  count(q.type_id) as so_luong
FROM type_question t
LEFT JOIN question q ON t.type_id = q.type_id
GROUP BY t.type_id 
HAVING so_luong = (SELECT count(q.type_id) as so_luong
					FROM type_question t
					LEFT JOIN question q ON t.type_id = q.type_id
					GROUP BY t.type_id 
					order by so_luong desc 
					limit 1);

DELIMITER $$
CREATE PROCEDURE question4 ()
	BEGIN
		-- logic xu ly bai toan
        SELECT t.*,  count(q.type_id) as so_luong
		FROM type_question t
		LEFT JOIN question q ON t.type_id = q.type_id
		GROUP BY t.type_id 
		HAVING so_luong = (SELECT count(q.type_id) as so_luong
							FROM type_question t
							LEFT JOIN question q ON t.type_id = q.type_id
							GROUP BY t.type_id 
							order by so_luong desc 
							limit 1);
    END $$
DELIMITER ;

CALL question4();

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question.
-- question4(OUT id của câu hỏi max);
-- viết procedure để tìm tên theo id trả ra ở trên

-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi 
-- và trả về group có tên chứa chuỗi của người dùng nhập vào 
-- hoặc trả về user có username chứa chuỗi của người dùng nhập vào.
DELIMITER $$
CREATE PROCEDURE question6 (IN v_name VARCHAR(100))
	BEGIN
		-- logic xu ly bai toan
		select group_id, group_name 
		from `group`
		where group_name like v_name
		union all
		select account_id, username 
		from account
		where username like v_name;
    END $$
DELIMITER ;

CALL question6('%b%');

-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:
-- username sẽ giống email nhưng bỏ phần @..mail đi
-- positionID: sẽ có default là developer
-- departmentID: sẽ được cho vào 1 phòng chờ
-- Sau đó in ra kết quả tạo thành công

select substring_index("nguyenvana@gmail@com", "@", 1);
select department_id from department where department_name like 'Phòng chờ';
select position_id from `position` where position_name like 'DEV';


drop procedure question7;
DELIMITER $$
CREATE PROCEDURE question7 (IN v_full_name VARCHAR(100), IN v_email VARCHAR(100))
	BEGIN
		-- logic xu ly bai toan
		declare v_username VARCHAR(100);
        declare v_department_id INT;
        declare v_position_id INT;
        
        SET v_username = substring_index(v_email, "@", 1);
        select position_id into v_position_id
			from `position` where position_name like 'DEV';
		-- nếu chưa tồn tại phòng chờ thì thêm
		insert into department(department_name) 
        select 'Phòng chờ'
        where not exists (
			select 1 from department where department_name like 'Phòng chờ'
        );
        
		select department_id into v_department_id
			from department where department_name like 'Phòng chờ';
        
        INSERT INTO account(email, username, fullname, department_id, position_id)
			values (v_email, v_username, v_full_name, v_department_id, v_position_id);
		SELECT "Tạo thành công";
    END $$
DELIMITER ;

CALL question7('fullname2', 'email2@gmail.com');


select * from account;


insert into department(department_name) 
select 'Phòng chờ 1234'
where not exists (
	select 1 from department where department_name like 'Phòng chờ 1234'
);



select * from department;




-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice
-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất


-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID
-- trước khi xóa exam thì xóa các exam_id ở các bảng liên quan 
DELIMITER $$
CREATE PROCEDURE question9 (IN v_exam_id INT)
	BEGIN
		-- logic xu ly bai toan
		DELETE from exam_question where exam_id = v_exam_id;
        DELETE from exam where exam_id = v_exam_id;
    END $$
DELIMITER ;

CALL question9(1);
select * from exam;
select * from exam_question;

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử
-- dụng store ở câu 9 để xóa)
-- Sau đó in số lượng record đã remove từ các table liên quan trong khi
-- removing


select date_sub(now(), interval 3 year);

-- đếm số exam bị xóa
select * from exam where create_date < date_sub(now(), interval 3 year);
-- đếm số exam_question liên quan bị xóa
select count(1)
from exam e
join exam_question eq on e.exam_id = eq.exam_id
where e.create_date < date_sub(now(), interval 3 year);

DROP PROCEDURE question10;

DELIMITER $$
CREATE PROCEDURE question10 ()
	BEGIN
		-- logic xu ly bai toan
		declare count_exam_delete int;
        declare count_exam_question_delete int;
        declare v_exam_id int;
        declare done int default 0;
        
        declare curs cursor for -- ds các exam_id cần xóa
			select exam_id from exam where create_date < date_sub(now(), interval 3 year);
		declare continue handler for not found set done = 1;
        
        -- đếm số exam bị xóa
		select count(1) into count_exam_delete from exam where create_date < date_sub(now(), interval 3 year);
		-- đếm số exam_question bị xóa
        select count(1) into count_exam_question_delete
		from exam e
		join exam_question eq on e.exam_id = eq.exam_id
		where e.create_date < date_sub(now(), interval 3 year);
        
		OPEN curs; -- 2 hàng   1  2  
        read_loop: LOOP
			fetch curs into v_exam_id;
            if done then 
				leave read_loop;
            end if;
			CALL question9(v_exam_id);
        END LOOP;
        CLOSE curs;
        
        SELECT concat("Đã xóa ", count_exam_delete, " exam");
        SELECT concat("Đã xóa ", count_exam_question_delete, " exam_question");
    END $$
DELIMITER ;

CALL question10();



-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng
-- nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được
-- chuyển về phòng ban default là phòng ban chờ việc

-- tìm id của phòng chờ viec = x
-- update các account liên quan department_id = x
-- xóa phòng


-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
with cte_12_month as (
	select 1 as month union
    select 2 union
    select 3 union
    select 4 union
    select 5 union
    select 6 union
    select 7 union
    select 8 union
    select 9 union
    select 10 union
    select 11 union
    select 12
)
select c.month,  count(month(q.create_date)) as so_luong
from cte_12_month c
left join question q on c.month = month(create_date) and year(create_date) = year(now())
group by c.month ;


-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng")
with cte_6_month as (
	select month(now()) as month, year(now()) as year union 
    select month(date_sub(now(), interval 1 month )) as month, year(date_sub(now(), interval 1 month )) as year union 
    select month(date_sub(now(), interval 2 month )) as month, year(date_sub(now(), interval 2 month )) as year union
    select month(date_sub(now(), interval 3 month )) as month, year(date_sub(now(), interval 3 month )) as year union 
    select month(date_sub(now(), interval 4 month )) as month, year(date_sub(now(), interval 4 month )) as year union 
    select month(date_sub(now(), interval 5 month )) as month, year(date_sub(now(), interval 5 month )) as year  
) 
select  c.month , count(q.question_id) as so_luong
from cte_6_month c
left join question q on c.month = month(create_date) 
and year(create_date) = year(now()) and create_date > date_sub(now(), interval 6 month)
group by c.month;
