# 일정 관리 앱

## API 명세서

## 일정 생성

1. 요청값
* 일정 제목(title)
* 일정 내용(content)
* 작성자명(author)
* 비밀번호(password)
* 작성/수정일: 자동생성 (최초 생성시 동일)

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
* Method: POST
* URL: /api/schedule
* 요청/응답: JSON

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
