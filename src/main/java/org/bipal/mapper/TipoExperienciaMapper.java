package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de TipoExperiencia
 *
 * @author Valentina Escobar
 */
@Mapper
public interface TipoExperienciaMapper {
    TipoExperienciaMapper INSTANCE = Mappers.getMapper(TipoExperienciaMapper.class);

}
