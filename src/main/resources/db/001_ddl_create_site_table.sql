CREATE TABLE IF NOT EXISTS site
(
    id serial primary key,
    domain varchar unique not null,
    login varchar unique not null,
    password varchar not null
);

comment on table site is 'Зарегистрированные сайты';
comment on column site.id is 'Идентификатор сайта';
comment on column site.domain is 'Домен сайта';
comment on column site.login is 'Логин сайта';
comment on column site.password is 'Пароль сайта';