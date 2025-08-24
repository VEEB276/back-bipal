package org.bipal.mapper;

import org.bipal.dto.DepartamentoMunicipioDTO;
import org.bipal.model.DepartamentoMunicipio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper de DepartamentoMunicipio
 *
 * @author Valentina Escobar
 */
@Mapper
public interface DepartamentoMunicipioMapper {
    DepartamentoMunicipioMapper INSTANCE = Mappers.getMapper(DepartamentoMunicipioMapper.class);

    @Mapping(target = "nombreCompleto", expression = "java(departamentoMunicipio.getDepartamento() + \", \" + departamentoMunicipio.getMunicipio())")
    DepartamentoMunicipioDTO toDepartamentoMunicipioDTO(DepartamentoMunicipio departamentoMunicipio);

    List<DepartamentoMunicipioDTO> toDepartamentoMunicipioDTOList(List<DepartamentoMunicipio> departamentoMunicipioList);

}
