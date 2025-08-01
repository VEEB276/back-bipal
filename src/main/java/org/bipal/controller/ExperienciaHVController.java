package org.bipal.controller;

import org.bipal.dto.ExperienciaHVDTO;
import org.bipal.dto.TipoExperienciaDTO;
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
    public ResponseEntity<ExperienciaHVDTO> createExperiencia(@RequestBody ExperienciaHVDTO experienciaHVDTO) {
        return new ResponseEntity<>(this.experienciaHVService.createExperiencia(experienciaHVDTO), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-experiencia")
    public ResponseEntity<ExperienciaHVDTO> updateEstudio(@RequestBody ExperienciaHVDTO experienciaHVDTO) {
        ExperienciaHVDTO experienciaDTO = this.experienciaHVService.createExperiencia(experienciaHVDTO);
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

    //Inyecciones
    @Autowired
    public void setExperienciaHVService(IExperienciaHVService experienciaHVService) {
        this.experienciaHVService = experienciaHVService;
    }

}


