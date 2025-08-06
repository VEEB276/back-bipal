package org.bipal.mapper;

import org.bipal.dto.ExperienciaHVDTO;
import org.bipal.model.ExperienciaHV;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    List<ExperienciaHVDTO> toExperienciaHVDTOList(List<ExperienciaHV> experienciaHVList);

    List<ExperienciaHV> toExperienciaHVList(List<ExperienciaHVDTO> experienciaHVDTOList);

}
