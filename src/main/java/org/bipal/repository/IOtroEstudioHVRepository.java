package org.bipal.repository;

import org.bipal.model.OtroEstudioHV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de otro estudio hoja de vida
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IOtroEstudioHVRepository extends JpaRepository<OtroEstudioHV, Long> {

}
