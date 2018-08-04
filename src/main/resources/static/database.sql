CREATE TABLE `main_config` (
  `main_config_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `title` varchar(256) DEFAULT NULL COMMENT 'title',
  `question` varchar(256) DEFAULT NULL COMMENT 'question',
  `left_button_text` varchar(4) DEFAULT NULL,
  `right_button_text` varchar(4) DEFAULT NULL,
  `tel` varchar(256) DEFAULT NULL COMMENT '手机号',
  `taobao_account` varchar(256) DEFAULT NULL COMMENT '淘宝账号',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`main_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COMMENT='配置表';


CREATE TABLE `popup_config` (
  `popup_config_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `main_config_id` int(10) unsigned NOT NULL COMMENT '主配置id',
  `group_type` varchar(256) DEFAULT NULL COMMENT '分组[ok-ok按钮的配置,no按钮的配置]',
  `order_index` int(2) unsigned NOT NULL COMMENT '排序,越小越考前',
  `type` varchar(256) DEFAULT NULL COMMENT 'type(msg-消息提示,html-弹框)',
  `icon` int(10) unsigned DEFAULT NULL COMMENT '图标',
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `path` varchar(256) DEFAULT NULL COMMENT '路径',
  `content` varchar(256) DEFAULT NULL COMMENT '内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`popup_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COMMENT='弹窗配置表';

