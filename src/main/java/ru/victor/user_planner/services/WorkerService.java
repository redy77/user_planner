package ru.victor.user_planner.services;

import org.springframework.stereotype.Service;
import ru.victor.user_planner.models.Worker;
import ru.victor.user_planner.repo.WorkerRepo;

@Service
public class WorkerService {

    private final WorkerRepo workerRepo;

    public WorkerService(WorkerRepo workerRepo) {
        this.workerRepo = workerRepo;
    }

    public void addWorker(Worker worker){
        workerRepo.save(worker);
    }
}
