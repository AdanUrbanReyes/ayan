package com.ayan.courses.apachecamel.processors;

import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * A bean processor method must return nothing (void)
 * for this example log method return void
 */
@Component
@Log4j2
public class LogProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("Processing message : {}", exchange.getMessage().getBody());
    }
}
