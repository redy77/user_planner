package ru.victor.user_planner.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.services.ScheduleService;
import ru.victor.user_planner.services.WorkerService;
import java.sql.SQLException;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final WorkerService workerService;

    public ScheduleController(ScheduleService scheduleService, WorkerService workerService) {
        this.scheduleService = scheduleService;
        this.workerService = workerService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<Schedule> addToSchedule(@RequestBody Schedule schedule) {
        scheduleService.addSchedule(schedule);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduleService.getScheduleWithId(schedule));
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<String> handleUniqueConstrain()  {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Too many work");
    }
}

