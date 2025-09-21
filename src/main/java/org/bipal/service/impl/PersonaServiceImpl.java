package org.bipal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bipal.dto.*;
import org.bipal.external.supabase.SupabaseAuthClient;
import org.bipal.mapper.*;
import org.bipal.model.HojaVidaPersona;
import org.bipal.model.Persona;
import org.bipal.repository.*;
import org.bipal.service.interfaces.IPersonaService;
import org.bipal.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PersonaServiceImpl implements IPersonaService {

    private IPersonaRepository personaRepository;

    private ITipoDocumentoRepository tipoDocumentoRepository;

    private IGeneroRepository generoRepository;

    private IEnfoqueDiferencialRepository enfoqueDiferencialRepository;

    private IDepartamentoMunicipioRepository departamentoMunicipioRepository;

    private IHojaVidaPersonaRepository hojaVidaPersonaRepository;

    private SupabaseAuthClient supabaseAuthClient;

    @Transactional
    @Override
    public PersonaDTO createPersona(PersonaDTO personaDTO) {

        //Persona DTO hacia Persona
        Persona toPersona = PersonaMapper.INSTANCE.toPersona(personaDTO);

        //Se guarda la persona
        Persona persona = this.personaRepository.save(toPersona);

        HojaVidaPersona hojaVidaPersona = new HojaVidaPersona();
        hojaVidaPersona.setIdPersona(persona.getId());
        hojaVidaPersona.setFechaCreacion(new Date());
        hojaVidaPersona.setFechaModificacion(new Date());

        //Se guarda la información de hoja de vida persona
        HojaVidaPersona hojaVida = this.hojaVidaPersonaRepository.save(hojaVidaPersona);

        //Se setean ids en el DTO
        personaDTO.setId(persona.getId());
        personaDTO.setIdHojaVida(hojaVida.getId());

        // Actualiza user_metadata.idPersona en Supabase (mismo comportamiento que create)
        this.actualizarIdPersonaEnSupabase(persona.getId());

        return personaDTO;
    }

    @Override
    public PersonaDTO updatePersona(PersonaDTO personaDTO) {
        var persona = this.personaRepository.findById(personaDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la persona para este numero de identificación"));
        //no se puede cambiar el número de documento.
        personaDTO.setNumeroDocumento(persona.getNumeroDocumento());
        //se actualiza la información de la persona
        this.personaRepository.save(PersonaMapper.INSTANCE.toPersona(personaDTO));
        return personaDTO;
    }

    @Override
    public PersonaDTO findByIdPersona(Long id) {

        //Se consulta información de la persona por identificador
        Persona persona = this.personaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona"));

        return PersonaMapper.INSTANCE.toPersonaDTO(persona);
    }

    @Override
    public List<TipoDocumentoDTO> searchAllTipoDocumento() {
        //Se consulta los tipos de documento existentes
        return TipoDocumentoMapper.INSTANCE.toTipoDocumentoDTOList(
                tipoDocumentoRepository.findAll());
    }

    @Override
    public List<GeneroDTO> searchAllSexo() {
        //Se consulta los sexos existentes
        return GeneroMapper.INSTANCE.toGeneroDTOList(
                generoRepository.findAll());
    }

    @Override
    public List<EnfoqueDiferencialDTO> searchAllEnfoqueDiferencial() {
        //Se consulta los enfoques diferenciales existentes
        return EnfoqueDiferencialMapper.INSTANCE.toEnfoqueDiferencialDTOList(
                enfoqueDiferencialRepository.findAll());
    }

    @Override
    public List<DepartamentoMunicipioDTO> searchAllDepartamentoMunicipio(String query) {

        //Se cambian los espacios de la cadena de búsqueda por porcentajes
        String queryConPorcentajes = ITools.formatearQueryConPorcentajes(query);

        //Se consulta departamento y municipio
        return DepartamentoMunicipioMapper.INSTANCE.toDepartamentoMunicipioDTOList(
                departamentoMunicipioRepository.findAllByQuery(queryConPorcentajes));
    }

    @Override
    public PersonaDTO findByNumeroDocumento(String numeroDocumento) {
        return personaRepository.findByNumeroDocumento(numeroDocumento)
                .map(PersonaMapper.INSTANCE::toPersonaDTO)
                .orElse(null);
    }

    @Override
    public boolean actualizarIdPersonaEnSupabase(Long idPersona) throws IllegalStateException {
        try {
            Map<String, Object> userUpdate = Map.of("data", Map.of("idPersona", idPersona));
            Map<String, Object> response = supabaseAuthClient.updateUser(userUpdate);
            if (response != null && response.containsKey("error") && response.get("error") != null) {
                log.error("Error en respuesta de Supabase: {}", response.get("error"));
                throw new IllegalStateException("Error de Supabase: " + response.get("error"));
            }
            log.info("Metadata actualizada en Supabase con idPersona: {}", idPersona);
            return true;
        } catch (Exception e) {
            log.error("Error al actualizar metadata de usuario en Supabase: {}", e.getMessage());
            throw new IllegalStateException("No se pudo actualizar la información del usuario. Intente de nuevo o contacte al administrador.", e);
        }
    }

    @Override
    public boolean migrarUsuarioPorNumeroDocumento(String numeroDocumento) throws IllegalStateException {
        PersonaDTO persona = this.findByNumeroDocumento(numeroDocumento);
        if (persona == null) {
            return false; // no existe persona para el documento
        }
        return this.actualizarIdPersonaEnSupabase(persona.getId());
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Autowired
    public void setTipoDocumentoRepository(ITipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Autowired
    public void setGeneroRepository(IGeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Autowired
    public void setEnfoqueDiferencialRepository(IEnfoqueDiferencialRepository enfoqueDiferencialRepository) {
        this.enfoqueDiferencialRepository = enfoqueDiferencialRepository;
    }

    @Autowired
    public void setDepartamentoMunicipioRepository(IDepartamentoMunicipioRepository departamentoMunicipioRepository) {
        this.departamentoMunicipioRepository = departamentoMunicipioRepository;
    }

    @Autowired
    public void setHojaVidaPersonaRepository(IHojaVidaPersonaRepository hojaVidaPersonaRepository) {
        this.hojaVidaPersonaRepository = hojaVidaPersonaRepository;
    }

    @Autowired
    public void setSupabaseAuthClient(SupabaseAuthClient supabaseAuthClient) {
        this.supabaseAuthClient = supabaseAuthClient;
    }

}
