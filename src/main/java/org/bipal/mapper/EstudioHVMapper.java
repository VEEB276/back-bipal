package org.bipal.mapper;

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

}
