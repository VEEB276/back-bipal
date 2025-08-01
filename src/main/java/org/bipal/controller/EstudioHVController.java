package org.bipal.controller;

import org.bipal.dto.*;
import org.bipal.service.interfaces.IEstudioHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for estudio hoja de vida
 *
 * @author Valentina Escobar
 */
@RestController
@RequestMapping("/api/estudio-hv")
public class EstudioHVController {

    private IEstudioHVService estudioHVService;

    @PostMapping("/create-estudio")
    public ResponseEntity<EstudioHVDTO> createEstudio(@RequestBody EstudioHVDTO estudioHVDTO) {
        return new ResponseEntity<>(this.estudioHVService.createEstudio(estudioHVDTO), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-estudio")
    public ResponseEntity<EstudioHVDTO> updateEstudio(@RequestBody EstudioHVDTO estudioHVDTO) {
        EstudioHVDTO estudioDTO = this.estudioHVService.createEstudio(estudioHVDTO);
        return new ResponseEntity<>(estudioDTO, HttpStatus.CREATED);
    }

    @GetMapping("/find-by-id-estudio/{id}")
    public ResponseEntity<EstudioHVDTO> findByIdEstudio(@PathVariable(name = "id") Long id) {

        EstudioHVDTO estudioHVDTO = this.estudioHVService.findByIdEstudio(id);

        if (estudioHVDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(estudioHVDTO, HttpStatus.OK);
    }

    @GetMapping("/search-all-nivel-educativo")
    public List<NivelEducativoDTO> searchNivelEducativo() {

        return this.estudioHVService.searchAllNivelEducativo();

    }

    //Inyecciones
    @Autowired
    public void setEstudioHVService(IEstudioHVService estudioHVService) {
        this.estudioHVService = estudioHVService;
    }

}


