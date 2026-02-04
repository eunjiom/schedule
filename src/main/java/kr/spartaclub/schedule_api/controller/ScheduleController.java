package kr.spartaclub.schedule_api.controller;

import kr.spartaclub.schedule_api.dto.CreateScheduleRequest;
import kr.spartaclub.schedule_api.dto.CreateScheduleResponse;
import kr.spartaclub.schedule_api.dto.GetOneScheduleResponse;
import kr.spartaclub.schedule_api.entity.Schedule;
import kr.spartaclub.schedule_api.repository.ScheduleRepository;
import kr.spartaclub.schedule_api.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor

public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    @PostMapping("/schedule")
    public CreateScheduleResponse createSchedule(@RequestBody CreateScheduleRequest request) {
        return scheduleService.save(request);
    }

    @GetMapping("/schedule/{scheduleId}")
    public CreateScheduleResponse getOne(@PathVariable Long scheduleId) {
        return scheduleService.getOne(scheduleId);
    }

    @GetMapping("/schedule")
    public List<CreateScheduleResponse> getAll() {
        return scheduleService.getAll();
    }

}
