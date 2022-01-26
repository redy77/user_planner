package ru.victor.user_planner.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Table(name = "schedule", uniqueConstraints =
@UniqueConstraint(columnNames = {"date", "worker_id"}))
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Shift shift;
    @OneToOne
    private Worker worker;

    public Schedule(LocalDate date, Shift shift, Worker worker) {
        this.date = date;
        this.shift = shift;
        this.worker = worker;
    }

    public enum Shift{
        SHIFT_from0_to8, SHIFT_from8_to16,
        SHIFT_from16_to24
    }
}
