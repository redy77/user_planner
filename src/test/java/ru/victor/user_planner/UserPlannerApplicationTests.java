package ru.victor.user_planner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import ru.victor.user_planner.controllers.ScheduleController;
import ru.victor.user_planner.exeptions.NotFoundScheduleException;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.models.Worker;
import ru.victor.user_planner.services.ScheduleService;
import ru.victor.user_planner.services.WorkerService;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class UserPlannerApplicationTests {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private ScheduleController scheduleController;

	@Autowired
	private ScheduleService scheduleService;


	@DisplayName("добавление нового расписания")
	@Test
	void testAddToScheduleRest() {
		Worker worker1 = new Worker("Ivan");
		Worker worker = new Worker("John");
		workerService.addWorker(worker1);
		workerService.addWorker(worker);
		LocalDate date1 = LocalDate.of(2020, 12, 30);
		Schedule schedule1 = new Schedule(date1, Schedule.Shift.SHIFT_from0_to8, worker1);
		Schedule schedule2 = new Schedule(date1, Schedule.Shift.SHIFT_from16_to24, worker);
		ResponseEntity<Schedule> scheduleWithId1 =  scheduleController.addToSchedule(schedule1);
		ResponseEntity<Schedule> scheduleWithId2 =  scheduleController.addToSchedule(schedule2);
		Assertions.assertEquals(scheduleWithId1.getBody().getWorker(), worker1);
		Assertions.assertEquals(scheduleWithId1.getBody().getDate(), schedule1.getDate());
		Assertions.assertEquals(scheduleWithId2.getBody().getShift(), schedule2.getShift());
		Assertions.assertEquals(scheduleWithId1.getBody().getShift(), schedule1.getShift());
	}

	@DisplayName("ошибка добавления расписания с тем же рабочим в тот же день")
	@Test()
	void testAddToScheduleException() {
		Worker worker2 = new Worker("Klim");
		workerService.addWorker(worker2);
		Schedule schedule2 = new Schedule(LocalDate.of(2020, 12, 31), Schedule.Shift.SHIFT_from16_to24, worker2);
		Schedule schedule1 = new Schedule(LocalDate.of(2020, 12, 31), Schedule.Shift.SHIFT_from0_to8, worker2);

		assertThrows(DataIntegrityViolationException.class, ()-> {
			scheduleController.addToSchedule(schedule1);
			scheduleController.addToSchedule(schedule2);
		});
	}

	@DisplayName("ошибка получения расписания с ID")
	@Test
	void testExceptionGetWithId(){
		Worker worker = new Worker("Klim");
		workerService.addWorker(worker);
		Schedule schedule = new Schedule(LocalDate.of(2020, 12, 31), Schedule.Shift.SHIFT_from16_to24, worker);
		scheduleService.addSchedule(schedule);
		Schedule schedule1 = new Schedule(LocalDate.of(2020, 11, 30), Schedule.Shift.SHIFT_from16_to24, worker);
		assertThrows(NotFoundScheduleException.class, () ->
				scheduleService.getScheduleWithId(schedule1));
	}
}
