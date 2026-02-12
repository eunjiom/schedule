package kr.spartaclub.schedule_api.service;

import jakarta.servlet.http.HttpSession;
import kr.spartaclub.schedule_api.dto.GetOneUserResponse;
import kr.spartaclub.schedule_api.dto.LoginRequest;
import kr.spartaclub.schedule_api.dto.CreateUserResponse;
import kr.spartaclub.schedule_api.dto.CreateUserRequest;
import kr.spartaclub.schedule_api.entity.User;
import kr.spartaclub.schedule_api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    // 회원가입
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public CreateUserResponse signup(CreateUserRequest request) {

        // 1) 이메일 중복 체크 (회원가입 규칙)
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "이미 존재하는 이메일입니다.");
        }

        // 2) User 엔티티 생성 (DB에 넣을 실제 데이터)
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );

        // 3) 저장 (DB에 INSERT)
        User savedUser = userRepository.save(user);

        // 4) 응답 DTO로 변환
        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    // 로그인

    @Transactional
    public String login(LoginRequest request, HttpSession session) {

        // 이메일 유저 찾기(없으면 로그인 실패)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "이메일 또는 비밀번호가 올바르지 않습니다"
                ));

        // 비밀번호 확인(틀리면 로그인 실패)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "이메일 또는 비밀번호가 올바르지 않습니다"
            );
        }

        // 로그인 성공 > 세션에 로그인 정보 저장
        session.setAttribute("LOGIN_USER", user.getId());

        return "로그인 성공";
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetOneUserResponse findUser(Long id){
        User user = userRepository.findByEmail(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "유저를 찾을 수 없습니다."
                ));

        return new GetOneUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 전체조회

}
