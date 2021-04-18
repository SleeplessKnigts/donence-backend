INSERT INTO cloud_roles (role)
SELECT 'ROLE_ADMIN'
WHERE NOT EXISTS(SELECT * FROM cloud_roles WHERE role = 'ROLE_ADMIN');
INSERT INTO cloud_roles (role)
SELECT 'ROLE_DRIVER'
WHERE NOT EXISTS(SELECT * FROM cloud_roles WHERE role = 'ROLE_DRIVER');
INSERT INTO cloud_roles (role)
SELECT 'ROLE_USER'
WHERE NOT EXISTS(SELECT * FROM cloud_roles WHERE role = 'ROLE_USER');

update 'cloud_users'
set role_id=1
where id=101;

update 'cloud_users'
set latitude=39,longitude=32
where id=102;