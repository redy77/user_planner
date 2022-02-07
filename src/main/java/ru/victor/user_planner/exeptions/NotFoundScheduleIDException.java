package ru.victor.user_planner.exeptions;

public class NotFoundScheduleIDException extends RuntimeException {
    public NotFoundScheduleIDException() {
        System.err.println("Schedule must be with ID");
    }
}