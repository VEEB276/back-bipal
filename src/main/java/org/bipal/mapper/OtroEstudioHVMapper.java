package org.bipal.mapper;

import org.bipal.dto.ExperienciaHVDTO;
import org.bipal.dto.OtroEstudioHVDTO;
import org.bipal.model.ExperienciaHV;
import org.bipal.model.OtroEstudioHV;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    List<OtroEstudioHVDTO> toOtroEstudioHVDTOList(List<OtroEstudioHV> otroEstudioHVList);

    List<OtroEstudioHV> toOtroEstudioHVList(List<OtroEstudioHVDTO> otroEstudioHVDTOList);

}
