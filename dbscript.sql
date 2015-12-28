/*
Navicat PGSQL Data Transfer

Source Server         : Postgres
Source Server Version : 90305
Source Host           : localhost:5432
Source Database       : productdb
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90305
File Encoding         : 65001

Date: 2015-12-27 15:28:29
*/


-- ----------------------------
-- Sequence structure for api_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "api_role_id_seq" CASCADE;
CREATE SEQUENCE "api_role_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;

-- ----------------------------
-- Sequence structure for api_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "api_user_id_seq" CASCADE;
CREATE SEQUENCE "api_user_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."api_user_id_seq"', 3, true);

-- ----------------------------
-- Sequence structure for api_user_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "api_user_role_id_seq" CASCADE;
CREATE SEQUENCE "api_user_role_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;

-- ----------------------------
-- Sequence structure for tbproduct_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "tbproduct_id_seq" CASCADE;
CREATE SEQUENCE "tbproduct_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."tbproduct_id_seq"', 2, true);

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS "persistent_logins" CASCADE;
CREATE TABLE "persistent_logins" (
"username" varchar(64) COLLATE "default" NOT NULL,
"series" varchar(64) COLLATE "default" NOT NULL,
"token" varchar(64) COLLATE "default" NOT NULL,
"last_used" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS "tb_user_role" CASCADE;
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
INSERT INTO "tb_user_role" VALUES ('1', '1', '1');
INSERT INTO "tb_user_role" VALUES ('2', '2', '2');
COMMIT;

-- ----------------------------
-- Table structure for tbproduct
-- ----------------------------
DROP TABLE IF EXISTS "tbproduct" CASCADE;
CREATE TABLE "tbproduct" (
"id" int4 DEFAULT nextval('tbproduct_id_seq'::regclass) NOT NULL,
"name" varchar COLLATE "default" NOT NULL,
"price" float4 NOT NULL,
"stock" int2 NOT NULL,
"created_date" date NOT NULL,
"updated_date" date NOT NULL,
"created_by" int2 NOT NULL,
"updated_by" int2 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbproduct
-- ----------------------------
BEGIN;
INSERT INTO "tbproduct" VALUES ('1', 'coca cola', '3.3', '50', '2015-12-27', '2015-12-27', '1', '1');
INSERT INTO "tbproduct" VALUES ('2', 'anchor', '11', '30', '2015-12-27', '2015-12-28', '1', '2');
COMMIT;

-- ----------------------------
-- Table structure for tbrole
-- ----------------------------
DROP TABLE IF EXISTS "tbrole" CASCADE;
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
INSERT INTO "tbrole" VALUES ('2', 'AUTHOR');
COMMIT;

-- ----------------------------
-- Table structure for tbuser
-- ----------------------------
DROP TABLE IF EXISTS "tbuser" CASCADE;
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
INSERT INTO "tbuser" VALUES ('1', 'admin', 'asd', '$2a$10$FUGODPP15.f7eqkDOVW.mOyeilUuFOG42HSElCz933a8NLI86B046', '1', '1', 'asdf', '1', '2015-12-01', '2015-12-28', '2015-12-22', '1', '1');
INSERT INTO "tbuser" VALUES ('2', 'author', 'assd', '$2a$10$FUGODPP15.f7eqkDOVW.mOyeilUuFOG42HSElCz933a8NLI86B046', '1', '1', 'asdf', '1', '2015-12-01', '2015-12-28', '2015-12-22', '1', '1');
COMMIT;

-- ----------------------------
-- View structure for v_list_product
-- ----------------------------
CREATE OR REPLACE VIEW "v_list_product" AS 
 SELECT tbproduct.id,
    tbproduct.name,
    tbproduct.price,
    tbproduct.stock,
    tbproduct.created_date,
    tbproduct.updated_date,
    tbproduct.created_by,
    ( SELECT tbuser.username
           FROM tbuser
          WHERE (tbuser.id = tbproduct.created_by)
         LIMIT 1) AS username_creater,
    tbproduct.updated_by,
    ( SELECT tbuser.username
           FROM tbuser
          WHERE (tbuser.id = tbproduct.updated_by)
         LIMIT 1) AS username_updater
   FROM tbproduct;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "api_role_id_seq" OWNED BY "tbrole"."id";
ALTER SEQUENCE "api_user_id_seq" OWNED BY "tbuser"."id";
ALTER SEQUENCE "api_user_role_id_seq" OWNED BY "tb_user_role"."id";
ALTER SEQUENCE "tbproduct_id_seq" OWNED BY "tbproduct"."id";

-- ----------------------------
-- Primary Key structure for table persistent_logins
-- ----------------------------
ALTER TABLE "persistent_logins" ADD PRIMARY KEY ("series");

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
ALTER TABLE "tb_user_role" ADD FOREIGN KEY ("user_id") REFERENCES "tbuser" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "tb_user_role" ADD FOREIGN KEY ("role_id") REFERENCES "tbrole" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "tbproduct"
-- ----------------------------
ALTER TABLE "tbproduct" ADD FOREIGN KEY ("updated_by") REFERENCES "tbuser" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "tbproduct" ADD FOREIGN KEY ("created_by") REFERENCES "tbuser" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
