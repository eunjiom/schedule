# 일정 관리 앱

## API 명세서

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
| author | O |String | 작성자명 |
| password | O |String | 비밀번호 |

2. 응답: ResponseBody(JSON)
* 응답값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| id | O |Long| 일정 고유 ID |
| title | O |String | 일정 제목 |
| content | O |String | 일정 내용 |
| author | O |String | 작성자명 |
| createdAt | O |String | 작성일 |
| modifiedAt | O |String | 수정일 |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 201 |Created | 일정 생성 성공 |
| 400 |Bad Request | 요청 값 누락/형식 오류(필수값 미입력 등) |
| 500 | Internal Server Error | 서버 내부 오류 |

## 일정 조회

### 전체 일정 조회

1. 요청: Request header, Request body(JSON)
* Method: GET
* Endpoint: /api/schedule(?author=홍길동)
* header: x
* body: x
* 정렬: modifiedAt > 내림차순
* 요청값

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
|author | X |String| 작성자 명으로 필터 |

2. 응답: ResponseBody(JSON)
* 응답값: 배열(List)

| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| id | O |Long| 일정 고유 ID |
| title | O |String | 일정 제목 |
| content | O |String | 일정 내용 |
| author | O |String | 작성자명 |
| createdAt | O |String | 작성일 |
| modifiedAt | O |String | 수정일 |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 201 |Created | 전체 일정 조회 성공 |
| 400 |Bad Request | 쿼리 파라미터 형식 오류 |
| 500 | Internal Server Error | 서버 내부 오류 |


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
| author | O |String | 작성자명 |
| createdAt | O |String | 작성일 |
| modifiedAt | O |String | 수정일 |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 201 |Created | 단건 조회 성공 |
| 404 |Not Found | 해당 ID 일정 없음 |
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
| title | O |String | 수정할 일정 제목 |
| author | O |String | 수정할 작성자명 |
| password | O |String | 저장된 비밀번호 일치여부 |

2. 응답: ResponseBody(JSON)
* 응답값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| id | O |Long| 일정 고유 ID |
| title | O |String | 수정된 제목 |
| content | O |String | 기존 내용 |
| author | O |String | 수정된 작성자명 |
| createdAt | O |String | 작성일 |
| modifiedAt | O |String | 수정일(갱신) |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 200 |Ok | 수정 성공 |
| 400 |Bad Request | 요청 값 누락/형식 오류|
| 403 |Forbidden | 비밀번호 불일치 |
| 404 |Not Found | 해당 ID 일정 없음 |

### 일정 삭제

1. 요청: Request header, Request body(JSON)
* Method: DELETE
* Endpoint: /api/schedule/{id}
* header: content-Type > application/json
* body: JSON
* 요청값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-------|-------------|
| id | O |Long| 삭제할 일정 ID |
| password | O |String | 저장된 비밀번호와 일치여부 |

2. 응답: ResponseBody(JSON)
* 응답값
  
| 필드명 | 필수 | 타입 | 설명 |
|-------|-----|-----|-------------|
| message | O |String| 삭제 결과 메세지 |

* 상태코드
  
| 상태코드 | 메시지 |  설명 |
|-------|-----|-------------|
| 200 |Ok | 삭제 성공 |
| 403 |Forbidden | 비밀번호 불일치 |
| 404 |Not Found | 해당 ID 일정 없음 |
