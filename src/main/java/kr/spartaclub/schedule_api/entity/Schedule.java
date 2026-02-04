package kr.spartaclub.schedule_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String title;   // 일정 제목

    @Column(length = 200, nullable = false)
    private String content; // 일정 내용

    @Column(nullable = false)
    private String author;  // 작성자명

    @Column(nullable = false)
    private String password; // 비밀번호

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 작성일

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정일

    // 일정 생성
    public Schedule(String title, String content, String author, String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    // 일정 수정 (제목, 작성자명만 가능)
    public void update(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // 비밀번호 검증
    public boolean isPasswordMatch(String password) {
        return this.password.equals(password);
    }
}
