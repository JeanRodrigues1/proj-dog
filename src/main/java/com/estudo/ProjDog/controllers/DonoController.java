package com.estudo.ProjDog.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.ProjDog.entity.Dono;
import com.estudo.ProjDog.services.DonoService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/dono")
public class DonoController {
    
    @Autowired
    private DonoService donoService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Dono>> getDonos() {
        List<Dono> donos = donoService.getAllDonos();
        if (donos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(donos, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dono> getDonoById(@PathVariable Long id) {
        Optional<Dono> dono = donoService.getDonoById(id);
        if (dono.isPresent()) {
            return new ResponseEntity<>(dono.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Dono>> getDonosByName(@PathVariable String nome) {
        List<Dono> donos = donoService.getDonosByName(nome);
        if (donos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(donos, HttpStatus.OK);
        }
    }

    @GetMapping("/paciente/{id_paciente}")
    public ResponseEntity<Dono> getDonoByPacienteId(@PathVariable Long id_paciente) {
        try {
            Dono dono = donoService.getDonoByPacienteId(id_paciente);
            return ResponseEntity.ok(dono);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<Dono> saveDono(@RequestBody Dono dono) {
        Dono donoObj = donoService.saveDono(dono);
        return new ResponseEntity<>(donoObj, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Dono> updateDonoById(@PathVariable Long id, @RequestBody Dono newDonoData) {
        Dono donoUpdate = donoService.updateDonoById(id, newDonoData);
        if (donoUpdate != null) { 
            return new ResponseEntity<>(donoUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDonoById(@PathVariable Long id) {
        donoService.deleteDonoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    

}
