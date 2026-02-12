package kr.spartaclub.schedule_api.controller;

import jakarta.validation.Valid;
import kr.spartaclub.schedule_api.dto.UserResponse;
import kr.spartaclub.schedule_api.dto.UserSignupRequest;
import kr.spartaclub.schedule_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(
            @Valid @RequestBody UserSignupRequest request) {
        UserResponse response = userService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
