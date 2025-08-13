package org.bipal.repository;

import org.bipal.model.HojaVidaPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de hoja de vida persona
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IHojaVidaPersonaRepository extends JpaRepository<HojaVidaPersona, Long> {

    //Consulta la hoja de vida por el id de la persona
    HojaVidaPersona findByIdPersona(Long idPersona);

}
