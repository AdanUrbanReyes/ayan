package com.ayan.courses.apachecamel.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Employee extends Person {

    private Double salary;
    private List<Task> tasks = new ArrayList<>();

    public Employee(Person person, Double salary) {
        super(person);
        this.salary = salary;
    }

    public Employee(Person person, Double salary, List<Task> tasks) {
        super(person);
        this.salary = salary;
        this.tasks = tasks;
    }

}
