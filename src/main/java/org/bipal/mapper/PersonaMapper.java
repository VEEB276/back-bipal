package org.bipal.mapper;

import org.bipal.dto.PersonaDTO;
import org.bipal.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de Persona
 *
 * @author Valentina Escobar
 */
@Mapper
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    @Mapping(target = "ciudadResidencia", source = "ciudadResidencia")
    Persona toPersona(PersonaDTO personaDTO);

    PersonaDTO toPersonaDTO(Persona persona);

}
