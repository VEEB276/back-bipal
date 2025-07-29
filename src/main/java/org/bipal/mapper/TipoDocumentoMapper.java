package org.bipal.mapper;

import org.bipal.dto.TipoDocumentoDTO;
import org.bipal.model.TipoDocumento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper de TipoDocumento
 *
 * @author Valentina Escobar
 */
@Mapper
public interface TipoDocumentoMapper {
    TipoDocumentoMapper INSTANCE = Mappers.getMapper(TipoDocumentoMapper.class);

    TipoDocumento toTipoDocumento(TipoDocumentoDTO tipoDocumentoDTO);

    TipoDocumentoDTO toTipoDocumentoDTO(TipoDocumento tipoDocumento);

    List<TipoDocumentoDTO> toTipoDocumentoDTOList(List<TipoDocumento> tipoDocumentoList);


}
