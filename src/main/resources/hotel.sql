/*
 Navicat Premium Dump SQL

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 15/07/2025 15:51:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '客户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '123456' COMMENT '客户密码',
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份证号',
  `register_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册/首次预订时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '酒店客户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_info
-- ----------------------------
INSERT INTO `customer_info` VALUES (1, '张三', '13800138001', '123456', '110101199001011234', '2025-07-08 18:16:54');
INSERT INTO `customer_info` VALUES (2, '李四', '13900139001', '123456', '110102199102021235', '2025-07-08 18:16:54');
INSERT INTO `customer_info` VALUES (3, '王五', '13700137001', '123456', '110103199203031236', '2025-07-08 18:16:54');
INSERT INTO `customer_info` VALUES (4, '赵六', '13666666666', '123456', '429000123412341234', '2025-07-14 15:43:11');

-- ----------------------------
-- Table structure for hotel_info
-- ----------------------------
DROP TABLE IF EXISTS `hotel_info`;
CREATE TABLE `hotel_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '酒店ID',
  `hotel_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '酒店名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '酒店地址',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '酒店基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_info
-- ----------------------------
INSERT INTO `hotel_info` VALUES (1, '海景大酒店', '海滨路100号', '010-12345678');

-- ----------------------------
-- Table structure for order_main
-- ----------------------------
DROP TABLE IF EXISTS `order_main`;
CREATE TABLE `order_main`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `customer_id` bigint UNSIGNED NOT NULL COMMENT '客户ID，关联customer_info.id',
  `hotel_id` bigint UNSIGNED NOT NULL DEFAULT 1 COMMENT '所属酒店ID',
  `room_type_id` bigint UNSIGNED NOT NULL COMMENT '预订房型ID',
  `room_number` bigint UNSIGNED NULL DEFAULT NULL COMMENT '实际入住房间ID（预订时可能为空，入住时关联）',
  `checkin_date` date NOT NULL COMMENT '入住日期',
  `checkout_date` date NOT NULL COMMENT '离店日期',
  `nights` int NOT NULL COMMENT '入住天数',
  `channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '预订渠道（如携程、美团）',
  `order_status` tinyint NOT NULL COMMENT '订单状态：0-待支付、1-已支付待入住、2-已入住、3-已完成、4-退订',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `breakfast` int NULL DEFAULT NULL COMMENT '是否含早餐，0含1不含',
  `amount` decimal(10, 2) NOT NULL COMMENT '费用金额',
  `payment_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支付方式',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer`(`customer_id` ASC) USING BTREE,
  INDEX `idx_hotel_room`(`hotel_id` ASC, `room_number` ASC) USING BTREE,
  INDEX `fk_order_room_type`(`room_type_id` ASC) USING BTREE,
  INDEX `fk_order_room`(`room_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '酒店订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_main
-- ----------------------------
INSERT INTO `order_main` VALUES (1, 1, 1, 1, 201, '2025-07-05', '2025-07-06', 1, '美团', 2, '2025-07-08 18:21:11', 1, 230.00, '微信');
INSERT INTO `order_main` VALUES (2, 2, 1, 1, 105, '2025-06-07', '2025-06-12', 5, '美团', 4, '2025-06-06 12:06:01', 1, 200.00, '微信');
INSERT INTO `order_main` VALUES (3, 3, 1, 3, 504, '2025-06-21', '2025-06-28', 7, '携程', 2, '2025-07-14 15:05:50', 0, 330.00, '微信');
INSERT INTO `order_main` VALUES (4, 4, 1, 3, 102, '2025-07-19', '2025-07-22', 3, '携程', 1, '2025-07-14 15:42:06', 0, 540.00, '支付宝');
INSERT INTO `order_main` VALUES (5, 4, 1, 3, 204, '2025-07-11', '2025-07-13', 2, '携程', 2, '2025-07-14 16:08:25', 0, 150.00, '支付宝');
INSERT INTO `order_main` VALUES (6, 1, 1, 3, 202, '2025-07-22', '2025-07-24', 2, '携程', 1, '2025-07-14 23:13:00', 1, 500.00, '支付宝');
INSERT INTO `order_main` VALUES (8, 3, 1, 3, 201, '2025-07-01', '2025-07-03', 2, '去哪儿', 2, '2025-07-14 23:16:33', 1, 500.00, '支付宝');
INSERT INTO `order_main` VALUES (9, 2, 1, 3, 501, '2025-07-29', '2025-07-31', 2, '携程', 1, '2025-07-15 15:30:06', 1, 500.00, '支付宝');
INSERT INTO `order_main` VALUES (10, 1, 1, 1, 101, '2025-07-22', '2025-07-24', 2, '美团', 1, '2025-07-15 15:37:11', 1, 200.00, '微信');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `price` decimal(10, 2) NOT NULL COMMENT '单价（元）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '矿泉水', 3.50);
INSERT INTO `product` VALUES (2, '泡面', 8.00);

-- ----------------------------
-- Table structure for product_order
-- ----------------------------
DROP TABLE IF EXISTS `product_order`;
CREATE TABLE `product_order`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `customer_id` bigint UNSIGNED NOT NULL COMMENT '顾客ID',
  `product_id` bigint UNSIGNED NOT NULL COMMENT '商品ID',
  `quantity` int NOT NULL COMMENT '购买数量',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：0-待支付 1-已支付 2-已取消',
  `order_date` datetime NULL DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer`(`customer_id` ASC) USING BTREE,
  INDEX `idx_product`(`product_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 194438963695 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_order
-- ----------------------------
INSERT INTO `product_order` VALUES (194438963690, 1, 1, 2, 1, '2025-07-13 14:19:28');
INSERT INTO `product_order` VALUES (194438963691, 2, 1, 3, 2, '2025-07-08 18:16:54');
INSERT INTO `product_order` VALUES (194438963692, 1, 1, 3, 0, '2025-07-08 18:16:54');
INSERT INTO `product_order` VALUES (194438963693, 2, 2, 1, 0, '2025-07-13 22:10:38');

-- ----------------------------
-- Table structure for room_info
-- ----------------------------
DROP TABLE IF EXISTS `room_info`;
CREATE TABLE `room_info`  (
  `room_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '房间号（如1001、2002）',
  `hotel_id` bigint UNSIGNED NOT NULL DEFAULT 1 COMMENT '所属酒店ID',
  `room_type_id` bigint UNSIGNED NOT NULL COMMENT '房型ID，关联room_type.id',
  `status` tinyint NOT NULL COMMENT '房间状态：0-脏房、1-干净未预定、2-已预定、3-已入住、4-锁房',
  PRIMARY KEY (`room_number`) USING BTREE,
  UNIQUE INDEX `idx_room_number`(`hotel_id` ASC, `room_number` ASC) USING BTREE,
  INDEX `idx_room_type`(`room_type_id` ASC) USING BTREE,
  CONSTRAINT `fk_room_type` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '酒店房间信息表（含状态）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_info
-- ----------------------------
INSERT INTO `room_info` VALUES ('101', 1, 1, 2);
INSERT INTO `room_info` VALUES ('102', 1, 1, 2);
INSERT INTO `room_info` VALUES ('103', 1, 1, 1);
INSERT INTO `room_info` VALUES ('104', 1, 1, 1);
INSERT INTO `room_info` VALUES ('105', 1, 1, 1);
INSERT INTO `room_info` VALUES ('106', 1, 1, 1);
INSERT INTO `room_info` VALUES ('107', 1, 1, 1);
INSERT INTO `room_info` VALUES ('108', 1, 1, 1);
INSERT INTO `room_info` VALUES ('109', 1, 1, 1);
INSERT INTO `room_info` VALUES ('110', 1, 1, 1);
INSERT INTO `room_info` VALUES ('111', 1, 1, 1);
INSERT INTO `room_info` VALUES ('112', 1, 1, 1);
INSERT INTO `room_info` VALUES ('201', 1, 2, 1);
INSERT INTO `room_info` VALUES ('202', 1, 2, 2);
INSERT INTO `room_info` VALUES ('203', 1, 2, 1);
INSERT INTO `room_info` VALUES ('204', 1, 2, 1);
INSERT INTO `room_info` VALUES ('205', 1, 2, 1);
INSERT INTO `room_info` VALUES ('501', 1, 3, 2);
INSERT INTO `room_info` VALUES ('502', 1, 3, 4);
INSERT INTO `room_info` VALUES ('503', 1, 3, 1);
INSERT INTO `room_info` VALUES ('504', 1, 3, 1);
INSERT INTO `room_info` VALUES ('505', 1, 3, 1);
INSERT INTO `room_info` VALUES ('506', 1, 3, 1);

-- ----------------------------
-- Table structure for room_type
-- ----------------------------
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '房型ID',
  `hotel_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属酒店ID，关联hotel_info.id',
  `type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '房型名称（如单人间、双人间）',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '房型描述',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_hotel_id`(`hotel_id` ASC) USING BTREE,
  CONSTRAINT `fk_room_type_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '酒店房型配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES (1, 1, '豪华单人间', '1.8米大床，海景阳台', 'http://192.168.10.100:9000/apartment/20250713/9818bcf9-497e-4436-8560-79277a0c08cc-one.jpg');
INSERT INTO `room_type` VALUES (2, 1, '海景双人间', '两张1.2米床，落地窗海景', 'http://192.168.10.100:9000/apartment/20250712/63e47d04-321f-4e8c-b598-377f2d7b4eee-two.jpg');
INSERT INTO `room_type` VALUES (3, 1, '行政套房', '独立客厅+卧室，行政礼遇', 'http://192.168.10.100:9000/apartment/20250712/19135591-1b58-4845-807c-3d7c5339482e-money.jpg');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号(用户是手机号，管理员是admin)',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13333333333', '123456');
INSERT INTO `user` VALUES ('13344445555', '123456');

SET FOREIGN_KEY_CHECKS = 1;
