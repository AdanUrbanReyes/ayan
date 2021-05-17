package com.ayan.courses.apachecamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.Exchange.*;
import static org.apache.camel.Exchange.HTTP_URI;
import static org.springframework.http.HttpMethod.GET;

@Component
public class TodoRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:todo")
                .setHeader(HTTP_URI).constant("https://jsonplaceholder.typicode.com")
                .setHeader(HTTP_PATH).constant("/todos")
                .setHeader(HTTP_METHOD).constant(GET)
                .toD("${header." + HTTP_URI + "}");
    }

}
