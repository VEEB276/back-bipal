package org.bipal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Modelo para experiencias hojas de vida
 * @author Valentina Escobar Bueno
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "experiencias_hojas_vida")
public class ExperienciaHV implements Serializable {

    @Serial
    private static final long serialVersionUID = -6702010167445321259L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "id_hoja_vida", nullable = false, insertable = false, updatable = false)
    private Long idHojaVida;

    @Column(name = "id_tipo_experiencia", nullable = false, insertable = false, updatable = false)
    private Long idTipoExperiencia;

    @Column(name = "descripcion_perfil")
    private String descripcionPerfil;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "nombre_cargo")
    private String nombreCargo;

    @Column(name = "dependencia_cargo")
    private String dependenciaCargo;

    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;

    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;

    //Relaciones
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoja_vida", insertable = false, updatable = false)
    private HojaVidaPersona hojaVidaPersona;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_experiencia", insertable = false, updatable = false)
    private TipoExperiencia tipoExperiencia;

}
