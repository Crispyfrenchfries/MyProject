/*게시판*/

drop table board;

create table board(
	idx number(8) constraint board_pk primary key,--글번호
	name varchar2(30) not null,--작성자
	pwd varchar2(20) not null,--비번
	subject varchar2(200),--제목
	content varchar2(2000),--내용
	wdate timestamp default systimestamp,--작성일
	readnum number(8) default 0, --조회수
	filename varchar2(200),--첨부파일명[년월일시분초_a.jpg]
	filesize number(8), --첨부파일 크기
);

drop sequence board_seq;

create sequence board_seq nocache;

drop table reply;

create table reply(
num number(8) primary key,
userid varchar2(30) not null,
content varchar2(500),
wdate date default sysdate,
idx_fk number(3) not null,
constraint userid_fk foreign key(userid)
references member(userid),
constraint reply_idx_fk foreign key(idx_fk)
references board(idx)
);

drop sequence reply_seq;
create sequence reply_seq nocache;


