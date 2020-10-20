drop table if exists t_user cascade;
create table t_user
(
    user_id       serial primary key,
    email         varchar(200) not null,
    username      varchar(50)  not null,
    password      varchar(60)  not null,
    is_active     smallint     not null    default 1,
    is_removed    smallint     not null    default 0,
    register_date timestamp with time zone default current_timestamp,
    closing_date  date                     default null,
    create_time   timestamp with time zone default current_timestamp,
    modify_time   timestamp with time zone default null
);
alter sequence t_user_user_id_seq restart with 100000;
create unique index on t_user (email);
create unique index on t_user (username);

-- 测试账号/密码： galudisu/123
insert into t_user(email, username, password) values ('galudisu@gmail.com','galudisu', '$2a$10$zTWOEDoqowlgF4BrxZvPP.Pu5M3OPG51LidvTD6EeDgNYeXeU/StC');

drop table if exists t_user_info cascade;
create table t_user_info
(
    user_id               integer references t_user (user_id) on delete set null on update cascade primary key,
    second_email          varchar(200)     default null,
    phone                 varchar(20)      default null,
    first_name            varchar(50)      default null,
    last_name             varchar(50)      default null,
    profile_picture_id    integer not null default 0,
    background_picture_id integer          default null,
    sex                   smallint         default 0,
    birthday              date             default current_date,
    city                  varchar(50)      default null,
    biography             text,
    footer                varchar(50)      default null
);
create unique index on t_user_info (second_email);


drop table if exists t_section cascade;
create table t_section
(
    section_id  serial primary key,
    name        varchar(50) not null,
    description text
);
alter sequence t_section_section_id_seq restart with 100000;
create unique index on t_section (name);

drop table if exists t_topic cascade;
create table t_topic
(
    topic_id    serial primary key,
    user_id     integer references t_user (user_id) on delete set null on update cascade,
    section_id  integer references t_section (section_id) on delete set null on update cascade,
    title       varchar(50) not null,
    content     text        not null,
    views       integer     not null     default 0,
    create_time timestamp with time zone default current_timestamp,
    modify_time timestamp with time zone default null,
    is_closed   smallint                 default 0
);
alter sequence t_topic_topic_id_seq restart with 100000;

drop table if exists t_post cascade;
create table t_post
(
    post_id     serial primary key,
    topic_id    integer references t_topic (topic_id) on delete set null on update cascade,
    user_id     integer references t_user (user_id) on delete set null on update cascade,
    content     text,
    create_time timestamp with time zone default current_timestamp,
    modify_time timestamp with time zone default null
);
alter sequence t_post_post_id_seq restart with 100000;

drop table if exists t_role cascade;
create table t_role
(
    role_id     serial primary key,
    name        varchar(50) not null,
    description text
);
alter sequence t_role_role_id_seq restart with 100000;
create unique index on t_role (name);

drop table if exists t_role_of_user cascade;
create table t_role_of_user
(
    user_id integer references t_user (user_id) on delete set null on update cascade,
    role_id integer references t_role (role_id) on delete set null on update cascade
);



