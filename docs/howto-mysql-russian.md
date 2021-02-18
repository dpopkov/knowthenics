How-to setup MySQL for russian symbols
--------------------------------------

1. В application.properties в spring.datasource.url прописываем:
```
    useUnicode=yes&characterEncoding=utf8
```

2. В /etc/mysql/*.cnf прописываем:
```
    [client]
    default-character-set = utf8

    [mysql]
    default-character-set = utf8

    [mysqld]
    character_set_server=utf8
    collation_server=utf8_unicode_ci
```

[Source1](https://ru.stackoverflow.com/questions/1071439/%D0%A0%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B5-%D1%81%D0%B8%D0%BC%D0%B2%D0%BE%D0%BB%D1%8B-%D0%BD%D0%B5-%D0%BF%D0%B8%D1%88%D1%83%D1%82%D1%81%D1%8F-%D0%BA%D0%BE%D1%80%D1%80%D0%B5%D0%BA%D1%82%D0%BD%D0%BE-%D0%B2-%D0%B1%D0%B0%D0%B7%D1%83-mysql)  
[Source2](https://medium.com/@manish_demblani/breaking-out-from-the-mysql-character-set-hell-24c6a306e1e5)
