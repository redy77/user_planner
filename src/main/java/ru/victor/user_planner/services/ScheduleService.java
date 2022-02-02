package ru.victor.user_planner.services;

import org.springframework.stereotype.Service;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.repo.ScheduleRepo;
import javax.transaction.Transactional;

@Service
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;
    private final WorkerService workerService;


    public ScheduleService(ScheduleRepo scheduleRepo, WorkerService workerService) {
        this.scheduleRepo = scheduleRepo;
        this.workerService = workerService;
    }


    @Transactional
    public void addSchedule(Schedule schedule) {
            scheduleRepo.save(schedule);
    }

    @Transactional
    public Schedule getScheduleWithId(Schedule schedule){
        return scheduleRepo.findByDateAndAndWorkerId(schedule.getDate(), schedule.getWorker().getId());
    }

    @Transactional
    public void updateSchedule(Schedule schedule){
        scheduleRepo.save(schedule);
    }

}
