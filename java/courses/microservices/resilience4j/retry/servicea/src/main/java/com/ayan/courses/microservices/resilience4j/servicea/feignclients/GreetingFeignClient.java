package com.ayan.courses.microservices.resilience4j.servicea.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "greeting"
        , url = "${feign.client.config.greeting.url}")
public interface GreetingFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "{name}")
    String general(@PathVariable("name") String name);

}
