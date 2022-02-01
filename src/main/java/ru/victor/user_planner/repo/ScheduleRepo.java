package ru.victor.user_planner.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.victor.user_planner.models.Schedule;
import java.time.LocalDate;

@Repository
public interface ScheduleRepo extends CrudRepository<Schedule, Long> {
    @Query(value = "SELECT e from Schedule e LEFT JOIN FETCH e.worker where e.worker.id = :worker and e.date = :date")
    Schedule searchScheduleByDateAndAndWorkerId(@Param("date") LocalDate date, @Param("worker")Long worker);
}
