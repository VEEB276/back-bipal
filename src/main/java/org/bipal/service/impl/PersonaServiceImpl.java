package org.bipal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bipal.dto.*;
import org.bipal.mapper.*;
import org.bipal.model.HojaVidaPersona;
import org.bipal.model.Persona;
import org.bipal.repository.*;
import org.bipal.service.interfaces.IPersonaService;
import org.bipal.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PersonaServiceImpl implements IPersonaService {

    private IPersonaRepository personaRepository;

    private ITipoDocumentoRepository tipoDocumentoRepository;

    private IGeneroRepository generoRepository;

    private IEnfoqueDiferencialRepository enfoqueDiferencialRepository;

    private IDepartamentoMunicipioRepository departamentoMunicipioRepository;

    private IHojaVidaPersonaRepository hojaVidaPersonaRepository;

    @Transactional
    @Override
    public PersonaDTO createPersona(PersonaDTO personaDTO) {

        //Persona DTO hacia Persona
        Persona toPersona = PersonaMapper.INSTANCE.toPersona(personaDTO);

        //Se guarda la persona
        Persona persona = this.personaRepository.save(toPersona);
        this.personaRepository.flush();

        HojaVidaPersona hojaVidaPersona = new HojaVidaPersona();
        hojaVidaPersona.setIdPersona(persona.getId());
        hojaVidaPersona.setFechaCreacion(new Date());
        hojaVidaPersona.setFechaModificacion(new Date());

        //Se guarda la información de hoja de vida persona
        HojaVidaPersona hojaVida = this.hojaVidaPersonaRepository.save(hojaVidaPersona);
        this.hojaVidaPersonaRepository.flush();

        //Se setea información del identificador de la hoja de vida persona
        personaDTO.setIdHojaVida(hojaVida.getId());

        return personaDTO;
    }

    @Override
    public PersonaDTO updatePersona(PersonaDTO personaDTO) {
        return null;
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

}
