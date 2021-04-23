package com.ayan.interviews.transactionpropagation.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseBody<B> {

    private final String message;
    private final B body;

    public ResponseBody(String message) {
        this.message = message;
        body = null;
    }

    public ResponseBody(B body) {
        message = null;
        this.body = body;
    }

}
