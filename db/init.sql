create table if not exists image_info
(
    id_ bigint auto_increment
        primary key,
    image_ longtext null,
    like_count_ int null,
    unlike_count_ int null
);