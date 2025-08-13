package org.bipal.controller;

import org.bipal.dto.*;
import org.bipal.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Personas
 *
 * @author Valentina Escobar
 */
@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    private IPersonaService personaService;

    @PostMapping("/create-persona")
    public ResponseEntity<PersonaDTO> createPersona(@RequestBody PersonaDTO personaDTO) {
        return new ResponseEntity<>(this.personaService.createPersona(personaDTO), HttpStatus.CREATED);
    // Nuevo endpoint /me: obtiene persona a partir del numeroDocumento que llega en header (en un escenario real se extraería del JWT)
    @GetMapping("/find")
    public ResponseEntity<PersonaDTO> getPersona(@RequestParam(name = "numero-documento") String numeroDocumento) {
        PersonaDTO dto = this.personaService.findByNumeroDocumento(numeroDocumento);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/actualizar-persona")
    public ResponseEntity<PersonaDTO> updatePersona(@RequestBody PersonaDTO personaDTO) {
        PersonaDTO persona = this.personaService.updatePersona(personaDTO);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @GetMapping("/find-by-id-persona/{id}")
    public ResponseEntity<PersonaDTO> findByIdPersona(@PathVariable(name = "id") Long id) {

        PersonaDTO persona = this.personaService.findByIdPersona(id);

        if (persona == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @GetMapping("/search-all-tipo-documento")
    public List<TipoDocumentoDTO> searchTipoDocumento() {

        return this.personaService.searchAllTipoDocumento();

    }

    @GetMapping("/search-all-sexo")
    public List<GeneroDTO> searchSexo() {

        return this.personaService.searchAllSexo();

    }

    @GetMapping("/search-all-enfoque-diferencial")
    public List<EnfoqueDiferencialDTO> searchEnfoqueDiferencial() {

        return this.personaService.searchAllEnfoqueDiferencial();

    }

    @GetMapping("/search-all-departamento-municipio")
    public List<DepartamentoMunicipioDTO> searchAllDepartamentoMunicipio(@RequestParam(name = "query", required = false) String query) {

        return this.personaService.searchAllDepartamentoMunicipio(query);

    }

    //Inyecciones
    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }

}
