CREATE TABLE IF NOT EXISTS urls (
    id SERIAL NOT NULL PRIMARY KEY,
    count SERIAL,
    url VARCHAR(500),
    hash_code VARCHAR(500),
    UNIQUE (url)
);