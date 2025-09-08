package org.bipal.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bipal.external.supabase.SupabaseFunctionsClient;
import org.bipal.model.HojaVidaPersona;
import org.bipal.model.Persona;
import org.bipal.repository.*;
import org.bipal.service.interfaces.IPersonaEliminarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
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
    private final SupabaseFunctionsClient supabaseFunctionsClient;

    @Override
    @Transactional
    public boolean eliminarPersonaCompleta(Long idPersona) {
        // 0. el usuario puede no tener registro como persona, entonces solo se elimina el usuario.
        if (idPersona != null) {
            Optional<Persona> personaOpt = personaRepository.findById(idPersona);
            if (personaOpt.isEmpty()) {
                return false; // 404 en controller
            }

            // 1. proceder con eliminación la persona
            HojaVidaPersona hojaVida = hojaVidaPersonaRepository.findByIdPersona(idPersona);
            if (hojaVida != null) {
                Long idHojaVida = hojaVida.getId();
                // Eliminar dependientes (orden: hijos -> hoja de vida -> persona)
                experienciaHVRepository.deleteByIdHojaVida(idHojaVida);
                estudioHVRepository.deleteByIdHojaVida(idHojaVida);
                otroEstudioHVRepository.deleteByIdHojaVida(idHojaVida);
                hojaVidaPersonaRepository.deleteById(hojaVida.getId());
            }

            personaRepository.deleteById(idPersona);
        }
        // 2. Llamar primero a edge function para eliminar usuario remoto (usa Authorization propagado por interceptor Feign).
        try {
            supabaseFunctionsClient.deleteUser(Map.of());
        } catch (Exception e) {
            log.warn("Fallo eliminando usuario en Supabase (edge function). Abortando transacción. Causa: {}", e.getMessage());
            throw new IllegalStateException("No se pudo eliminar el usuario remoto en Supabase", e);
        }
        return true;
    }
}
