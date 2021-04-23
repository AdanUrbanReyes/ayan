package com.ayan.interviews.transactionpropagation.exceptions;

import lombok.Getter;

@Getter
public final class InsufficientFundsException extends RuntimeException {

    private final Double requested;
    private final Double actual;

    public InsufficientFundsException(Double requested, Double actual) {
        super(String.format("The origin account has %f funds but the requested are %f", actual, requested));
        this.requested = requested;
        this.actual = actual;
    }

}
