package com.ayan.courses.apachecamel.routes;

import com.ayan.courses.apachecamel.processors.LogProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FilesRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .bean(LogProcessor.class)
                .to("file:files/output");
    }

}