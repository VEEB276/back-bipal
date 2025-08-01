package org.bipal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bipal.util.IConstantesBipal;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * DTO que contiene la información de hoja de vida de una persona
 * @version  1.0
 * @author Valentina Escobar
 */
@Getter
@Setter
@NoArgsConstructor
public class HojaVidaPersonaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4052069810614558350L;

    private Long id;

    private Long idPersona;

    @JsonFormat(pattern = IConstantesBipal.PATTERN_DATE_CLIENTE, timezone = IConstantesBipal.ZONA_HORARIA_COLOMBIA)
    private Date fechaCreacion;

    @JsonFormat(pattern = IConstantesBipal.PATTERN_DATE_CLIENTE, timezone = IConstantesBipal.ZONA_HORARIA_COLOMBIA)
    private Date fechaModificacion;

}
