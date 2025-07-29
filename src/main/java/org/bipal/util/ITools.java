package org.bipal.util;

import java.util.function.Consumer;

/**
 * Interface para funciones comunes del proyecto
 *
 * @author Valentina Escobar
 *
 */

public interface ITools {

    /**
     * Metodo encargado de asigna el valor al setter solo si el valor no es null.
     *
     * @param sourceValue  el valor que se desea copiar (puede ser null)
     * @param setter       el metodo setter que recibe el valor si no es null
     */
    static <T> void copyIfNotNull(T sourceValue, Consumer<T> setter) {
        if (sourceValue != null) {
            setter.accept(sourceValue);
        }
    }

    /**
     * Metodo encargado de cambiar los espacios por porcentajes en la cadena de búsqueda
     *
     * @param query cadena de búsqueda
     * @return query con porcentajes
     */
    static String formatearQueryConPorcentajes(String query) {
        if (query != null) {
            query = query.trim().replaceAll("\\s+", "%");
        }
        return query;
    }

}
