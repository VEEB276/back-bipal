package org.bipal.repository;

import org.bipal.model.ExperienciaHV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de experiencia hoja de vida
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IExperienciaHVRepository extends JpaRepository<ExperienciaHV, Long> {

    //Consulta las experiencias por id hoja de vida
    List<ExperienciaHV> findByIdHojaVida(Long idHojaVida);

    //Elimina en bloque por hoja de vida
    void deleteByIdHojaVida(Long idHojaVida);
}
