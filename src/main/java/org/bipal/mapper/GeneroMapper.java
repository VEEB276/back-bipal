package org.bipal.mapper;

import org.bipal.dto.GeneroDTO;
import org.bipal.dto.TipoDocumentoDTO;
import org.bipal.model.Genero;
import org.bipal.model.TipoDocumento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper de Genero
 *
 * @author Valentina Escobar
 */
@Mapper
public interface GeneroMapper {
    GeneroMapper INSTANCE = Mappers.getMapper(GeneroMapper.class);

    List<GeneroDTO> toGeneroDTOList(List<Genero> generoList);

}
