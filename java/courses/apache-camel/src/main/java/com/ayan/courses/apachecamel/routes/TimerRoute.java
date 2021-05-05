package com.ayan.courses.apachecamel.routes;

import com.ayan.courses.apachecamel.processors.LogProcessor;
import com.ayan.courses.apachecamel.transformers.CurrentTimeTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:timer-route")
                .log("${body}")//must be null cause we are not setting any
                .bean(CurrentTimeTransformer.class)//calling a bean that will transform the stream; setting a body message
                .log("${body}")//must be the string put by CurrentTimeTransformer bean
                .bean(LogProcessor.class)//It wont transform the stream; just log the message
                .log("${body}")//must be the same string put by CurrentTimeTransformer bean; cause LogProcessor not alter the message
                .to("log:timer-route");
    }

}
