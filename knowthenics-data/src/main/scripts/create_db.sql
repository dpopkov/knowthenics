# Use to run mysql db docker image:
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql
# or
# docker run --name mysqldb -p 3306:3306 -v /my/own/datadir:/var/lib/mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# Connect to mysql and run as root

# 1. Create databases
CREATE DATABASE IF NOT EXISTS knowthenics_dev DEFAULT CHARACTER SET 'utf8';
CREATE DATABASE IF NOT EXISTS knowthenics_prod DEFAULT CHARACTER SET 'utf8';

# 2. Create db accounts
CREATE USER 'knowthenics_dev_user'@'localhost' IDENTIFIED BY 'dev';
CREATE USER 'knowthenics_dev_user'@'%' IDENTIFIED BY 'dev';     # for Docker
CREATE USER 'knowthenics_prod_user'@'localhost' IDENTIFIED BY 'prod';
# CREATE USER 'knowthenics_prod_user'@'%' IDENTIFIED BY 'prod';   # for Docker

# 3. Database grants
GRANT INSERT, UPDATE, DELETE, SELECT
    ON knowthenics_dev.*
    TO 'knowthenics_dev_user'@'localhost';
GRANT INSERT, UPDATE, DELETE, SELECT
    ON knowthenics_dev.*
    TO 'knowthenics_dev_user'@'%';

GRANT INSERT, UPDATE, DELETE, SELECT
    ON knowthenics_prod.*
    TO 'knowthenics_prod_user'@'localhost';
GRANT INSERT, UPDATE, DELETE, SELECT
    ON knowthenics_prod.*
    TO 'sfg_prod_user'@'%';
