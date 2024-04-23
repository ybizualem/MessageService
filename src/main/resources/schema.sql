
create table messageStore(
    id int auto_increment primary key ,
    sender varchar(255) not null ,
    receiver varchar(255) not null,
    content text not null,
    timestamp TIMESTAMP default current_timestamp()
);
