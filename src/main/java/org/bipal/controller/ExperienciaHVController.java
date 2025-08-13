package org.bipal.controller;

import org.bipal.dto.ExperienciaHVDTO;
import org.bipal.dto.TipoExperienciaDTO;
import org.bipal.model.ExperienciaHV;
import org.bipal.service.interfaces.IExperienciaHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for experiencia hoja de vida
 *
 * @author Valentina Escobar
 */
@RestController
@RequestMapping("/api/experiencia-hv")
public class ExperienciaHVController {

    private IExperienciaHVService experienciaHVService;

    @PostMapping("/create-experiencia")
    public ResponseEntity<List<ExperienciaHVDTO>> createExperiencia(@RequestBody List<ExperienciaHVDTO> experienciaHVDTO) {
        return new ResponseEntity<>(this.experienciaHVService.createExperiencias(experienciaHVDTO), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-experiencia")
    public ResponseEntity<List<ExperienciaHVDTO>> updateEstudio(@RequestBody List<ExperienciaHVDTO> experienciaHVDTO) {
        List<ExperienciaHVDTO> experienciaDTO = this.experienciaHVService.createExperiencias(experienciaHVDTO);
        return new ResponseEntity<>(experienciaDTO, HttpStatus.CREATED);
    }

    @GetMapping("/find-by-id-experiencia/{id}")
    public ResponseEntity<ExperienciaHVDTO> findByIdEstudio(@PathVariable(name = "id") Long id) {

        ExperienciaHVDTO experienciaHVDTO = this.experienciaHVService.findByIdExperiencia(id);

        if (experienciaHVDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(experienciaHVDTO, HttpStatus.OK);
    }

    @GetMapping("/search-all-tipo-experiencia")
    public List<TipoExperienciaDTO> searchTipoExperiencia() {

        return this.experienciaHVService.searchAllTipoExperiencia();

    }

    @GetMapping("/search-experiencias-by-id-persona/{idPersona}")
    public ResponseEntity<List<ExperienciaHVDTO>> searchExperienciasByIdPersona(@PathVariable("idPersona") Long idPersona) {
        List<ExperienciaHVDTO> experiencias = this.experienciaHVService.searchExperienciasByIdPersona(idPersona);
        if (experiencias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(experiencias, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar-experiencia-hv/{id}")
    public ResponseEntity<Boolean> deleteExperienciaHV(@PathVariable(value = "id") Long id) {

        ExperienciaHV experienciaHV = this.experienciaHVService.deleteExperienciaHV(id);

        if (experienciaHV != null) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }

        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.EXPECTATION_FAILED);
    }

    //Inyecciones
    @Autowired
    public void setExperienciaHVService(IExperienciaHVService experienciaHVService) {
        this.experienciaHVService = experienciaHVService;
    }

}
