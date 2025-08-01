package org.bipal.mapper;

import org.bipal.dto.OtroEstudioHVDTO;
import org.bipal.model.OtroEstudioHV;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de OtroEstudioHV
 *
 * @author Valentina Escobar
 */
@Mapper
public interface OtroEstudioHVMapper {
    OtroEstudioHVMapper INSTANCE = Mappers.getMapper(OtroEstudioHVMapper.class);

    OtroEstudioHV toOtroEstudioHV(OtroEstudioHVDTO otroEstudioHVDTO);

    OtroEstudioHVDTO toOtroEstudioHVDTO(OtroEstudioHV otroEstudioHV);

}
