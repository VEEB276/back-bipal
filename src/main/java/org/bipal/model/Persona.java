package org.bipal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Modelo para personas
 * @author Valentina Escobar Bueno
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "personas")
public class Persona implements Serializable {

    @Serial
    private static final long serialVersionUID = -330167024393046859L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "id_tipo_documento", nullable = false)
    private Long idTipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "fecha_expedicion_documento")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaExpedicionDoc;

    @Column(name = "id_genero", nullable = false)
    private Long idGenero;

    @Column(name = "id_enfoque_diferencial", nullable = false)
    private Long idEnfoqueDiferencial;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaNacimiento;

    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;

    @Column(name = "departamento_residencia")
    private String departamentoResidencia;

    @Column(name = "ciudad_residencia")
    private String ciudadResidencia;

    @Column(name = "direccion_residencia")
    private String direccionResidencia;

    @Column(name = "numero_telefono")
    private String telefono;

    @Column(name = "correo_electronico")
    private String correo;

    @Column(name = "nombre_contacto")
    private String nombreContacto;

    @Column(name = "numero_telefono_contacto")
    private String telefonoContacto;

    @Column(name = "correo_electronico_contacto")
    private String correoContacto;

    //Relaciones
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento", insertable = false, updatable = false)
    private TipoDocumento tipoDocumento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero", insertable = false, updatable = false)
    private Genero genero;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfoque_diferencial", insertable = false, updatable = false)
    private EnfoqueDiferencial enfoqueDiferencial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id_persona", insertable = false, updatable = false)
    private HojaVidaPersona hojaVidaPersona;

}
