package ru.victor.user_planner.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Worker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Worker(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Worker worker = (Worker) o;
        return id != null && Objects.equals(id, worker.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
