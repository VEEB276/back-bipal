package org.bipal.controller;

import org.bipal.dto.*;
import org.bipal.model.EstudioHV;
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

    @PostMapping("/create-estudios")
    public ResponseEntity<List<EstudioHVDTO>> createEstudios(@RequestBody List<EstudioHVDTO> estudios) {
        List<EstudioHVDTO> creados = this.estudioHVService.createEstudios(estudios);
        return new ResponseEntity<>(creados, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-estudio")
    public ResponseEntity<List<EstudioHVDTO>> updateEstudio(@RequestBody List<EstudioHVDTO> estudios) {
        List<EstudioHVDTO> estudioDTO = this.estudioHVService.createEstudios(estudios);
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

    @DeleteMapping("/eliminar-estudio-hv/{id}")
    public ResponseEntity<Boolean> deleteEstudioHV(@PathVariable(value = "id") Long id) {

        EstudioHV estudioHV = this.estudioHVService.deleteEstudioHV(id);

        if (estudioHV != null) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }

        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/search-estudios-by-id-persona/{idPersona}")
    public ResponseEntity<List<EstudioHVDTO>> searchEstudiosByIdPersona(@PathVariable("idPersona") Long idPersona) {
        List<EstudioHVDTO> estudios = this.estudioHVService.searchEstudiosByIdPersona(idPersona);
        if (estudios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(estudios, HttpStatus.OK);
    }

    //Inyecciones
    @Autowired
    public void setEstudioHVService(IEstudioHVService estudioHVService) {
        this.estudioHVService = estudioHVService;
    }

}
