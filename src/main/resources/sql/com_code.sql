-- 현재 직업의 종류 (중복된 행 제거 )
create table com_code (
    com_cd char(4) not null primary key,
    com_nm varchar2(100) not null,
    com_parent char(4),
    com_ord number(3),
    com_del_yn char(1) default 'N'
);    

select distinct  mem_like from member;   
-- 취미    
insert into com_code values ('HB00','취미' , null, 1, 'N');
insert into com_code values ('HB01','서예','HB00', 1, 'N');
insert into com_code values ('HB02','장기','HB00', 1, 'N');
insert into com_code values ('HB03','수영','HB00', 1, 'N');
insert into com_code values ('HB04','독서','HB00', 1, 'N');
insert into com_code values ('HB05','당구','HB00', 1, 'N');
insert into com_code values ('HB06','바둑','HB00', 1, 'N');
insert into com_code values ('HB07','볼링','HB00', 1, 'N');
insert into com_code values ('HB08','스키','HB00', 1, 'N');
insert into com_code values ('HB09','만화','HB00', 1, 'N');
insert into com_code values ('HB10','낚시','HB00', 1, 'N');
insert into com_code values ('HB11','영화감상','HB00', 1, 'N');
insert into com_code values ('HB12','등산','HB00', 1, 'N');
insert into com_code values ('HB13','개그','HB00', 1, 'N');
insert into com_code values ('HB14','카레이싱','HB00', 1, 'N');

-- 직업 
insert into com_code values ('JB00','직업' , null, 1, 'N');
insert into com_code values ('JB01','주부', 'JB00', 1, 'N');
insert into com_code values ('JB02','은행원', 'JB00', 1, 'N');
insert into com_code values ('JB03','공무원', 'JB00', 1, 'N');
insert into com_code values ('JB04','축산업', 'JB00', 1, 'N');
insert into com_code values ('JB05','회사원', 'JB00', 1, 'N');
insert into com_code values ('JB06','농업', 'JB00', 1, 'N');
insert into com_code values ('JB07','자영업', 'JB00', 1, 'N');
insert into com_code values ('JB08','학생', 'JB00', 1, 'N');
insert into com_code values ('JB09','교사', 'JB00', 1, 'N');

commit;
/*
select * from com_code;

-- 직업(mem_job)이  '주부' 회원의 직업을 'JB01' 로 수정 
update member 
  set mem_job 
      = (select com_cd from com_code where com_nm = mem_job);
      
-- 취미(mem_like)를 공통코드를 통해 코드로 수정   
update member 
  set mem_like 
      = (select com_cd from com_code where com_nm = mem_like);
 
select * from member;

insert into com_code values ('HB99','기타','HB00', 1, 'N');
insert into com_code values ('JB99','기타','JB00', 1, 'N');

commit;

-- join 으로 
select mem_id, mem_name, mem_job, mem_like 
     , j.com_nm as mem_job_nm 
     , h.com_nm as mem_like_nm
  from member , com_code j , com_code h 
 where mem_job = j.com_cd   
   and mem_like = h.com_cd 
   and mem_id = 'b001';
-- 서브쿼리로 
select member.*
      , (select com_nm from com_code where mem_job = com_cd ) as mem_job_nm
      , (select com_nm from com_code where mem_like = com_cd ) as mem_like_nm
  from member
 where mem_id = 'b001';


-- <option value="HB01">영화감상</option> 
select '<option value="' || com_cd ||'">' ||  com_nm || '</option>'  
  from com_code 
 where com_parent = 'JB00'  
 order by com_ord asc;

SELECT mem_id,
       mem_pass,
       mem_name,
       mem_regno1,
       mem_regno2,
       TO_CHAR(mem_bir, 'YYYY.MM.DD') as mem_bir,
       mem_zip,
       mem_add1,
       mem_add2,
       mem_hp,
       mem_mail,
       mem_job,
       mem_like,
       mem_mileage,
       mem_delete
  FROM member
 WHERE mem_delete = 'N' 
    OR mem_delete is null
  ;


UPDATE member
   SET mem_name   = ?
     , mem_regno1 = ?
     , mem_regno2 = ?
     , mem_bir    = ?
     , mem_zip    = ?
     , mem_add1   = ?
     , mem_add2   = ?
     , mem_hp     = ?
     , mem_mail   = ?
     , mem_job    = ?
     , mem_like   = ?
     , mem_mileage = ?
     , mem_delete = ?
 WHERE mem_id     = ?
*/   

-- 게시판 내용분류  
insert into com_code values ('BC00','게시판 내용분류' , null, 1, 'N');
insert into com_code values ('BC01','JAVA', 'BC00', 1, 'N');
insert into com_code values ('BC02','JSP', 'BC00', 1, 'N');
insert into com_code values ('BC03','C++', 'BC00', 1, 'N');
insert into com_code values ('BC04','CSS/JS', 'BC00', 1, 'N');
insert into com_code values ('BC99','기타', 'BC00', 1, 'N');










