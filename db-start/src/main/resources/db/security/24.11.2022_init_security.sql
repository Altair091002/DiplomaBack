create table lesson
(
    id bigint primary key generated by default as identity,
    desciption varchar(255),
    created_on timestamp,
    title      varchar(255),
    updated_on timestamp
);