package org.bipal.repository;

import org.bipal.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de tipo de documento
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

}
