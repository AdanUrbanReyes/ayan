package com.ayan.courses.apachecamel.processors;

import com.ayan.courses.apachecamel.enums.TaskStatusEnum;
import com.ayan.courses.apachecamel.models.Task;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SetCompletedTaskProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Task t = exchange.getMessage().getBody(Task.class);
        t.setStatus(TaskStatusEnum.COMPLETED);
        exchange.getMessage().setBody(t);
    }

}
