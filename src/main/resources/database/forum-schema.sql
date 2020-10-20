drop table if exists t_user;
create table t_user
(
    user_id serial primary key,
    email varchar(200) not null,
    second_email varchar(200) default null,
    username varchar(50) not null,
    password varchar(60) not null,
    phone varchar(20) default null,
    first_name varchar(50) default null,
    last_name varchar(50) default null,
    profile_picture_id integer not null default 0,
    background_picture_id integer default null,
    sex smallint default 0,
    birthday date default current_date ,
    city varchar(50) default null,
    biography text,
    footer varchar(50) default null,
    register_date timestamp with time zone default current_timestamp ,
    is_email_verified smallint default 0,
    last_login_date date,
    is_active smallint not null default 1,
    closing_date date default null
);
alter sequence t_user_user_id_seq restart with 100000;
create unique index on t_user (email);
create unique index on t_user (second_email);
create unique index on t_user (username);


drop table if exists t_section;
create table t_section
(
    section_id serial primary key,
    name varchar(50) not null,
    description text
);
alter sequence t_section_section_id_seq restart with 100000;
create unique index on t_section (name);

drop table if exists t_topic;
create table t_topic
(
    topic_id serial primary key,
    user_id integer references t_user (user_id) on delete set null on update cascade ,
    section_id integer references t_section (section_id) on delete set null on update cascade ,
    title varchar(50) not null,
    content text not null,
    views integer not null default 0,
    create_time timestamp with time zone default current_timestamp,
    modify_time timestamp with time zone default null,
    is_closed smallint default 0
);
alter sequence t_topic_topic_id_seq restart with 100000;

drop table if exists t_post;
create table t_post
(
post_id serial primary key,
topic_id integer references t_topic (topic_id) on delete set null on update cascade ,
user_id integer references t_user (user_id) on delete set null on update cascade ,
content text,
create_time timestamp with time zone default current_timestamp,
modify_time timestamp with time zone default null
);
alter sequence t_post_post_id_seq restart with 100000;

drop table if exists t_role;
create table t_role
(
role_id serial primary key,
name varchar(50) not null,
description text
);
alter sequence t_role_role_id_seq restart with 100000;
create unique index on t_role (name);

drop table if exists t_role_of_user;
create table t_role_of_user
(
user_id integer references t_user (user_id) on delete set null on update cascade,
role_id integer references t_role (role_id) on delete set null on update cascade
);



