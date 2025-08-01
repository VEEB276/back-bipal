package org.bipal.mapper;

import org.bipal.dto.NivelEducativoDTO;
import org.bipal.dto.TipoDocumentoDTO;
import org.bipal.model.NivelEducativo;
import org.bipal.model.TipoDocumento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper de NivelEducativo
 *
 * @author Valentina Escobar
 */
@Mapper
public interface NivelEducativoMapper {
    NivelEducativoMapper INSTANCE = Mappers.getMapper(NivelEducativoMapper.class);

    List<NivelEducativoDTO> toNivelEducativoDTOList(List<NivelEducativo> nivelEducativoList);

}
