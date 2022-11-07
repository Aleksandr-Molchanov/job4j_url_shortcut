CREATE TABLE IF NOT EXISTS url
(
    id serial primary key,
    url varchar not null,
    code varchar unique not null,
    total int default 0
);

comment on table url is 'Преобразованные url-адреса';
comment on column url.id is 'Идентификатор url-адреса';
comment on column url.url is 'Полный url-адрес';
comment on column url.code is 'Преобразованный url-адрес';
comment on column url.total is 'Количество запросов url-адреса';