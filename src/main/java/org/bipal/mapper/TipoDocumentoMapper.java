package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de TipoDocumento
 *
 * @author Valentina Escobar
 */
@Mapper
public interface TipoDocumentoMapper {
    TipoDocumentoMapper INSTANCE = Mappers.getMapper(TipoDocumentoMapper.class);

}
