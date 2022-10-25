CREATE TABLE IF NOT EXISTS site(
    id serial primary key,
    domain varchar unique not null,
    login varchar unique not null,
    password varchar not null
);

CREATE TABLE IF NOT EXISTS url(
    id serial primary key,
    url varchar not null,
    code varchar unique not null,
    total int default 0
);