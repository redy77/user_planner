package ru.victor.user_planner.exeptions;

import java.sql.SQLException;

public class NotFoundScheduleException extends RuntimeException {
    public NotFoundScheduleException() {
        System.out.println("Schedule not found");

    }
}
