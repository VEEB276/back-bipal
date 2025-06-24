package org.bipal.repository;

import org.bipal.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de usuario rol
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

}
