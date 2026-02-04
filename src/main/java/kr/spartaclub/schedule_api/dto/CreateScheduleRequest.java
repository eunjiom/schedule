package kr.spartaclub.schedule_api.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String title;   // 일정 제목
    private String content; // 일정 내용
    private String author;  // 작성자명
    private String password; // 비밀번호
}
