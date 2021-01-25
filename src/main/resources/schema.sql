create table if not exists cloud_users
(
    username      text unique,
    email         text not null unique,
    id            int primary key,
    auth_provider text not null,
    image_url     text,
    f_name        text
);

create table if not exists "cloud_roles"
(
    role_id serial primary key,
    role    varchar(50) not null unique
);

create table if not exists "recycle_point"
(
    recycle_point_id   serial primary key,
    recycle_point_name text not null,
    geolocation        point
);
