package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de HojaVidaPersona
 *
 * @author Valentina Escobar
 */
@Mapper
public interface HojaVidaPersonaMapper {
    HojaVidaPersonaMapper INSTANCE = Mappers.getMapper(HojaVidaPersonaMapper.class);

}
