package org.bipal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bipal.dto.EstudioHVDTO;
import org.bipal.dto.OtroEstudioHVDTO;
import org.bipal.mapper.OtroEstudioHVMapper;
import org.bipal.model.OtroEstudioHV;
import org.bipal.model.HojaVidaPersona;
import org.bipal.repository.IOtroEstudioHVRepository;
import org.bipal.repository.IHojaVidaPersonaRepository;
import org.bipal.service.interfaces.IOtroEstudioHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OtroEstudioHVServiceImpl implements IOtroEstudioHVService {

    private IOtroEstudioHVRepository otroEstudioHVRepository;
    private IHojaVidaPersonaRepository hojaVidaPersonaRepository;

    @Override
    public OtroEstudioHVDTO createOtroEstudio(OtroEstudioHVDTO otroEstudioHVDTO) {

        //OtroEstudioHV DTO hacia OtroEstudioHV
        OtroEstudioHV otroEstudioHV = OtroEstudioHVMapper.INSTANCE.toOtroEstudioHV(otroEstudioHVDTO);

        //Se guarda el otro estudio
        this.otroEstudioHVRepository.save(otroEstudioHV);

        return otroEstudioHVDTO;
    }

    @Override
    public List<OtroEstudioHVDTO> createOtrosEstudios(List<OtroEstudioHVDTO> otrosEstudiosHVDTO) {

        //OtroEstudioHV DTO List hacia OtroEstudioHV List
        List<OtroEstudioHV> otroEstudioHV = OtroEstudioHVMapper.INSTANCE.toOtroEstudioHVList(otrosEstudiosHVDTO);

        //Se guardan otros estudios
        List<OtroEstudioHV> otrosEstudiosHV = this.otroEstudioHVRepository.saveAll(otroEstudioHV);

        return OtroEstudioHVMapper.INSTANCE.toOtroEstudioHVDTOList(otrosEstudiosHV);
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

    @Override
    public OtroEstudioHV deleteOtroEstudioHV(Long id) {

        //Consulta el otro estudio por su id y en caso de no obtenerlo lanza excepción
        OtroEstudioHV estudio = otroEstudioHVRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el otro estudio"));

        //Se elimina el estudio
        this.otroEstudioHVRepository.delete(estudio);

        return estudio;
    }

    @Override
    public List<OtroEstudioHVDTO> searchOtrosEstudiosByIdPersona(Long idPersona) {
        HojaVidaPersona hojaVidaPersona = hojaVidaPersonaRepository.findByIdPersona(idPersona);
        if (hojaVidaPersona == null) {
            return List.of();
        }
        List<OtroEstudioHV> otros = otroEstudioHVRepository.findByIdHojaVida(hojaVidaPersona.getId());
        return OtroEstudioHVMapper.INSTANCE.toOtroEstudioHVDTOList(otros);
    }

    @Autowired
    public void setOtroEstudioHVRepository(IOtroEstudioHVRepository otroEstudioHVRepository) {
        this.otroEstudioHVRepository = otroEstudioHVRepository;
    }

    @Autowired
    public void setHojaVidaPersonaRepository(IHojaVidaPersonaRepository hojaVidaPersonaRepository) {
        this.hojaVidaPersonaRepository = hojaVidaPersonaRepository;
    }

}
