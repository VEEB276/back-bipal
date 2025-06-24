package org.bipal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Modelo para usuarios roles
 * @author Valentina Escobar Bueno
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "usuarios_roles")
public class UsuarioRol implements Serializable {

    @Serial
    private static final long serialVersionUID = -1491722393679852638L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "nombre_usuario")
    private String nombre;

    @Column(name = "rol")
    private String rol;

}
