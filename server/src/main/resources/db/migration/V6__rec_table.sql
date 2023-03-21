create table re_comment (
                            re_comment_id bigint not null auto_increment,
                            content varchar(255),
                            created_at varchar(255),
                            member_sim bit default false,
                            modified_at varchar(255),
                            vote_count bigint default 0,
                            free_comment_id bigint,
                            member_id bigint,
                            primary key (re_comment_id)
)ENGINE=InnoDB;
