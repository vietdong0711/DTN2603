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

SET @username = 'demo';

select @username;


create table account
(
    account_id    int auto_increment
        primary key,
    email         varchar(100)                        not null unique,
    username      varchar(100)                        not null unique,
    fullname      varchar(100)                        null,
    department_id int                                 null,
    position_id   int                                 null,
    create_date   timestamp default CURRENT_TIMESTAMP null,
    constraint fk_account_department
        foreign key (department_id) references department (department_id) on delete cascade,
    constraint fk_account_position
        foreign key (position_id) references position (position_id)
);
-- username ko dc null, ko dc trùng nhau, ko dc chứa ít hơn 8 kí tự và ko dc nhiefu hơn 100 kí tự
-- ngày sinh:  ko dc nhỏ hơn 1-1-1900

-- khi thêm mới account, username phải lớn hơn 8 và nhỏ hơn 100 kí tự, nếu ko thõa mãn thì báo lỗi "username phải lớn hơn 8 và nhỏ hơn 100 kí tự"

-- viết trigger
	-- 1 ..hành động của bài toán này là gì? thêm, sửa, xóa:  				Thêm mới
    -- 2 ..hành này liên quan đến bảng nào?  								account
    -- 3 ..trigger sẽ dc thực hiện trước hay sau hành động ở câu hỏi 1		trigger thực thi trước khi thêm mới account
    -- 4 ..dữ liệu đề bài cho là dữ liệu cũ hay mới? phải kiểm tra username    		
		-- NEW: dữ đó trong DB chưa có
        -- OLD: trong DB có rồi 
        -- VD: bạn Huy đang dùng ip16prm  thì   ip16prm này sẽ dc coi là dữ liệu cũ
			-- bạn Huy đang muốn mua ip17ptm  	ip17prm này sẽ dc coi là dữ liệu mới 
    -- XÁC ĐỊNH DỮ LIỆU NEW HAY OLD\
    -- insert   new
    -- delete 	old
    -- update 		update tên_table SET columnA =  ?1  , columnB = ?2  where id = ?3
							-- ?1 ?2  new 				?3 old
		
-- khi thêm mới account, username phải lớn hơn 8 và nhỏ hơn 100 kí tự, nếu ko thõa mãn thì báo lỗi "username phải lớn hơn 8 và nhỏ hơn 100 kí tự"
-- insert       account			before				usernam: new

DELIMITER $$
create trigger trg_1
BEFORE INSERT ON account
FOR EACH ROW
	BEGIN
		-- logic xử lý
        IF length(NEW.username) < 8 OR length(NEW.username) > 100 THEN
			signal sqlstate '12345'
			set message_text = 'username phải lớn hơn 8 và nhỏ hơn 100 kí tự';
        END IF;
	END $$
DELIMITER ;



insert into account(email, username, fullname, department_id, position_id) 
values			('user12@gmail.com', 'abcd', 'abc', 1, 1),		
				('user12@gmail.com', 'abcd', 'abc', 1, 1),
                ('user12@gmail.com', 'abcd', 'abc', 1, 1),
                ('user12@gmail.com', 'abcd', 'abc', 1, 1),
                ('user12@gmail.com', 'abcd', 'abc', 1, 1),
                ('user12@gmail.com', 'abcd', 'abc', 1, 1),
                ('user12@gmail.com', 'abcd', 'abc', 1, 1),
                ('user12@gmail.com', 'abcd', 'abc', 1, 1);



SELECT * FROM dtn2603_testing_system.account;


-- KO DC DÙNG ON DELETE CASCADE, trước khi xóa department theo department_id thì phải xóa các account liên quan đến department đó trước
-- delete 		bảng department        before
-- department_id: OLD.
-- trước khi xóa department thì phải xóa account

DELIMITER $$
create trigger trg_2
BEFORE delete ON department
FOR EACH ROW
	BEGIN
		-- logic xử lý xóa account liên quan
        DELETE from account where department_id = OLD.department_id;
	END $$
DELIMITER ;

delete from department where department_id = 11;


-- after
-- sau khi thêm mới 1 account thì sẽ tăng số lượng quantity_account ở bảng department tương ứng lên 1  theo department_id 
																					-- insert ở bảng account

-- after 		update		department		OLD.quantity_account		A
-- after		insert		account			OLD.quantity_account		B
-- after		insert		account			NEW.quantity_account		C
-- after		insert		account			NEW.department_id			D

-- after 		insert 		account			do insert nên toàn bộ biến new.department   
	
 DELIMITER $$
create trigger trg_10
after insert ON account
FOR EACH ROW
	BEGIN
		-- logic xử lý tăng quantity_account
        declare v_quantity_account int;
        -- tìm ra quantity_account hiện tại
        select quantity_account into v_quantity_account 
        from department 
        where department_id = new.department_id;
        -- tăng số lượng lên 1
        update department SET quantity_account = (v_quantity_account + 1) 
        where department_id = new.department_id;
        
	END $$
DELIMITER ;   

select * from department where quantity_account = 1;-- 3

insert into account(email, username, fullname, department_id, position_id) 
values			('dongnv@gmail.com', 'dongnv123', 'abc', 1, 1);

-- cách xác định các thành phần chính của trigger
-- trwosc chữ "thì", "nếu" dùng để xác định thành phàn chính
-- sau chữ "thì" thì sẽ đưa vào logic trong begin và end


-- thêm 1 cột quantity_account vào bảng department
-- c1: thao tác trên giao diện
-- c2: alter table
alter table department 
	add column quantity_account INT; 
    
select dep.*, count(acc.department_id) as sl
from department dep 
left join account acc on dep.department_id = acc.department_id
group by dep.department_id;

update department d
left join (select dep.*, count(acc.department_id) as sl
			from department dep 
			left join account acc on dep.department_id = acc.department_id
			group by dep.department_id) t on d.department_id = t.department_id
SET d.quantity_account = sl;

SELECT * FROM dtn2603_testing_system.department;
