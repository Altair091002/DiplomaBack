alter table lesson
    alter column id
        set maxvalue 2147483647;

alter table lesson
    add completed varchar default false;

