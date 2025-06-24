package org.bipal.repository;

import org.bipal.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de personas
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {

}
