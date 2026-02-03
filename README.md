# 일정 관리 앱

## API 명세서

### 일정 생성

1. 요청: Request header, Request body(JSON)
* Method: POST
* Endpoint: /api/schedule
* header: content-Type: application/json
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

1. 요청값
* 작성자명 (author): 선택사항

2. 반환값
* 일정 목록 (List)

3. 인증/인가 방식
* 없음

4. 데이터 및 전달 형식
* Method: GET
* URL: /api/schedule
* 요청/응답: JSON

### 선택 일정 조회

1. 요청값
* 일정 ID (id)

2. 반환값
* 일정 ID (id)
* 일정 제목 (title)
* 일정 내용 (content)
* 작성자명 (author)
* 작성일 (createdAt)
* 수정일 (modifiedAt)

3. 인증/인가 방식
* 없음

4. 데이터 및 전달 형식
* Method: GET
* URL: /api/schedule/{id}
* 요청/응답: JSON

## 일정 수정

## 일정 삭제
