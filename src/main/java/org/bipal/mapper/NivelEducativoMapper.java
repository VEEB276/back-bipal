package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de NivelEducativo
 *
 * @author Valentina Escobar
 */
@Mapper
public interface NivelEducativoMapper {
    NivelEducativoMapper INSTANCE = Mappers.getMapper(NivelEducativoMapper.class);

}
