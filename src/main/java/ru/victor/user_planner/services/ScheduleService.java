package ru.victor.user_planner.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.repo.ScheduleRepo;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScheduleService {

    Logger logger = Logger.getLogger(ScheduleService.class.getName());
    private final ScheduleRepo scheduleRepo;

    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public void addSchedule(Schedule schedule) {
        try {
            scheduleRepo.save(schedule);
        }catch (DataIntegrityViolationException e){
            logger.log(Level.WARNING, ("У " + schedule.getWorker().getName() +  " слишком много работы " ));
        }

    }
}
