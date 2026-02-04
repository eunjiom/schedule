package kr.spartaclub.schedule_api.repository;

import kr.spartaclub.schedule_api.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // author로 필터 + 수정일 내림차순
    List<Schedule> findByAuthorOrderByModifiedAtDesc(String author);

    // 전체 조회 + 수정일 내림차순
    List<Schedule> findAllByOrderByModifiedAtDesc();
}