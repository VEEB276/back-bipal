package org.bipal.mapper;

import org.bipal.dto.TipoExperienciaDTO;
import org.bipal.model.TipoExperiencia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper de TipoExperiencia
 *
 * @author Valentina Escobar
 */
@Mapper
public interface TipoExperienciaMapper {
    TipoExperienciaMapper INSTANCE = Mappers.getMapper(TipoExperienciaMapper.class);

    List<TipoExperienciaDTO> toTipoExperienciaDTOList(List<TipoExperiencia> tipoExperienciaList);
}
