create table student
(
    id     bigint
        constraint student_pk
            primary key
        constraint student__user_id_fk
            references _user,
    status varchar
);

create table teacher
(
    id     bigint
        constraint teacher_pk
            primary key
        constraint teacher__user_id_fk
            references _user,
    status varchar
);

create table "group"
(
    id         bigserial
        constraint group_pk
            primary key,
    name       varchar,
    created_at timestamp default now()
);

create table student_group
(
    student_id bigint not null
        constraint student_id_fk
            references student,
    group_id bigint not null
        constraint group_student_id_fk
            references "group",
    primary key (student_id, group_id)
);


create table teacher_group
(
    teacher_id bigint not null
        constraint teacher_id_fk
            references teacher,
    group_id bigint not null
        constraint group_teacher_id_fk
            references "group",
    primary key (teacher_id, group_id)
);

create table author_lesson
(
    user_id bigint not null
        constraint user_id_fk
            references _user,
    lesson_id bigint not null
        constraint author_lesson_id_fk
            references lesson,
    primary key (user_id, lesson_id)
);

