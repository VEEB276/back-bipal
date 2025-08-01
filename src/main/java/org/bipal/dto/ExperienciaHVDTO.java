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
 * DTO que contiene la información de experiencia de la hoja de vida
 * @version  1.0
 * @author Valentina Escobar
 */
@Getter
@Setter
@NoArgsConstructor
public class ExperienciaHVDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 162956582564035477L;

    private Long id;

    private Long idHojaVida;

    private Long idTipoExperiencia;

    private String descripcionPerfil;

    private String nombreEmpresa;

    private String nombreCargo;

    private String dependenciaCargo;

    @JsonFormat(pattern = IConstantesBipal.PATTERN_DATE_CLIENTE, timezone = IConstantesBipal.ZONA_HORARIA_COLOMBIA)
    private Date fechaDesde;

    @JsonFormat(pattern = IConstantesBipal.PATTERN_DATE_CLIENTE, timezone = IConstantesBipal.ZONA_HORARIA_COLOMBIA)
    private Date fechaHasta;

}
