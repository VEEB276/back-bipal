package org.bipal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Modelo para otros estudios hoja de vida
 * @author Valentina Escobar Bueno
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "otros_estudios_hojas_vida")
public class OtroEstudioHV implements Serializable {

    @Serial
    private static final long serialVersionUID = -2858970331910382643L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "id_hoja_vida", nullable = false)
    private Long idHojaVida;

    @Column(name = "numero_horas")
    private Integer numeroHoras;

    @Column(name = "graduado")
    private Boolean graduado;

    @Column(name = "nombre_curso")
    private String nombreCurso;

    @Column(name = "nombre_institucion")
    private String nombreInstitucion;

}
