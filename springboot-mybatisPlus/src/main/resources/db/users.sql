drop table if exists users;

create table if not exists users (
    id serial,
    name varchar (20),
    age integer,
    primary key(id)
);