package ru.victor.user_planner.handlers;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.victor.user_planner.exeptions.NotFoundScheduleException;
import ru.victor.user_planner.exeptions.NotFoundScheduleIDException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerHandlers {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> emptyWork()  {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("The worker is not registered");
    }

    @ExceptionHandler(value = PSQLException.class)
    public ResponseEntity<String> handleUniqueConstrain()  {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Too much work");
    }

    @ExceptionHandler(value = NotFoundScheduleException.class)
    public ResponseEntity<String> handlerNotFoundSchedule(){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Schedule not found");
    }

    @ExceptionHandler(value = NotFoundScheduleIDException.class)
    public ResponseEntity<String> handlerNotFoundIDSchedule(){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Schedule must be with ID");
    }
}
