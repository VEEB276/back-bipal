package org.bipal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO que contiene la información de otro estudio de la hoja de vida
 * @version  1.0
 * @author Valentina Escobar
 */
@Getter
@Setter
@NoArgsConstructor
public class OtroEstudioHVDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8831429681791714878L;

    private Long id;

    private Long idHojaVida;

    private Integer numeroHoras;

    private Boolean graduado;

    private String nombreCurso;

    private String nombreInstitucion;

}
