package kr.spartaclub.schedule_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateScheduleRequest {

    private String title;   // 부분 수정하고 싶으면 null 허용
    private String content; // 부분 수정하고 싶으면 null 허용
}

