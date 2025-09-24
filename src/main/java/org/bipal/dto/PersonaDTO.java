package org.bipal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bipal.util.IConstantesBipal;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * DTO que contiene la información de una persona
 * @version  1.0
 * @author Valentina Escobar
 */
@Getter
@Setter
@NoArgsConstructor
public class PersonaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6095783285259833271L;

    private Long id;

    private Long idTipoDocumento;

    private String numeroDocumento;

    @JsonFormat(pattern = IConstantesBipal.PATTERN_DATE_CLIENTE, timezone = IConstantesBipal.ZONA_HORARIA_COLOMBIA)
    private LocalDate fechaExpedicionDoc;

    private Long idGenero;

    private Long idEnfoqueDiferencial;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    @JsonFormat(pattern = IConstantesBipal.PATTERN_DATE_CLIENTE, timezone = IConstantesBipal.ZONA_HORARIA_COLOMBIA)
    private LocalDate fechaNacimiento;

    private String lugarNacimiento;

    private String departamentoResidencia;

    private String ciudadResidencia;

    private String direccionResidencia;

    private String telefono;

    private String correo;

    private String nombreContacto;

    private String telefonoContacto;

    private String correoContacto;

    private Long idHojaVida;

}
