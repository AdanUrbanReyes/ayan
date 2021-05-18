package com.ayan.courses.apachecamel.routes;

import com.ayan.courses.apachecamel.aggregationStrategies.HiredPersonTodoAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonsHirerRouter extends RouteBuilder {

    @Autowired
    private HiredPersonTodoAggregationStrategy hiredPersonTodoAggregationStrategy;

    @Override
    public void configure() throws Exception {
        from("direct:personsHirer")
                .log("Hiring ${header.id} ${body}")
                .enrich("direct:todo", hiredPersonTodoAggregationStrategy)
                .log("Sending new created employee ${body}")
                .to("direct:setAllTasksCompleted");
    }

}
