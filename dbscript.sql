/*
Navicat PGSQL Data Transfer

Source Server         : postgres
Source Server Version : 90303
Source Host           : localhost:5432
Source Database       : productdb
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90303
File Encoding         : 65001

Date: 2015-12-25 17:01:42
*/


-- ----------------------------
-- Sequence structure for api_role_id_seq
-- ----------------------------
DROP SEQUENCE "api_role_id_seq";
CREATE SEQUENCE "api_role_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for api_user_id_seq
-- ----------------------------
DROP SEQUENCE "api_user_id_seq";
CREATE SEQUENCE "api_user_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."api_user_id_seq"', 2, true);

-- ----------------------------
-- Sequence structure for api_user_role_id_seq
-- ----------------------------
DROP SEQUENCE "api_user_role_id_seq";
CREATE SEQUENCE "api_user_role_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for tbproduct_id_seq
-- ----------------------------
DROP SEQUENCE "tbproduct_id_seq";
CREATE SEQUENCE "tbproduct_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS "tb_user_role";
CREATE TABLE "tb_user_role" (
"id" int4 DEFAULT nextval('api_user_role_id_seq'::regclass) NOT NULL,
"user_id" int4,
"role_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO "tb_user_role" VALUES ('1', '2', '1');
COMMIT;

-- ----------------------------
-- Table structure for tbproduct
-- ----------------------------
DROP TABLE IF EXISTS "tbproduct";
CREATE TABLE "tbproduct" (
"id" int4 DEFAULT nextval('tbproduct_id_seq'::regclass) NOT NULL,
"name" varchar COLLATE "default" NOT NULL,
"price" float4 NOT NULL,
"stock" int2 NOT NULL,
"created_date" date NOT NULL,
"updated_date" date NOT NULL,
"enabled" int2 NOT NULL,
"created_by" int2 NOT NULL,
"update_by" int2 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbproduct
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tbrole
-- ----------------------------
DROP TABLE IF EXISTS "tbrole";
CREATE TABLE "tbrole" (
"id" int4 DEFAULT nextval('api_role_id_seq'::regclass) NOT NULL,
"role_name" varchar(100) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbrole
-- ----------------------------
BEGIN;
INSERT INTO "tbrole" VALUES ('1', 'ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tbuser
-- ----------------------------
DROP TABLE IF EXISTS "tbuser";
CREATE TABLE "tbuser" (
"id" int4 DEFAULT nextval('api_user_id_seq'::regclass) NOT NULL,
"username" varchar(100) COLLATE "default" NOT NULL,
"email" varchar(100) COLLATE "default" NOT NULL,
"password" varchar(100) COLLATE "default" NOT NULL,
"enabled" int4 NOT NULL,
"locked" int4,
"position" varchar(100) COLLATE "default",
"approved_by" int4,
"approved_date" date,
"created_date" date,
"updated_date" date,
"created_by" int4,
"updated_by" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbuser
-- ----------------------------
BEGIN;
INSERT INTO "tbuser" VALUES ('2', 'admin', 'asd', '$2a$10$FUGODPP15.f7eqkDOVW.mOyeilUuFOG42HSElCz933a8NLI86B046', '1', '1', 'asdf', '1', '2015-12-01', '2015-12-28', '2015-12-22', '2', '1');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "api_role_id_seq" OWNED BY "tbrole"."id";
ALTER SEQUENCE "api_user_id_seq" OWNED BY "tbuser"."id";
ALTER SEQUENCE "api_user_role_id_seq" OWNED BY "tb_user_role"."id";
ALTER SEQUENCE "tbproduct_id_seq" OWNED BY "tbproduct"."id";

-- ----------------------------
-- Uniques structure for table tb_user_role
-- ----------------------------
ALTER TABLE "tb_user_role" ADD UNIQUE ("id");

-- ----------------------------
-- Primary Key structure for table tbproduct
-- ----------------------------
ALTER TABLE "tbproduct" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table tbrole
-- ----------------------------
ALTER TABLE "tbrole" ADD UNIQUE ("id");

-- ----------------------------
-- Uniques structure for table tbuser
-- ----------------------------
ALTER TABLE "tbuser" ADD UNIQUE ("email");
ALTER TABLE "tbuser" ADD UNIQUE ("id");
ALTER TABLE "tbuser" ADD UNIQUE ("username");

-- ----------------------------
-- Foreign Key structure for table "tb_user_role"
-- ----------------------------
ALTER TABLE "tb_user_role" ADD FOREIGN KEY ("role_id") REFERENCES "tbrole" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "tb_user_role" ADD FOREIGN KEY ("user_id") REFERENCES "tbuser" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "tbproduct"
-- ----------------------------
ALTER TABLE "tbproduct" ADD FOREIGN KEY ("created_by") REFERENCES "tbuser" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "tbproduct" ADD FOREIGN KEY ("update_by") REFERENCES "tbuser" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
