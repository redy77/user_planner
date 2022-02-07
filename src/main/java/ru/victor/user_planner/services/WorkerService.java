package ru.victor.user_planner.services;

import org.springframework.stereotype.Service;
import ru.victor.user_planner.models.Worker;
import ru.victor.user_planner.repo.WorkerRepo;
import javax.transaction.Transactional;

@Service
public class WorkerService {

    private final WorkerRepo workerRepo;

    public WorkerService(WorkerRepo workerRepo) {
        this.workerRepo = workerRepo;
    }

    @Transactional
    public void addWorker(Worker worker){
        workerRepo.save(worker);
    }

    public Worker getWorker(Long id){
       return workerRepo.findById(id).get();
    }
}
