DROP TABLE event;
create table event (
                       their_id bigint not null,
                       date varchar(255),
                       hyper_link varchar(255),
                       image_url varchar(255),
                       source varchar(255),
                       title varchar(255),
                       primary key (their_id)
) engine=InnoDB;