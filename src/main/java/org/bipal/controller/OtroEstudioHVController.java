package org.bipal.controller;

import org.bipal.dto.OtroEstudioHVDTO;
import org.bipal.model.OtroEstudioHV;
import org.bipal.service.interfaces.IOtroEstudioHVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for otro estudio hoja de vida
 *
 * @author Valentina Escobar
 */
@RestController
@RequestMapping("/api/otro-estudio-hv")
public class OtroEstudioHVController {

    private IOtroEstudioHVService otroEstudioHVService;

    @PostMapping("/create-otro-estudio")
    public ResponseEntity<List<OtroEstudioHVDTO>> createOtroEstudio(@RequestBody List<OtroEstudioHVDTO> otroEstudioHVDTO) {
        return new ResponseEntity<>(this.otroEstudioHVService.createOtrosEstudios(otroEstudioHVDTO), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-otro-estudio")
    public ResponseEntity<OtroEstudioHVDTO> updateOtroEstudio(@RequestBody OtroEstudioHVDTO otroEstudioHVDTO) {
        OtroEstudioHVDTO otroEstudioDTO = this.otroEstudioHVService.createOtroEstudio(otroEstudioHVDTO);
        return new ResponseEntity<>(otroEstudioDTO, HttpStatus.CREATED);
    }

    @GetMapping("/find-by-id-otro-estudio/{id}")
    public ResponseEntity<OtroEstudioHVDTO> findByIdOtroEstudio(@PathVariable(name = "id") Long id) {

        OtroEstudioHVDTO otroEstudioHVDTO = this.otroEstudioHVService.findByIdOtroEstudio(id);

        if (otroEstudioHVDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(otroEstudioHVDTO, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar-otro-estudio-hv/{id}")
    public ResponseEntity<Boolean> deleteOtroEstudioHV(@PathVariable(value = "id") Long id) {

        OtroEstudioHV otroEstudioHV = this.otroEstudioHVService.deleteOtroEstudioHV(id);

        if (otroEstudioHV != null) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }

        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.EXPECTATION_FAILED);
    }

    //Inyecciones
    @Autowired
    public void setOtroEstudioHVService(IOtroEstudioHVService otroEstudioHVService) {
        this.otroEstudioHVService = otroEstudioHVService;
    }

}


