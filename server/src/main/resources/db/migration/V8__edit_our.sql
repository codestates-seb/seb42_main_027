DROP TABLE our;

create table our (
                     event_id bigint not null,
                     content text(65535),
                     date varchar(255),
                     image_url varchar(255),
                     title varchar(255),
                     view_count bigint,
                     primary key (event_id)
) engine=InnoDB;