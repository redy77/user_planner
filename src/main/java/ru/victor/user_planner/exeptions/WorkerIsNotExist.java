package ru.victor.user_planner.exeptions;

public class WorkerIsNotExist extends RuntimeException{
    public WorkerIsNotExist(){
        System.out.println("WorkerIsNotExist");
    }
}
