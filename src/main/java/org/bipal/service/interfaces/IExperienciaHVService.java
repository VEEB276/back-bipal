package org.bipal.service.interfaces;

import org.bipal.dto.EstudioHVDTO;
import org.bipal.dto.ExperienciaHVDTO;
import org.bipal.dto.NivelEducativoDTO;
import org.bipal.dto.TipoExperienciaDTO;

import java.util.List;

/**
 * Interface para el servicio de ExperienciaHV
 * @version 1.0
 * @author Valentina Escobar
 */
public interface IExperienciaHVService {

    /**
     * Servicio encargado de crear la experiencia hv
     *
     * @param experienciaHVDTO información de la experiencia hv a crear
     * @return experiencia creada
     */
    ExperienciaHVDTO createExperiencia(ExperienciaHVDTO experienciaHVDTO);

    /**
     * Servicio encargado de actualizar el estudio hoja de vida
     *
     * @param estudioHVDTO información del estudio a actualizar
     * @return estudio actualizado
     */
    EstudioHVDTO updateEstudio(EstudioHVDTO estudioHVDTO);

    /**
     * Metodo encargado de consultar la información de la experiencia por su identificador
     *
     * @param id identificador de la experiencia
     * @return información de la experiencia hv
     */
    ExperienciaHVDTO findByIdExperiencia(Long id);

    /**
     * Servicio encargado de consultar los tipos de experiencia
     *
     * @return lista de tipos de experiencia
     */
    List<TipoExperienciaDTO> searchAllTipoExperiencia();

}
