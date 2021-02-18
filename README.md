[![dpopkov](https://circleci.com/gh/dpopkov/knowthenics.svg?style=shield)](https://circleci.com/gh/dpopkov/knowthenics/tree/main)

# KnowThenics

[History](History.md)

### How to Start
Not finished yet.  
* Create database: [create-db.sql](knowthenics-data/src/main/scripts/create_db.sql)
* Create schema: [knowthenics_db_schema.sql](knowthenics-data/src/main/scripts/knowthenics_db_schema.sql)
* Test connection: `jdbc:mysql://localhost:3306/knowthenics_prod?useSSL=false&serverTimezone=UTC`
* Run: `java -jar knowthenics-web-X.Y.Z-SNAPSHOT.jar --spring.active.profile=... --spring.datasource.password=...`

Revise It
---------

Use Profiles:
* default 
* dev
* prod