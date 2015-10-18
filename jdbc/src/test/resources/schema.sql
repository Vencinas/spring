DROP TABLE IF EXISTS tabla;
CREATE TABLE tabla
( id integer GENERATED  ALWAYS AS IDENTITY(START WITH 1),
  name varchar(256),
  age integer);