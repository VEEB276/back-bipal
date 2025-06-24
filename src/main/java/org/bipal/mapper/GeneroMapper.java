package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de Genero
 *
 * @author Valentina Escobar
 */
@Mapper
public interface GeneroMapper {
    GeneroMapper INSTANCE = Mappers.getMapper(GeneroMapper.class);

}
