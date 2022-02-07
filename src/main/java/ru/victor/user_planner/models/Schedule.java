package ru.victor.user_planner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(uniqueConstraints = {
@UniqueConstraint(columnNames = { "date", "worker_id"})})
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Shift shift;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Worker worker;

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Schedule(LocalDate date, Shift shift, Worker worker) {
        this.date = date;
        this.shift = shift;
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }

    public enum Shift{
        SHIFT_from0_to8, SHIFT_from8_to16,
        SHIFT_from16_to24
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Schedule schedule = (Schedule) o;
        return id != null && Objects.equals(id, schedule.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
