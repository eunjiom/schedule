# API 명세서

## 일정

### 일정 생성

1. 요청: Request header, Request body(JSON)
* Method: POST
* Endpoint: /api/schedule
* header: content-Type > application/json
* body: JSON
* 요청값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-------|-------------|
| title | O |String| 일정 제목 |
| content | O |String | 일정 내용 |

2. 응답: ResponseBody(JSON)
* 응답값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| id | O |Long| 일정 고유 ID |
| title | O |String | 일정 제목 |
| content | O |String | 일정 내용 |
| username | O |String | 작성자명 |
| createdAt | O |String | 작성일 |
| modifiedAt | O |String | 수정일 |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 200 | OK | 일정 생성 성공 |
| 400 |Bad Request | 요청 값 누락/형식 오류(필수값 미입력 등) |
| 500 | Internal Server Error | 서버 내부 오류 |

### 전체 일정 조회

1. 요청: Request header, Request body(JSON)
* Method: GET
* Endpoint: /api/schedule(?username=홍길동)
* header: x
* body: x
* 정렬: modifiedAt > 내림차순
* 요청값

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| username | X |String| 작성자 명으로 필터 |

2. 응답: ResponseBody(JSON)
* 응답값: 배열(List)

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| id | O |Long| 일정 고유 ID |
| title | O |String | 일정 제목 |
| content | O |String | 일정 내용 |
| username | O |String | 작성자명 |
| createdAt | O |String | 작성일 |
| modifiedAt | O |String | 수정일 |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 200 | OK | 전체 일정 조회 성공 |
| 400 | Bad Request | 쿼리 파라미터 형식 오류 |

### 선택 일정 조회

1. 요청: Request header, Request body(JSON)
* Method: GET
* Endpoint: /api/schedule/{id}
* header: x
* body: x
* 요청값

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
|id | O |Long| 조회할 일정 ID |

2. 응답: ResponseBody(JSON)
* 응답값

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| id | O |Long| 일정 고유 ID |
| title | O |String | 일정 제목 |
| content | O |String | 일정 내용 |
| username | O |String | 작성자명 |
| createdAt | O |String | 작성일 |
| modifiedAt | O |String | 수정일 |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 200 |OK | 단건 조회 성공 |
| 500 |Internal Server Error | 서버 내부 오류 |

### 일정 수정

1. 요청: Request header, Request body(JSON)
* Method: PATCH(일부 수정)
* Endpoint: /api/schedule/{id}
* header: content-Type > application/json
* body: JSON
* 요청값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-------|-------------|
| id | O |Long| 수정할 일정 ID |
| title | X |String | 수정할 일정 제목 |
| content | X |String | 수정할 내용 |

2. 응답: ResponseBody(JSON)
* 응답값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| id | O |Long| 일정 고유 ID |
| title | O |String | 수정된 제목 |
| content | O |String | 수정된 내용 |
| username | O |String | 작성자명 |
| createdAt | O |String | 작성일 |
| modifiedAt | O |String | 수정일(갱신) |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 200 |Ok | 수정 성공 |
| 401 | Unauthorized | 로그인 필요 |

* 존재하지 않는 일정 요청 시 예외 발생

### 일정 삭제

1. 요청: Request header, Request body(JSON)
* Method: DELETE
* Endpoint: /api/schedule/{id}
* header: X
* body: X
* 요청값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-------|-------------|
| id | O |Long| 삭제할 일정 ID |

2. 응답: ResponseBody(JSON)
* 응답값: 삭제 성공 시 응답 Body는 없습니다.

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 200 |Ok | 삭제 성공 |
| 401 | Unauthorized | 로그인 필요 |

* 존재하지 않는 일정 요청 시 예외 발생

## 유저

### 유저 생성(회원가입)

1. 요청: Request header, Request body(JSON)
* Method: POST
* Endpoint: /api/user/signup
* header: content-Type > application/json
* body: JSON
* 요청값

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-------|-------------|
| username | O |String| 유저명 |
| email | O |String| 이메일 |
| password | O |String | 비밀번호(8자 이상) |

2. 응답: ResponseBody(JSON)
* 응답값

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-------|-------------|
| id | O |Long| 유저 고유 ID |
| username | O |String| 유저명 |
| email | O |String| 이메일 |
| createdAt | O |String | 가입일 |
| modifiedAt | O |String | 회원정보 수정일 |

* 상태코드

| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 200 |OK| 회원가입 성공 |
| 400 |Bad Request | 요청 값 누락/형식 오류, 비밀번호 8자 미만 |

### 유저 전체 조회

1. 요청: Request header, Request body(JSON)
* Method: GET
* Endpoint: /api/user
* header: X
* body: X

2. 응답: ResponseBody(JSON)
* 응답값: 배열(List)

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-------|-------------|
| id | O |Long| 유저 고유 ID |
| username | O |String| 유저명 |
| email | O |String| 이메일 |
| createdAt | O |String | 가입일 |
| modifiedAt | O |String | 회원정보 수정일 |

* 상태코드

| 상태코드 | 메시지                   | 설명          |
| ---- | --------------------- | ----------- |
| 200  | OK                    | 유저 전체 조회 성공 |

### 유저 선택 조회

1. 요청: Request header, Request body(JSON)
* Method: GET
* Endpoint: /api/user/{id}
* header: x
* body: x
* 요청값

| 필드명 | 필수 | 타입   | 설명        |
| --- | -- | ---- | --------- |
| id  | O  | Long | 조회할 유저 ID |

2. 응답: ResponseBody(JSON)
* 응답값

| 필드명        | 필수 | 타입     | 설명       |
| ---------- | -- | ------ | -------- |
| id         | O  | Long   | 유저 고유 ID |
| username   | O  | String | 유저명      |
| email      | O  | String | 이메일      |
| createdAt  | O  | String | 가입일      |
| modifiedAt | O  | String | 회원정보 수정일 |

* 상태코드

| 상태코드 | 메시지                   | 설명         |
| ---- | --------------------- | ---------- |
| 200  | OK                    | 단건 조회 성공   |
| 404  | Not Found             | 존재하지 않는 유저 |

### 유저 수정

1. 요청: Request header, Request body(JSON)
* Method: PATCH(일부 수정)
* Endpoint: /api/user/{id}
* header: content-Type > application/json
* body: JSON
* 요청값

| 필드명      | 필수 | 타입     | 설명                      |
| -------- | -- | ------ | ----------------------- |
| id       | O  | Long   | 수정할 유저 ID(PathVariable) |
| username | X  | String | 수정할 유저명                 |
| email    | X  | String | 수정할 이메일                 |
| password    | X  | String | 수정할 비밀번호                |

2. 응답: ResponseBody(JSON)

* 응답값

| 필드명        | 필수 | 타입     | 설명           |
| ---------- | -- | ------ | ------------ |
| id         | O  | Long   | 유저 고유 ID     |
| username   | O  | String | 유저명          |
| email      | O  | String | 이메일          |
| createdAt  | O  | String | 가입일          |
| modifiedAt | O  | String | 회원정보 수정일(갱신) |

* 상태코드

| 상태코드 | 메시지                   | 설명                                     |
| ---- | --------------------- | -------------------------------------- |
| 200  | OK                    | 유저 수정 성공                               |
| 400  | Bad Request           | 요청 값 누락/형식 오류(이메일 형식 오류, 비밀번호 8자 미만 등) |
| 404  | Not Found             | 존재하지 않는 유저                             |

### 유저 삭제

1. 요청: Request header, Request body(JSON)
* Method: DELETE
* Endpoint: /api/user/{id}
* header: x
* body: x
* 요청값

| 필드명 | 필수 | 타입   | 설명                      |
| --- | -- | ---- | ----------------------- |
| id  | O  | Long | 삭제할 유저 ID(PathVariable) |

2. ResponseBody(JSON)

* 응답값: X
* 상태코드

| 상태코드 | 메시지                   | 설명         |
| ---- | --------------------- | ---------- |
| 204  | No Content                    | 유저 삭제 / 반환 메세지x   |
| 404  | Not Found             | 존재하지 않는 유저 |

## 로그인(인증)

### 로그인(쿠키/세션)

1. 요청: Request header, Request body(JSON)
* Method: POST
* Endpoint: /api/auth/login
* header: content-Type > application/json
* body: JSON
* 요청값

| 필드명      | 필수 | 타입     | 설명   |
| -------- | -- | ------ | ---- |
| email    | O  | String | 이메일  |
| password | O  | String | 비밀번호 |

2. 응답: ResponseBody(JSON)
* 응답값

| 필드명     | 필수 | 타입     | 설명     |
| ------- | -- | ------ | ------ |
| message | O  | String | 로그인 성공 |

* 상태코드

| 상태코드 | 메시지                   | 설명            |
| ---- | --------------------- | ------------- |
| 200  | OK                    | 로그인 성공(세션 생성) |
| 401  | Unauthorized          | 이메일/비밀번호 불일치  |
| 400  | Bad Request           | 요청 값 누락/형식 오류 |


## ERD

<img width="173" height="507" alt="ERD!" src="https://github.com/user-attachments/assets/9099200c-961d-4f2e-a5de-03a4defbc881" />

