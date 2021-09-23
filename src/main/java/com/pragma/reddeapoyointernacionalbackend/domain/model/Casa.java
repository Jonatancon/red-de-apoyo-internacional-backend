package com.pragma.reddeapoyointernacionalbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Casa {

    private String direccion;
    private String ciudad;
    private String pais;
    private String telefono;
    private byte[] foto;
    private Usuario usuario;
}
