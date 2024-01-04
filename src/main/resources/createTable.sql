DROP TABLE IF EXISTS test_table;

CREATE TABLE test_table(
                           id SERIAL PRIMARY KEY,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           name varchar(50) NOT NULL,
                           password VARCHAR NOT NULL,
                           balance NUMERIC(15, 2) DEFAULT 0.00
);