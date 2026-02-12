package kr.spartaclub.schedule_api.controller;

import kr.spartaclub.schedule_api.dto.*;
import kr.spartaclub.schedule_api.repository.ScheduleRepository;
import kr.spartaclub.schedule_api.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor

public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public CreateScheduleResponse createSchedule(@RequestBody CreateScheduleRequest request) {
        return scheduleService.save(request);
    }

    @GetMapping("/schedule/{scheduleId}")
    public GetOneScheduleResponse getOne(@PathVariable Long scheduleId) {
        return scheduleService.getOne(scheduleId);
    }

    @GetMapping("/schedule")
    public List<CreateScheduleResponse> getAll(
            @RequestParam(required = false) String author
    ) {
        return scheduleService.getAll(author);
    }

    @PatchMapping("/schedule/{scheduleId}")
    public UpdateScheduleResponse update(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return scheduleService.update(scheduleId, request);
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public void delete(
            @PathVariable Long scheduleId,
            @RequestBody DeleteScheduleRequest request
    ){
        scheduleService.delete(scheduleId, request);
    }
}
