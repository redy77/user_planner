package ru.victor.user_planner.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.services.ScheduleService;
import ru.victor.user_planner.services.WorkerService;

import javax.persistence.NonUniqueResultException;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final WorkerService workerService;

    public ScheduleController(ScheduleService scheduleService, WorkerService workerService) {
        this.scheduleService = scheduleService;
        this.workerService = workerService;
    }

    @PostMapping("/schedule")
    public @ResponseBody ResponseEntity<Schedule> addToSchedule(@RequestBody Schedule schedule) throws NonUniqueResultException {
        scheduleService.addSchedule(schedule);
        return new ResponseEntity<>(scheduleService.getScheduleWithId(schedule), HttpStatus.CREATED);
    }
}
