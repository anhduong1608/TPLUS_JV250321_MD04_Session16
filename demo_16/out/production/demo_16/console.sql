CREATE DATABASE IF NOT EXISTS BookManagement;
USE BookManagement;

CREATE TABLE IF NOT EXISTS Book_Type (
                                         Type_id INT AUTO_INCREMENT PRIMARY KEY,
                                         Type_name VARCHAR(100) NOT NULL UNIQUE,
    Type_description TEXT NOT NULL,
    Type_status BIT DEFAULT 1
    );

CREATE TABLE IF NOT EXISTS Book (
                                    Book_id CHAR(5) PRIMARY KEY,
    Book_name VARCHAR(100) NOT NULL UNIQUE,
    Book_title VARCHAR(200) NOT NULL,
    Book_pages INT NOT NULL CHECK (Book_pages > 0),
    Book_author VARCHAR(200) NOT NULL,
    Book_price FLOAT NOT NULL CHECK (Book_price > 0),
    Type_id INT NOT NULL,
    Book_status BIT DEFAULT 1,
    FOREIGN KEY (Type_id) REFERENCES Book_Type(Type_id)
    );
-- Book_Type - Loại sách
-- Lấy tất cả thông tin loại sách
DELIMITER $$
CREATE PROCEDURE get_all_book_types()
BEGIN
SELECT * FROM Book_Type;
END $$
DELIMITER ;
-- Kiểm tra sự tồn tại của tên loại sách
DELIMITER $$
CREATE PROCEDURE is_book_type_exists(IN name VARCHAR(100))
BEGIN
SELECT COUNT(*) FROM Book_Type WHERE Type_name = name;
END $$
DELIMITER ;

-- Thêm mới loại sách
DELIMITER $$
CREATE PROCEDURE insert_book_type(IN name VARCHAR(100), IN tdesc TEXT)
BEGIN
INSERT INTO Book_Type(Type_name, Type_description)
VALUES (name, tdesc);
END $$
DELIMITER ;

-- Lấy thông tin loại sách theo mã loại sách
DELIMITER $$
CREATE PROCEDURE get_book_type_by_id(IN tid INT)
BEGIN
SELECT * FROM Book_Type WHERE Type_id = tid;
END $$
DELIMITER ;

-- Cập nhật loại sách
DELIMITER $$
CREATE PROCEDURE update_book_type(
    IN tid INT,
    IN tname VARCHAR(100),
    IN tdesc TEXT,
    IN tstatus BIT
)
BEGIN
UPDATE Book_Type
SET
    Type_name = tname,
    Type_description = tdesc,
    Type_status = tstatus
WHERE Type_id = tid;
END $$
DELIMITER ;

-- Xóa loại sách (Xóa khi loại sách chưa chứa sách, khi đã chứa
-- sách chỉ cập nhật trạng thái loại sách là 0)
DELIMITER $$
CREATE PROCEDURE delete_book_type(IN tid INT)
BEGIN
    IF EXISTS (SELECT 1 FROM Book WHERE Type_id = tid) THEN
UPDATE Book_Type
SET Type_status = 0
WHERE Type_id = tid;
ELSE
DELETE FROM Book_Type
WHERE Type_id = tid;
END IF;
END $$
DELIMITER ;
-- Book - Sách
-- Lấy tất cả thông tin sách sắp xếp theo giá sách tăng dần (gồm cả mã loại sách và tên loại sách)
DELIMITER $$
CREATE PROCEDURE get_all_books()
BEGIN
SELECT b.Book_id, b.Book_name, b.Book_title, b.Book_pages, b.Book_author,
       b.Book_price, b.Type_id, bt.Type_name, b.Book_status
FROM Book b
         JOIN Book_Type bt ON b.Type_id = bt.Type_id
ORDER BY b.Book_price ASC;
END $$
DELIMITER ;

-- Kiểm tra sự tồn tại của tên sách
DELIMITER $$
CREATE PROCEDURE is_book_name_exists(IN bname VARCHAR(100))
BEGIN
SELECT COUNT(*) AS total
FROM Book
WHERE Book_name = bname;
END $$
DELIMITER ;

-- Thêm mới sách
DELIMITER $$
CREATE PROCEDURE insert_book(
    IN bid CHAR(5),
    IN bname VARCHAR(100),
    IN btitle VARCHAR(200),
    IN bpages INT,
    IN bauthor VARCHAR(200),
    IN bprice FLOAT,
    IN tid INT
)
BEGIN
INSERT INTO Book(Book_id, Book_name, Book_title, Book_pages, Book_author, Book_price, Type_id)
VALUES (bid, bname, btitle, bpages, bauthor, bprice, tid);
END $$
DELIMITER ;

-- Lấy thông tin sách theo mã sách
DELIMITER $$
CREATE PROCEDURE get_book_by_id(IN bid CHAR(5))
BEGIN
SELECT b.Book_id, b.Book_name, b.Book_title, b.Book_pages, b.Book_author,
       b.Book_price, b.Type_id, bt.Type_name, b.Book_status
FROM Book b
         JOIN Book_Type bt ON b.Type_id = bt.Type_id
WHERE b.Book_id = bid;
END $$
DELIMITER ;

-- Cập nhật sách
DELIMITER $$
CREATE PROCEDURE update_book(
    IN bid CHAR(5),
    IN bname VARCHAR(100),
    IN btitle VARCHAR(200),
    IN bpages INT,
    IN bauthor VARCHAR(200),
    IN bprice FLOAT,
    IN tid INT,
    IN bstatus BIT
)
BEGIN
UPDATE Book
SET Book_name = bname,
    Book_title = btitle,
    Book_pages = bpages,
    Book_author = bauthor,
    Book_price = bprice,
    Type_id = tid,
    Book_status = bstatus
WHERE Book_id = bid;
END $$
DELIMITER ;

-- Xóa sách
DELIMITER $$
CREATE PROCEDURE delete_book(IN bid CHAR(5))
BEGIN
DELETE FROM Book
WHERE Book_id = bid;
END $$
DELIMITER ;

-- Tìm kiếm sách theo tên sách (tìm tương đối)
DELIMITER $$
CREATE PROCEDURE search_book_by_name(IN keyword VARCHAR(100))
BEGIN
SELECT b.Book_id, b.Book_name, b.Book_title, b.Book_pages, b.Book_author,
       b.Book_price, b.Type_id, bt.Type_name, b.Book_status
FROM Book b
         JOIN Book_Type bt ON b.Type_id = bt.Type_id
WHERE b.Book_name LIKE CONCAT('%', keyword, '%');
END $$
DELIMITER ;

-- Thống kê số lượng sách theo tác giả
DELIMITER $$
CREATE PROCEDURE count_books_by_author()
BEGIN
SELECT Book_author, COUNT(*) AS total_books
FROM Book
GROUP BY Book_author;
END $$
DELIMITER ;

SELECT * FROM Book_Type;
SELECT * FROM Book;
