'''
数据库初始化脚本
'''


CREATE TABLE BankUser(
    id VARCHAR(10) KEY,
    name VARCHAR(10),
    password VARCHAR(255) NOT NULL,
    identity_id VARCHAR(12) UNIQUE,
    phone_number VARCHAR(11) UNIQUE,
    gender VARCHAR(1),
    birth_date DATE,
    balance DOUBLE,
)

INSERT INTO BankUser (id,name,password,identity_id,phone_number,gender,birth_date,balance) VALUES
('1010101010','图灵','TuringComplete','001001001001','M','1912-06-23',2000.00),
('1010101011','李四','Java2024','011011011011','F','2004-01-01',5000.23);
