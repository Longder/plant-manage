create table if not exists image_info
(
    id_ bigint auto_increment
        primary key,
    image_ longtext null,
    upload_time_ datetime,
    check_result_ varchar(255),
    like_count_ int null,
    unlike_count_ int null
);


create table if not exists comment
(
    id_ bigint auto_increment
        primary key,
    comment_time_ datetime,
    content_ text,
    image_info_id_ bigint
);


create table if not exists image_info_image_details
(
    image_info_id_ bigint not null,
    name varchar(255) null,
    score double null
);