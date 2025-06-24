package org.bipal.repository;

import org.bipal.model.EnfoqueDiferencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de enfoque diferencial
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IEnfoqueDiferencialRepository extends JpaRepository<EnfoqueDiferencial, Long> {

}
