CREATE TABLE manufacturers (id UUID, name VARCHAR(100), PRIMARY KEY (id));
CREATE TABLE products (id UUID, name VARCHAR(100), price BIGINT, manufacturer_id UUID, PRIMARY KEY (id), FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id));