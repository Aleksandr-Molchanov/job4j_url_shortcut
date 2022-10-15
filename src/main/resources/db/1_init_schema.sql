CREATE TABLE IF NOT EXISTS person(
    id serial primary key,
    username varchar,
    login varchar,
    password varchar
);

CREATE TABLE IF NOT EXISTS url(
    id serial primary key,
    url varchar,
    code varchar,
    total int default 0
);