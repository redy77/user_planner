package ru.victor.user_planner.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.victor.user_planner.exeptions.NotFoundScheduleException;
import ru.victor.user_planner.exeptions.NotFoundScheduleIDException;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.services.ScheduleService;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    @Tag(name="Post schedule", description="Creating a shift for the selected worker. Returns a new schedule. If a shift has already been scheduled for that day, it returns the response \"Too much work\".")
    public ResponseEntity<Schedule> addToSchedule(@RequestBody Schedule schedule) throws NotFoundScheduleException {
        scheduleService.addSchedule(schedule);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduleService.getScheduleWithId(schedule));
    }

    @PutMapping("/update_schedule")
    @Tag(name = "Update schedule", description="Updating the schedule for the selected worker. Accepts a schedule with an ID not equal to 0.")
    public ResponseEntity<Schedule> updateSchedule(@RequestBody Schedule schedule) throws NotFoundScheduleIDException {
        scheduleService.updateSchedule(schedule);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduleService.getScheduleWithId(schedule));
    }


}

