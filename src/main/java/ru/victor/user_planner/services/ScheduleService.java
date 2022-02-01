package ru.victor.user_planner.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.repo.ScheduleRepo;

import javax.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScheduleService {

    Logger logger = Logger.getLogger(ScheduleService.class.getName());
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
        return scheduleRepo.searchScheduleByDateAndAndWorkerId(schedule.getDate(), schedule.getWorker().getId());
    }
}
