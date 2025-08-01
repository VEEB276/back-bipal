package org.bipal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bipal.dto.EstudioHVDTO;
import org.bipal.dto.ExperienciaHVDTO;
import org.bipal.dto.TipoExperienciaDTO;
import org.bipal.mapper.ExperienciaHVMapper;
import org.bipal.mapper.TipoExperienciaMapper;
import org.bipal.model.ExperienciaHV;
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

    @Override
    public ExperienciaHVDTO createExperiencia(ExperienciaHVDTO experienciaHVDTO) {

        //ExperienciaHV DTO hacia ExperienciaHV
        ExperienciaHV experienciaHV = ExperienciaHVMapper.INSTANCE.toExperienciaHV(experienciaHVDTO);

        //Se guarda la experiencia
        this.experienciaHVRepository.save(experienciaHV);

        return experienciaHVDTO;
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

    @Autowired
    public void setExperienciaHVRepository(IExperienciaHVRepository experienciaHVRepository) {
        this.experienciaHVRepository = experienciaHVRepository;
    }

    @Autowired
    public void setTipoExperienciaRepository(ITipoExperienciaRepository tipoExperienciaRepository) {
        this.tipoExperienciaRepository = tipoExperienciaRepository;
    }

}
