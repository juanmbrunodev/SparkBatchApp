
--Creates the Client Configuration Table
CREATE TABLE CLIENT_CONFIG (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  client_id VARCHAR(250) NOT NULL,
  config_name VARCHAR(250) NOT NULL,
  value VARCHAR(250) DEFAULT NOT NULL
);