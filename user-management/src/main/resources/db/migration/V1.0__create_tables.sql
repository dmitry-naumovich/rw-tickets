CREATE TABLE country (
  code VARCHAR2(3) PRIMARY KEY,
  country VARCHAR2(60) NOT NULL
);

CREATE TABLE city (
  code VARCHAR2(3) NOT NULL,
  country VARCHAR2(3),
  name VARCHAR2(60) NOT NULL,
  CONSTRAINT city_pk PRIMARY KEY (code, country),
  CONSTRAINT city_country_fk FOREIGN KEY (country) REFERENCES country(code) ON DELETE CASCADE
);

CREATE TABLE rw_user (
  id NUMBER(7) PRIMARY KEY,
  login VARCHAR2(20) NOT NULL,
  pwd VARCHAR2(30 CHAR) NOT NULL,
  fname VARCHAR2(50),
  sname VARCHAR2(50),
  email VARCHAR2(60) NOT NULL,
  birth_date DATE,
  city VARCHAR2(60),
  country VARCHAR2(3),
  address VARCHAR2(70),
  phone CHAR(12 CHAR),
  passport CHAR(12 CHAR),
  is_admin NUMBER(1) NOT NULL,
  CONSTRAINT rw_user_city_country_fk FOREIGN KEY (city, country) REFERENCES city(code, country)
);

CREATE UNIQUE INDEX rw_user_email_idx ON rw_user(email);
CREATE UNIQUE INDEX rw_user_login_idx ON rw_user(login);
CREATE UNIQUE INDEX rw_user_passport_idx ON rw_user(passport);

CREATE TABLE rw_group (
  id NUMBER(7) PRIMARY KEY,
  name VARCHAR2(40) NOT NULL,
  cr_datetime DATE NOT NULL,
  owner NUMBER(7) NOT NULL,
  CONSTRAINT rw_group_user_fk FOREIGN KEY (owner) REFERENCES rw_user(id)
);

CREATE UNIQUE INDEX rw_group_name_idx ON rw_group(name);

CREATE TABLE gr_involve (
  user_id NUMBER(7),
  gr_id NUMBER(7),
  st_datetime DATE,
  CONSTRAINT gr_involve_rw_user_fk FOREIGN KEY (user_id) REFERENCES rw_user(id),
  CONSTRAINT gr_involve_rw_group_fk FOREIGN KEY (gr_id) REFERENCES rw_group(id)
);

CREATE TABLE gr_request (
  rq_num NUMBER(7) PRIMARY KEY,
  from_user NUMBER(7) NOT NULL,
  to_user NUMBER(7) NOT NULL,
  gr_id NUMBER(7) NOT NULL,
  cr_datetime DATE NOT NULL,
  cl_datetime DATE,
  status CHAR(1) NOT NULL,
  rq_comment VARCHAR2(200),
  CONSTRAINT gr_request_from_user_fk FOREIGN KEY (from_user) REFERENCES rw_user(id),
  CONSTRAINT gr_request_to_user_fk FOREIGN KEY (to_user) REFERENCES rw_user(id),
  CONSTRAINT gr_request_rw_group_fk FOREIGN KEY (gr_id) REFERENCES rw_group(id)
);


