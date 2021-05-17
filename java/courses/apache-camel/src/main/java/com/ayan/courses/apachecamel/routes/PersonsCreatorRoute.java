package com.ayan.courses.apachecamel.routes;


import com.ayan.courses.apachecamel.processors.PersonsCreatorProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PersonsCreatorRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:personsCreator?period=7000")
                .bean(PersonsCreatorProcessor.class)
                .log("Sending created person ${header.id} ${body}")
                .to("direct:personsHirer");
    }

}
