package ru.victor.user_planner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.models.Worker;
import ru.victor.user_planner.services.ScheduleService;
import ru.victor.user_planner.services.WorkerService;
import java.time.LocalDate;

@SpringBootTest
class UserPlannerApplicationTests {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private WorkerService workerService;

	@Test
	void contextLoads() {
	}



	@Test
	void testAddSchedule(){
		Worker worker1 = new Worker("Ivan");
		workerService.addWorker(worker1);
		Schedule schedule1 = new Schedule(LocalDate.of(2020, 11, 3), Schedule.Shift.SHIFT_from0_to8, worker1);
		Schedule schedule2 = new Schedule(LocalDate.of(2020, 11, 3), Schedule.Shift.SHIFT_from0_to8, worker1);
		scheduleService.addSchedule(schedule1);
		scheduleService.addSchedule(schedule2);
	}

}
