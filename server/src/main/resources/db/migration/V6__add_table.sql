create table free_vote (
                           free_vote_id bigint not null auto_increment,
                           status varchar(255),
                           target varchar(255),
                           free_id bigint,
                           free_comment_id bigint,
                           member_id bigint,
                           primary key (free_vote_id)
) engine=InnoDB;

alter table free_vote
    add constraint FKhc5vdeqt3i1krkh5stlhxpg7t
        foreign key (free_id)
            references free (free_id);

alter table free_vote
    add constraint FKtkw4tg043pyvpu37f8wxsepsv
        foreign key (free_comment_id)
            references free_comment (free_comment_id);

alter table free_vote
    add constraint FK3time945f2rove5ydgmlguyix
        foreign key (member_id)
            references member (member_id);