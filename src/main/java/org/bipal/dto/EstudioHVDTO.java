package org.bipal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO que contiene la información de estudio de la hoja de vida
 * @version  1.0
 * @author Valentina Escobar
 */
@Getter
@Setter
@NoArgsConstructor
public class EstudioHVDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1382846718464645931L;

    private Long id;

    private Long idHojaVida;

    private Long idNivelEducativo;

    private Boolean graduado;

    private Integer semestresAprobados;

    private String nombreTitulo;

    private String nombreInstitucion;

}
