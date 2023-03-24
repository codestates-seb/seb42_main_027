
create table answer (
                        answer_id bigint not null auto_increment,
                        adopt_status varchar(255),
                        content TEXT(65535),
                        created_at varchar(255),
                        modified_at varchar(255),
                        vote_count bigint default 0,
                        member_id bigint,
                        question_id bigint,
                        primary key (answer_id)
) engine=InnoDB;

create table event (
                       event_id bigint not null,
                       date varchar(255),
                       hyper_link varchar(255),
                       image_url varchar(255),
                       source varchar(255),
                       title varchar(255),
                       primary key (event_id)
) engine=InnoDB;

create table free (
                      free_id bigint not null,
                      category varchar(255),
                      comments_list_num integer not null,
                      content varchar(255),
                      created_at varchar(255),
                      modified_at varchar(255),
                      title varchar(255),
                      view_count bigint not null,
                      vote_count bigint not null,
                      member_id bigint,
                      primary key (free_id)
) engine=InnoDB;

create table free_comment (
                              free_comment_id bigint not null auto_increment,
                              content varchar(255),
                              created_at varchar(255),
                              member_sim bit default false,
                              modified_at varchar(255),
                              vote_count bigint default 0,
                              free_id bigint,
                              member_id bigint,
                              primary key (free_comment_id)
) engine=InnoDB;

create table free_re_comment (
                                 free_re_comment_id bigint not null auto_increment,
                                 content varchar(255),
                                 created_at varchar(255),
                                 member_sim bit default false,
                                 modified_at varchar(255),
                                 vote_count bigint default 0,
                                 free_comment_id bigint,
                                 member_id bigint,
                                 primary key (free_re_comment_id)
) engine=InnoDB;

create table grade_tag (
                           grade_tag_id bigint not null auto_increment,
                           grade varchar(255),
                           primary key (grade_tag_id)
) engine=InnoDB;

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

create table lecture (
                         lecture_id bigint not null auto_increment,
                         introduction varchar(255),
                         star_point_average double precision not null,
                         status varchar(255),
                         title varchar(255),
                         total_review_count bigint not null,
                         teacher_id bigint,
                         primary key (lecture_id)
) engine=InnoDB;

create table lecture_grade_tag (
                                   lecture_grade_tag_id bigint not null auto_increment,
                                   grade_tag_id bigint,
                                   lecture_id bigint,
                                   primary key (lecture_grade_tag_id)
) engine=InnoDB;

create table lecture_platform_tag (
                                      lecture_platform_tag_id bigint not null auto_increment,
                                      lecture_id bigint,
                                      platform_tag_id bigint,
                                      primary key (lecture_platform_tag_id)
) engine=InnoDB;

create table lecture_review (
                                lecture_review_id bigint not null auto_increment,
                                content varchar(255),
                                created_at varchar(255),
                                modified_at varchar(255),
                                star_point integer not null,
                                title varchar(255),
                                total_comment_count bigint not null,
                                view_count bigint not null,
                                vote_count bigint default 0,
                                lecture_id bigint,
                                member_id bigint,
                                primary key (lecture_review_id)
) engine=InnoDB;

create table lecture_review_comment (
                                        lecture_review_comment_id bigint not null auto_increment,
                                        content varchar(255),
                                        created_at varchar(255),
                                        modified_at varchar(255),
                                        vote_count bigint not null,
                                        lecture_review_id bigint,
                                        member_id bigint,
                                        primary key (lecture_review_comment_id)
) engine=InnoDB;

create table lecture_subject_tag (
                                     lecture_subject_tag_id bigint not null auto_increment,
                                     lecture_id bigint,
                                     subject_tag_id bigint,
                                     primary key (lecture_subject_tag_id)
) engine=InnoDB;

create table member (
                        member_id bigint not null auto_increment,
                        created_at varchar(255),
                        display_name varchar(255),
                        email varchar(255),
                        icon_image_url varchar(255),
                        member_status varchar(255) not null,
                        password varchar(255),
                        phone_number varchar(255),
                        state varchar(255) not null,
                        username varchar(255),
                        primary key (member_id)
) engine=InnoDB;

create table member_roles (
                              member_member_id bigint not null,
                              roles varchar(255)
) engine=InnoDB;

create table our (
                     event_id bigint not null,
                     content varchar(255),
                     date varchar(255),
                     image_url varchar(255),
                     title varchar(255),
                     primary key (event_id)
) engine=InnoDB;

create table platform_tag (
                              platform_tag_id bigint not null auto_increment,
                              platform varchar(255),
                              primary key (platform_tag_id)
) engine=InnoDB;

create table qna_comment (
                             qna_comment_id bigint not null auto_increment,
                             content varchar(255),
                             created_at varchar(255),
                             modified_at varchar(255),
                             target integer,
                             vote_count bigint not null,
                             answer_id bigint,
                             member_id bigint,
                             question_id bigint,
                             primary key (qna_comment_id)
) engine=InnoDB;

create table qna_re_comment (
                                qna_re_comment_id bigint not null auto_increment,
                                content varchar(255),
                                created_at varchar(255),
                                modified_at varchar(255),
                                vote_count bigint not null,
                                member_id bigint,
                                qna_comment_id bigint,
                                primary key (qna_re_comment_id)
) engine=InnoDB;

create table qna_vote (
                          qna_vote_id bigint not null auto_increment,
                          target varchar(255),
                          vote_status varchar(255),
                          answer_id bigint,
                          member_id bigint,
                          qna_comment_id bigint,
                          qna_re_comment_id bigint,
                          question_id bigint,
                          primary key (qna_vote_id)
) engine=InnoDB;

create table question (
                          question_id bigint not null auto_increment,
                          adopt_answer_id bigint not null,
                          answer_count bigint not null,
                          content varchar(255),
                          created_at varchar(255),
                          modified_at varchar(255),
                          title varchar(255),
                          view_count bigint not null,
                          vote_count bigint not null,
                          member_id bigint,
                          primary key (question_id)
) engine=InnoDB;

create table question_subject_tag (
                                      question_subject_tag_id bigint not null auto_increment,
                                      question_id bigint,
                                      subject_tag_id bigint,
                                      primary key (question_subject_tag_id)
) engine=InnoDB;

create table review_vote (
                             review_vote_id bigint not null auto_increment,
                             status varchar(255),
                             target varchar(255),
                             lecture_review_id bigint,
                             lecture_review_comment_id bigint,
                             member_id bigint,
                             primary key (review_vote_id)
) engine=InnoDB;

create table subject_tag (
                             subject_tag_id bigint not null auto_increment,
                             subject varchar(255),
                             primary key (subject_tag_id)
) engine=InnoDB;

create table teacher (
                         teacher_id bigint not null auto_increment,
                         image_url varchar(255),
                         introduction varchar(255),
                         name varchar(255),
                         star_point_average double precision not null,
                         total_review_count bigint not null,
                         primary key (teacher_id)
) engine=InnoDB;

create table teacher_analects (
                                  teacher_teacher_id bigint not null,
                                  analects varchar(255)
) engine=InnoDB;

create table teacher_profile (
                                 teacher_teacher_id bigint not null,
                                 profile varchar(255)
) engine=InnoDB;

create table teacher_grade_tag (
                                   teacher_grade_tag_id bigint not null auto_increment,
                                   grade_tag_id bigint,
                                   teacher_id bigint,
                                   primary key (teacher_grade_tag_id)
) engine=InnoDB;

create table teacher_platform_tag (
                                      teacher_platform_tag_id bigint not null auto_increment,
                                      platform_tag_id bigint,
                                      teacher_id bigint,
                                      primary key (teacher_platform_tag_id)
) engine=InnoDB;

create table teacher_subject_tag (
                                     teacher_subject_tag_id bigint not null auto_increment,
                                     subject_tag_id bigint,
                                     teacher_id bigint,
                                     primary key (teacher_subject_tag_id)
) engine=InnoDB;

alter table grade_tag
    add constraint UK_87dymc8wlju3ftfhwp0ce8ptk unique (grade);

alter table member
    add constraint UK_k3kf09s6pp3ntqfis89la08gq unique (display_name);

alter table member
    add constraint UK_mbmcqelty0fbrvxp1q58dn57t unique (email);

alter table platform_tag
    add constraint UK_e9wl32ojt9klm2v16xncxl4a9 unique (platform);

alter table subject_tag
    add constraint UK_q7itho7r0absk5o8mgbg52ttr unique (subject);

alter table answer
    add constraint FKn2sp5pa6h0u2kixjl2r4vfluf
        foreign key (member_id)
            references member (member_id);

alter table answer
    add constraint FK8frr4bcabmmeyyu60qt7iiblo
        foreign key (question_id)
            references question (question_id);

alter table free
    add constraint FK9pgx4ljmyc3l7nf47w7h4trjb
        foreign key (member_id)
            references member (member_id);

alter table free_comment
    add constraint FK3ngeid4vrb20y78bmreldamph
        foreign key (free_id)
            references free (free_id);

alter table free_comment
    add constraint FK3jpy0evxe4ugliv4okkfqln7e
        foreign key (member_id)
            references member (member_id);

alter table free_re_comment
    add constraint FKhipi5fw52l6opnhy6w0yxx0gg
        foreign key (free_comment_id)
            references free_comment (free_comment_id);

alter table free_re_comment
    add constraint FK2pljld682yaequyw7q88xc73p
        foreign key (member_id)
            references member (member_id);

alter table lecture
    add constraint FK2ea1ueblrv09ngwf3i0lf0h2o
        foreign key (teacher_id)
            references teacher (teacher_id);

alter table lecture_grade_tag
    add constraint FK1fo0un7rh17rtlylkh134iem7
        foreign key (grade_tag_id)
            references grade_tag (grade_tag_id);

alter table lecture_grade_tag
    add constraint FKjw4b2xx94ydi26rtlbn0g6x2r
        foreign key (lecture_id)
            references lecture (lecture_id);

alter table lecture_platform_tag
    add constraint FKk5qlpydfn413wjv1ugam9e1ql
        foreign key (lecture_id)
            references lecture (lecture_id);

alter table lecture_platform_tag
    add constraint FKgrqaivf6fa38lex9sf34cxisx
        foreign key (platform_tag_id)
            references platform_tag (platform_tag_id);

alter table lecture_review
    add constraint FKiohdf2u9g51xgwxkfxbhcrr6t
        foreign key (lecture_id)
            references lecture (lecture_id);

alter table lecture_review
    add constraint FKnvktpu0k0pa9usjdftct1telu
        foreign key (member_id)
            references member (member_id);

alter table lecture_review_comment
    add constraint FKjkuk0jh3302chb3q3fqd2g9c6
        foreign key (lecture_review_id)
            references lecture_review (lecture_review_id);

alter table lecture_review_comment
    add constraint FK5jlqhbu9a4w615v1mbwhrsxpd
        foreign key (member_id)
            references member (member_id);

alter table lecture_subject_tag
    add constraint FKb99j4bhax49wvh2pf68vivytn
        foreign key (lecture_id)
            references lecture (lecture_id);

alter table lecture_subject_tag
    add constraint FKpom9m3j76k8l4jno9fpg9ddam
        foreign key (subject_tag_id)
            references subject_tag (subject_tag_id);

alter table member_roles
    add constraint FKruptm2dtwl95mfks4bnhv828k
        foreign key (member_member_id)
            references member (member_id);

alter table qna_comment
    add constraint FKfiv00r13npuo2xnsfx1nxd1pm
        foreign key (answer_id)
            references answer (answer_id);

alter table qna_comment
    add constraint FKmh8skisep9wumqmcj01latgsk
        foreign key (member_id)
            references member (member_id);

alter table qna_comment
    add constraint FK10yxo6np2o3qwwujk1i9k14i6
        foreign key (question_id)
            references question (question_id);

alter table qna_re_comment
    add constraint FKrsv3d7bxaikumnaoe7ssn1y5q
        foreign key (member_id)
            references member (member_id);

alter table qna_re_comment
    add constraint FKnpjlk243u809ettnssb32na7h
        foreign key (qna_comment_id)
            references qna_comment (qna_comment_id);

alter table qna_vote
    add constraint FK7evwwp62ou2f9wj98egt1bo1q
        foreign key (answer_id)
            references answer (answer_id);

alter table qna_vote
    add constraint FKrpmimvnpgo5doxs4dbcy30spd
        foreign key (member_id)
            references member (member_id);

alter table qna_vote
    add constraint FK38dqbwe5jm3hudmw0cxh8mwc3
        foreign key (qna_comment_id)
            references qna_comment (qna_comment_id);

alter table qna_vote
    add constraint FKlaq5p6a6sp1e37uc139b6h5li
        foreign key (qna_re_comment_id)
            references qna_re_comment (qna_re_comment_id);

alter table qna_vote
    add constraint FKd21g0wq4w92fgofhm0warpmw7
        foreign key (question_id)
            references question (question_id);

alter table question
    add constraint FK1nuuke7olg7b9fkyp2ba9d5bx
        foreign key (member_id)
            references member (member_id);

alter table question_subject_tag
    add constraint FKmfnilj6jov274u0dk7xy3yyss
        foreign key (question_id)
            references question (question_id);

alter table question_subject_tag
    add constraint FK3tmtgdgdueltccrs0uia7ik10
        foreign key (subject_tag_id)
            references subject_tag (subject_tag_id);

alter table review_vote
    add constraint FKtb9sejb61jktvk0grw2hksq70
        foreign key (lecture_review_id)
            references lecture_review (lecture_review_id);

alter table review_vote
    add constraint FKhftohgagv69lifyl231plnaq6
        foreign key (lecture_review_comment_id)
            references lecture_review_comment (lecture_review_comment_id);

alter table review_vote
    add constraint FKms5u6o0ina2x1chjovstrgsf6
        foreign key (member_id)
            references member (member_id);

alter table teacher_analects
    add constraint FKq19eufmdg3sfrfu3by0v66hth
        foreign key (teacher_teacher_id)
            references teacher (teacher_id);

alter table teacher_profile
    add constraint FK85ltsys67q7vsykl6j4edbyk4
        foreign key (teacher_teacher_id)
            references teacher (teacher_id);

alter table teacher_grade_tag
    add constraint FKaa2217x5kvrfspusnfq85j87j
        foreign key (grade_tag_id)
            references grade_tag (grade_tag_id);

alter table teacher_grade_tag
    add constraint FKsct36cei3vb6vmk9dldx1e343
        foreign key (teacher_id)
            references teacher (teacher_id);

alter table teacher_platform_tag
    add constraint FKg491oct5ihn2ba9886o4cecnb
        foreign key (platform_tag_id)
            references platform_tag (platform_tag_id);

alter table teacher_platform_tag
    add constraint FKkplxnk4yuo8okestn2tjpdoy1
        foreign key (teacher_id)
            references teacher (teacher_id);

alter table teacher_subject_tag
    add constraint FKtpabkb5phw2sbs6vocdxiy82r
        foreign key (subject_tag_id)
            references subject_tag (subject_tag_id);

alter table teacher_subject_tag
    add constraint FK2tym783ro5l3fjusbw3e6hnu
        foreign key (teacher_id)
            references teacher (teacher_id);