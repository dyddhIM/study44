create sequence seq_reply;
create table reply(
  re_no number primary key,
  re_category   varchar2(30) not null,
  re_parent_no  number not null,
  re_mem_id varchar2(30) not null,
  re_content varchar2(4000) ,
  re_ip  varchar2(30),
  re_reg_date date default sysdate, 
  re_mod_date date
);
COMMENT ON table reply is '댓글정보 테이블';
COMMENT ON COLUMN reply.re_no IS '댓글번호';
COMMENT ON COLUMN reply.re_category IS '분류(BOARD,PDS,..)';
COMMENT ON COLUMN reply.re_parent_no IS '부모 번호';
COMMENT ON COLUMN reply.re_mem_id IS '작성자ID';
COMMENT ON COLUMN reply.re_content IS '내용';
COMMENT ON COLUMN reply.re_ip IS 'ip';
COMMENT ON COLUMN reply.re_reg_date IS '등록일';
COMMENT ON COLUMN reply.re_mod_date IS '수정일';


SELECT re_no,
       re_category,
       re_parent_no,
       re_mem_id,
       re_content,
       re_ip,
       re_reg_date,
       re_mod_date
  FROM reply;
    
    
INSERT INTO reply 
       ( re_no,      re_category,   re_parent_no,
         re_mem_id,  re_content,    re_ip,
         re_reg_date
        ) VALUES 
       ( seq_reply.nextval , ?,  ?,
         ?, ?, ?,
         sysdate 
        );

UPDATE reply
   SET re_content  = ? ,
       re_mod_date = sysdate   
 WHERE re_no = ?   

DELETE FROM reply
 WHERE re_no = ? 


