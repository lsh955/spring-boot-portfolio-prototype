# 사용자정보 테이블

CREATE TABLE user
(
    idx          int auto_increment primary key,
    type         varchar(50)  not null comment '계정 타입',
    level        varchar(50)  not null comment '계정 권한',
    state        varchar(50)  not null comment '계정 상태',
    email        varchar(255) not null comment '사용자 이메일',
    name         varchar(255) not null comment '사용자 성함',
    tel          varchar(255) null comment '사용자 전화번호',
    password     varchar(255) not null comment '사용자 패스워드',
    loginDate    datetime     null comment '로그인 일자',
    logOutDate   datetime     null comment '로그아웃 일자',
    joinDate     datetime     null comment '가입 일자',
    deleteDate   datetime     null comment '탈퇴 일자',
    ipAddress    varchar(255) null comment '접속 아이피'
);