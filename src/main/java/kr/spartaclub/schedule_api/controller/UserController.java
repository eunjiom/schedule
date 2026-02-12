package kr.spartaclub.schedule_api.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import kr.spartaclub.schedule_api.dto.*;
import kr.spartaclub.schedule_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> signup(
            @Valid @RequestBody CreateUserRequest request) {
        CreateUserResponse response = userService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session
            ) {
        String message = userService.login(request, session);

        return ResponseEntity.ok(message);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetOneUserResponse>> getUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetOneUserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUser(id));
    }


    // 유저 수정
    @PatchMapping("/{id}")
    public ResponseEntity<GetOneUserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request
            )
    {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
}
