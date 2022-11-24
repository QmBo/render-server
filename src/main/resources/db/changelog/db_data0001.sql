create table if not exists users(
    id serial primary key,
    login varchar(255) not null ,
    password varchar(255) not null,
    token varchar(255)
);
create table if not exists tasks(
    id serial primary key,
    user_id int references users(id) not null,
    start_date timestamp not null,
    complete_date timestamp,
    status varchar(255)
);

