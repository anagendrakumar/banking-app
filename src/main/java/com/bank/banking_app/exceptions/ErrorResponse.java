package com.bank.banking_app.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime localDateTime;
    private String message;
    private int Status;
    private String error;

    public ErrorResponse(LocalDateTime localDateTime, String message, int status, String error) {
        this.localDateTime = localDateTime;
        this.message = message;
        Status = status;
        this.error = error;
    }


}
