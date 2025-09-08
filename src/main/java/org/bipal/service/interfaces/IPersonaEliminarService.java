package org.bipal.service.interfaces;

/**
 * Servicio dedicado a eliminación completa (hard delete) de una persona y su información asociada.
 */
public interface IPersonaEliminarService {

    /**
     * Elimina definitivamente la persona y todos sus datos asociados (hoja de vida, estudios, experiencias, otros estudios).
     * Primero invoca la función edge de Supabase para eliminar el usuario remoto reutilizando el Authorization del request (propagado por interceptor Feign).
     * @param idPersona identificador persona
     * @return true si eliminó, false si no existe
     */
    boolean eliminarPersonaCompleta(Long idPersona);
}
