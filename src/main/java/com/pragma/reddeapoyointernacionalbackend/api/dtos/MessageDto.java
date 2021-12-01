package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class MessageDto {

    private String code;
    private String message;

    public String getMessage() {
        return message;
    }
    public String getCode() {return code;}
}
