
DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(128) DEFAULT NULL COMMENT '??',
  `gender` varchar(16) DEFAULT NULL COMMENT '0 false FEMALE\n1 true  MALE',
  `birthday` date DEFAULT NULL,
  `icon_id` bigint(11) DEFAULT NULL,
  `ext_params` varchar(4096) DEFAULT NULL COMMENT '????\n',
  `mobile` varchar(16) NOT NULL,
  `self_introduction` varchar(1024) DEFAULT NULL,
  `account` varchar(32) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `is_verify` tinyint(1) NOT NULL DEFAULT '0',
  `alipay` varchar(128) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT '',
  `wechat` varchar(128) DEFAULT NULL,
  `timezone` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  UNIQUE KEY `account_unique` (`account`),
  UNIQUE KEY `email_unique` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `account_bind`;

CREATE TABLE `account_bind` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `account_type` varchar(32) DEFAULT NULL COMMENT 'type of third part account',
  `account_name` varchar(128) DEFAULT NULL COMMENT 'nickname of third part account',
  `access_token` varchar(512) DEFAULT NULL COMMENT 'temp access_token',
  `open_id` varchar(256) DEFAULT NULL COMMENT 'open id of third part account',
  `icon_url` varchar(512) DEFAULT NULL COMMENT 'icon url of third part account',
  `icon_id` bigint(11) DEFAULT NULL COMMENT 'icon id which stored in system',
  `ext_params` varchar(4096) DEFAULT NULL COMMENT 'extend ',
  `has_bind` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `account_login_log`;

CREATE TABLE `account_login_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(128) DEFAULT NULL,
  `plat` varchar(256) DEFAULT NULL COMMENT 'enums\nweb\nios\nandroid',
  `token` varchar(128) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '????',
  `account_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `account_params`;

CREATE TABLE `account_params` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(11) NOT NULL COMMENT '??id',
  `param_name` varchar(64) NOT NULL COMMENT '???\n',
  `param_value` varchar(4096) DEFAULT NULL COMMENT '???\n',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL COMMENT '????\\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `account_role`;

CREATE TABLE `account_role` (
  `account_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `account_setting`;

CREATE TABLE `account_setting` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(11) NOT NULL COMMENT 'account id',
  `biz_type` varchar(32) DEFAULT NULL COMMENT 'biz_type',
  `subject_id` bigint(11) DEFAULT NULL,
  `setting_type` varchar(32) NOT NULL COMMENT 'type',
  `setting_value` varchar(128) DEFAULT NULL COMMENT 'value',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `workspace_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `operator_id` bigint(20) NOT NULL,
  `operation` varchar(32) NOT NULL,
  `object_type` varchar(32) NOT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `workspace_id` bigint(20) DEFAULT NULL,
  `ext_params` varchar(4096) DEFAULT NULL,
  `operator_avatar_id` bigint(11) DEFAULT NULL,
  `operator_name` varchar(32) DEFAULT NULL,
  `object_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `attach`;

CREATE TABLE `attach` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `attach_type` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `meta_data` text COMMENT 'json',
  `account_id` bigint(11) DEFAULT NULL,
  `is_del` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `url_UNIQUE` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `attach_relate`;

CREATE TABLE `attach_relate` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(11) DEFAULT NULL,
  `attach_id` bigint(11) DEFAULT NULL,
  `biz_type` varchar(32) DEFAULT NULL,
  `ext_params` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(11) NOT NULL,
  `title` varchar(256) DEFAULT NULL,
  `content` text,
  `email` varchar(128) DEFAULT NULL,
  `mobile` varchar(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `invitation_code`;

CREATE TABLE `invitation_code` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `type` varchar(32) DEFAULT NULL,
  `is_expired` tinyint(4) DEFAULT NULL,
  `account_id` bigint(11) DEFAULT NULL,
  `inviter_id` bigint(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `receiver_id` bigint(11) NOT NULL COMMENT '???id',
  `sender_id` bigint(11) NOT NULL COMMENT '???id',
  `is_read` tinyint(4) DEFAULT '0',
  `is_archived` tinyint(4) DEFAULT '0',
  `is_deleted` tinyint(4) DEFAULT '0',
  `title` varchar(64) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `content` text COMMENT '????',
  `biz_id` bigint(11) DEFAULT NULL COMMENT '??id?',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '????',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `sender_name` varchar(32) DEFAULT NULL,
  `biz_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `receiver_id_index` (`receiver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `operation` varchar(32) DEFAULT NULL,
  `system` varchar(32) DEFAULT NULL COMMENT '??id',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT 'enum\nCUSTOMER\nADMIN\netc.',
  `remark` varchar(128) DEFAULT NULL COMMENT '??',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` bigint(11) NOT NULL,
  `permission_id` bigint(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sms_verify_code`;

CREATE TABLE `sms_verify_code` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT NULL COMMENT '???',
  `mobile` varchar(16) DEFAULT NULL COMMENT '????',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '???????',
  `expire_time` timestamp NOT NULL COMMENT '???????',
  `is_active` tinyint(1) DEFAULT '1' COMMENT '1????0??',
  `sms_type` varchar(32) NOT NULL COMMENT '????',
  `account_id` bigint(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `system_params`;

CREATE TABLE `system_params` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(64) NOT NULL COMMENT '''???''',
  `param_value` varchar(4096) DEFAULT NULL COMMENT '''???''',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `is_active` tinyint(4) DEFAULT '1' COMMENT '????',
  `param_type` varchar(64) NOT NULL DEFAULT 'DEFAULT',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `workspace`;

CREATE TABLE `workspace` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `name` varchar(256) NOT NULL,
  `description` text,
  `is_verified` tinyint(1) NOT NULL DEFAULT '0',
  `type` varchar(32) NOT NULL,
  `ext_params` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `workspace_account`;

CREATE TABLE `workspace_account` (
  `create_time` timestamp NULL DEFAULT NULL,
  `workspace_id` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `role` varchar(32) NOT NULL,
  PRIMARY KEY (`workspace_id`,`account_id`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `workspace_verify`;

CREATE TABLE `workspace_verify` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(11) NOT NULL COMMENT 'account id',
  `workspace_id` bigint(11) NOT NULL COMMENT 'workspace id',
  `is_verified` tinyint(1) NOT NULL DEFAULT '0',
  `expired_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



