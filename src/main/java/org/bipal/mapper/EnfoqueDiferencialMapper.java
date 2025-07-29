package org.bipal.mapper;

import org.bipal.dto.EnfoqueDiferencialDTO;
import org.bipal.model.EnfoqueDiferencial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper de EnfoqueDiferencial
 *
 * @author Valentina Escobar
 */
@Mapper
public interface EnfoqueDiferencialMapper {
    EnfoqueDiferencialMapper INSTANCE = Mappers.getMapper(EnfoqueDiferencialMapper.class);

    List<EnfoqueDiferencialDTO> toEnfoqueDiferencialDTOList(List<EnfoqueDiferencial> enfoqueDiferencialList);

}
