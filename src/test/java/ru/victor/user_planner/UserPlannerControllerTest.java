package ru.victor.user_planner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ru.victor.user_planner.models.Schedule;
import ru.victor.user_planner.models.Worker;
import ru.victor.user_planner.repo.ScheduleRepo;
import ru.victor.user_planner.repo.WorkerRepo;
import java.time.LocalDate;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserPlannerControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ScheduleRepo scheduleRepo;

    @Autowired
    WorkerRepo workerRepo;

    @Test
    public void testControllerPost() {
        Worker worker = new Worker("Ivan");
        workerRepo.save(worker);
        Schedule schedule = new Schedule(LocalDate.of(2020, 5, 21), Schedule.Shift.SHIFT_from0_to8,
                worker);
        ResponseEntity<Schedule> response = restTemplate.postForEntity("/schedule", schedule, Schedule.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(Objects.requireNonNull(response.getBody()).getId(), notNullValue());
        assertThat(response.getBody().getWorker(), equalTo(schedule.getWorker()));
        assertThat(response.getBody().getDate(), equalTo(schedule.getDate()));
        assertThat(response.getBody().getShift(), equalTo(schedule.getShift()));
    }

    @Test
    public void testControllerTooManyWork() {
        Worker worker = new Worker("Ivan");
        workerRepo.save(worker);
        Schedule schedule = new Schedule(LocalDate.of(2020, 5, 21), Schedule.Shift.SHIFT_from0_to8, worker);
        Schedule schedule1 = new Schedule(LocalDate.of(2020, 5, 21), Schedule.Shift.SHIFT_from16_to24, worker);
        restTemplate.postForEntity("/schedule", schedule, String.class);
        ResponseEntity<String> response = restTemplate.postForEntity("/schedule", schedule1, String.class);
        assertTrue(response.getBody().contains("Too many work"));
    }

    @Test
    public void testControllerUpdateSchedule() {
        Worker worker = new Worker("Ivan");
        workerRepo.save(worker);
        Schedule schedule1 = new Schedule(LocalDate.of(2020, 5, 21), Schedule.Shift.SHIFT_from0_to8, worker);
        ResponseEntity<Schedule> response1 = restTemplate.postForEntity("/schedule", schedule1, Schedule.class);
        Schedule schedule2 = scheduleRepo.findByDateAndAndWorkerId(LocalDate.of(2020, 5, 21), schedule1.getWorker().getId());
        Schedule schedule3 = new Schedule(schedule2.getId(), schedule2.getDate(), Schedule.Shift.SHIFT_from16_to24, schedule2.getWorker());
        HttpEntity<Schedule> request = new HttpEntity<>(schedule3);
        ResponseEntity<Schedule> response2 = restTemplate.exchange("/update_schedule", HttpMethod.PUT, request, Schedule.class);

        assertThat(response1.getBody().getShift(), equalTo(Schedule.Shift.SHIFT_from0_to8));
        assertThat(response2.getBody().getShift(), equalTo(Schedule.Shift.SHIFT_from16_to24));
        assertThat(response1.getBody().getId(), equalTo(response2.getBody().getId()));
    }
}

