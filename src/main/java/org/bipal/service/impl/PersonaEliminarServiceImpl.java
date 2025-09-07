package org.bipal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bipal.model.HojaVidaPersona;
import org.bipal.model.Persona;
import org.bipal.repository.*;
import org.bipal.service.interfaces.IPersonaEliminarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementación de eliminación completa (hard delete) de persona.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PersonaEliminarServiceImpl implements IPersonaEliminarService {

    private final IPersonaRepository personaRepository;
    private final IHojaVidaPersonaRepository hojaVidaPersonaRepository;
    private final IEstudioHVRepository estudioHVRepository;
    private final IExperienciaHVRepository experienciaHVRepository;
    private final IOtroEstudioHVRepository otroEstudioHVRepository;

    @Override
    @Transactional
    public boolean eliminarPersonaCompleta(Long idPersona) {
        Optional<Persona> personaOpt = personaRepository.findById(idPersona);
        if (personaOpt.isEmpty()) {
            return false;
        }

        // Obtiene hoja de vida (1:1 asumido)
        HojaVidaPersona hojaVida = hojaVidaPersonaRepository.findByIdPersona(idPersona);
        if (hojaVida != null) {
            Long idHojaVida = hojaVida.getId();
            // Eliminar dependientes (orden: hijos -> hoja de vida -> persona)
            experienciaHVRepository.deleteByIdHojaVida(idHojaVida);
            estudioHVRepository.deleteByIdHojaVida(idHojaVida);
            otroEstudioHVRepository.deleteByIdHojaVida(idHojaVida);
            hojaVidaPersonaRepository.deleteById(hojaVida.getId());
        }

        // TODO: Implementar auditoria de accion de eliminar (requerido futuro legal?)

        personaRepository.deleteById(idPersona);
        // flush se delega al commit de la transacción
        return true;
    }
}

