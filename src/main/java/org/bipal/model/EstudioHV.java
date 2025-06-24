package org.bipal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Modelo para estudios hojas de vida
 * @author Valentina Escobar Bueno
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "estudios_hojas_vida")
public class EstudioHV implements Serializable {

    @Serial
    private static final long serialVersionUID = 1813433944496314142L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "id_hoja_vida", nullable = false, insertable = false, updatable = false)
    private Long idHojaVida;

    @Column(name = "id_nivel_educativo", nullable = false, insertable = false, updatable = false)
    private Long idNivelEducativo;

    @Column(name = "graduado")
    private Boolean graduado;

    @Column(name = "semestres_aprobados")
    private Integer semestresAprobados;

    @Column(name = "nombre_titulo")
    private String nombreTitulo;

    @Column(name = "nombre_institucion")
    private String nombreInstitucion;

    //Relaciones
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoja_vida", insertable = false, updatable = false)
    private HojaVidaPersona hojaVidaPersona;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_educativo", insertable = false, updatable = false)
    private NivelEducativo nivelEducativo;

}
