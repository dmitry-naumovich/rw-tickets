## How to setup Oracle database using Docker
The manual can be found [here](https://github.com/oracle/docker-images/tree/master/OracleDatabase). You need to download 
Oracle images, clone that project to yourself and follow the instructions.

To avoid [issues](https://github.com/oracle/docker-images/issues/439) with access run:

`sudo chmod -R a+w /home/docker/oracle_db`

and then a container with the next command:

`sudo docker run --name oracle-rw-tickets -p 1521:1521 -p 5500:5500 -e ORACLE_SID=ORCLDB -e ORACLE_PWD=12345 -v 
/home/docker/oracle_db:/opt/oracle/oradata oracle/database:12.2.0.1-se2 
`

You can access container-running db using shell and sqlplus tool:
1. `docker exec -it <CONT_ID> bash`
2. `sqlplus SYS/12345 as sysdba`

Instructions for creating tablespaces and [user](https://www.techonthenet.com/oracle/schemas/create_schema.php):
```
CREATE TABLESPACE tbs_perm_01 DATAFILE 'tbs_perm_01.dat' SIZE 20M ONLINE;
CREATE TEMPORARY TABLESPACE tbs_temp_01 TEMPFILE 'tbs_temp_01.dbf' SIZE 5M AUTOEXTEND ON;
ALTER SESSION SET "_ORACLE_SCRIPT"=true;
CREATE USER dzmitry IDENTIFIED BY 12345 DEFAULT TABLESPACE tbs_perm_01 TEMPORARY TABLESPACE tbs_temp_01 QUOTA 20M on tbs_perm_01;
```

You need to give necessary permissions to your user:
```
GRANT create session TO dzmitry;
GRANT create table TO dzmitry;
GRANT create view TO dzmitry;
GRANT create any trigger TO dzmitry;
GRANT create any procedure TO dzmitry;
GRANT create sequence TO dzmitry;
GRANT create synonym TO dzmitry;
```

Now you can connect as your user:
1. Inside sqlplus console run `CONNECT dzmitry`
2. Inside docker container run `sqlplus dzmitry/12345`

To get current user run `SELECT user FROM dual;`

To list all user tables run `SELECT table_name FROM user_tables;`


Fixing failed migrations:
1. Connect to sqlplus, switch to owner, run:

	`DROP TABLE "flyway_schema_history";`
	
	and manually undo all the migrations (e.g. drop tables)

2. Use maven flyway [plugin](https://flywaydb.org/documentation/maven/)
```
<plugin>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-maven-plugin</artifactId>
	<version>${org.flywaydb.version}</version>
	<configuration>
		<url>jdbc:oracle:thin:@//localhost:1521/ORCLDB</url>
		<user>dzmitry</user>
		<password>12345</password>
	</configuration>
</plugin>
```            
and then [run](https://flywaydb.org/documentation/maven/repair) `mvn flyway:repair`.