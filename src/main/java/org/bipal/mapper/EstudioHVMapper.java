package org.bipal.mapper;

import org.bipal.dto.EstudioHVDTO;
import org.bipal.dto.PersonaDTO;
import org.bipal.model.EstudioHV;
import org.bipal.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    List<EstudioHVDTO> toEstudioHVDTOList(List<EstudioHV> estudioHVList);

    List<EstudioHV> toEstudioHVList(List<EstudioHVDTO> estudioHVDTOList);

}
