insert into cloud_roles (role)
select 'ROLE_ADMIN' where not exists(select * from cloud_roles where role='ROLE_ADMIN');
insert into cloud_roles (role)
select 'ROLE_DRIVER' where not exists(select * from cloud_roles where role='ROLE_DRIVER');
insert into cloud_roles (role)
select 'ROLE_USER' where not exists(select * from cloud_roles where role='ROLE_USER');