package org.bipal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO que contiene la información de departamento y municipio
 * @version  1.0
 * @author Valentina Escobar
 */
@Getter
@Setter
@NoArgsConstructor
public class DepartamentoMunicipioDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -301568008709643166L;

    private Long id;

    private String departamento;

    private String municipio;

    private String nombreCompleto;

}
