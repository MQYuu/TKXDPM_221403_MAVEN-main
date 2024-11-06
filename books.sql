CREATE DATABASE books;

USE books;

CREATE TABLE books (
    id INT PRIMARY KEY,         -- Mã sách
    publisher VARCHAR(100),      -- Nhà xuất bản
    type VARCHAR(20),            -- Loại sách ("Giáo Khoa" hoặc "Tham Khảo")
    unit_price DECIMAL(10, 2),   -- Đơn giá
    quantity INT,                -- Số lượng
    date_imported DATE,          -- Ngày nhập
    status VARCHAR(50),          -- Tình trạng (chỉ cho sách giáo khoa)
    tax DECIMAL(5, 2)            -- Thuế (chỉ cho sách tham khảo)
);

INSERT INTO books (id, publisher, type, unit_price, quantity, date_imported, status, tax) VALUES
(1, 'NXB Giáo Dục', 'Giáo Khoa', 12000.00, 100, '2023-01-10', 'Mới', NULL),
(2, 'NXB Lao Động', 'Tham Khảo', 35000.00, 50, '2023-02-12', NULL, 10.00),
(3, 'NXB Trẻ', 'Giáo Khoa', 15000.00, 200, '2023-03-05', 'Cũ', NULL),
(4, 'NXB Khoa Học', 'Tham Khảo', 42000.00, 75, '2023-04-08', NULL, 12.00),
(5, 'NXB Giáo Dục', 'Giáo Khoa', 18000.00, 120, '2023-05-20', 'Mới', NULL),
(6, 'NXB Kim Đồng', 'Tham Khảo', 52000.00, 30, '2023-06-15', NULL, 15.00),
(7, 'NXB Văn Học', 'Giáo Khoa', 20000.00, 90, '2023-07-17', 'Mới', NULL),
(8, 'NXB Giáo Dục', 'Tham Khảo', 46000.00, 60, '2023-08-22', NULL, 11.00),
(9, 'NXB Lao Động', 'Giáo Khoa', 16000.00, 110, '2023-09-18', 'Cũ', NULL),
(10, 'NXB Khoa Học', 'Tham Khảo', 38000.00, 40, '2023-10-10', NULL, 8.00);

SELECT * FROM books
