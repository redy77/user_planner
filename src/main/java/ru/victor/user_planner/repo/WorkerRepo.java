package ru.victor.user_planner.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.victor.user_planner.models.Worker;

@Repository
public interface WorkerRepo extends CrudRepository<Worker, Long> {
}
