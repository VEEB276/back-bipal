package org.bipal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Modelo para tipos experiencia
 * @author Valentina Escobar Bueno
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "tipos_experiencia")
public class TipoExperiencia implements Serializable {

    @Serial
    private static final long serialVersionUID = -4296664553278428778L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "habilitado")
    private Boolean habilitado;

}
