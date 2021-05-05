package com.ayan.courses.apachecamel.transformers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
/**
 * A Bean transformer method always will return something
 * for example get method return a string
 */
public class CurrentTimeTransformer {

    public static final DateTimeFormatter LONG_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String get() {
        return String.format("Current time is %s", LocalDateTime.now().format(LONG_DATETIME_FORMAT));
    }

}