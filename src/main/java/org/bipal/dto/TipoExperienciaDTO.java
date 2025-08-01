package org.bipal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO que contiene la información del tipo de experiencia
 * @version  1.0
 * @author Valentina Escobar
 */
@Getter
@Setter
@NoArgsConstructor
public class TipoExperienciaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 260080639281819156L;

    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean habilitado;

}
