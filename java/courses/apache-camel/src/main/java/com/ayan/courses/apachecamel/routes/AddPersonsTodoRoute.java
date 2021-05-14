package com.ayan.courses.apachecamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class AddPersonsTodoRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:addPersonsTodo")
                .log("Adding to do to ${body}")
                .to("https://jsonplaceholder.typicode.com/todos");
    }

}
