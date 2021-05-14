package com.ayan.courses.apachecamel.models;

import com.ayan.courses.apachecamel.enums.SexEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private String fullName;
    private Byte age;
    private SexEnum sex;

    public Person(Person person) {
        fullName = person.fullName;
        age = person.age;
        sex = person.sex;
    }

}
