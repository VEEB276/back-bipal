package org.bipal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bipal.dto.*;
import org.bipal.mapper.*;
import org.bipal.model.EstudioHV;
import org.bipal.repository.*;
import org.bipal.service.interfaces.IEstudioHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EstudioHVServiceImpl implements IEstudioHVService {

    private IEstudioHVRepository estudioHVRepository;

    private INivelEducativoRepository nivelEducativoRepository;

    @Override
    public EstudioHVDTO createEstudio(EstudioHVDTO estudioHVDTO) {

        //EstudioHV DTO hacia EstudioHV
        EstudioHV estudioHV = EstudioHVMapper.INSTANCE.toEstudioHV(estudioHVDTO);

        //Se guarda el estudio
        this.estudioHVRepository.save(estudioHV);

        return estudioHVDTO;
    }

    @Override
    public List<EstudioHVDTO> createEstudios(List<EstudioHVDTO> estudiosHVDTO) {

        //EstudioHV DTO List hacia EstudioHV List
        List<EstudioHV> estudiosHV = EstudioHVMapper.INSTANCE.toEstudioHVList(estudiosHVDTO);

        //Se guardan los estudios
        List<EstudioHV> estudioHV = this.estudioHVRepository.saveAll(estudiosHV);

        return EstudioHVMapper.INSTANCE.toEstudioHVDTOList(estudioHV);
    }

    @Override
    public EstudioHVDTO updateEstudio(EstudioHVDTO estudioHVDTO) {
        return null;
    }

    @Override
    public EstudioHVDTO findByIdEstudio(Long id) {

        //Se consulta información del estudio por identificador
        EstudioHV estudio = this.estudioHVRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el estudio"));

        return EstudioHVMapper.INSTANCE.toEstudioHVDTO(estudio);
    }

    @Override
    public List<NivelEducativoDTO> searchAllNivelEducativo() {
        //Se consulta los niveles educativos existentes
        return NivelEducativoMapper.INSTANCE.toNivelEducativoDTOList(
                nivelEducativoRepository.findAll());
    }

    @Override
    public EstudioHV deleteEstudioHV(Long id) {

        //Consulta el estudio por su id y en caso de no obtenerlo lanza excepción
        EstudioHV estudio = estudioHVRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el estudio"));

        //Se elimina el estudio
        this.estudioHVRepository.delete(estudio);

        return estudio;
    }

    @Autowired
    public void setEstudioHVRepository(IEstudioHVRepository estudioHVRepository) {
        this.estudioHVRepository = estudioHVRepository;
    }

    @Autowired
    public void setNivelEducativoRepository(INivelEducativoRepository nivelEducativoRepository) {
        this.nivelEducativoRepository = nivelEducativoRepository;
    }

}
