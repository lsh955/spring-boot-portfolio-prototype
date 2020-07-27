CREATE TABLE USER
(
    IDX               int auto_increment primary key,
    Account_TYPE      varchar(50)  null comment '계정 타입',
    Account_LEVEL     varchar(50)  null comment '계정 권한',
    Account_STATE     varchar(50)  null comment '계정 상태',
    USER_ClientId     varchar(255) null comment '사용자 식별 아이디',
    USER_PASSWORD     varchar(255) null comment '사용자 패스워드',
    USER_NAME         varchar(255) null comment '사용자 성함',
    USER_EMAIL        varchar(255) null comment '사용자 이메일',
    USER_TEL          varchar(255) null comment '사용자 전화번호',
    USER_JoinDate     datetime     null comment '가입 일자',
    USER_DeleteDate   datetime     null comment '탈퇴 일자',
    USER_LoginDate    datetime     null comment '로그인 일자',
    USER_LogOutDate   datetime     null comment '로그아웃 일자',
    USER_IpAddress    varchar(255) null comment '접속 아이피'
);