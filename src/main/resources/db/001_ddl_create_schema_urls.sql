create table if not exists urls (
    id serial primary key not null,
    count int,
    url varchar(500),
    key varchar(500),
    unique (url)
);