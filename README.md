## How to setup Oracle database using Docker
The manual can be found [here](https://github.com/oracle/docker-images/tree/master/OracleDatabase)
You need to download Oracle images, clone that project to yourself and follow the instructions.

To avoid [issues](https://github.com/oracle/docker-images/issues/439) with access run:

``
sudo chmod -R a+w /home/docker/oracle_db
``

before creating a container with the next command:

``
sudo docker run --name oracle-rw-tickets -p 1521:1521 -p 5500:5500 -e ORACLE_SID=ORCLDB -e ORACLE_PWD=12345 -v /home/docker/oracle_db:/opt/oracle/oradata oracle/database:12.2.0.1-se2 
``

To access container-running db using shell and sqlplus tool:
1. docker exec -it <CONT_ID> bash
2. sqlplus SYS/12345 as sysdba
3. 