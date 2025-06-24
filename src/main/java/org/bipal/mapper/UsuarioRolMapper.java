package org.bipal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper de UsuarioRol
 *
 * @author Valentina Escobar
 */
@Mapper
public interface UsuarioRolMapper {
    UsuarioRolMapper INSTANCE = Mappers.getMapper(UsuarioRolMapper.class);

}
