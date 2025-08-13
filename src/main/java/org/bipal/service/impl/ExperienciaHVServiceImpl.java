package org.bipal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bipal.dto.EstudioHVDTO;
import org.bipal.dto.ExperienciaHVDTO;
import org.bipal.dto.TipoExperienciaDTO;
import org.bipal.mapper.ExperienciaHVMapper;
import org.bipal.mapper.TipoExperienciaMapper;
import org.bipal.model.ExperienciaHV;
import org.bipal.model.HojaVidaPersona;
import org.bipal.repository.*;
import org.bipal.service.interfaces.IExperienciaHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExperienciaHVServiceImpl implements IExperienciaHVService {

    private IExperienciaHVRepository experienciaHVRepository;

    private ITipoExperienciaRepository tipoExperienciaRepository;

    private IHojaVidaPersonaRepository hojaVidaPersonaRepository;

    @Override
    public ExperienciaHVDTO createExperiencia(ExperienciaHVDTO experienciaHVDTO) {

        //ExperienciaHV DTO hacia ExperienciaHV
        ExperienciaHV experienciaHV = ExperienciaHVMapper.INSTANCE.toExperienciaHV(experienciaHVDTO);

        //Se guarda la experiencia
        this.experienciaHVRepository.save(experienciaHV);

        return experienciaHVDTO;
    }

    @Override
    public List<ExperienciaHVDTO> createExperiencias(List<ExperienciaHVDTO> experienciasHVDTO) {
        //ExperienciaHV DTO List hacia ExperienciaHV List
        List<ExperienciaHV> experienciaHV = ExperienciaHVMapper.INSTANCE.toExperienciaHVList(experienciasHVDTO);

        //Se guardan las experiencias
        List<ExperienciaHV> experienciasHV = this.experienciaHVRepository.saveAll(experienciaHV);

        return ExperienciaHVMapper.INSTANCE.toExperienciaHVDTOList(experienciasHV);
    }

    @Override
    public EstudioHVDTO updateEstudio(EstudioHVDTO estudioHVDTO) {
        return null;
    }

    @Override
    public ExperienciaHVDTO findByIdExperiencia(Long id) {

        //Se consulta información de la experiencia por identificador
        ExperienciaHV experiencia = this.experienciaHVRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la experiencia"));

        return ExperienciaHVMapper.INSTANCE.toExperienciaHVDTO(experiencia);
    }

    @Override
    public List<TipoExperienciaDTO> searchAllTipoExperiencia() {
        //Se consulta los tipos de experiencia existentes
        return TipoExperienciaMapper.INSTANCE.toTipoExperienciaDTOList(
                tipoExperienciaRepository.findAll());
    }

    @Override
    public ExperienciaHV deleteExperienciaHV(Long id) {

        //Consulta la experiencia por su id y en caso de no obtenerla lanza excepción
        ExperienciaHV experiencia = experienciaHVRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la experiencia"));

        //Se elimina la experiencia
        this.experienciaHVRepository.delete(experiencia);

        return experiencia;
    }

    @Override
    public List<ExperienciaHVDTO> searchExperienciasByIdPersona(Long idPersona) {
        //Cruce persona -> hoja vida -> experiencias
        HojaVidaPersona hojaVidaPersona = hojaVidaPersonaRepository.findByIdPersona(idPersona);
        if (hojaVidaPersona == null) {
            return List.of();
        }
        List<ExperienciaHV> experiencias = experienciaHVRepository.findByIdHojaVida(hojaVidaPersona.getId());
        return ExperienciaHVMapper.INSTANCE.toExperienciaHVDTOList(experiencias);
    }

    @Autowired
    public void setExperienciaHVRepository(IExperienciaHVRepository experienciaHVRepository) {
        this.experienciaHVRepository = experienciaHVRepository;
    }

    @Autowired
    public void setTipoExperienciaRepository(ITipoExperienciaRepository tipoExperienciaRepository) {
        this.tipoExperienciaRepository = tipoExperienciaRepository;
    }

    @Autowired
    public void setHojaVidaPersonaRepository(IHojaVidaPersonaRepository hojaVidaPersonaRepository) {
        this.hojaVidaPersonaRepository = hojaVidaPersonaRepository;
    }

}
