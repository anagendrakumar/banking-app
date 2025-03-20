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
    private String error;

    public ErrorResponse(LocalDateTime localDateTime, String error) {
        this.localDateTime = localDateTime;
        this.error = error;
    }


}
