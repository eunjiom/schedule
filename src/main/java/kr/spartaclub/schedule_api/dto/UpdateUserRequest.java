package kr.spartaclub.schedule_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

    private String username;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 8, message = "비밀번호 8글자 이상이어야 합니다.")
    private String password;

}
