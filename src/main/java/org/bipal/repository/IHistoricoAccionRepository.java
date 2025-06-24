package org.bipal.repository;

import org.bipal.model.HistoricoAccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio del histórico de acción
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IHistoricoAccionRepository extends JpaRepository<HistoricoAccion, Long> {

}
