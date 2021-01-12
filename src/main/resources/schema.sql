CREATE TABLE IF NOT EXISTS users
(
    username text UNIQUE,
    email    text NOT NULL UNIQUE,
    id       int primary key,
    auth_provider text NOT NULL,
    image_url text,
    fName text
);