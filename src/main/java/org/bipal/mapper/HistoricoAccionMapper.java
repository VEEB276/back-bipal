package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de HistoricoAccion
 *
 * @author Valentina Escobar
 */
@Mapper
public interface HistoricoAccionMapper {
    HistoricoAccionMapper INSTANCE = Mappers.getMapper(HistoricoAccionMapper.class);

}
