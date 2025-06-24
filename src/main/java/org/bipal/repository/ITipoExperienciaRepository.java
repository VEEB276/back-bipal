package org.bipal.repository;

import org.bipal.model.TipoExperiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de tipo experiencia
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface ITipoExperienciaRepository extends JpaRepository<TipoExperiencia, Long> {

}
