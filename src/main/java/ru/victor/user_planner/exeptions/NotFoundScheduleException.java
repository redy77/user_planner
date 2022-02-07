package ru.victor.user_planner.exeptions;

public class NotFoundScheduleException extends RuntimeException {
    public NotFoundScheduleException() {
        System.err.println("Schedule not found");

    }
}
