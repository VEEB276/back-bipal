package org.bipal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO que contiene la información de enfoque diferencial
 * @version  1.0
 * @author Valentina Escobar
 */
@Getter
@Setter
@NoArgsConstructor
public class EnfoqueDiferencialDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3744521855043172144L;

    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean habilitado;

}
