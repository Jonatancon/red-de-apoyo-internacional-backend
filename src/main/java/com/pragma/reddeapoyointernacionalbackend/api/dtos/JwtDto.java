package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
public class JwtDto {

    @NotNull
    @NotBlank
    private String token;

    public String getToken() {
        return token;
    }
}
