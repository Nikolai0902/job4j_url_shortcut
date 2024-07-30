create table if not exists site (
    id serial primary key not null,
    login varchar(500),
    site varchar(500),
    password varchar(500),
    unique (site)
);