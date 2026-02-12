package kr.spartaclub.schedule_api.service;

import jakarta.servlet.http.HttpSession;
import kr.spartaclub.schedule_api.dto.CreateScheduleRequest;
import kr.spartaclub.schedule_api.dto.CreateScheduleResponse;
import kr.spartaclub.schedule_api.dto.GetOneScheduleResponse;
import kr.spartaclub.schedule_api.dto.UpdateScheduleRequest;
import kr.spartaclub.schedule_api.dto.UpdateScheduleResponse;
import kr.spartaclub.schedule_api.entity.Schedule;
import kr.spartaclub.schedule_api.entity.User;
import kr.spartaclub.schedule_api.repository.ScheduleRepository;
import kr.spartaclub.schedule_api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleService {

    private static final String LOGIN_USER = "LOGIN_USER";

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 로그인 유저 확인
    private Long getLoginUserId(HttpSession session) {
        Object userId = session.getAttribute(LOGIN_USER);

        if (userId == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "로그인이 필요합니다."
            );
        }

        return (Long) userId;
    }

    // 일정 생성
    @Transactional
    public CreateScheduleResponse createSchedule(
            CreateScheduleRequest request,
            HttpSession session
    ) {

        Long loginUserId = getLoginUserId(session);

        User user = userRepository.findById(loginUserId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "유저를 찾을 수 없습니다."
                        )
                );

        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                user
        );

        Schedule saved = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                user.getUsername(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getSchedules() {

        return scheduleRepository.findAll()
                .stream()
                .map(s -> new GetOneScheduleResponse(
                        s.getId(),
                        s.getTitle(),
                        s.getContent(),
                        s.getUser().getUsername(),
                        s.getCreatedAt(),
                        s.getModifiedAt()
                ))
                .toList();
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getSchedule(Long id) {

        Schedule s = scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "일정을 찾을 수 없습니다."
                        )
                );

        return new GetOneScheduleResponse(
                s.getId(),
                s.getTitle(),
                s.getContent(),
                s.getUser().getUsername(),
                s.getCreatedAt(),
                s.getModifiedAt()
        );
    }

    // 일정 수정
    @Transactional
    public UpdateScheduleResponse updateSchedule(
            Long id,
            UpdateScheduleRequest request,
            HttpSession session
    ) {

        Long loginUserId = getLoginUserId(session);

        Schedule s = scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "일정을 찾을 수 없습니다."
                        )
                );

        if (!s.getUser().getId().equals(loginUserId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "수정 권한이 없습니다."
            );
        }

        String newTitle = (request.getTitle() != null)
                ? request.getTitle()
                : s.getTitle();

        String newContent = (request.getContent() != null)
                ? request.getContent()
                : s.getContent();

        s.update(newTitle, newContent);

        return new UpdateScheduleResponse(
                s.getId(),
                s.getTitle(),
                s.getContent(),
                s.getUser().getUsername(),
                s.getCreatedAt(),
                s.getModifiedAt()
        );
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long id, HttpSession session) {

        Long loginUserId = getLoginUserId(session);

        Schedule s = scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "일정을 찾을 수 없습니다."
                        )
                );

        if (!s.getUser().getId().equals(loginUserId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "삭제 권한이 없습니다."
            );
        }

        scheduleRepository.delete(s);
    }
}
