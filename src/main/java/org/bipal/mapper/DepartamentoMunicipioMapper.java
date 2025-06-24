package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de DepartamentoMunicipio
 *
 * @author Valentina Escobar
 */
@Mapper
public interface DepartamentoMunicipioMapper {
    DepartamentoMunicipioMapper INSTANCE = Mappers.getMapper(DepartamentoMunicipioMapper.class);

}
