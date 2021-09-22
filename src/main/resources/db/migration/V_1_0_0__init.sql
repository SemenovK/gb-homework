create table PUBLIC.Users
(
    user_id       bigint auto_increment,
    user_name     varchar(255),
    user_password text,
    constraint users_pk
        primary key (user_id)
);



create table PUBLIC.Score
(
    user_id    bigint not null,
    user_score int default 0,
    constraint score_pk
        primary key (user_Id),
    constraint score_users_user_id_fk
        foreign key (user_Id) references Users (user_id)
);


insert into PUBLIC.USERS(user_name, user_password)
values ('User1', '$2a$10$vV1gFqCFRtZR72Ichfgp.uyOk5bzKQuGFjTmN1DnKtDloZn5jF3JK')
     , ('User2', '$2a$10$4ToIJaCyd7n4TKikJWEHNeeczODBnBD.fy4/1RxGik2gVL2zb3YFO')
     , ('User3', '$2a$10$P.4dlYQRWcq9p2/lgwlaHuuHRCSXtz1Iado48MuhfpnGyZvRBBqk.');


insert into PUBLIC.SCORE(user_id, user_score)
values (1, 1),
       (2, 2),
       (3, 3);