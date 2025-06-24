package org.bipal.mapper;

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

}
