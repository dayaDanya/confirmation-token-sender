package com.goncharov.securityapi.exceptions;

public class EmailNotUniqueException extends RuntimeException{
    public EmailNotUniqueException(String field) {
        super("Email is already used: " + field);
    }

}
