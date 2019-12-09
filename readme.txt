STS (Spring Tools Suite) , Spring Tool 3,4

maven 구조
  - 소스 빌드, 라이브러리 관리(종속라이브), 배포, 테스트 

  pom.xml : maven 설정파일(Project Object Model) 
  src - main - java      (자바파일 생성  -> classes  ) 
             - resource  (자바파일아닌 일반파일 -> classes)
             - webapp    (웹 파일 생성) 
      - test - java
             - resource 
  target - 위 소스가 컴파일 되어 (jar, war)  

-- 
pom.xml java 버전 설정
maven scope 
 - compile  : (default) 컴파일 할때 사용
              배포에 포함  
 - runtime  : 컴파일 할 때는 필요가 없지만 실행할 때는 필요한 것
              배포에 포함( jdbc 드라이버 같은 것)   
 - provided : 컴파일때 필요하긴 하지만 런타임때는 해당서버의 자원를 사용
              배포에서는 제외(서블릿 같은 것  ) 
 - test     : 테스트 코드를 컴파일 할때 필요
              배포에서는 제외
 - system   : provided와 유사하지만 JAR 파일을 직접 사용  
              해당 jar 파일을 명시적으로 기술
              배포에서는 제외                  
                     
--------------------------------------------
프로젝트 :
maven : 업데이트 프로젝트 실행 -> pom.xml 설정대로 프로젝트 설정이 변경 
run as : maven clean
run as : maven install 

중앙저장소에 있는 라이브러리가 로컬 저장소에 잘못되어 다운로드 되는경우가
있습니다. 
확인 라이브러리에서 해당 jar 확장해서 확인 
실행시 loc에러가 나면 잘못된 것입니다.
로컬저장소(.m2)에서 삭제하시면 됩니다.
 

--- 이클립스의 자동빌드된 클래스와  Maven 배포된(war) 파일과 충돌  
run as : maven clean

------------------------------------------------------------------------
<context:component-scan base-package="com.study" /> 
위 설정에 따라서 "com.study" 하위의 @Controller 를 찾아서 빈으로 등록 

web.xml 
서블릿 매핑 
 경로매핑 : /admin/*
 완전매핑 : /mypage/info
 확장자 매핑 : *.do       
 기본 매핑 : /
 
 / , /* 차이점
 
 WAS 에 기본적으로 설정된 서블릿(톰켓 web.xml)  
  "/"     -> defaultServlet jsp 파일이 아닌 정적자원(html, css, js, img ..) 
  "*.jsp" -> jspServlet     jsp 처리 

현재 우리프로젝트에서 "/" -> 스프링을 기본매핑으로 했기 때문에
우선순위 
1. 등록된 서블릿매핑이 우선 처리 
2. 요청이 jsp 이면 WAS 의  jspServlet 전담 
3. 그 외 요청은 "/" 으로 등록된 스프링이 처리    

* 스프링을 확장자 매핑으로 사용하면 정적인 파일의 문제가 발생하지 않습니다.
* 스프링을 "/*" 로 설정하지 마세요 : 모든 요청을 스프링이 처리하겠다는 뜻 
    ( jsp 또한 스프링이 처리한다는 뜻 ) 
* 스프링을 "/"로 설정하였을 때 

그런데.. RESTful하게 구현 (확장자 없이 사용)
  -  /board/boardView.do?bo_no=26
  -  /board/view/26  , /board/list , /board/list/2   
  
스프링에서 정적자원처리를 위해서 설정파일에서(servlet-context.xml) 
-- 1.  <resources mapping="/resources/**" location="/resources/" />
mapping : 클라이언트가 요청하는 논리적인 경로 
location : 그 요청에 대한 물리적인 경로 

-- 2. <default-servlet-handler/> 설정 
  기본적으로 해당 요청에 맡는 컨트롤러가 찾아서 실행
  만약 없다면 WAS defaultServlet 를 호출하여 정적자원을 처리 

위 둘중에 하나로 하시면 됩니다.    
-------------------------------------------
과제는   
 BoardEditController 및 BoardModifyController 의 내용을 
 BoardController에 적용하시고 
 메서드 이름 변경 
 req, resp 사용을 지양해 주시고 
 메서드 파라미터에서  ModelMap, 커맨드객체(VO)로 사용하여 처리해 주세요 
  
 
현재 filter 가 오류가 발생하는데...
서블릿 3.1을 사용할 경우 인터페이스 구현시 모든 메서드를 구현 하지 않은 경우 에러가 발생
서블릿 4.0은 java 8로 컴파일 되어 interface의 기본 메서드 정의가 되어서
pom.xml 의 서블릿을 버전업 해주세요   
<!-- Servlet -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
  
-----------------------------
현재 자유게시판 작성시 url구성이 

등록화면 : /board/boardForm.do
등록처리 : /board/boardRegist.do 
로 되어 있는데...

case 1. 나쁜놈이 직접 GET 방식으로 파라미터를 던지는걸 막고싶데요 
cf : /board/boardRegist.do?bo_title=merong&bo_class=BC02&bo_writer=jackson&bo_pass=1234 

case 2. 글등록 관련해서 url이 나누어져 있는데 하나로 해 달래요
cf : /board/regist.do


@ModelAttribute 2가지 기능
1. 커맨드객체를 자동으로 속성 저장
2. 공통적으로 자주 속성에 저장되는 객체를 별도로 분리 

---------------------------
댓글 구현 
- 로그인 한사람만 글 등록 (오늘은 예전 login 사용)
- 테이블 구성  


--------------------------------------
@MVC 어노테이션 기반 컨트롤러 
 * @Controller
 * @RequestMapping 
 * @ModelAttribute 
 * @RequestParam
 - @SessionAttributes

 - @GetMapping  = @RequestMapping(method = RequestMethod.GET)
 - @PostMapping = @RequestMapping(method = RequestMethod.POST)
 

 - RESTful 기반일 때 많이 사용 
 * @RestController : REST 기반의 컨트롤러 지정 
                    자동으로 모든 요청의 메서드는 반환은  @ResponseBody 붙은것과 동일
                       
 * @ResponseBody : 리턴객체를 그대로 응답의 본체로 내보내기  
 * @PathVariable : 요청경로의 정보를 그대로 변수에 할당
                   @RequestMapping(value = "/board/{no}/view", "/board/{no}/edit" 
	               @PathVariable("no") int bo_no                    
                    
 - @RequestBody  : 요청정보자체를 그대로 받아들이기  
 


@SessionAttributes 언제 사용하나요 ???
입력하는 정보가 많아서 , 화면을 분할 
 - step1 : 가입동의확안, 개인정보처리 동의
 - step2 : 필수정보 입력 
 - step3 : 필수, 선택 정보 입력 
 - step4 : 가입처리  
 ? 단계를 거치지않고 오면
 ? 각 단계별로 입력한 정보의 저장은 어떻게
   - 각 이전단계의 정보를 hidden 으로 숨겨서 마지막 단계까지 끌고 이동
   - DB 에 임시저장소를 생성해서 각 단계별 저장을 하고
     마지막 단계에서 취합해서 처리  
 * 해당 객체를 사용하려고 할때 세션에 존재해야 합니다.
 * 최종단계에서는 세션을 정리(SessionStatus.setComplete())

css/main.css 생성 

body {
  min-height: 2000px;
  padding-top: 70px;
}

inc/common_header.jsp 에 main.css 추가  

<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">

-----------------------------
Bean Validation  

javax.validation.constraints. 

하이버네이트에서 확장된 어노테이션 
org.hibernate.validator.constraints.
@URL; @Range; @CreditCardNumber; @Email; @NotBlank; @NotEmpty;


1. Spring Validator 구현체로 검증
  -- 등록할 때, 수정할 때 별개로 해서 구성 
      (BoardRegistValidator , BoardModifyValidator .. )
      
2. @Valid 로 자동 검증 
  -- Spring Validator 구현하지 않아도 되죠... 
  
  -- 등록할 때, 수정할 때 별개로 해야 하는데.... 음.. 
  어노테이션 속성에 group 을 활용해서 구현하실수 있습니다.
  기존 @Valid 는 아직 구현되지 않아서 
  스프링에서 제공하는 @Validated  어노테이션을 사용해야 합니다. 
   
  com.study.common.valid 에 RegistType, ModifyType 인터페이스 생성(껍데기만)  
  -- 모등 검증 어노테이션에 groups 를 기술..안하셔도 되는데...
  -- 스프링 Default 라는것을 기술하시면 됩니다. @Validator 에.... 
     javax.validation.groups.Default 클래스 넣어주세요
     
  
  -- 게시판 수정, 수정처리 부분을 최대한 스프링답게 해주세요 
   boardEdit.jsp 도 기존것 백업하시고 최대한 스프링 폼 태그 적용해주시고
   해당 컨트롤러 매핑 메서드에도 @Valid 적용해주시고 깔끔하게...
   
  
-- IoC/DI 
 제어의 역전 : 객체생성 및 관리를 개발자가 했어요  
               이게 컨테이너에게 넘겨주었죠
  A a = new A(); // 개발자가 직접 생성 
  
  @autowire 
  A a;      // 컨테이너에 의해 생성된 객체를 주입(injection) 받음 
----
 Spring DI 설정하는 방법 3가지 
  - 1. XML 로 설정
  - 2. 최소의 xml 설정 + 어노테이션    
  - 3. Java 설정 + 어노테이션
                  
  - AppMain : main 
   - OldMan  
   - DiMan
   - Sparrow : SamsungPhone 
   - Pigeon  : IPhone

-- JSP교재 428 ~ 440 page 정독해 주세요 

-- Inject과 관련한 어노테이션 

 @Autowire : 타입기반, 필드, 세터, 생성자  
 @Resource : JSR 250 DI를 위해 생성
             이름기반, 필드, 세터 
             한개의 변수에 DI 적용시 사용      
 @Inject : JSR 330 DI 표준으로 
           - @Autowire 동급 필드, 세터, 생성자 
 @Value : 일반적인 값, 시스텝의 설정값을 셑팅 하는 용도                              


-- 이클립스  플러그인
STS : 스프링 관련 
Tern clipse :  JSP에서  자바스크립트, jQuery
MyBatipse : MyBatis 관련
    
--------------------------------------------------
  select : resultType O  
  insert,update, delete : resultType X

 * 파라미터 설정 
 #{param} : PreparedStatement ?  
  where mem_id = #{mem_id}
   ->  where mem_id = ? 
       pstmt.setString(1, vo.getMem_id()) 
 ${param} : Statement , 구문에 직접 대입  (a001) 
   where mem_id = ${mem_id}
   -> where mem_id = a001  <- 오류 
   where ${col} = 'N'     - mem_del
   -> where mem_del = 'N'    
   
구문을 재사용 
<sql />  <include />   
   
* mybatis xml 파일로 작성 
  크다, 작다의  < , > 문제 발생 
  html표기법 &lt;  &gt;
  또는 <![CDATA[   ]]> 안에 표기      

    
  #{mem_id}  객체의 getter 입니다. 
  프리미티브형인 경우는 아무이름으로 기술 가능 
  
-- 이번시간에 BoardDaoOracle 에서 했던 모든 내용이 board.xml에 기술하시면 됩니다. 

java : IBoardDao 클래스 @Mapper 선어
       CommonCodeDaoOracle 클래스에  @Repository 선언
       BoardController 에 IBoardDao , ICommonCodeDao 에 Injection 했습니다.
resoucrce : mybatis-config.xml  
            mapper/board.xml <- IBoardDao 에 대한 매핑파일    
            spring/ mvc-servlet.xml : scan 제한
	        spring/ context-main.xml , context-datasource.xml 참고
	         
selectBoard : 임용오 
insertBoard : 조용혁 
updateBoard : 최명환
deleteBoard : 송지수
increaseBoardCount : 김동한 



외부 클래스의 정적 메서드 호출 가능  
<when test="@org.apache.commons.lang3.StringUtils@isNotBlank(searchWord)" >
                     
-- 
게시판에 신규로 글을 등록했죠.. 
 - 현재 성공했어요.. -> 목록으로  
 - 글 등록하고 해당글로 바로 상세보기 가고 싶어??? (지수엄마)
 - insert into board (bo_no, bo_title, ...) values ( seq_board.nextval, ?,.. )
 
  dao.selectPk() :  select  seq_board.nextval from dual -> boardVo에 담고 
  dao.insertBoard() : insert into board (bo_no, bo_title, ...) values (?, ?,.. )

 - mybatis 의 insert에 이 기능이 존재합니다.   
  

- 게시판 관리자가  스팸관련 글을 삭제하고 싶죠.. 
   근데... 현재 상세보기에서 삭제하고 있습니다.
  목록에서 체크박스로 선택해서 삭제해줘~~ 요청 
 
 - mybatis 의 동적SQL 에서 foreach 확인해 주세요    
   
 - join, 통계쿼리 :  resultMap    
 
 ----- 
 서비스 구현
  - 비즈니스 로직 구현
  - 객체 검증(업무적으로 맞는지)
  - 업무 오류가있는 경우 예외 던지기(글존재유무, 패스워드가 맞지않을때 등 )
  - 트랜잭션 처리    
   
 board, member, code, reply 
 ---- 
 파일 업로드 
 JSP 교재 (마지막에 있는 부록 B ) 를 정독해주세요 6장 
 실습은 정독한 후에 다음시간에 할게요....
 
 -----------------------------
 mail3.nextit.or.kr 
 id : student
 pw : student8850
 -----------------------------
 서블릿 3.0기반의 web.xml의 스키마 2.5
 
 
 
 
 
 
 
 
