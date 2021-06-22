/*
 Navicat Premium Data Transfer

 Source Server         : mysql_local
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : work_ui

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 05/06/2020 01:39:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_controller
-- ----------------------------
DROP TABLE IF EXISTS `t_controller`;
CREATE TABLE `t_controller` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `package_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '包名',
  `class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类名',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'Controller.ftl' COMMENT 'freemark路径',
  `freemark_content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT 'freemark内容',
  `imports` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '需要注入的类型 ,逗号分隔',
  PRIMARY KEY (`db_name`,`db_table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='Contrller相关的生成';

-- ----------------------------
-- Records of t_controller
-- ----------------------------
BEGIN;
INSERT INTO `t_controller` VALUES ('docker2', 't_about_us', '关于我们', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_banner_meun', '一级菜单', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_base', '基础配置', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_blog', '', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_limited_seckill', '限时秒杀', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_notice', '公告', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_order_type', '订购类别', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_pic', '存放轮转图片的路径', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_producation', '产品', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
INSERT INTO `t_controller` VALUES ('docker2', 't_title', '', NULL, 'Controller', 'Controller.java', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_dao
-- ----------------------------
DROP TABLE IF EXISTS `t_dao`;
CREATE TABLE `t_dao` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `package_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '包名',
  `class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类名',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'DAO.ftl' COMMENT 'freemark路径',
  `freemark_content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT 'freemark内容',
  `imports` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'vo 需要注入的类型 ,逗号分隔',
  PRIMARY KEY (`db_name`,`db_table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_field
-- ----------------------------
DROP TABLE IF EXISTS `t_field`;
CREATE TABLE `t_field` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',
  `field_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '字段名称',
  `field_type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '数据类型',
  `field_java_type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '对应的java类型',
  `field_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '注释',
  `field_is_not_null` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否允许为空',
  `field_is_pri` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否是主键',
  `field_china_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '字段的中文',
  `field_enum_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '如果是枚举,直接是枚举值',
  `field_is_business_uniq` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否是业务唯一性',
  `field_is_query` varchar(255) COLLATE utf8_bin DEFAULT 'true' COMMENT '是否参与查询',
  `field_is_return` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否参与返回',
  `field_is_insert` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否参与插入',
  `field_is_auto_increment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否自动递增(递增的默认不参与插入)',
  PRIMARY KEY (`db_name`,`db_table_name`,`field_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_mapper
-- ----------------------------
DROP TABLE IF EXISTS `t_mapper`;
CREATE TABLE `t_mapper` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `dir_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件夹名称',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'Mapper.ftl' COMMENT 'freemark路径',
  `freemark_content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT 'freemark内容',
  PRIMARY KEY (`db_name`,`db_table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_mullti_term_vo
-- ----------------------------
DROP TABLE IF EXISTS `t_mullti_term_vo`;
CREATE TABLE `t_mullti_term_vo` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `package_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '包名',
  `class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类名',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'Controller.ftl' COMMENT 'freemark路径',
  `freemark_content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT 'freemark内容',
  `imports` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '需要注入的类型 ,逗号分隔',
  PRIMARY KEY (`db_name`,`db_table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `project_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目名称',
  `project_port` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '端口号',
  `project_context_path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目上下文',
  `project_root_package` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目root包',
  `project_mail_host` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目邮件主机',
  `project_mail_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目邮件用户名',
  `project_mail_password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目邮件密码',
  `project_author_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目作者name',
  `project_author_qq` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目作者qq',
  `project_author_phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目作者phone',
  `project_db_driver_class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目数据库驱动',
  `project_db_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目数据库地址',
  `project_db_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目数据库用户名',
  `project_db_password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目数据库密码',
  `project_url_swagger_ui` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目swagger_ui地址',
  PRIMARY KEY (`db_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_project
-- ----------------------------
BEGIN;
INSERT INTO `t_project` VALUES ('docker2', NULL, '80', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_service
-- ----------------------------
DROP TABLE IF EXISTS `t_service`;
CREATE TABLE `t_service` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `package_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '包名',
  `class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类名',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'Service.ftl' COMMENT 'freemark路径',
  `freemark_content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT 'freemark内容',
  `imports` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'vo 需要注入的类型 ,逗号分隔',
  PRIMARY KEY (`db_name`,`db_table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_service_impl
-- ----------------------------
DROP TABLE IF EXISTS `t_service_impl`;
CREATE TABLE `t_service_impl` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `package_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '包名',
  `class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类名',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'ServiceImpl.ftl' COMMENT 'freemark路径',
  `freemark_content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT 'freemark内容',
  `imports` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '需要注入的类型 ,逗号分隔',
  PRIMARY KEY (`db_name`,`db_table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_vo
-- ----------------------------
DROP TABLE IF EXISTS `t_vo`;
CREATE TABLE `t_vo` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `vo_package` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '包名',
  `vo_class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类名',
  `vo_file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `vo_freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'Vo.ftl' COMMENT 'freemark路径',
  `vo_imports` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'vo 需要注入的类型 ,逗号分隔',
  PRIMARY KEY (`db_name`,`db_table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_vo_no_pri
-- ----------------------------
DROP TABLE IF EXISTS `t_vo_no_pri`;
CREATE TABLE `t_vo_no_pri` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `vo_package` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '包名',
  `vo_class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类名',
  `vo_file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `vo_freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'VoNoPri.ftl' COMMENT 'freemark路径',
  `vo_imports` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'vo 需要注入的类型 ,逗号分隔',
  PRIMARY KEY (`db_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_vo_pri
-- ----------------------------
DROP TABLE IF EXISTS `t_vo_pri`;
CREATE TABLE `t_vo_pri` (
  `db_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '库名',
  `db_table_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表名',
  `db_table_comment` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '表注释',
  `vo_package` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '包名',
  `vo_class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类名',
  `vo_file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `vo_freemark_path` varchar(255) COLLATE utf8_bin DEFAULT 'VoPri.ftl' COMMENT 'freemark路径',
  `vo_imports` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'vo 需要注入的类型 ,逗号分隔',
  PRIMARY KEY (`db_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
