package org.bipal.repository;

import org.bipal.model.DepartamentoMunicipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de departamento municipio
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IDepartamentoMunicipioRepository extends JpaRepository<DepartamentoMunicipio, Long> {

}
