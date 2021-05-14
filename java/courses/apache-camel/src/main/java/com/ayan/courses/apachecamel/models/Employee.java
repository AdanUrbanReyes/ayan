package com.ayan.courses.apachecamel.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Employee extends Person {

    private Double salary;

    public Employee(Person person, Double salary) {
        super(person);
        this.salary = salary;
    }

}
