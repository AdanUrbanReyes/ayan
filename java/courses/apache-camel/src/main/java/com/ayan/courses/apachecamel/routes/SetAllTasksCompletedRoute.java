package com.ayan.courses.apachecamel.routes;

import com.ayan.courses.apachecamel.models.Employee;
import com.ayan.courses.apachecamel.processors.SetCompletedTaskProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SetAllTasksCompletedRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:setAllTasksCompleted")
                .process(exchange -> {
                    Employee e = exchange.getMessage().getBody(Employee.class);
                    exchange.getMessage().setBody(e.getTasks());
                })
                .split(body())
                .log("Setting ${body} completed")
                .bean(SetCompletedTaskProcessor.class)
                .log("${body}")
                .end();
    }

}
