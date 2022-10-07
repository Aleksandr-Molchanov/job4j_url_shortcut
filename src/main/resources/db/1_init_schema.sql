CREATE TABLE IF NOT EXISTS person(
    id serial primary key not null,
    username varchar(255),
    login varchar(255),
    password varchar(255)
);

CREATE TABLE IF NOT EXISTS url(
    id serial primary key not null,
    url varchar(2000),
    code varchar(7),
    total integer,
    person_id integer references person(id)
);