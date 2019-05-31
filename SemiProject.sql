create user semi identified by semi;
grant connect, resource to semi;
commit;

--------------------------------------------------------------------------------

create table members(
    m_email varchar(30) primary key,
    m_pw varchar(100),
    m_name varchar(20) not null,
    m_phone varchar(20),
    m_zipcode varchar(20),
    m_address1 varchar(100),
    m_addredd2 varchar(100),
    m_joindate timestamp default sysdate not null,
    m_ipaddress varchar(20) not null,
    m_admin char(1) check(m_admin in('y', 'n'))
);
drop table members;

select * from members;

commit;


--------------------------------------------------------------------------------

create table board(
    b_no number primary key,
    b_title varchar(100) not null,
    b_writer varchar(20) not null,
    b_amount number not null,
    b_bank varchar(20) not null,
    b_account varchar(30) not null,
    b_due_date timestamp not null,
    b_contents1 varchar(4000) not null,
    b_contents2 varchar(4000),
    b_contents3 varchar(4000),
    b_viewcount number default 0,
    b_writedate timestamp default sysdate not null,
    b_recommend number default 0 not null,
    b_sum_amount number default 0
);
drop table board;

create sequence b_no_seq
start with 1
increment by 1
nocache
nomaxvalue;
drop sequence b_no_seq;

select * from board;

--------------------------------------------------------------------------------

create table title_img(
    t_b_no number,
    t_fileSeq number primary key,
    t_fileName varchar(300) not null,
    t_oriFileName varchar(300) not null,
    t_filePath varchar(300) not null,
    t_fileSize number not null,
    constraint t_b_no_fk foreign key(t_b_no) references board(b_no) on delete cascade
);
drop table title_img;

create sequence t_fileSeq_seq
start with 1
increment by 1
nocache
nomaxvalue;
drop sequence t_fileSeq_seq;

select * from title_img;



--------------------------------------------------------------------------------

create table payment(
    p_b_no number not null,
    p_name varchar(20) not null,
    p_email varchar(30) not null,
    p_phone varchar(20) not null,
    p_amount number not null,
    p_payment_date timestamp default sysdate not null
);
drop table payment;

select * from payment;

commit;

--------------------------------------------------------------------------------

create table ufile(
    fileSeq number primary key,
    fileName varchar(300) not null,
    oriFileName varchar(300) not null,
    filePath varchar(300) not null,
    fileSize number not null
);
drop table ufile;

create sequence file_seq
start with 1
increment by 1
nocache
nomaxvalue;
drop sequence file_seq;

select * from ufile;

commit;
