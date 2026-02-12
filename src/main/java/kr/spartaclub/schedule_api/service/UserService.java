package kr.spartaclub.schedule_api.service;

import kr.spartaclub.schedule_api.dto.UserResponse;
import kr.spartaclub.schedule_api.dto.UserSignupRequest;
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
    public UserResponse signup(UserSignupRequest request) {

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
        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }
}
