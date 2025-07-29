package org.bipal.service.interfaces;

import org.bipal.dto.*;

import java.util.List;

public interface IPersonaService {

    /**
     * Servicio encargado de crear la persona
     *
     * @param personaDTO información personal de la persona a crear
     * @return persona creada
     */
    PersonaDTO createPersona(PersonaDTO personaDTO);

    /**
     * Servicio encargado de actualizar la persona
     *
     * @param personaDTO información personal de la persona a actualizar
     * @return persona actualizada
     */
    PersonaDTO updatePersona(PersonaDTO personaDTO);

    /**
     * Metodo encargado de consultar la información de la persona por su identificador
     *
     * @param id identificador de la persona
     * @return información de la persona
     */
    PersonaDTO findByIdPersona(Long id);

    /**
     * Servicio encargado de consultar los tipos de documento
     *
     * @return lista de tipos de documento
     */
    List<TipoDocumentoDTO> searchAllTipoDocumento();

    /**
     * Servicio encargado de consultar los sexos
     *
     * @return lista de sexos
     */
    List<GeneroDTO> searchAllSexo();

    /**
     * Servicio encargado de consultar los enfoques diferenciales
     *
     * @return lista de enfoque diferencial
     */
    List<EnfoqueDiferencialDTO> searchAllEnfoqueDiferencial();

    /**
     * Servicio encargado de consultar los departamentos y municipios
     *
     * @param query cadena de búsqueda
     * @return lista de departamentos y municipios
     */
    List<DepartamentoMunicipioDTO> searchAllDepartamentoMunicipio(String query);

}
