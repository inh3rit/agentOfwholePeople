-- drop table
DROP TABLE IF EXISTS `role_interface`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `interface`;
DROP TABLE IF EXISTS `navigation`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `agent`;
DROP TABLE IF EXISTS `credit_card`;
DROP TABLE IF EXISTS `customer`;

-- create table
-- 用户账号表
CREATE TABLE IF NOT EXISTS `user`(
  `id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(128) NOT NULL COMMENT '用户名',
  `password` CHAR(128) NOT NULL DEFAULT '6443a391de091eb7dc939df60b89ab637524f0e1'
      COMMENT '密码hash值，生成算法：sha1(sha1(MD5(pwd) + salt))，其中pwd是用户输入的字符串值，默认是123456, salt 固定为w9h@f4p3，sha1(MD5(pwd) + salt)由前端会给后端',
  `status` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号状态，0：正常，1：禁用',
  `ctime` DATETIME DEFAULT NOW() COMMENT '记录添加时间',
  UNIQUE KEY (`username`),
  KEY (`status`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;

-- 角色表
CREATE TABLE IF NOT EXISTS `role` (
  `id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL COMMENT '用户角色名',
  `des` TEXT COMMENT '描述',
  `status` TINYINT UNSIGNED DEFAULT 0 COMMENT '状态，0:启用，1：禁用',
  `ctime` DATETIME DEFAULT NOW() COMMENT '记录添加时间',
  UNIQUE KEY (`name`),
  KEY (`status`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;

-- 用户-角色表
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `uid` INT(11) UNSIGNED NOT NULL COMMENT '用户id',
  `rid` INT(11) UNSIGNED NOT NULL COMMENT '角色id',
  `status` TINYINT UNSIGNED DEFAULT 0 COMMENT '状态，0:启用，1：禁用',
  `ctime` DATETIME DEFAULT NOW() COMMENT '记录添加时间',
  UNIQUE KEY (`uid`, `rid`),
  KEY (`status`),
  KEY `fk_user_role_uid` (`uid`),
  KEY `fk_user_role_rid` (`rid`),
  CONSTRAINT `fk_user_role_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_role_rid` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;

-- 导航栏表，父级菜单
CREATE TABLE IF NOT EXISTS `navigation` (
  `id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL COMMENT '导航栏名称',
  `status` TINYINT UNSIGNED DEFAULT 0 COMMENT '状态，0：显示，1：不显示',
  `order_num` INT UNSIGNED DEFAULT 0 COMMENT '排序数字，越大越前面',
  `ctime` DATETIME DEFAULT NOW() COMMENT '记录添加时间',
  UNIQUE KEY (`name`),
  KEY (`status`),
  KEY (`order_num`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;

-- 接口表
CREATE TABLE IF NOT EXISTS `interface` (
  `id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL COMMENT '接口名称',
  `type` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '接口类型，0：API接口url，1：可访问页面url',
  `url` VARCHAR(255) COMMENT 'API接口或可访问页面url',
  `status` TINYINT UNSIGNED DEFAULT 0 COMMENT '状态，0:启用，1：禁用',
  `order_num` INT UNSIGNED DEFAULT 0 COMMENT '排序数字，越大越前面',
  `nid` INT(11) UNSIGNED COMMENT '如果type为1，此字段记录导航栏id',
  `ctime` DATETIME DEFAULT NOW() COMMENT '记录添加时间',
  UNIQUE KEY (`name`),
  KEY (`type`),
  UNIQUE KEY (`url`),
  KEY (`status`),
  KEY (`order_num`),
  KEY `fk_interface_navigation_nid` (`nid`),
  CONSTRAINT `fk_interface_navigation_nid` FOREIGN KEY (`nid`) REFERENCES `navigation` (`id`) ON DELETE SET NULL
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;

-- 角色-接口表
CREATE TABLE IF NOT EXISTS `role_interface` (
  `id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `rid` INT(11) UNSIGNED NOT NULL COMMENT '角色id',
  `iid` INT(11) UNSIGNED NOT NULL COMMENT '接口id',
  `status` TINYINT UNSIGNED DEFAULT 0 COMMENT '状态，0:启用，1：禁用',
  `ctime` DATETIME DEFAULT NOW() COMMENT '记录添加时间',
  UNIQUE KEY (`rid`, `iid`),
  KEY (`status`),
  KEY `fk_role_interface_uid` (`rid`),
  KEY `fk_role_interface_iid` (`iid`),
  CONSTRAINT `fk_role_interface_rid` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_role_interface_iid` FOREIGN KEY (`iid`) REFERENCES `interface` (`id`) ON DELETE CASCADE
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;

-- auto-generated definition
create table agent
(
  id        int auto_increment
    primary key,
  name      varchar(20)  null,
  passwd    varchar(100) null,
  telephone varchar(20)  null,
  id_num    varchar(20)  not null,
  sex       int          null,
  constraint agent_id_uindex
  unique (id),
  constraint agent_id_num_uindex
  unique (id_num)
)
  comment '经纪人表'
  engine = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;

-- auto-generated definition
create table credit_card
(
  id            int auto_increment
    primary key,
  card_num      varchar(20)  not null,
  bank_name     varchar(200) null,
  sub_bank_name varchar(200) null,
  city          varchar(200) null,
  is_default    int          null,
  agent_id_num  varchar(20)  not null,
  constraint credit_card_id_uindex
  unique (id),
  constraint credit_card_card_num_agent_id_num_pk
  unique (card_num, agent_id_num)
)
  comment '银行卡信息表'
  engine = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;

-- auto-generated definition
create table customer
(
  id           int auto_increment
    primary key,
  name         varchar(20) not null,
  sex          int         null,
  description  text        null,
  agent_id_num varchar(20) not null,
  telephone    varchar(20) not null,
  constraint customer_telephone_agent_id_num_pk
  unique (telephone, agent_id_num)
)
  comment '客户表'
  engine = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_general_ci;



-- start init data
-- admin
INSERT INTO `user`(username, password) VALUES ('admin', '6443a391de091eb7dc939df60b89ab637524f0e1'); -- password: 123456
-- role
INSERT INTO `role`(`name`, `des`)
VALUES ('基础角色', '最基础的角色，所有用户都应该拥有该角色，用于获取基本页面内容的获取'),
  ('系统管理员', '系统管理员角色');
-- user_role
INSERT INTO `user_role`(`uid`, `rid`) VALUES (1, 1), (1, 2);
-- navigation
INSERT INTO `navigation`(name) VALUES ('后台管理'),('业务管理');
-- html url
INSERT INTO `interface`(`name`, `type`, `url`, `nid`)
VALUES ('用户管理', 1,'/pages/system/userList.html', 1),
  ('角色管理', 1, '/pages/system/roleList.html', 1),
  ('接口管理', 1,'/pages/system/interfaceList.html', 1),
  ('导航栏管理', 1,'/pages/system/navigationList.html', 1),
  ('经纪人管理', 1,	'/pages/business/agentList.html', 2);
-- html url
INSERT INTO `role_interface`(`rid`, `iid`)
VALUES (2, 1), (2, 2), (2, 3),(2, 4); -- 系统管理员
-- api
INSERT INTO `interface`(`name`, `type`, `url`, `order_num`)
    VALUES ('获取自己的信息', 0, '/api/user/selfInfo', 40), -- 5
      ('自己修改密码', 0, '/api/user/resetPwd', 40), -- 6
      ('分页获取用户列表', 0, '/api/user/list', 40), -- 7
      ('添加用户', 0, '/api/user/add', 40), -- 8
      ('修改用户信息', 0, '/api/user/update', 40), -- 9
      ('删除用户', 0, '/api/user/del', 40), -- 10
      ('设置用户角色', 0, '/api/user/setRoles', 40), -- 11
      ('获取所有角色列表', 0, '/api/role/all', 30), -- 12
      ('分页获取角色列表', 0, '/api/role/list', 30), -- 13
      ('添加角色', 0, '/api/role/add', 30), -- 14
      ('修改角色', 0, '/api/role/update', 30), -- 15
      ('删除角色', 0, '/api/role/del', 30), -- 16
      ('分页获取接口列表', 0, '/api/interface/list', 20), -- 17
      ('添加接口', 0, '/api/interface/add', 20), -- 18
      ('修改接口', 0, '/api/interface/update', 20), -- 19
      ('删除接口', 0, '/api/interface/del', 20), -- 20
      ('设置接口的导航栏', 0, '/api/interface/setNav', 20), -- 21
      ('设置接口角色', 0, '/api/interface/setRoles', 20), -- 22
      ('分页获取导航栏列表', 0, '/api/navigation/list', 10), -- 23
      ('添加导航栏', 0, '/api/navigation/add', 10), -- 24
      ('修改导航栏', 0, '/api/navigation/update', 10), -- 25
      ('删除导航栏', 0, '/api/navigation/del', 10), -- 26
      ('获取所有启用的导航栏', 0, '/api/navigation/enabled', 10), -- 27
      ('根据用户获取用于显示的导航栏', 0, '/api/navigation/navShow', 10);
-- api
INSERT INTO `role_interface`(`rid`, `iid`) VALUES (1, 5), (1, 6), (1, 28), -- 基础角色
  (2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18), -- 系统管理员
  (2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27); -- 系统管理员
-- end init data

INSERT INTO demo.agent (id, name, passwd, telephone, id_num, sex) VALUES (1, 'xuyetong', 'pyf3237829', '13855199239', '0', 1);
INSERT INTO demo.agent (id, name, passwd, telephone, id_num, sex) VALUES (2, '111', '1111', '11111111111', '111111111111111', 0);
INSERT INTO demo.agent (id, name, passwd, telephone, id_num, sex) VALUES (4, '222', '2222', '22222222222', '222222222222222', 1);
INSERT INTO demo.agent (id, name, passwd, telephone, id_num, sex) VALUES (6, 'xuyetong', 'pyf3237829', '13855199239', '342622199009010638', 0);
INSERT INTO demo.agent (id, name, passwd, telephone, id_num, sex) VALUES (12, '222', '222', '22222222222', '222222222222222222', 1);
INSERT INTO demo.agent (id, name, passwd, telephone, id_num, sex) VALUES (16, '11111', '1211', '11111111111', '111211111111111', 0);

INSERT INTO demo.credit_card (id, card_num, bank_name, sub_bank_name, city, is_default, agent_id_num) VALUES (1, '1', '1', '1', '1', 1, '111111111111111');
INSERT INTO demo.credit_card (id, card_num, bank_name, sub_bank_name, city, is_default, agent_id_num) VALUES (3, '2', '浙商银行', '合肥支行', '合肥', 1, '111111111111111');
INSERT INTO demo.credit_card (id, card_num, bank_name, sub_bank_name, city, is_default, agent_id_num) VALUES (4, '3', '工商银行', '合肥支行', '合肥', 1, '111111111111111');
INSERT INTO demo.credit_card (id, card_num, bank_name, sub_bank_name, city, is_default, agent_id_num) VALUES (5, '4', '4', '4', '4', 1, '111111111111111');
INSERT INTO demo.credit_card (id, card_num, bank_name, sub_bank_name, city, is_default, agent_id_num) VALUES (6, '5', '5', '5', '5', 1, '111111111111111');

INSERT INTO demo.customer (id, name, sex, description, agent_id_num, telephone) VALUES (1, 'lina', 0, 'test', '111111111111111', '12121212121');
INSERT INTO demo.customer (id, name, sex, description, agent_id_num, telephone) VALUES (3, 'tom', 1, 'test1', '111111111111111', '12121212122');
INSERT INTO demo.customer (id, name, sex, description, agent_id_num, telephone) VALUES (4, 'jack', 1, 'test2', '111111111111111', '12121212123');
INSERT INTO demo.customer (id, name, sex, description, agent_id_num, telephone) VALUES (5, 'crystina', 0, 'test4', '111111111111111', '23232323232');