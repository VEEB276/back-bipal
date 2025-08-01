package org.bipal.service.interfaces;

import org.bipal.dto.*;

import java.util.List;

/**
 * Interface para el servicio de EstudioHV
 * @version 1.0
 * @author Valentina Escobar
 */
public interface IEstudioHVService {

    /**
     * Servicio encargado de crear el estudio hv
     *
     * @param estudioHVDTO información del estudio hv a crear
     * @return estudio creado
     */
    EstudioHVDTO createEstudio(EstudioHVDTO estudioHVDTO);

    /**
     * Servicio encargado de actualizar el estudio hoja de vida
     *
     * @param estudioHVDTO información del estudio a actualizar
     * @return estudio actualizado
     */
    EstudioHVDTO updateEstudio(EstudioHVDTO estudioHVDTO);

    /**
     * Metodo encargado de consultar la información del estudio por su identificador
     *
     * @param id identificador del estudio
     * @return información del estudio hv
     */
    EstudioHVDTO findByIdEstudio(Long id);

    /**
     * Servicio encargado de consultar los niveles educativos
     *
     * @return lista de niveles educativos
     */
    List<NivelEducativoDTO> searchAllNivelEducativo();

}
