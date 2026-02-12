package kr.spartaclub.schedule_api.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.spartaclub.schedule_api.dto.*;
import kr.spartaclub.schedule_api.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 생성
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> create(@Valid @RequestBody CreateScheduleRequest request,
                                                         HttpSession session) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(scheduleService.createSchedule(request, session));
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetOneScheduleResponse>> getAll() {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetOneScheduleResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    // 수정(PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponse> update(@PathVariable Long id,
                                                         @RequestBody UpdateScheduleRequest request,
                                                         HttpSession session) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, request, session));
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpSession session) {
        scheduleService.deleteSchedule(id, session);
        return ResponseEntity.noContent().build();
    }
}
