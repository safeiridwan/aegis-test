CREATE TABLE IF NOT EXISTS "user"
(
    user_id   varchar(255) not null
        constraint users_pkey primary key,
    full_name varchar(255) null,
    email     varchar(255) not null unique,
    password  varchar(255) not null,
    role      varchar(255) null
);

create table if not exists "organization"
(
    id          VARCHAR(255) NOT NULL
        CONSTRAINT organization_pkey PRIMARY KEY,
    name        VARCHAR(255) NULL,
    description VARCHAR(255) NULL,
    created_by  VARCHAR(255) NULL
        CONSTRAINT fk_created_by REFERENCES "user" (user_id)
);