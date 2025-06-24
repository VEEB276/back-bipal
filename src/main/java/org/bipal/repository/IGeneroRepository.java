package org.bipal.repository;

import org.bipal.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de género
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Long> {

}
