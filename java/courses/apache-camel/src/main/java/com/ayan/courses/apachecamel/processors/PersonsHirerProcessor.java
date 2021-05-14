package com.ayan.courses.apachecamel.processors;

import com.ayan.courses.apachecamel.models.Employee;
import com.ayan.courses.apachecamel.models.Person;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Random;

public class PersonsHirerProcessor implements Processor {

    private static final Random RANDOM = new Random();

    @Override
    public void process(Exchange exchange) throws Exception {
        Person p = exchange.getMessage().getBody(Person.class);
        Double s = RANDOM.nextDouble();
        exchange.getMessage().setBody(new Employee(p, s));
    }

}
