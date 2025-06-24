package org.bipal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Modelo para departamentos municipios
 * @author Valentina Escobar Bueno
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "departamentos_municipios")
public class DepartamentoMunicipio implements Serializable {

    @Serial
    private static final long serialVersionUID = 8184893602652129544L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "municipio")
    private String municipio;

}
