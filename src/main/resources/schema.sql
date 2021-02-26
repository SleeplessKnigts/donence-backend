CREATE TABLE IF NOT EXISTS cloud_users
(
    id             INT PRIMARY KEY,
    username       TEXT UNIQUE,
    email          TEXT NOT NULL UNIQUE,
    auth_provider  TEXT NOT NULL,
    image_url      TEXT,
    f_name         TEXT,
    latitude       FLOAT,
    longitude      FLOAT,
    address_detail TEXT
);

CREATE TABLE IF NOT EXISTS "cloud_roles"
(
    role_id SERIAL PRIMARY KEY,
    role    VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "recycle_points"
(
    recycle_point_id     SERIAL PRIMARY KEY,
    recycle_point_detail TEXT NOT NULL,
    geolocation          POINT
);

CREATE TABLE IF NOT EXISTS "news"
(
    news_id   SERIAL PRIMARY KEY,
    heading   TEXT    NOT NULL,
    published BOOLEAN NOT NULL DEFAULT FALSE,
    content   TEXT    NOT NULL
);

CREATE TABLE IF NOT EXISTS "recycling_numbers"
(
    recycling_numbers_id SERIAL PRIMARY KEY,
    glass                INT DEFAULT 0,
    paper                INT DEFAULT 0,
    cardboard            INT DEFAULT 0,
    metal                INT DEFAULT 0,
    plastic              INT DEFAULT 0,
    battery              INT DEFAULT 0,
    user_id              INT NOT NULL REFERENCES cloud_users (id)
);

CREATE TABLE IF NOT EXISTS "collection_events"
(
    collection_events_id SERIAL PRIMARY KEY,
    material_type        TEXT                        NOT NULL,
    event_detail         TEXT                        NOT NULL,
    geolocation          POINT,
    event_date           TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS "requests"
(
    request_id   SERIAL PRIMARY KEY,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    request_type TEXT NOT NULL,
    issuer INT NOT NULL REFERENCES cloud_users (id)
);