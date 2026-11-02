22500354 성하민
https://two6ws-week09-assignment.onrender.com/api/music


① 프로젝트 소개
주제 및 주요 기능 : Music management
Entity 필드 구조 : id(Long) - 음악 api CRUD 구현을 위함, title(String) - 곡 제목, artist (String) - 가수, category(String) - 음악 장르, releaseYear(Integer) - 발매 년도, rating(Double) - 음악 평점
프로젝트 구조 및 개발환경 : 
- Controller → Service → JpaRepository → Hibernate → MariaDB
- JDK(21), IntelliJ(26.2.1), DB(MariaDB)
GitHub Repository URL : https://github.com/2026-2-WebService/assign09-c01-22500354
Render Deployment URL : https://two6ws-week09-assignment.onrender.com/api/music


② Database SQL Practice
기존 Table 조회 및 데이터 추가 결과 : images폴더 1.1
새 Table 생성 SQL : images폴더 2.1
INSERT / UPDATE / DELETE / SELECT SQL과 실행 결과 : images 폴더 2.2 ~ 2.5.3


③ Spring Data JPA 구현
Entity 및 JpaRepository 설명
- id(primary key), title(곡 제목), artist(곡 가수), category(음악 장르), releaseYear(발매년도), rating(평점)
- String Data JPA에서 제공하는 Jpa repository 직접 상속 받아 메서드를 내가 직접 짜지 않더라도 데이터베이스의 CRUD 기능을 사용할 수 있게 한 것.
사용한 Annotation의 역할
- Application : @SpringBootApplication (Spring boot 자동설정)
- Music : @Entity(entity클래스), @Id(기본 키), @GenerateValue(자동생성키), @Column(데이터 field)
- Service : @Service (서비스 클래스)
- Controller : @RestController(REST API 요청 처리 클래스), @RestMapping(공통 URL 지정), @PostMapping(Post처리), @GetMapping(Get처리), @PutMapping(Put 처리), @DeleteMapping(delete 처리), @PathVariable(URL에서 ID값 가져오기), @RequestBody(JSON데이터 JAVA객체 변환), @Valid(유효성 검사)

REST API Endpoint 목록 및 요청·응답 JSON
    HTTP - Method	Endpoint - 기능
    POST - /api/music - 음악 등록
    GET - /api/music - 전체 음악 조회
    GET - /api/music/{id} - 특정 음악 조회
    PUT - /api/music/{id} - 특정 음악 수정
    DELETE - /api/music/{id} - 특정 음악 삭제
    1. POST - api/music { "title": "Dynamite", "artist": "BTS", "category": "Pop", "releaseYear": 2020, "rating": 9.5
    2. GET - api/music or /api/music/1
    3. PUT  - api/music/1 { "title": "Butter", "artist": "BTS","category": "Pop","releaseYear": 2021,"rating": 10.0 }
    4. DELETE - api/music/1

JPA Entity와 MariaDB Table 비교
- 형태가 Java 클래스인 반면, 데이터베이스 테이블인 것
- 데이터 표현이 객체와 필드인 반면, table은 행과 열로 나타내는 것
- 데이터 저장을 Java 객체 형태로 하는 반면, 테이블의 레코드로 나타내는 것

④ JPA와 SQL 비교
save(), findAll(), findById(), deleteById() 사용 결과
- 별도로 메서드를 추가로 적지 않아도 JpaRepository에서 제공하는 메서드를 활용하여 CRUD기능을 구혔했다.
- save()의 결과 : Music saved = repository.save(new Music(request));에서 사용하고 save를 호출해 데이터베이스에 저장 후, 자동으로 ID생성되어 MusicResponse형태로 변환해 반환한다.
- findAll() :  return repository.findAll().stream().map(MusicResponse::from).toList(); 에서 사용해 모든 음악을 조회 후 MusicResponse 객체로 변환 후 리스트 형태로 반환한다.
- findById() : api/music/1 을 통해 1이 아이디인 데이터를 조회하였고, 특히 문제 속 Id 존재 여부에 따라 예외 발생 조건을 만족시키기 위해 따로 findEntitiy 메서드를 만들어서 MusicResponse 형태로 빈횐하게 했다.
- DeleteById() : 204 No Content, 우선 해당 아이디의 데이터가 있는지 확인 후 존재하면 실행해 삭제 시키고(204), 존재하지 않을 시 404 예외 발생 하게 했다.

실제 Hibernate SQL 확인 : application.properties에 spring.jpa.show-sql=true을 추가하여 SQL문을 콘솔에서 확인할 수 있게 한다.
SQL을 직접 작성하는 방식과 JPA 방식의 차이 : 가장 큰 차이는 SQL문을 직접 작성하는 것과 작성하지 않아도 Hibernate에서 자동 생성 및제공되는 CRUD 메서드를 활용해 Java 객체 중심 관리를 할 수 있다는 것이다. 

⑤ 배포 및 환경 설정
IntelliJ 및 Render 환경변수 설정 방법(변수명만 기록) : 실행-> 구성 편집 -> 옵션수정(환경변수 추가) -> DB_URL, USERNAME, PASSWORD추가. render는 webProject 제작 시 environment에서 해당 변수 추가 
Render 배포 과정 : dockerfile 만들기 -> 개인 repo 만들어서 git push -> render에 주소를 통해 연동 -> DB 정보 environment에 추가 -> 배포
배포 URL의 CRUD 테스트 결과 : images 폴더 5.3.1 ~ 5.3.5
MariaDB 데이터 저장 확인 : images 폴더 5.4.1
발생한 문제와 해결 방법 : render에서 environment 설정을 통해 따로 DB정보를 입력해야하는 줄 모르고, 바로 배포를 시작해 오류가 발생함. 오류 코드를 찾아 검색을 통해 DB 정보를 따로 입력해야하는 것을 알고 [enviroment](https://dashboard.render.com/web/srv-  /env) 을 직접 입력해 db 정보를 추가함

⑥ Weekly Report
Key Learning: 새롭게 이해한 내용 3가지
- JpaRepository를 활용한다면 메서드를 하나씩 입력하지 않아도 CRUD 기능을 구현할 수 있다
- application.properties 환경변수로 DB 정보 입력 후 배포할 때는 render에서도 환경변수 설정해야 한다.
- 직접 SQL 작성하는 방식과 JPA를 사용하는 방식을 최종적으로 동일한 데이터 베이스를 사용하지만, INSERT문과 컬럼명을 직접 지정하는 것과 달리 save()만 호출해도 동일한 작업을 수행할 수 있다는 점에서 차이가 있다.
Problem & Solution: 문제 1개 이상과 해결 과정
- render 배포 과정에서 환경 변수 설정을 따로 하지 않아 배포 과정에서 오류 발생, 변수를 따로 처리하지 못함을 알고 Render에도 환경변수를 등록해 해결함
Code Review: 중요한 코드 1개와 동작 설명
- private Music findEntity(Long id) {
  return repository.findById(id)
  .orElseThrow(() -> new ResponseStatusException(
  HttpStatus.NOT_FOUND, "Music not found: " + id
  ));
  }
- ID를 활용해 데이터 베이스 내 특정 정보를 조회하는 역할로, 해당 ID의 데이터가 존재한다면 music객체를 반환하고 존재하지 않으면 orElseThrow()를 통해 예외 발생시킴. 
AI Usage: AI 활용 내용 및 직접 수정·검증한 부분, 자신이 생각하는 AI사용률(%)
-  render 배포 과정 오류 발생 해결, 코드 작성 중 오류 발생 해결
- 30%, 일단 과제 전 공부를 하는 과정에서 모르는 코드 및 개념이 나오면 AI 를 활용해 해당 개념과 코드를 이해했고 인터넷 검색으로 해결되지 않는 오류는 인공지능을 활용했기 때문
Reflection: 추가로 공부하고 싶은 내용
- Hibernate가 SQL을 생성하는 과정