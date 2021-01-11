CREATE TABLE IF NOT EXISTS users
(
    username varchar(100) NOT NULL UNIQUE,
    email    varchar(100) NOT NULL UNIQUE,
    id       int primary key
);