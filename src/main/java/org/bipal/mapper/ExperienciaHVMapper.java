package org.bipal.mapper;

import org.bipal.dto.ExperienciaHVDTO;
import org.bipal.model.ExperienciaHV;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de ExperienciaHV
 *
 * @author Valentina Escobar
 */
@Mapper
public interface ExperienciaHVMapper {
    ExperienciaHVMapper INSTANCE = Mappers.getMapper(ExperienciaHVMapper.class);

    ExperienciaHV toExperienciaHV(ExperienciaHVDTO experienciaHVDTO);

    ExperienciaHVDTO toExperienciaHVDTO(ExperienciaHV experienciaHV);

}
