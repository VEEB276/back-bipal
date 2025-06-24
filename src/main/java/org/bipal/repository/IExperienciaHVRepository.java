package org.bipal.repository;

import org.bipal.model.ExperienciaHV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de experiencia hoja de vida
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IExperienciaHVRepository extends JpaRepository<ExperienciaHV, Long> {

}
