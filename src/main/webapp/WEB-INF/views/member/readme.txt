-- 21일차 

18일차 member 관련 jsp 21일차로 복사 

헤더 부트스트랩, js, css 관련된 부분이 매번 동일 -> 인클루드 디렉티브 사용해서   
/WEB-INF/inc/common_header.jsp



기존 쿼리를 별도의 자바클래스에 정의 
DAO (Data Access Object) 패턴 
-- 하나의 작업(쿼리)을 하나의 메서드에서 처리하도록 하는 패턴 
-- MemberDao  -> iBatis, myBatis 를 많이 사용하는 개발자들은 mapper 라고 부릅니다.
IMemberDao   <- MemberDaoOracle  

com.study.code.dao : ICommonCodeDao  <- CommonCodeDaoOracle
com.study.code.vo  : CommonCodeVO
  
 등록화면에 취미, 직업 처리  
-----------------------------------------------  

과제 : 21일 및 22일 2교시 까지

-- memberView.jsp   
   JDBC 로 작업된 내용을 MemberDao로 변경해 주셔야 합니다.
   또한 조회된 회원 정보를 EL 표기법으로 변경해 주셔야 합니다. 

-- memberEdit.jsp 생성  
   형식은 memberForm.jsp 과 유사하며 내부 내용은 memberView.jsp 와 유사합니다.
   memberForm.jsp를 memberEdit.jsp 로 복사하시고 내용은 memberView.jsp를 참고하셔서 작업 
   주의 : 1. 회원아이디(mem_id)는 사용자가 수정할 수 없어야 합니다.
             (즉 화면에 보여주는것, 파라미터로 넘겨야 하는것 따로 작업)
          2. 패스워드는 삭제함( 비밀번호 변경은 별도 처리 ) 
          3. 기존 직업 및 취미 (select box) 처리 필요
             예 : 회원직업이 주부이면 옵션도 선택되게끔 
                  <option value="JB01" selected="selected">주부</option>

-- memberModify.jsp 생성  
   형식은 memberRegist.jsp 와 90%이상 같으므로 복사하셔서 수정
   업데이트시 패스워드는 하지 않겠습다. 
   
   
  
-- 회원가입폼    memberForm.jsp    (memberRegist.jsp)
-- 회원가입처리  memberRegist.jsp  (memberRegistProc.jsp)

-- 회원목록      memberList.jsp 
-- 회원상세      memberView.jsp
-- 회원수정폼    memberEdit.jsp
-- 회원수정처리  memberModify.jsp
-- 회원탈퇴      memberDelete.jsp  
   
    