package ru.victor.user_planner.exeptions;

public class NotFoundScheduleIDException extends RuntimeException {
    public NotFoundScheduleIDException() {
        System.out.println("Schedule must be with ID");
    }
}