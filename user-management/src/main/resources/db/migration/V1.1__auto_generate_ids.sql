/* Oracle 12c brought us a IDENTITY column (more here: https://stackoverflow.com/a/11296469/7409687) */

CREATE SEQUENCE rw_user_seq START WITH 1;
CREATE SEQUENCE rw_group_seq START WITH 1;
CREATE SEQUENCE gr_request_seq START WITH 1;

CREATE OR REPLACE TRIGGER rw_user_auto_gen
BEFORE INSERT ON rw_user
FOR EACH ROW
  BEGIN
    SELECT rw_user_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;

CREATE OR REPLACE TRIGGER rw_group_auto_gen
BEFORE INSERT ON rw_group
FOR EACH ROW
  BEGIN
    SELECT rw_group_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;

CREATE OR REPLACE TRIGGER gr_request_auto_gen
BEFORE INSERT ON gr_request
FOR EACH ROW
  BEGIN
    SELECT gr_request_seq.NEXTVAL
    INTO   :new.rq_num
    FROM   dual;
  END;
