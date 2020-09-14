DROP TABLE IF EXISTS fin_instrument;

CREATE TABLE daily_stats (
     id INT(11) AUTO_INCREMENT PRIMARY KEY,
     status_date DATE /*NOT NULL*/,
     instrument_id INTEGER,
     price DECIMAL(10.2) DEFAULT NULL
);

