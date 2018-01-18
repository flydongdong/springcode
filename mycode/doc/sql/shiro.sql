drop table if exists sys_users;
drop table if exists sys_roles;
drop table if exists sys_permissions;
drop table if exists sys_users_roles;
drop table if exists sys_roles_permissions;
DROP TABLE IF EXISTS t_base_person;

create table sys_users (
  id bigint auto_increment,
  username varchar(100),
  realname varchar(100),
  password varchar(100),
  salt varchar(100),
  locked bool default false,
  constraint pk_sys_users primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_users_username on sys_users(username);

create table sys_roles (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_sys_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_roles_role on sys_roles(role);

create table sys_permissions (
  id bigint auto_increment,
  permission varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_sys_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_permissions_permission on sys_permissions(permission);

create table sys_users_roles (
  user_id bigint,
  role_id bigint,
  constraint pk_sys_users_roles primary key(user_id, role_id)
) charset=utf8 ENGINE=InnoDB;

create table sys_roles_permissions (
  role_id bigint,
  permission_id bigint,
  constraint pk_sys_roles_permissions primary key(role_id, permission_id)
) charset=utf8 ENGINE=InnoDB;

DELETE from sys_users where id in(1,2,3);
INSERT INTO sys_users(ID,USERNAME,PASSWORD,REALNAME,SALT,LOCKED) VALUES(1,'admin','123456','超级管理员','',0);
INSERT INTO sys_users(ID,USERNAME,PASSWORD,REALNAME,SALT,LOCKED) VALUES(2,'zhuyd','123456','冬冬管理员','',0);
INSERT INTO sys_users(ID,USERNAME,PASSWORD,REALNAME,SALT,LOCKED) VALUES(3,'fly','123456','fly先生','',0);

DELETE from sys_roles where id in(1,2,3);
INSERT INTO sys_roles(ID,ROLE,description,available) VALUES(1,'ADMIN','超级管理员',0);
INSERT INTO sys_roles(ID,ROLE,description,available) VALUES(2,'ADMIN_CMS','后台超级管理员',0);
INSERT INTO sys_roles(ID,ROLE,description,available) VALUES(3,'ADMIN_TOP','顶级管理员',0);

DELETE from sys_permissions where id in(1,2);
INSERT INTO sys_permissions(ID,permission,description,available) VALUES(1,'SYSTEM_ALL','系统管理',0);
INSERT INTO sys_permissions(ID,permission,description,available) VALUES(2,'ADMIN','后台管理',0);

DELETE from sys_roles_permissions where role_id in(1,2,3);
INSERT INTO sys_roles_permissions(role_id,permission_id) VALUES(1,1);
INSERT INTO sys_roles_permissions(role_id,permission_id) VALUES(1,2);
INSERT INTO sys_roles_permissions(role_id,permission_id) VALUES(2,1);
INSERT INTO sys_roles_permissions(role_id,permission_id) VALUES(2,2);
INSERT INTO sys_roles_permissions(role_id,permission_id) VALUES(3,1);
INSERT INTO sys_roles_permissions(role_id,permission_id) VALUES(3,2);

DELETE from sys_users_roles where user_id in(1,2,3);
INSERT INTO sys_users_roles(user_id,role_id) VALUES(1,1);
INSERT INTO sys_users_roles(user_id,role_id) VALUES(2,2);
INSERT INTO sys_users_roles(user_id,role_id) VALUES(3,3);


CREATE TABLE `t_base_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `age` tinyint(3) NOT NULL DEFAULT '0',
  `max_power` int(11) DEFAULT '0',
  `max_it` int(11) DEFAULT '0',
  `max_HP` bigint(12) DEFAULT '0',
  `max_MP` bigint(12) DEFAULT '0',
  `exp` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
