package com.pragma.reddeapoyointernacionalbackend.domain.model;

import com.pragma.reddeapoyointernacionalbackend.domain.model.enums.NombreRol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rol {

    private NombreRol nombreRol;
}
