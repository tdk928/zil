package org.softuni.nuggets.areas.admin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadTimeFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadTimeFormatException(String dateString) {
        super(dateString);
    }
}
