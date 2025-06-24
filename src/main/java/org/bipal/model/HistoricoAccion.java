package org.bipal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Modelo para historico acciones
 * @author Valentina Escobar Bueno
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "historico_acciones")
public class HistoricoAccion implements Serializable {

    @Serial
    private static final long serialVersionUID = -4328660123122420575L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "identificador_tipo")
    private String identificador;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

}
