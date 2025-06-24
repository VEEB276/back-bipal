package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de EnfoqueDiferencial
 *
 * @author Valentina Escobar
 */
@Mapper
public interface EnfoqueDiferencialMapper {
    EnfoqueDiferencialMapper INSTANCE = Mappers.getMapper(EnfoqueDiferencialMapper.class);

}
