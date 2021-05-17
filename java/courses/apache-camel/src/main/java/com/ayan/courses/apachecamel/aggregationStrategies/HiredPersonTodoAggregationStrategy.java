package com.ayan.courses.apachecamel.aggregationStrategies;

import com.ayan.courses.apachecamel.deserializers.TaskDeserializer;
import com.ayan.courses.apachecamel.models.Employee;
import com.ayan.courses.apachecamel.models.Person;
import com.ayan.courses.apachecamel.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Log4j2
public class HiredPersonTodoAggregationStrategy implements AggregationStrategy {

    private static final Random RANDOM = new Random();

    public List<Task> fromJson(String json) {
        try {
            ObjectMapper om = new ObjectMapper();
            SimpleModule sm = new SimpleModule();
            sm.addDeserializer(Task.class, new TaskDeserializer(Task.class));
            om.registerModule(sm);
            return Arrays.asList(om.readValue(json, Task[].class));
        } catch (JsonProcessingException e) {
            log.warn("Unable to read tasks {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
        Person p = original.getMessage().getBody(Person.class);
        Double s = RANDOM.nextDouble();
        List<Task> ts = fromJson(resource.getMessage().getBody(String.class));
        original.getMessage().setBody(new Employee(p, s, ts));
        return original;
    }

}
