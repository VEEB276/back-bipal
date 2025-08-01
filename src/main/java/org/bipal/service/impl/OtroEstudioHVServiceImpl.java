package org.bipal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bipal.dto.EstudioHVDTO;
import org.bipal.dto.OtroEstudioHVDTO;
import org.bipal.mapper.OtroEstudioHVMapper;
import org.bipal.model.OtroEstudioHV;
import org.bipal.repository.IOtroEstudioHVRepository;
import org.bipal.service.interfaces.IOtroEstudioHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OtroEstudioHVServiceImpl implements IOtroEstudioHVService {

    private IOtroEstudioHVRepository otroEstudioHVRepository;

    @Override
    public OtroEstudioHVDTO createOtroEstudio(OtroEstudioHVDTO otroEstudioHVDTO) {

        //OtroEstudioHV DTO hacia OtroEstudioHV
        OtroEstudioHV otroEstudioHV = OtroEstudioHVMapper.INSTANCE.toOtroEstudioHV(otroEstudioHVDTO);

        //Se guarda el otro estudio
        this.otroEstudioHVRepository.save(otroEstudioHV);

        return otroEstudioHVDTO;
    }

    @Override
    public EstudioHVDTO updateEstudio(EstudioHVDTO estudioHVDTO) {
        return null;
    }

    @Override
    public OtroEstudioHVDTO findByIdOtroEstudio(Long id) {

        //Se consulta información del otro estudio por identificador
        OtroEstudioHV otroEstudio = this.otroEstudioHVRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el otro estudio"));

        return OtroEstudioHVMapper.INSTANCE.toOtroEstudioHVDTO(otroEstudio);
    }

    @Autowired
    public void setOtroEstudioHVRepository(IOtroEstudioHVRepository otroEstudioHVRepository) {
        this.otroEstudioHVRepository = otroEstudioHVRepository;
    }

}
