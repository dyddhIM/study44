select * from board 
where bo_del_yn = 'N'
order by bo_no desc
;

update board 
   set bo_title = bo_title
               || '192개 행 이(가) 업데이트되었습니다. 디자인은 어려워요... '  
                 || bo_title || '메롱'
where bo_writer like '이%';
commit;

-- 게시판 
-- 일부 JSTL 중 수업안했던것 같이 하겠습니다.

-- 게시판 
-- 테이블 , VO , DAO , 뷰단...

-- 기존 board (있다면) board2로 변경 
alter table board rename to board2;
alter table board2 drop constraint pk_board;
alter table board2 add constraint pk_board2 primary key(bo_no);
-- alter index pk_board rename to pk_board2;
-- alter index pk_board2 rebuild;

drop sequence seq_board;

create sequence seq_board;
drop table board cascade constraints; 

create table board (
  bo_no number not null, --  글번호
  bo_title varchar2(250) not null, -- 제목
  bo_writer varchar2(50) not null, -- 작성자
  bo_pass varchar2(50) not null, -- 비번   
  bo_content clob ,  -- 내용
  bo_hit number default 0 , -- 조회수 
  bo_ip varchar2(30),  -- ip
  bo_reg_date date default sysdate ,  -- 등록일
  bo_mod_date date ,  -- 수정일
  bo_del_yn char(1) default 'N',  -- 삭제여부
  primary key(bo_no)
 ); 
 
 COMMENT ON COLUMN BOARD.BO_NO IS '글번호';
 COMMENT ON COLUMN BOARD.bo_title IS '제목';
 COMMENT ON COLUMN BOARD.bo_writer IS '작성자';
 COMMENT ON COLUMN BOARD.bo_pass IS '비밀번호';
 COMMENT ON COLUMN BOARD.bo_content IS '내용';
 COMMENT ON COLUMN BOARD.bo_hit IS '조회수';
 COMMENT ON COLUMN BOARD.bo_ip IS 'IP';
 COMMENT ON COLUMN BOARD.bo_reg_date IS '등록일';
 COMMENT ON COLUMN BOARD.bo_mod_date IS '수정일';
 COMMENT ON COLUMN BOARD.bo_del_yn IS '삭제여부';
 
 insert into board ( bo_no,    bo_writer,    bo_pass,
    bo_title,    bo_content,    bo_hit,
    bo_ip,    bo_reg_date )
SELECT
    seq_board.nextval,
    bo_writer,
    bo_pass,
    bo_title,
    bo_content,
    bo_hit,
    bo_ip,
    bo_reg_date
FROM board2
where rownum < 20;

select * from board;

alter table board add bo_class char(4) ;
COMMENT ON COLUMN BOARD.bo_class IS '내용분류';
update board set bo_class = 'BC01';


-- 파이팅 해주실 아빠 PC 번호 ~~~ 
select 'pc' 
       || ceil(dbms_random.value(0,4)) 
       || ceil(dbms_random.value(0,5)) 
  from dual;     
/* jsp 임시폴더에 폴더 생성해주시고 
   BoardVO, IBoardDao, BoardDaoOracle, 
   jsp 하신것 까지 올려 주시면 됩니다. 
*/   

 
 select rpad( 'private '
          || decode(a.data_type , 'NUMBER', 'int ', 'String ')
          || lower(a.column_name)
          || decode(a.data_type , 'NUMBER', ';', ' = "";')
       , 40)
      || nvl2(b.comments, '/* ' || b.comments || ' */', ' ')
  from user_tab_cols a, user_col_comments b
 where a.table_name  = b.table_name
   and a.column_name = b.column_name
   and a.table_name = upper(:TB)
order by column_id;
 
 
 
 
 

 