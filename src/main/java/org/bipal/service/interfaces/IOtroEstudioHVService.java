package org.bipal.service.interfaces;

import org.bipal.dto.EstudioHVDTO;
import org.bipal.dto.NivelEducativoDTO;
import org.bipal.dto.OtroEstudioHVDTO;

import java.util.List;

/**
 * Interface para el servicio de OtroEstudioHV
 * @version 1.0
 * @author Valentina Escobar
 */
public interface IOtroEstudioHVService {

    /**
     * Servicio encargado de crear el otro estudio hv
     *
     * @param otroEstudioHVDTO información del otro estudio hv a crear
     * @return otro estudio creado
     */
    OtroEstudioHVDTO createOtroEstudio(OtroEstudioHVDTO otroEstudioHVDTO);

    /**
     * Servicio encargado de actualizar el estudio hoja de vida
     *
     * @param estudioHVDTO información del estudio a actualizar
     * @return estudio actualizado
     */
    EstudioHVDTO updateEstudio(EstudioHVDTO estudioHVDTO);

    /**
     * Metodo encargado de consultar la información del otro estudio por su identificador
     *
     * @param id identificador del otro estudio
     * @return información del otro estudio hv
     */
    OtroEstudioHVDTO findByIdOtroEstudio(Long id);

}
