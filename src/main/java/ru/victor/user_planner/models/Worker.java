package ru.victor.user_planner.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Worker(String name) {
        this.name = name;
    }
}
