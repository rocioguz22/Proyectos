--------------------------------------------------------
-- Archivo creado  - jueves-diciembre-22-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table APPOINTMENTS
--------------------------------------------------------

  CREATE TABLE "UBD3282"."APPOINTMENTS" 
   (	"APPOINTMENT_ID" NUMBER, 
	"HOUR" DATE, 
	"OFFICE" NUMBER, 
	"MEDIUM" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS"   NO INMEMORY ;
--------------------------------------------------------
--  DDL for Table CAMPAIGNS
--------------------------------------------------------

  CREATE TABLE "UBD3282"."CAMPAIGNS" 
   (	"CAMPAIGN_ID" NUMBER, 
	"NAME" VARCHAR2(20 BYTE), 
	"MEDIUM" VARCHAR2(20 BYTE), 
	"TYPE" VARCHAR2(20 BYTE), 
	"VERTICAL" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS"   NO INMEMORY ;
--------------------------------------------------------
--  DDL for Table CLIENTS
--------------------------------------------------------

  CREATE TABLE "UBD3282"."CLIENTS" 
   (	"CLIENT_ID" NUMBER, 
	"NAME" VARCHAR2(30 BYTE), 
	"SURNAME" VARCHAR2(30 BYTE), 
	"EMAIL" VARCHAR2(30 BYTE), 
	"PHONE" NUMBER, 
	"DATE_ID" NUMBER, 
	"CAMPAIGN_ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS"   NO INMEMORY ;
--------------------------------------------------------
--  DDL for Table COMPANIES
--------------------------------------------------------

  CREATE TABLE "UBD3282"."COMPANIES" 
   (	"COMPANY_NAME" VARCHAR2(20 BYTE), 
	"WEB" VARCHAR2(30 BYTE), 
	"ADDRESS" VARCHAR2(50 BYTE), 
	"VERTICAL" VARCHAR2(20 BYTE), 
	"CAMPAIGN_ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS"   NO INMEMORY ;
--------------------------------------------------------
--  DDL for Table EMPLOYEES
--------------------------------------------------------

  CREATE TABLE "UBD3282"."EMPLOYEES" 
   (	"EMPLOYEE_ID" NUMBER, 
	"NAME" VARCHAR2(50 BYTE), 
	"EMAIL" VARCHAR2(50 BYTE), 
	"AGE" NUMBER, 
	"PHONE" NUMBER, 
	"SALARY" NUMBER, 
	"HIRE_DATE" VARCHAR2(30 BYTE), 
	"APPOINTMENT_ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS"   NO INMEMORY ;
--------------------------------------------------------
--  DDL for Table EVENTS
--------------------------------------------------------

  CREATE TABLE "UBD3282"."EVENTS" 
   (	"EVENT_NAME" VARCHAR2(20 BYTE), 
	"EVENT_DATE" DATE, 
	"EVENT_CONTACT" VARCHAR2(20 BYTE), 
	"COMPANY_NAME" VARCHAR2(20 BYTE), 
	"CLIENT_ID" NUMBER, 
	"ATTENDEE_NAME" VARCHAR2(20 BYTE), 
	"EVENT_ID" NUMBER, 
	"ATTENDEES_NUMBER" NUMBER, 
	"CAMPAIGN_ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS"   NO INMEMORY ;
--------------------------------------------------------
--  DDL for Table VERTICAL
--------------------------------------------------------

  CREATE TABLE "UBD3282"."VERTICAL" 
   (	"VERTICAL" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS"   NO INMEMORY ;
--------------------------------------------------------
--  DDL for View V_QUERY1
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "UBD3282"."V_QUERY1" ("ATTENDEE_NAME") AS 
  SELECT DISTINCT ATTENDEE_NAME FROM EVENTS
WHERE COMPANY_NAME IS NULL
;
--------------------------------------------------------
--  DDL for View V_QUERY2
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "UBD3282"."V_QUERY2" ("VERTICAL", "NUMBER_CAMPAIGNS") AS 
  SELECT VERTICAL, COUNT(CAMPAIGN_ID) AS NUMBER_CAMPAIGNS FROM CAMPAIGNS
GROUP BY VERTICAL
;
REM INSERTING into UBD3282.APPOINTMENTS
SET DEFINE OFF;
Insert into UBD3282.APPOINTMENTS (APPOINTMENT_ID,HOUR,OFFICE,MEDIUM) values ('528',to_date('08/07/22','DD/MM/RR'),'67','P');
Insert into UBD3282.APPOINTMENTS (APPOINTMENT_ID,HOUR,OFFICE,MEDIUM) values ('28',to_date('09/07/13','DD/MM/RR'),'7','O');
Insert into UBD3282.APPOINTMENTS (APPOINTMENT_ID,HOUR,OFFICE,MEDIUM) values ('58',to_date('08/07/22','DD/MM/RR'),'67','P');
Insert into UBD3282.APPOINTMENTS (APPOINTMENT_ID,HOUR,OFFICE,MEDIUM) values ('828',to_date('18/03/05','DD/MM/RR'),'57','O');
Insert into UBD3282.APPOINTMENTS (APPOINTMENT_ID,HOUR,OFFICE,MEDIUM) values ('328',to_date('08/07/22','DD/MM/RR'),'23','P');
Insert into UBD3282.APPOINTMENTS (APPOINTMENT_ID,HOUR,OFFICE,MEDIUM) values ('208',to_date('05/07/22','DD/MM/RR'),'24','P');
Insert into UBD3282.APPOINTMENTS (APPOINTMENT_ID,HOUR,OFFICE,MEDIUM) values ('8',to_date('24/07/02','DD/MM/RR'),'45','P');
REM INSERTING into UBD3282.CAMPAIGNS
SET DEFINE OFF;
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('134','Black and White','P','Specific','Paint');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('678','Meet you','P','Specific','Medicine');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('221','Home need it','O','Specific','Pharmacy');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('235','Love it','P','Specific','Real State');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('1288','Here to there','P','Specific','Transportation');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('5567','First aid kit','P','Specific','Pharmacy');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('666','Why?','O','General','No specific');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('999','Move','P','Specific','Real State');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('69','Go go go','P','Specific','Sport');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('76','Adrenaline','P','Specific','Sport');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('1234','Cybersecurity','P','Specific','Technology');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('194','Robots','P','Specific','Technology');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('342','One click away','P','Specific','Technology');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('348','Memories of Mozart','P','Specific','Music');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('24','Colors','P','Specific','Paint');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('23','Spring soltstice','P','General','No specific');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('264','Nany','O','General','No specific');
Insert into UBD3282.CAMPAIGNS (CAMPAIGN_ID,NAME,MEDIUM,TYPE,VERTICAL) values ('237','Cybermumies','P','General','No specific');
REM INSERTING into UBD3282.CLIENTS
SET DEFINE OFF;
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('11','Tom','Cruss','t.cruss@gmail.com','670452311','528','134');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('2','Mary','Tomson','m.tomson@gmail.com','676452311','528','999');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('341','Tom','Rayan','t.rayan@gmail.com','678453311','58','134');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('110','Rosse','Guztan','r.guztan@gmail.com','658452001','828','666');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('30','Carla','Cruss','c.cruss@gmail.com','678452301','208','134');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('116','Rocio','Rivers','r.rivers@gmail.com','678477891','528','221');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('1451','Xavier','Cruss','x.cruss@gmail.com','678552671','528','235');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('119','Eric','Hanz','t.hanz@gmail.com','678412341','58','69');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('151','Dereck','Ruzos','dereckyuo@gmail.com','678765311','58','76');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('171','Dakota','Cruss','dakota.cruss@gmail.com','608452311','528','1234');
Insert into UBD3282.CLIENTS (CLIENT_ID,NAME,SURNAME,EMAIL,PHONE,DATE_ID,CAMPAIGN_ID) values ('801','Tomas','Cruss','tomas.cruss@gmail.com','618452311','28','1234');
REM INSERTING into UBD3282.COMPANIES
SET DEFINE OFF;
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Sky','k.imaginated.es','k.imaginated@sky.es','Paint','134');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Innovation','innovation.es','ino@innovation.es','Medicine','678');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Bioinvestigation','bio.investigation.es','bio.investigation@gmail.es','Medicine','666');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Brothers Hernandez','brotherbrotherh.es','brother@brotherh.es','Pharmacy','221');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('FlamingandCo','flamingandco.es','flamingandco@gmail.es','Pharmacy','5567');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('BORK','bork.es','b@bork.es','Pharmacy','221');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Security connect','connectto.es','connect@security.es','Technology','194');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Virus removed','virusremoved.com','virus@removed.es','Technology','342');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Ding Dong','dingdong.es','dingdong@gmail.es','Music','348');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Chips','chipschips.com','chips@gmail.com','Technology','194');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Remmind','remmind.es','remmind@gmail.es','Medicine','678');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('La laz','lalaz.es','lalaz@gamil.com','Pharmacy','221');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Your health','heathyour.es','your.health@gmail.es','Pharmacy','5567');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Sleep in sky','insky.es','sleepinthsky@gmail.com','Real State','999');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Gym pool','gympool.es','gympool@gmail.com','Sport','69');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Full body','fullbody.es','fullbody@gym.es','Sport','76');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Pitxel','pitxel.es','pitxel@gmail.com','Technology','1234');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Twmt','twmt.es','twmt2@gmail.com','Technology','194');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Gooble','gooble.es','gooble@gmail.com','Technology','194');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('IREA','irea.com','irea@gmail.es','Real State','235');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('ManoloandCo','manoloco.es','manoloco@gmail.es','Real State','235');
Insert into UBD3282.COMPANIES (COMPANY_NAME,WEB,ADDRESS,VERTICAL,CAMPAIGN_ID) values ('Bath Manuel','bathmanuel.com','bathroom.manuel@gmail.com','Real State','235');
REM INSERTING into UBD3282.EMPLOYEES
SET DEFINE OFF;
Insert into UBD3282.EMPLOYEES (EMPLOYEE_ID,NAME,EMAIL,AGE,PHONE,SALARY,HIRE_DATE,APPOINTMENT_ID) values ('1','Tomas Johson','t.johson@gmail.com','35','794199456','1200','05/04/15','8');
Insert into UBD3282.EMPLOYEES (EMPLOYEE_ID,NAME,EMAIL,AGE,PHONE,SALARY,HIRE_DATE,APPOINTMENT_ID) values ('4','Firulais Johson','f.johson@gmail.com','23','795523456','1200','05/04/20','528');
Insert into UBD3282.EMPLOYEES (EMPLOYEE_ID,NAME,EMAIL,AGE,PHONE,SALARY,HIRE_DATE,APPOINTMENT_ID) values ('73','Carlota Tomas','c.tomas@gmail.com','34','733323456','1200','05/04/21','28');
Insert into UBD3282.EMPLOYEES (EMPLOYEE_ID,NAME,EMAIL,AGE,PHONE,SALARY,HIRE_DATE,APPOINTMENT_ID) values ('23','German Weyman','g.wey@gmail.com','40','790023456','1200','08/04/13','58');
Insert into UBD3282.EMPLOYEES (EMPLOYEE_ID,NAME,EMAIL,AGE,PHONE,SALARY,HIRE_DATE,APPOINTMENT_ID) values ('78','Jonh Gerh','j.gerh@gmail.com','18','794120056','1200','19/05/22','208');
REM INSERTING into UBD3282.EVENTS
SET DEFINE OFF;
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Magic of chips',to_date('09/06/22','DD/MM/RR'),'123753231',null,null,'Pepe Gomez','1','5','194');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Magic of chips',to_date('09/06/22','DD/MM/RR'),'123753231',null,null,'Melisa Suarez','2','5','194');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Magic of chips',to_date('09/06/22','DD/MM/RR'),'123753231','Chips',null,'Chips','3','5','194');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Magic of chips',to_date('09/06/22','DD/MM/RR'),'123753231','Gooble',null,'Gooble','8','5','194');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Magic of chips',to_date('09/06/22','DD/MM/RR'),'123753231',null,'1451','Xavier Cruss','4','5','194');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Yourself',to_date('18/04/20','DD/MM/RR'),'120003231',null,null,'Grabiela Domingez','10','8','666');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Yourself',to_date('18/04/20','DD/MM/RR'),'120003231',null,'341','Tom Rayan','11','8','666');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Yourself',to_date('18/04/20','DD/MM/RR'),'120003231',null,null,'Crucella Mortizlla','15','8','666');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Yourself',to_date('18/04/20','DD/MM/RR'),'120003231',null,null,'David Towers','19','8','666');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Yourself',to_date('18/04/20','DD/MM/RR'),'120003231',null,'119','Eric Hanz','17','8','666');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Yourself',to_date('18/04/20','DD/MM/RR'),'120003231',null,null,'Xavier Rivers','13','8','666');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Yourself',to_date('18/04/20','DD/MM/RR'),'120003231',null,null,'Peter Venger','14','8','666');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Yourself',to_date('18/04/20','DD/MM/RR'),'120003231',null,null,'Grabiela LLobret','12','8','666');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Your owm home',to_date('24/01/19','DD/MM/RR'),'240003231',null,null,'Grabiela Domingez','20','2','999');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Your owm home',to_date('24/01/19','DD/MM/RR'),'240003231','Sleep in sky',null,'Sleep in sky','29','2','999');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Attitude',to_date('01/04/21','DD/MM/RR'),'120243231',null,null,'Grabiela Domingez','32','2','69');
Insert into UBD3282.EVENTS (EVENT_NAME,EVENT_DATE,EVENT_CONTACT,COMPANY_NAME,CLIENT_ID,ATTENDEE_NAME,EVENT_ID,ATTENDEES_NUMBER,CAMPAIGN_ID) values ('Attitude',to_date('01/04/21','DD/MM/RR'),'120243231',null,'151','Dereck Ruzos','33','2','69');
REM INSERTING into UBD3282.VERTICAL
SET DEFINE OFF;
Insert into UBD3282.VERTICAL (VERTICAL) values ('Medicine');
Insert into UBD3282.VERTICAL (VERTICAL) values ('Music');
Insert into UBD3282.VERTICAL (VERTICAL) values ('No specific');
Insert into UBD3282.VERTICAL (VERTICAL) values ('Paint');
Insert into UBD3282.VERTICAL (VERTICAL) values ('Pharmacy');
Insert into UBD3282.VERTICAL (VERTICAL) values ('Real State');
Insert into UBD3282.VERTICAL (VERTICAL) values ('Sport');
Insert into UBD3282.VERTICAL (VERTICAL) values ('Technology');
Insert into UBD3282.VERTICAL (VERTICAL) values ('Transportation');
--------------------------------------------------------
--  Constraints for Table CAMPAIGNS
--------------------------------------------------------

  ALTER TABLE "UBD3282"."CAMPAIGNS" ADD CONSTRAINT "CAMPAIGNS_CHK_MEDIUM" CHECK (MEDIUM = 'P' or MEDIUM = 'O') ENABLE;
  ALTER TABLE "UBD3282"."CAMPAIGNS" ADD CONSTRAINT "CAMPAIGNS_CHK_TYPE" CHECK (TYPE = 'Specific' or TYPE = 'General') ENABLE;
  ALTER TABLE "UBD3282"."CAMPAIGNS" ADD CONSTRAINT "CAMPAIGNS_PK" PRIMARY KEY ("CAMPAIGN_ID")
  USING INDEX (CREATE UNIQUE INDEX "UBD3282"."CAMPAIGN_PK" ON "UBD3282"."CAMPAIGNS" ("CAMPAIGN_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS" )  ENABLE;
  ALTER TABLE "UBD3282"."CAMPAIGNS" MODIFY ("CAMPAIGN_ID" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."CAMPAIGNS" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."CAMPAIGNS" MODIFY ("MEDIUM" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."CAMPAIGNS" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."CAMPAIGNS" MODIFY ("VERTICAL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CLIENTS
--------------------------------------------------------

  ALTER TABLE "UBD3282"."CLIENTS" ADD CONSTRAINT "CLIENTS_PK" PRIMARY KEY ("CLIENT_ID")
  USING INDEX (CREATE UNIQUE INDEX "UBD3282"."CLIENT_PK" ON "UBD3282"."CLIENTS" ("CLIENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS" )  ENABLE;
  ALTER TABLE "UBD3282"."CLIENTS" MODIFY ("CLIENT_ID" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."CLIENTS" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."CLIENTS" MODIFY ("PHONE" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."CLIENTS" MODIFY ("DATE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table VERTICAL
--------------------------------------------------------

  ALTER TABLE "UBD3282"."VERTICAL" MODIFY ("VERTICAL" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."VERTICAL" ADD CONSTRAINT "VERTICAL_PK" PRIMARY KEY ("VERTICAL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table EMPLOYEES
--------------------------------------------------------

  ALTER TABLE "UBD3282"."EMPLOYEES" ADD CONSTRAINT "EMPLOYEES_PK" PRIMARY KEY ("EMPLOYEE_ID")
  USING INDEX (CREATE UNIQUE INDEX "UBD3282"."EMPLOYEE_PK" ON "UBD3282"."EMPLOYEES" ("EMPLOYEE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS" )  ENABLE;
  ALTER TABLE "UBD3282"."EMPLOYEES" MODIFY ("EMPLOYEE_ID" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EMPLOYEES" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EMPLOYEES" MODIFY ("PHONE" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EMPLOYEES" MODIFY ("SALARY" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EMPLOYEES" MODIFY ("HIRE_DATE" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EMPLOYEES" MODIFY ("APPOINTMENT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EVENTS
--------------------------------------------------------

  ALTER TABLE "UBD3282"."EVENTS" ADD CONSTRAINT "EVENTS_PK" PRIMARY KEY ("EVENT_ID")
  USING INDEX (CREATE UNIQUE INDEX "UBD3282"."EVENT_PK" ON "UBD3282"."EVENTS" ("EVENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS" )  ENABLE;
  ALTER TABLE "UBD3282"."EVENTS" MODIFY ("EVENT_NAME" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EVENTS" MODIFY ("EVENT_DATE" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EVENTS" MODIFY ("EVENT_CONTACT" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EVENTS" MODIFY ("EVENT_ID" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EVENTS" MODIFY ("ATTENDEES_NUMBER" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EVENTS" MODIFY ("CAMPAIGN_ID" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."EVENTS" MODIFY ("ATTENDEE_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table COMPANIES
--------------------------------------------------------

  ALTER TABLE "UBD3282"."COMPANIES" ADD CONSTRAINT "COMPANIES_PK" PRIMARY KEY ("COMPANY_NAME")
  USING INDEX (CREATE UNIQUE INDEX "UBD3282"."BUSSINES_PK" ON "UBD3282"."COMPANIES" ("COMPANY_NAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS" )  ENABLE;
  ALTER TABLE "UBD3282"."COMPANIES" MODIFY ("COMPANY_NAME" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."COMPANIES" MODIFY ("ADDRESS" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."COMPANIES" MODIFY ("VERTICAL" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."COMPANIES" MODIFY ("CAMPAIGN_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table APPOINTMENTS
--------------------------------------------------------

  ALTER TABLE "UBD3282"."APPOINTMENTS" ADD CONSTRAINT "APPOINTMENTS_PK" PRIMARY KEY ("APPOINTMENT_ID")
  USING INDEX (CREATE UNIQUE INDEX "UBD3282"."APPOINTMENT_PK" ON "UBD3282"."APPOINTMENTS" ("APPOINTMENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_ALUMNOS" )  ENABLE;
  ALTER TABLE "UBD3282"."APPOINTMENTS" MODIFY ("APPOINTMENT_ID" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."APPOINTMENTS" MODIFY ("HOUR" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."APPOINTMENTS" MODIFY ("OFFICE" NOT NULL ENABLE);
  ALTER TABLE "UBD3282"."APPOINTMENTS" MODIFY ("MEDIUM" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table CAMPAIGNS
--------------------------------------------------------

  ALTER TABLE "UBD3282"."CAMPAIGNS" ADD CONSTRAINT "CAMPAIGNS_FK_VERTICAL" FOREIGN KEY ("VERTICAL")
	  REFERENCES "UBD3282"."VERTICAL" ("VERTICAL") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CLIENTS
--------------------------------------------------------

  ALTER TABLE "UBD3282"."CLIENTS" ADD CONSTRAINT "CLIENTS_FK_APPOINTMENTS" FOREIGN KEY ("DATE_ID")
	  REFERENCES "UBD3282"."APPOINTMENTS" ("APPOINTMENT_ID") ENABLE;
  ALTER TABLE "UBD3282"."CLIENTS" ADD CONSTRAINT "CLIENTS_FK_CAMPAIGNS" FOREIGN KEY ("CAMPAIGN_ID")
	  REFERENCES "UBD3282"."CAMPAIGNS" ("CAMPAIGN_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table COMPANIES
--------------------------------------------------------

  ALTER TABLE "UBD3282"."COMPANIES" ADD CONSTRAINT "COMPANIES_FK_CAMPAIGNS" FOREIGN KEY ("CAMPAIGN_ID")
	  REFERENCES "UBD3282"."CAMPAIGNS" ("CAMPAIGN_ID") ENABLE;
  ALTER TABLE "UBD3282"."COMPANIES" ADD CONSTRAINT "COMPANIES_FK_VERTICAL" FOREIGN KEY ("VERTICAL")
	  REFERENCES "UBD3282"."VERTICAL" ("VERTICAL") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLOYEES
--------------------------------------------------------

  ALTER TABLE "UBD3282"."EMPLOYEES" ADD CONSTRAINT "EMPLOYEES_FK_APPOINTMENTS" FOREIGN KEY ("APPOINTMENT_ID")
	  REFERENCES "UBD3282"."APPOINTMENTS" ("APPOINTMENT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EVENTS
--------------------------------------------------------

  ALTER TABLE "UBD3282"."EVENTS" ADD CONSTRAINT "EVENTS_FK_CAMPAIGNS" FOREIGN KEY ("CAMPAIGN_ID")
	  REFERENCES "UBD3282"."CAMPAIGNS" ("CAMPAIGN_ID") ENABLE;
  ALTER TABLE "UBD3282"."EVENTS" ADD CONSTRAINT "EVENTS_FK_CLIENTS" FOREIGN KEY ("CLIENT_ID")
	  REFERENCES "UBD3282"."CLIENTS" ("CLIENT_ID") ENABLE;
  ALTER TABLE "UBD3282"."EVENTS" ADD CONSTRAINT "EVENTS_FK_COMPANIES" FOREIGN KEY ("COMPANY_NAME")
	  REFERENCES "UBD3282"."COMPANIES" ("COMPANY_NAME") ENABLE;