package org.bipal.mapper;

import org.bipal.dto.EstudioHVDTO;
import org.bipal.dto.PersonaDTO;
import org.bipal.model.EstudioHV;
import org.bipal.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de EstudioHV
 *
 * @author Valentina Escobar
 */
@Mapper
public interface EstudioHVMapper {
    EstudioHVMapper INSTANCE = Mappers.getMapper(EstudioHVMapper.class);

    EstudioHV toEstudioHV(EstudioHVDTO estudioHVDTO);

    EstudioHVDTO toEstudioHVDTO(EstudioHV estudioHV);

}
