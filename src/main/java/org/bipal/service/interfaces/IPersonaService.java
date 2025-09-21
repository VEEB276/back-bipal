package org.bipal.service.interfaces;

import org.bipal.dto.*;

import java.util.List;

/**
 * Interface para el servicio de Persona
 * @version 1.0
 * @author Valentina Escobar
 */
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

    /**
     * Servicio encargado de consultar una persona por su número de documento
     *
     * @param numeroDocumento número de documento de la persona
     * @return información de la persona
     */
    PersonaDTO findByNumeroDocumento(String numeroDocumento);

    /**
     * Actualiza en Supabase (GoTrue) el user_metadata.idPersona del usuario autenticado.
     * Debe ejecutarse dentro del contexto de una request con Authorization válido.
     *
     * @param idPersona identificador de la persona a propagar en user_metadata
     * @return true si se actualiza correctamente
     * @throws IllegalStateException si Supabase retorna error o falla la llamada
     */
    boolean actualizarIdPersonaEnSupabase(Long idPersona);

    /**
     * Busca una persona por número de documento y, si existe, actualiza en Supabase
     * el user_metadata.idPersona del usuario autenticado.
     *
     * @param numeroDocumento número de documento a buscar
     * @return true si la persona existe y se actualiza en Supabase; false si no existe la persona
     * @throws IllegalStateException si Supabase retorna error o falla la llamada
     */
    boolean migrarUsuarioPorNumeroDocumento(String numeroDocumento);
}
