package kr.spartaclub.schedule_api.repository;

import kr.spartaclub.schedule_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 로그인 할 때 등록된 유저인지 확인
    Optional<User> findByEmail(String email);
    // 회원가입 할 때 이미 있는 이메일인지 확인
    boolean existsByEmail(String email);
}
