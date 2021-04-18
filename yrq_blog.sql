/*
 Navicat Premium Data Transfer

 Source Server         : yrq
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : yrq_blog

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 12/03/2021 00:07:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `first_picture` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `views` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '浏览器次数',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '分类类型ID',
  `tag_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签类型列表ID',
  `appreciation` tinyint(1) NULL DEFAULT NULL COMMENT '赞赏开启',
  `share_statement` tinyint(1) NULL DEFAULT NULL COMMENT '版权开启',
  `commentabled` tinyint(1) NULL DEFAULT NULL COMMENT '评论开启',
  `published` tinyint(1) NULL DEFAULT NULL COMMENT '发布',
  `recommend` tinyint(1) NULL DEFAULT NULL COMMENT '推荐',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (31, '第一次测试', '# 线段树\r\n```java\r\npackage _test;\r\n\r\npublic class _2数组版线段树 {\r\n	static int size = 6;\r\n	static int[] arr = new int[]{1,3,5,7,9,11};//13 20 33\r\n	static int[] tree = new int[size*4];\r\n	public static void main(String[] args){\r\n		\r\n		//起点坐标为0   左端点为0 右端点为size-1  \r\n		build(0,0,size-1);\r\n		for (int i = 0; i < tree.length; i++) {\r\n			System.out.println(	i+\" \"+ tree[i]);\r\n		\r\n		}\r\n		//把下标2的值更新为6\r\n		update(0,0,size-1,2,6);\r\n		for (int i = 0; i <  tree.length; i++) {\r\n			System.out.println(	i+\" \"+ tree[i]);\r\n		\r\n		}\r\n		//查询区间2 到5的和\r\n		int sum = query(0,0,size-1,2,5);\r\n		System.out.println(sum);\r\n	}\r\n	private static int query(int i, int st, int ed, int L, int R) {\r\n		// TODO Auto-generated method stub\r\n		if(R<st || L>ed){\r\n			return 0;\r\n		}else if(L<=st && ed<=R){\r\n			return tree[i];\r\n		}\r\n		\r\n		int mid =  (st+ed)/2;\r\n		int left_node = 2*i+1;\r\n		int right_node = 2*i+2;\r\n		\r\n		int sum1 = 0;\r\n		int sum2 = 0;\r\n		if(st<=mid) sum1 = query(left_node,st,mid,L,R);\r\n		if(ed>mid) sum2 = query(right_node, mid+1, ed, L, R);\r\n		return sum1+sum2;\r\n		\r\n		\r\n	}\r\n	private static void update(int i, int l,int r,int id, int val) {\r\n		// TODO Auto-generated method stub\r\n		if(l==r){\r\n			arr[id] = val;\r\n			tree[i] = val;\r\n			return;\r\n		}\r\n		int mid =  (l+r)/2;\r\n		int left_node = 2*i+1;\r\n		int right_node = 2*i+2;\r\n		if(id<=mid) update(left_node,	l,	 mid,id, val);\r\n		else 		update(right_node, mid+1, r, id, val);\r\n		tree[i] = tree[left_node]+tree[right_node];\r\n		\r\n		\r\n	}\r\n	private static void build(int i, int l, int r) {\r\n		// TODO Auto-generated method stub\r\n		if(l==r){\r\n			tree[i] = arr[l];\r\n			return ; \r\n		}\r\n		int mid = (l+r)/2;\r\n		build(2*i+1,l,mid);\r\n		build(2*i+2,mid+1,r);\r\n		tree[i] = tree[2*i+1] + tree[2*i+2];   //维护区间和\r\n		\r\n	}\r\n}\r\n```', '聚会宴请，喝酒时很多人都爱说：你随意，我干了。如此劝酒方式立即显示出劝酒者的风度和胸怀来，不勉强，不蛮硬，给对方进退的空间和台阶，营造了随意舒适的氛围。喝酒嘛，本来就是一件尽兴怡情的事，当应如此洒脱不羁。\r\n\r\n　　这种潇洒的酒场格调，还有一种文气的说法：君且随意，我自倾杯。\r\n\r\n　　醉笑陪君三万场，不用诉离觞。那盈盈酒杯里是酒，也不是酒。苍茫人世间，不就是一场连一场的盛宴吗？\r\n\r\n　　每个人都端着自己的', 'http://img3.imgtn.bdimg.com/it/u=1120872932,2859619195&fm=26&gp=0.jpg', '', '0', 1, 49, '33', 1, 1, 0, 1, 1, 0, '2020-03-29 15:00:47', '2020-03-29 15:04:31');
INSERT INTO `blog` VALUES (32, '第二次测试', '#线段树\r\n````java\r\npackage _test;\r\n\r\npublic class _2数组版线段树 {\r\n	static int size = 6;\r\n	static int[] arr = new int[]{1,3,5,7,9,11};//13 20 33\r\n	static int[] tree = new int[size*4];\r\n	public static void main(String[] args){\r\n		\r\n		//起点坐标为0   左端点为0 右端点为size-1  \r\n		build(0,0,size-1);\r\n		for (int i = 0; i < tree.length; i++) {\r\n			System.out.println(	i+\" \"+ tree[i]);\r\n		\r\n		}\r\n		//把下标2的值更新为6\r\n		update(0,0,size-1,2,6);\r\n		for (int i = 0; i <  tree.length; i++) {\r\n			System.out.println(	i+\" \"+ tree[i]);\r\n		\r\n		}\r\n		//查询区间2 到5的和\r\n		int sum = query(0,0,size-1,2,5);\r\n		System.out.println(sum);\r\n	}\r\n	private static int query(int i, int st, int ed, int L, int R) {\r\n		// TODO Auto-generated method stub\r\n		if(R<st || L>ed){\r\n			return 0;\r\n		}else if(L<=st && ed<=R){\r\n			return tree[i];\r\n		}\r\n		\r\n		int mid =  (st+ed)/2;\r\n		int left_node = 2*i+1;\r\n		int right_node = 2*i+2;\r\n		\r\n		int sum1 = 0;\r\n		int sum2 = 0;\r\n		if(st<=mid) sum1 = query(left_node,st,mid,L,R);\r\n		if(ed>mid) sum2 = query(right_node, mid+1, ed, L, R);\r\n		return sum1+sum2;\r\n		\r\n		\r\n	}\r\n	private static void update(int i, int l,int r,int id, int val) {\r\n		// TODO Auto-generated method stub\r\n		if(l==r){\r\n			arr[id] = val;\r\n			tree[i] = val;\r\n			return;\r\n		}\r\n		int mid =  (l+r)/2;\r\n		int left_node = 2*i+1;\r\n		int right_node = 2*i+2;\r\n		if(id<=mid) update(left_node,	l,	 mid,id, val);\r\n		else 		update(right_node, mid+1, r, id, val);\r\n		tree[i] = tree[left_node]+tree[right_node];\r\n		\r\n		\r\n	}\r\n	private static void build(int i, int l, int r) {\r\n		// TODO Auto-generated method stub\r\n		if(l==r){\r\n			tree[i] = arr[l];\r\n			return ; \r\n		}\r\n		int mid = (l+r)/2;\r\n		build(2*i+1,l,mid);\r\n		build(2*i+2,mid+1,r);\r\n		tree[i] = tree[2*i+1] + tree[2*i+2];   //维护区间和\r\n		\r\n	}\r\n}\r\n````', '聚会宴请，喝酒时很多人都爱说：你随意，我干了。如此劝酒方式立即显示出劝酒者的风度和胸怀来，不勉强，不蛮硬，给对方进退的空间和台阶，营造了随意舒适的氛围。喝酒嘛，本来就是一件尽兴怡情的事，当应如此洒脱不羁。\r\n\r\n　　这种潇洒的酒场格调，还有一种文气的说法：君且随意，我自倾杯。\r\n\r\n　　醉笑陪君三万场，不用诉离觞。那盈盈酒杯里是酒，也不是酒。苍茫人世间，不就是一场连一场的盛宴吗？\r\n\r\n　　每个人都端着自己的', 'http://b-ssl.duitang.com/uploads/item/201504/23/20150423H2952_Zm8WH.jpeg', '', '0', 1, 49, '33', 1, 1, 1, 1, 1, 0, '2020-03-29 15:06:18', '2020-03-29 15:06:43');
INSERT INTO `blog` VALUES (33, '建站有感    (好啦本网站从 2020年3月29日正式上线  ~~  O(∩_∩)O哈哈~)', '#建站有感\r\n从3月23日正式开始编写，中间有过不少bug，果然有心做一件事情\r\n真的能够做的好，网站还有很多功能都可以编写，之后会慢慢添加。。敬请期待！\r\n从数据库建模到后端springboot编写\r\n再到前端jquery和thymeleaf编写\r\n好啦\r\n接下来就是开始部署啦\r\n', '从3月23日正式开始编写，中间有过不少bug，果然有心做一件事情\r\n真的能够做的好，网站还有很多功能都可以编写，之后会慢慢添加。。敬请期待！\r\n从数据库建模到后端springboot编写\r\n再到前端jquery和thymeleaf编写\r\n好啦\r\n接下来就是开始部署啦', 'http://img0.imgtn.bdimg.com/it/u=3250956676,2607081072&fm=26&gp=0.jpg', '', '2', 1, 49, '33', 1, 1, 1, 1, 1, 0, '2020-03-29 15:14:56', '2020-03-29 15:14:56');
INSERT INTO `blog` VALUES (34, '测试', '啊', '啊', '啊', '', '0', 1, 49, '33', 1, 1, 1, 1, 1, 0, '2020-03-30 11:50:05', '2020-03-30 11:50:05');

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `blog_id` bigint(20) NOT NULL COMMENT '博客ID',
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES (1, 1, 1);
INSERT INTO `blog_tag` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `blog_id` bigint(20) NOT NULL COMMENT '评论博客ID',
  `nickname` bigint(20) NOT NULL COMMENT '评论用户昵称',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论用户邮箱',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论用户头像',
  `pre_commentId` bigint(20) NOT NULL COMMENT '上级评论ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `blog_size` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (24, 'springboot', 0, '2020-03-26 18:33:05', '2020-03-26 18:33:05', 0);
INSERT INTO `tag` VALUES (25, 'java', 1, '2020-03-26 18:33:11', '2020-03-26 18:33:11', 1);
INSERT INTO `tag` VALUES (26, 'golang', 0, '2020-03-26 18:33:19', '2020-03-26 18:33:19', 0);
INSERT INTO `tag` VALUES (27, 'vue', 0, '2020-03-26 18:33:25', '2020-03-26 18:33:25', 0);
INSERT INTO `tag` VALUES (28, 'python', 0, '2020-03-26 18:33:33', '2020-03-26 18:33:33', 0);
INSERT INTO `tag` VALUES (29, 'C++', 0, '2020-03-26 18:33:45', '2020-03-26 18:33:45', 0);
INSERT INTO `tag` VALUES (30, 'JavaScript', 0, '2020-03-26 18:34:14', '2020-03-26 18:34:14', 0);
INSERT INTO `tag` VALUES (31, '测试', 1, '2020-03-26 18:35:12', '2020-03-26 18:35:12', 2);
INSERT INTO `tag` VALUES (32, 'java', 0, '2020-03-29 14:56:49', '2020-03-29 14:56:49', 0);
INSERT INTO `tag` VALUES (33, '网站debug测试', 0, '2020-03-29 14:57:05', '2020-03-29 14:57:05', 4);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `blog_size` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (45, 'springboot', 0, '2020-03-29 14:57:19', '2020-03-29 14:57:19', 0);
INSERT INTO `type` VALUES (46, 'springcloud', 0, '2020-03-29 14:57:32', '2020-03-29 14:57:32', 0);
INSERT INTO `type` VALUES (47, '数据结构与算法', 0, '2020-03-29 14:57:44', '2020-03-29 14:57:44', 0);
INSERT INTO `type` VALUES (48, '前端UI设计', 0, '2020-03-29 14:58:10', '2020-03-29 14:58:10', 0);
INSERT INTO `type` VALUES (49, '网站debug测试', 0, '2020-03-29 14:58:25', '2020-03-29 14:58:25', 4);
INSERT INTO `type` VALUES (50, '测试', 1, '2020-03-30 11:41:10', '2020-03-30 11:41:10', 0);
INSERT INTO `type` VALUES (51, '一', 1, '2020-03-30 11:54:57', '2020-03-30 11:54:57', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `type` int(11) NULL DEFAULT NULL COMMENT '身份',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '杨瑞卿', 'yrq', '9de9e004f787b88ca06a2417bf2a9e3e', '98c435f0a60a5e70ebcef062a50a6780', 'admin', '1193700079@qq.com', '../static/image/1.png', NULL, '2020-03-29 10:05:10', '2020-03-29 10:05:10');
INSERT INTO `user` VALUES (2, '张飞飞', 'zff', '55f8ed304286bdd3c28bed4246954d61', '7eae10b1c4a331be447ae559427c6f5e', NULL, '1193700079@qq.com', '../static/image/1.png', NULL, '2020-03-29 14:30:48', '2020-03-29 14:30:48');

SET FOREIGN_KEY_CHECKS = 1;
