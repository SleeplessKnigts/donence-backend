INSERT INTO cloud_roles (role)
SELECT 'ROLE_ADMIN'
WHERE NOT EXISTS(SELECT * FROM cloud_roles WHERE role = 'ROLE_ADMIN');
INSERT INTO cloud_roles (role)
SELECT 'ROLE_DRIVER'
WHERE NOT EXISTS(SELECT * FROM cloud_roles WHERE role = 'ROLE_DRIVER');
INSERT INTO cloud_roles (role)
SELECT 'ROLE_USER'
WHERE NOT EXISTS(SELECT * FROM cloud_roles WHERE role = 'ROLE_USER');

-- INSERT INTO cloud_users (id, username, email, auth_provider, latitude, longitude, role_id)
-- values (1, 'admin', 'donence.dev@gmail.com', 'google', 32.0, 39.0, 1)