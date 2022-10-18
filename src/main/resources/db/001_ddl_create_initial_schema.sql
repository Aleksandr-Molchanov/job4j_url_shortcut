CREATE TABLE IF NOT EXISTS site(
    id serial primary key,
    domain varchar,
    login varchar,
    password varchar
);

CREATE TABLE IF NOT EXISTS url(
    id serial primary key,
    url varchar,
    code varchar,
    total int default 0
);