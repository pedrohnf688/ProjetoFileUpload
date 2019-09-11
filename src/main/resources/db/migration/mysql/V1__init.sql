CREATE TABLE arquivo (
  id varchar(255) NOT NULL,
  data longblob NOT NULL,
  file_name varchar(255) NOT NULL,
  file_type varchar(255) NOT NULL,
  PRIMARY KEY (id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;