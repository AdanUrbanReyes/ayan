package com.ayan.courses.apachecamel.models;

import com.ayan.courses.apachecamel.enums.TaskStatusEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {

    private String title;
    private TaskStatusEnum status;

}
