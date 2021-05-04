package com.ayan.courses.aspectorientedprogramming.components;

import com.ayan.courses.aspectorientedprogramming.models.BasicCalculator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ArithmeticCalculatorComponent implements BasicCalculator {

    @Override
    public double addition(double one, double two) {
        double r = one + two;
        log.info("{} + {} = {}", one, two, r);
        return r;
    }

    @Override
    public double subtract(double minuend, double subtrahend) {
        if (subtrahend > minuend) {
            log.info("{} - {} = {}", minuend, subtrahend, "Subtrahend must be greater than minuend");
            throw new IllegalArgumentException("Subtrahend must be greater than minuend");
        }
        double r = minuend - subtrahend;
        log.info("{} - {} = {}", minuend, subtrahend, r);
        return r;
    }

    @Override
    public double product(double multiplicand, double multiplier) {
        double r = multiplicand * multiplier;
        log.info("{} * {} = {}", multiplicand, multiplier, r);
        return multiplicand * multiplier;
    }

    @Override
    public double divide(double dividend, double divisor) {
        if (divisor == 0) {
            log.info("{} / {} = {}", dividend, divisor, "Divisor can't be zero");
            throw new IllegalArgumentException("Divisor can't be zero");
        }
        double r = dividend / divisor;
        log.info("{} / {} = {}", dividend, divisor, r);
        return dividend / divisor;
    }

    @Override
    public String toString() {
        return "SUPER arithmetic calculator";
    }

}