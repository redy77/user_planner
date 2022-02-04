package ru.victor.user_planner.services;

import org.springframework.stereotype.Service;
import ru.victor.user_planner.exeptions.NotFoundScheduleException;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.repo.ScheduleRepo;
import javax.transaction.Transactional;
import java.sql.SQLException;

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
    public Schedule getScheduleWithId(Schedule schedule) throws NotFoundScheduleException{
        Schedule scheduleWithId = null;
            scheduleWithId = scheduleRepo.findByDateAndAndWorkerId(schedule.getDate(), schedule.getWorker().getId());
            if (scheduleWithId == null) throw new NotFoundScheduleException();
        return scheduleWithId;
    }

    @Transactional
    public void updateSchedule(Schedule schedule) throws NotFoundScheduleException{
        if (schedule.getId() == null) throw new NotFoundScheduleException();
        scheduleRepo.save(schedule);
    }

}
