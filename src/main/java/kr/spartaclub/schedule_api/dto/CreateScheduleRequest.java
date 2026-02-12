package kr.spartaclub.schedule_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateScheduleRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}