package kr.spartaclub.schedule_api.repository;

import kr.spartaclub.schedule_api.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

