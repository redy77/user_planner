package ru.victor.user_planner.services;

import org.springframework.stereotype.Component;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.models.Worker;
import javax.annotation.PostConstruct;
import java.time.LocalDate;


@Component
public class StarterKit {

    private final ScheduleService scheduleService;
    private final WorkerService workerService;

    public StarterKit(ScheduleService scheduleService, WorkerService workerService) {
        this.scheduleService = scheduleService;
        this.workerService = workerService;
    }

    @PostConstruct
    private void init() {
        Worker worker1 = new Worker("Ivan");
        workerService.addWorker(worker1);

        Worker worker2 = new Worker("Dmitriy");
        workerService.addWorker(worker2);

        Schedule schedule = new Schedule(LocalDate.of(2020, 11, 2), Schedule.Shift.SHIFT_from8_to16, worker1);
        Schedule schedule1 = new Schedule(LocalDate.of(2020, 11, 20), Schedule.Shift.SHIFT_from16_to24, worker2);
        Schedule schedule2 = new Schedule(LocalDate.of(2020, 11, 2), Schedule.Shift.SHIFT_from0_to8, worker1);
        scheduleService.addSchedule(schedule);
        scheduleService.addSchedule(schedule1);
        scheduleService.addSchedule(schedule2);

    }
}
