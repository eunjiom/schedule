package kr.spartaclub.schedule_api.service;

import kr.spartaclub.schedule_api.dto.*;
import kr.spartaclub.schedule_api.entity.Schedule;
import kr.spartaclub.schedule_api.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 저장
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request){
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getPassword()

        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse( //변수 인라인화

            savedSchedule.getId(),
            savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );


    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 다 건 조회
    @Transactional(readOnly = true)
    public List<CreateScheduleResponse> getAll(String author) {
        List<Schedule> schedules;

        if (author != null) {
            // author 필터 있는 경우
            schedules = scheduleRepository.findByAuthorOrderByModifiedAtDesc(author);
        } else {
            // 전체 조회
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        }

        List<CreateScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            CreateScheduleResponse dto = new CreateScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 수정
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다")
        );

        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(
                request.getTitle(),
                request.getAuthor()
        );


        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 삭제
    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {

        // 1) 일정(=유저라고 말했는데 여기선 일정)이 없는 경우 -> 예외
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 일정입니다."));

        // 2) 일정은 있는데 비밀번호가 틀린 경우 -> 예외
        if (!schedule.isPasswordMatch(request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 3) 일정이 있고 비밀번호가 맞는 경우 -> 삭제(정상 케이스)
        scheduleRepository.delete(schedule);
    }

}
