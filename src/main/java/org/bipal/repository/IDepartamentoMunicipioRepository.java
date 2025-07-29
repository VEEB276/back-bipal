package org.bipal.repository;

import org.bipal.model.DepartamentoMunicipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List

;

/**
 * Repositorio de departamento municipio
 *
 * @author Valentina Escobar
 * @since 1.0
 */
@Repository
public interface IDepartamentoMunicipioRepository extends JpaRepository<DepartamentoMunicipio, Long> {

    /**
     * Query encargada de consultar los departamentos y municipios por cadena de búsqueda
     *
     * @param query cadena de búsqueda
     * @return lista de departamentos y municipios
     */
    @Query(value = """
    SELECT * FROM departamentos_municipios dm 
    WHERE 
      UPPER(
        REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(
          CONCAT(dm.departamento, dm.municipio),
        'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u')
      ) 
      LIKE 
      UPPER(
        REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(
          CONCAT('%', :query, '%'),
        'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u')
      )""", nativeQuery = true)
    List<DepartamentoMunicipio> findAllByQuery(@Param("query") String query);




}
