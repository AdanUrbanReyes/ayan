package com.ayan.interviews.transactionpropagation.exceptions;

import lombok.Getter;

@Getter
public final class NoSuchAccountException extends RuntimeException {

    private final Long accountId;

    public NoSuchAccountException(Long accountId) {
        super(String.format("No such account %d founded", accountId));
        this.accountId = accountId;
    }

}
