package org.bipal.repository;

import org.bipal.model.EstudioHV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de estudio hoja de vida
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IEstudioHVRepository extends JpaRepository<EstudioHV, Long> {

    //Consulta los estudios por id hoja de vida
    List<EstudioHV> findByIdHojaVida(Long idHojaVida);

}
