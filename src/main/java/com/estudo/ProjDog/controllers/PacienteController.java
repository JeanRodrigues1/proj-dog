package com.estudo.ProjDog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.ProjDog.entity.Dono;
import com.estudo.ProjDog.entity.Paciente;
import com.estudo.ProjDog.services.PacienteService;
import com.estudo.ProjDog.repository.DonoInterface;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired DonoInterface donoInterface;

    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> getPacientes(){
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        if (pacientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.getPacienteById(id);
        if (paciente.isPresent()) {
            return new ResponseEntity<>(paciente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{nome}")
    public ResponseEntity<List<Paciente>> getPacienteByName(@PathVariable String nome) {
        List<Paciente> pacientes = pacienteService.getPacientesByName(nome);
        if (pacientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        }
    }

    @GetMapping("/dono/{id_dono}")
    public ResponseEntity<List<Paciente>> getPacientesByDonoId(@PathVariable Long id_dono) {
        try {
            List<Paciente> pacientes = pacienteService.getPacientesByDonoId(id_dono);
            return ResponseEntity.ok(pacientes);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente) {
        try {
            Long donoId = paciente.getDono().getId();

            Optional<Dono> donoOptional = donoInterface.findById(donoId);
            
            if (donoOptional.isPresent()) {
                Paciente pacienteObj = pacienteService.savePaciente(paciente);
                return new ResponseEntity<>(pacienteObj, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @PutMapping("/update/{id}")
    public ResponseEntity<Paciente> updatePacienteById(@PathVariable Long id, @RequestBody Paciente newPacienteData) {
        Paciente pacienteUpdate = pacienteService.updatePacienteById(id, newPacienteData);
        if (pacienteUpdate != null) { 
            return new ResponseEntity<>(pacienteUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePacienteById(@PathVariable Long id) {
        pacienteService.deleteByPacienteId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
