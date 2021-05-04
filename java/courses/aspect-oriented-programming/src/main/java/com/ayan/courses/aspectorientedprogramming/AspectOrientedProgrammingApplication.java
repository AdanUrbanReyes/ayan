package com.ayan.courses.aspectorientedprogramming;

import com.ayan.courses.aspectorientedprogramming.models.BasicCalculator;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Log4j2
public class AspectOrientedProgrammingApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(AspectOrientedProgrammingApplication.class, args);
        BasicCalculator bc = ac.getBean(BasicCalculator.class);
        // for follow call will be executed an advice before the actual product code
        bc.product(12, 5);
        bc.addition(2, 2);
        /**
         * for follow divide call 2 methods the after advice will be execute after the actual divide code
         * and JUST FOR THE SECOND CALL the afterThrowing advice also will be executed cause divide call will
         * throw an exception
         */
        bc.divide(0, 3);
        try {
            bc.divide(3, 0);
        } catch (IllegalArgumentException iae) {
            log.warn(iae.getMessage());
        }
        /**
         * for follow subtract 2 methods JUST FOR THE FIRST CALL an advice will be execute after the actual subtract code.
         * The second call not executed the advice cause it throws an exception
         */
        bc.subtract(13711, 137);
        try {
            bc.subtract(137, 13711);
        } catch (IllegalArgumentException iae) {
            log.warn(iae.getMessage());
        }
    }

}
