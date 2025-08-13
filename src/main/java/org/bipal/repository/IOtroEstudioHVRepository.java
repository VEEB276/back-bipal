package org.bipal.repository;

import org.bipal.model.OtroEstudioHV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de otro estudio hoja de vida
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IOtroEstudioHVRepository extends JpaRepository<OtroEstudioHV, Long> {

    //Consulta otros estudios por id hoja de vida
    List<OtroEstudioHV> findByIdHojaVida(Long idHojaVida);

}
