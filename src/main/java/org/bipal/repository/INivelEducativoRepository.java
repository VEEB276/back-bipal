package org.bipal.repository;

import org.bipal.model.NivelEducativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de nivel educativo
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface INivelEducativoRepository extends JpaRepository<NivelEducativo, Long> {

}
