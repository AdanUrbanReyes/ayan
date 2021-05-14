package com.ayan.courses.apachecamel.routes;

import com.ayan.courses.apachecamel.processors.PersonsHirerProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class PersonsHirerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:personsHirer")
                .log("Hiring ${header.id} ${body}")
                .bean(PersonsHirerProcessor.class)
                .log("${body} hired")
                .to("direct:addPersonsTodo");
    }

}
